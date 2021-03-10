package ro.deiutzblaxo.bank.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ro.deiutzblaxo.bank.Main;
import ro.deiutzblaxo.bank.config.MESSAGE;
import ro.deiutzblaxo.bank.config.MESSAGELIST;
import ro.deiutzblaxo.bank.config.MessagesManager;
import ro.deiutzblaxo.bank.player.PlayerBank;
import ro.deiutzblaxo.bank.utils.Utils;

import java.util.Arrays;
import java.util.List;

public class BankerMenu {


    public static Inventory getBankerMenu(Player player){
        PlayerBank playerBank = Main.getInstance().getPlayerManager().getPlayer(player);
        Inventory inventory = Bukkit.createInventory(null,9, MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_MENU_TITLE_BANKER)));

        ItemStack info = new ItemStack(Material.MAP); // deposit
        ItemMeta meta = info.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_TITLE_INFO)));
        List<String> lore = Main.getInstance().getMessagesManager().getList(MESSAGELIST.GUI_ITEM_LORE_INFO);
        for(int i = 0 ; i < lore.size();i++){
            lore.set(i,MessagesManager.translate(lore.get(i).replace("{amount}",playerBank.getAmount()+"")));
        }
        meta.setLore(lore);
        info.setItemMeta(meta);
        inventory.setItem(0,info);




        ItemStack deposit = new ItemStack(Material.GOLD_NUGGET); // deposit
        meta = deposit.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_TITLE_DEPOSIT)));
         lore = Main.getInstance().getMessagesManager().getList(MESSAGELIST.GUI_ITEM_LORE_DEPOSIT);
        for(int i = 0 ; i < lore.size();i++){
            lore.set(i,MessagesManager.translate(lore.get(i).replace("{amount}",playerBank.getAmount()+"")));
        }
        meta.setLore(lore);
        deposit.setItemMeta(meta);
        inventory.setItem(3,deposit);

        ItemStack withdraw = new ItemStack(Material.DISPENSER);
        meta = withdraw.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_TITLE_WITHDRAW)));

         lore = Main.getInstance().getMessagesManager().getList(MESSAGELIST.GUI_ITEM_LORE_WITHDRAW);
        for(int i = 0 ; i < lore.size();i++){
            lore.set(i,MessagesManager.translate(lore.get(i).replace("{amount}",playerBank.getAmount()+"")));
        }
        meta.setLore(lore);

        withdraw.setItemMeta(meta);
        inventory.setItem(5,withdraw);

        ItemStack log = new ItemStack(Material.BOOK);
        meta = log.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_TITLE_LOG)));
        meta.setLore(playerBank.getTransactions());
        log.setItemMeta(meta);

        inventory.setItem(8,log);
    return inventory;
    }
    public static Inventory getDepositMenu(Player player){
        PlayerBank playerBank = Main.getInstance().getPlayerManager().getPlayer(player);
        Inventory inventory = Bukkit.createInventory(null,9,MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_MENU_TITLE_DEPOSIT)));

        ItemStack item = new ItemStack(Material.CHEST,64);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_DEPOSIT_TITLE_FULL)));

        List<String> lore = Main.getInstance().getMessagesManager().getList(MESSAGELIST.GUI_ITEM_DEPOSIT_LORE_FULL);
        for(int i = 0 ; i < lore.size();i++){
            lore.set(i,MessagesManager.translate(lore.get(i).replace("{amount}",playerBank.getAmount()+"")));
        }
        meta.setLore(lore);


        item.setItemMeta(meta);
        inventory.setItem(1,item);

        item = new ItemStack(Material.CHEST,32);
        meta = item.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_DEPOSIT_TITLE_HALF)));

        lore = Main.getInstance().getMessagesManager().getList(MESSAGELIST.GUI_ITEM_DEPOSIT_LORE_HALF);
        for(int i = 0 ; i < lore.size();i++){
            lore.set(i,MessagesManager.translate(lore.get(i).replace("{amount}",playerBank.getAmount()+"")));
        }
        meta.setLore(lore);

        item.setItemMeta(meta);
        inventory.setItem(3,item);

        item = new ItemStack(Material.CHEST);
        meta = item.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_DEPOSIT_TITLE_CUSTOM)));

        lore = Main.getInstance().getMessagesManager().getList(MESSAGELIST.GUI_ITEM_DEPOSIT_LORE_CUSTOM);
        for(int i = 0 ; i < lore.size();i++){
            lore.set(i,MessagesManager.translate(lore.get(i).replace("{amount}",playerBank.getAmount()+"")));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inventory.setItem(5,item);

        item = new ItemStack(Material.BARRIER);
        meta = item.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_CLOSE)));
        item.setItemMeta(meta);
        inventory.setItem(8,item);

        return inventory;
    }
    public static Inventory getWithdrawMenu(Player player){
        PlayerBank playerBank = Main.getInstance().getPlayerManager().getPlayer(player);
        Inventory inventory = Bukkit.createInventory(null,9,MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_MENU_TITLE_WITHDRAW)));

        ItemStack item = new ItemStack(Material.DISPENSER,64);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_WITHDRAW_TITLE_FULL)));

        List<String> lore = Main.getInstance().getMessagesManager().getList(MESSAGELIST.GUI_ITEM_WITHDRAW_LORE_FULL);
        for(int i = 0 ; i < lore.size();i++){
            lore.set(i,MessagesManager.translate(lore.get(i).replace("{amount}",playerBank.getAmount()+"")));
        }
        meta.setLore(lore);

        item.setItemMeta(meta);
        inventory.setItem(1,item);

        item = new ItemStack(Material.DISPENSER,32);
        meta = item.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_WITHDRAW_TITLE_HALF)));

        lore = Main.getInstance().getMessagesManager().getList(MESSAGELIST.GUI_ITEM_WITHDRAW_LORE_HALF);
        for(int i = 0 ; i < lore.size();i++){
            lore.set(i,MessagesManager.translate(lore.get(i).replace("{amount}",playerBank.getAmount()+"")));
        }
        meta.setLore(lore);


        item.setItemMeta(meta);
        inventory.setItem(3,item);

        item = new ItemStack(Material.DISPENSER);
        meta = item.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_WITHDRAW_TITLE_CUSTOM)));

        lore = Main.getInstance().getMessagesManager().getList(MESSAGELIST.GUI_ITEM_WITHDRAW_LORE_CUSTOM);
        for(int i = 0 ; i < lore.size();i++){
            lore.set(i,MessagesManager.translate(lore.get(i).replace("{amount}",playerBank.getAmount()+"")));
        }
        meta.setLore(lore);

        item.setItemMeta(meta);
        inventory.setItem(5,item);

        item = new ItemStack(Material.BARRIER);
        meta = item.getItemMeta();
        meta.setDisplayName(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.GUI_ITEM_CLOSE)));
        item.setItemMeta(meta);
        inventory.setItem(8,item);

        return inventory;
    }
}
