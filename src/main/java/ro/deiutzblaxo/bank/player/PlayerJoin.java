package ro.deiutzblaxo.bank.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener {
    private PlayerManager playerManager;
    public PlayerJoin(PlayerManager playerManager){
        this.playerManager = playerManager;

    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        playerManager.loadPlayer(event.getPlayer());

    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){playerManager.unloadPlayer(event.getPlayer());}
}
