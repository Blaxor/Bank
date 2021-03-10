package ro.deiutzblaxo.bank.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ro.deiutzblaxo.bank.Main;
import ro.deiutzblaxo.bank.config.MESSAGE;
import ro.deiutzblaxo.bank.config.MessagesManager;
import ro.deiutzblaxo.bank.player.PlayerBank;
import ro.deiutzblaxo.bank.transaction.TransactionType;

import java.util.HashMap;

public class ChatInsertNumber implements Listener {
    public static HashMap<Player, TransactionType> awaitingCustomNumber= new HashMap<>();
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(awaitingCustomNumber.containsKey(event.getPlayer())){
            event.setCancelled(true);
            try {
                double amount = Double.parseDouble(event.getMessage());
                PlayerBank playerBank = Main.getInstance().getPlayerManager().getPlayer(player);
                switch (awaitingCustomNumber.get(player)) {
                    case DEPOSIT:
                        if(Main.getInstance().getEconomy().has(player,amount)) {
                            playerBank.makeTransaciton(awaitingCustomNumber.get(player), amount);
                            Main.getInstance().getEconomy().withdrawPlayer(player,amount);
                            player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.DEPOSIT_CHAT_MESSAGE).replace("{amount}", amount + "")));
                        }else{

                            player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.NOT_ENOUGH_MONEY_TO_DEPOSIT).replace("{amount}", amount + "")));
                        }
                        break;
                    case WITHDRAW:
                        if(Main.getInstance().getPlayerManager().getPlayer(player).getAmount() >= amount) {
                            Main.getInstance().getEconomy().depositPlayer(player,amount);
                            playerBank.makeTransaciton(awaitingCustomNumber.get(player), amount);
                            player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.WITHDRAW_CHAT_MESSAGE).replace("{amount}", amount + "")));
                            return;
                        }else{
                            player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.NOT_ENOUGH_MONEY_TO_WITHDRAW).replace("{amount}", amount + "")));
                        }

                        break;
                }

            }catch (NumberFormatException e){
                player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.INVALID_NUMBER_FORMAT).replace("{amount}", event.getMessage() + "")));
            }finally {
                awaitingCustomNumber.remove(player);
            }

        }
    }
}
