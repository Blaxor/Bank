package ro.deiutzblaxo.bank.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ro.deiutzblaxo.bank.Main;
import ro.deiutzblaxo.bank.utils.DBManager;
import ro.deiutzblaxo.bank.utils.datastructure.ListaLiniara;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerManager {

    protected HashMap<Player,PlayerBank> accountsMap = new HashMap<>();
    private Main plugin;


    public PlayerManager(Main plugin){
        this.plugin= plugin;
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoin(this),plugin);
    }

    public void loadPlayer(Player player){

         accountsMap.put(player,new PlayerBank(player));
    }
    public void unloadPlayer(Player player){
       accountsMap.get(player).save();
       accountsMap.remove(player);

    }

    public void resetPlayer(Player player){
        accountsMap.get(player).deposited = 0.0;
        accountsMap.get(player).transactions = new ListaLiniara<>();
        accountsMap.get(player).save();
        Main.getInstance().getLogger().log(Level.INFO , player.getDisplayName() + " reseted");
    }
    public void resetPlayer(OfflinePlayer player){
        if(Main.SQL)
        Main.getConnectionManager().deleteRow("BANK","UUID",player.getUniqueId().toString());
        else{
            Main.getInstance().getDatabaseYML().load();
            Main.getInstance().getDatabaseYML().getFileConfiguration().set(player.getUniqueId().toString(),null);
            Main.getInstance().getDatabaseYML().save();
        }
        Main.getInstance().getLogger().log(Level.INFO , player.getName() + " (Offline Player) reseted");
    }
    public HashMap<OfflinePlayer, Double> getTopPlayers()  {
        ResultSet set = null;
        String a ;
        Double b ;
        HashMap<OfflinePlayer,Double> list = new HashMap<>();
        try {
            set = Main.getConnectionManager().executeQuery(Main.getConnection().getConnection().prepareStatement("SELECT * FROM `nxs_oneblock`.`bank` ORDER BY `VALUE` DESC LIMIT 10;"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true){
            try {
                if (!set.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                list.put(Bukkit.getOfflinePlayer(UUID.fromString(set.getString("UUID"))), set.getDouble("VALUE"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    return list;

    }


    public PlayerBank getPlayer(Player player) {
        return accountsMap.get(player);
    }
}
