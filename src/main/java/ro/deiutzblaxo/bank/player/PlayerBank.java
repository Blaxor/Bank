package ro.deiutzblaxo.bank.player;

import org.bukkit.entity.Player;
import ro.deiutzblaxo.bank.Main;
import ro.deiutzblaxo.bank.utils.datastructure.ListaLiniara;
import ro.deiutzblaxo.bank.utils.Utils;
import ro.deiutzblaxo.bank.config.MESSAGE;
import ro.deiutzblaxo.bank.config.MessagesManager;
import ro.deiutzblaxo.bank.transaction.Transaction;
import ro.deiutzblaxo.bank.transaction.TransactionType;
import ro.nexs.db.manager.exception.DifferentArgLengthException;
import ro.nexs.db.manager.exception.NoDataFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PlayerBank {

    protected Player player;
    Double deposited;
    ListaLiniara<Transaction> transactions = new ListaLiniara<>(5);





    public PlayerBank(Player player) {
        this.player = player;
        if(Main.SQL) {
            try {
                deposited = Main.getConnectionManager().get("bank", "VALUE", "UUID", player.getUniqueId().toString(), Double.class);
            } catch (NoDataFoundException e) {
                deposited = 0.0;

            }

            try {
                List<String> a = Arrays.asList(Main.getConnectionManager().get("bank", "TRANSACTION", "UUID", player.getUniqueId().toString(), String.class).split(";"));
                a.forEach(transaction -> {
                    this.transactions.add(stringToTransaction(this, transaction));
                });
            } catch (NoDataFoundException e) {

            }
        }else{
            Main.getInstance().getDatabaseYML().load();
            deposited = Main.getInstance().getDatabaseYML().getFileConfiguration().contains(player.getUniqueId().toString()+".VALUE") ? Main.getInstance().getDatabaseYML().getFileConfiguration().getDouble(player.getUniqueId().toString()+".VALUE"):0;
            List<String> a = Main.getInstance().getDatabaseYML().getFileConfiguration().contains(player.getUniqueId().toString()+".TRANSACTION") ? Arrays.asList(Main.getInstance().getDatabaseYML().getFileConfiguration().getString(player.getUniqueId().toString()+".TRANSACTION").split(";")) : new ArrayList<String>();
            a.forEach(transaction -> {
                this.transactions.add(stringToTransaction(this, transaction));
            });
        }
    }
    public List<String> getTransactions(){
        List<String> lista = new ArrayList<>();
        if(transactions.isEmpty())
            return lista;
        for(int i = 0 ; i < transactions.size();i++){
            Transaction transaction = transactions.get(i).getValue();
            String typetran = transaction.getType().equals(TransactionType.DEPOSIT) ? Main.getInstance().getMessagesManager().get(MESSAGE.TYPE_DEPOSIT) : Main.getInstance().getMessagesManager().get(MESSAGE.TYPE_WITHDRAW);

            lista.add(MessagesManager.translate(
                    Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_LORE_TRANSACTION_FORMAT)
                            .replace("{type}",typetran)
                            .replace("{amount}",transaction.getAmount()+"")
                            .replace("{ago}",""+ Utils.timeConvert(timeToSecond(transaction.getWhen())))));
        }


        return lista;
    }

    private long timeToSecond(Long time){

        return (System.currentTimeMillis() - time)/1000;

    }

    public void makeTransaciton(TransactionType type, Double amount){
        switch (type){
            case DEPOSIT:

                this.deposited += amount;
                transactions.add(new Transaction(this,type,amount,System.currentTimeMillis()));
                save();
                break;

            case WITHDRAW:
               this.deposited -= amount;
               transactions.add(new Transaction(this,type,amount,System.currentTimeMillis()));
               save();
               break;
        }

    }


    public void save(){
        StringBuilder builder = new StringBuilder();
        if(transactions.size() <= 0)
            return;
        for(int i = 0 ; i < transactions.size() ; i++){
            builder.append(transactions.get(i).toString());
            if(i != transactions.size()-1){
                builder.append(";");
            }
        }
        if(Main.SQL) {
            if (Main.getConnectionManager().existString("bank", "UUID", player.getUniqueId().toString())) {

                Main.getConnectionManager().set("bank", "VALUE", "UUID", deposited, player.getUniqueId().toString());
                Main.getConnectionManager().set("bank", "TRANSACTION", "UUID", builder.toString(), player.getUniqueId().toString());

            } else {
                try {
                    Main.getConnectionManager().insert("bank", new String[]{"UUID", "VALUE", "TRANSACTION"},
                            new Object[]{player.getUniqueId().toString(), deposited, builder.toString()});
                } catch (DifferentArgLengthException e) {

                }
            }
        }else{
            Main.getInstance().getDatabaseYML().load();
            Main.getInstance().getDatabaseYML().getFileConfiguration().set(player.getUniqueId().toString()+".VALUE",getAmount());
            Main.getInstance().getDatabaseYML().getFileConfiguration().set(player.getUniqueId().toString()+".TRANSACTION",builder.toString());
            Main.getInstance().getDatabaseYML().save();
        }

    }

    public double getAmount(){
        return deposited;
    }
    private static Transaction stringToTransaction(PlayerBank player, String string){
    String[] strings = string.split(",");
    return  new Transaction(player, TransactionType.valueOf(strings[0]),Double.valueOf(strings[1]), Long.valueOf(strings[2]));
    }

}
