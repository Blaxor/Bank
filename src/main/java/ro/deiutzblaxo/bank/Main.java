package ro.deiutzblaxo.bank;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ro.deiutzblaxo.bank.command.Command;
import ro.deiutzblaxo.bank.config.MessagesManager;
import ro.deiutzblaxo.bank.config.ymlDataBase;
import ro.deiutzblaxo.bank.gui.InventoryEvent;
import ro.deiutzblaxo.bank.player.PlayerDeath;
import ro.deiutzblaxo.bank.player.PlayerManager;
import ro.deiutzblaxo.bank.listener.ChatInsertNumber;
import ro.deiutzblaxo.bank.utils.DBManager;
import ro.nexs.db.manager.connection.DBConnection;

import java.util.logging.Level;

public final class Main extends JavaPlugin {

    private static DBConnection con;
    private static DBManager conManager;
    private static Main instance;
    public static boolean SQL = false;
    private PlayerManager playerManager;
    private MessagesManager messagesManager;
    private Economy econ;
    private ymlDataBase database;



    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        reloadConfig();
        if(getConfig().getBoolean("database-enabled")) {
            SQL = true;
            //load DB
            con = new DBConnection(getConfig().getString("host"), getConfig().getInt("port"), getConfig().getString("username"),getConfig().getString("password"));
            con.switchDatabase(getConfig().getString("database"));
            conManager = new DBManager(con);
            getConnectionManager().createTable("bank", new String[]{"UUID varchar(256)", "VALUE double", "TRANSACTION varchar(256)"});

        }else{

            database =new ymlDataBase();
        }
        //load classes
        playerManager = new PlayerManager(this);
        messagesManager = new MessagesManager(this);



        //load vault

        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // LISTENERS
        getServer().getPluginManager().registerEvents(new InventoryEvent(this),this);
        getServer().getPluginManager().registerEvents(new ChatInsertNumber(),this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(),this);
        getCommand("bank").setExecutor(new Command());
        getCommand("bank").setPermission("bank.command");


    }

    @Override
    public  void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public Economy getEconomy(){
        return econ;
    }


    public static DBConnection getConnection(){
        return con;
    }
    public static DBManager getConnectionManager(){
        return conManager;
    }
    public static Main getInstance() {
        return instance;
    }

    public PlayerManager getPlayerManager(){return playerManager;}
    public MessagesManager getMessagesManager(){return messagesManager;}

    public ymlDataBase getDatabaseYML() {
        return database;
    }


}
