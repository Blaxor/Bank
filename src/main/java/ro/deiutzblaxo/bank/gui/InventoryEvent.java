package ro.deiutzblaxo.bank.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import ro.deiutzblaxo.bank.Main;
import ro.deiutzblaxo.bank.config.MESSAGE;
import ro.deiutzblaxo.bank.config.MessagesManager;
import ro.deiutzblaxo.bank.player.PlayerBank;
import ro.deiutzblaxo.bank.listener.ChatInsertNumber;
import ro.deiutzblaxo.bank.transaction.TransactionType;

public class InventoryEvent implements Listener {

    private Main plugin;
    public InventoryEvent(Main main){
        plugin = main;
    }

    @EventHandler
    public void playerClickEvent(InventoryClickEvent event){
        //PRINCIPAL MENU
        if(event.getView().getTitle().equalsIgnoreCase(MessagesManager
                .translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_MENU_TITLE_BANKER)))){
            if(event.getCurrentItem() != null)
            if(event.getCurrentItem().hasItemMeta()){

                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_TITLE_DEPOSIT)))){
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().openInventory(BankerMenu.getDepositMenu((Player)event.getWhoClicked()));
                }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_TITLE_WITHDRAW)))){
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().openInventory(BankerMenu.getWithdrawMenu((Player)event.getWhoClicked()));
                }else{
                    event.setCancelled(true);
                }
            }
            //MENU WITHDRAW
        }else if(event.getView().getTitle().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_MENU_TITLE_WITHDRAW)))){
            if(event.getCurrentItem() != null)
                if(event.getCurrentItem().hasItemMeta()) {
                    //ALL MONEY
                    Player player =(Player)event.getWhoClicked();
                    PlayerBank pb =plugin.getPlayerManager().getPlayer(player);
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_WITHDRAW_TITLE_FULL)))) {
                        player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.WITHDRAW_CHAT_MESSAGE).replace("{amount}",pb.getAmount() + "")));
                        plugin.getEconomy().depositPlayer(player,pb.getAmount());
                        plugin.getPlayerManager().getPlayer(player).makeTransaciton(TransactionType.WITHDRAW,pb.getAmount());
                        event.setCancelled(true);
                        player.closeInventory();
            //MENU HALF OF MONEY
                    }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_WITHDRAW_TITLE_HALF)))){
                        player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.WITHDRAW_CHAT_MESSAGE).replace("{amount}",(pb.getAmount()/2) + "")));
                        plugin.getEconomy().depositPlayer(player,pb.getAmount()/2);
                        plugin.getPlayerManager().getPlayer(player).makeTransaciton(TransactionType.WITHDRAW,pb.getAmount()/2);
                        event.setCancelled(true);
                        player.closeInventory();
                    }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_WITHDRAW_TITLE_CUSTOM)))){
                        ChatInsertNumber.awaitingCustomNumber.put((Player)event.getWhoClicked(),TransactionType.WITHDRAW);
                        ((Player) event.getWhoClicked()).sendTitle(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.INSERT_AMOUNT_TITLE)),"");
                        event.setCancelled(true);
                        event.getWhoClicked().closeInventory();
                    } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_CLOSE)))) {
                        event.setCancelled(true);
                        event.getWhoClicked().closeInventory();
                    }
                }
            //MENU DEPOSIT
        }else if(event.getView().getTitle().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_MENU_TITLE_DEPOSIT)))){
            if(event.getCurrentItem() != null) {
                ItemStack item = event.getCurrentItem();
                if (event.getCurrentItem().hasItemMeta()) {//ALL MONEY
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_DEPOSIT_TITLE_FULL)))){
                        Player player =(Player)event.getWhoClicked();
                        PlayerBank pb =plugin.getPlayerManager().getPlayer(player);
                        player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.DEPOSIT_CHAT_MESSAGE).replace("{amount}",plugin.getEconomy().getBalance(player)+"")));
                        pb.makeTransaciton(TransactionType.DEPOSIT,plugin.getEconomy().getBalance(player));
                        plugin.getEconomy().withdrawPlayer(player, plugin.getEconomy().getBalance(player));
                        event.setCancelled(true);
                        player.closeInventory();

                    //HALF MONEY
                    } else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_DEPOSIT_TITLE_HALF)))) {
                        Player player = (Player) event.getWhoClicked();
                        PlayerBank pb = plugin.getPlayerManager().getPlayer(player);
                        player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.DEPOSIT_CHAT_MESSAGE).replace("{amount}", plugin.getEconomy().getBalance(player) / 2 + "")));
                        pb.makeTransaciton(TransactionType.DEPOSIT, plugin.getEconomy().getBalance(player) / 2);
                        plugin.getEconomy().withdrawPlayer(player, plugin.getEconomy().getBalance(player) / 2);
                        event.setCancelled(true);
                        player.closeInventory();
                        //CUSTOM MONEY
                    }else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_DEPOSIT_TITLE_CUSTOM)))){
                        ChatInsertNumber.awaitingCustomNumber.put((Player)event.getWhoClicked(),TransactionType.DEPOSIT);
                        ((Player) event.getWhoClicked()).sendTitle(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.INSERT_AMOUNT_TITLE)),"");
                        event.setCancelled(true);
                        event.getWhoClicked().closeInventory();

                    } else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_CLOSE)))) {
                        event.setCancelled(true);
                        event.getWhoClicked().closeInventory();
                    }
                }
            }
        }
    }
}
