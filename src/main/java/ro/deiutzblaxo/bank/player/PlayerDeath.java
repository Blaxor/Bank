package ro.deiutzblaxo.bank.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import ro.deiutzblaxo.bank.Main;
import ro.deiutzblaxo.bank.config.MESSAGE;
import ro.deiutzblaxo.bank.config.MessagesManager;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();

        player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.MONEY_LOSE_DEATH).replace("{amount}",(Main.getInstance().getEconomy().getBalance(player)/2)+"")));
        Main.getInstance().getEconomy().withdrawPlayer(player,Main.getInstance().getEconomy().getBalance(player)/2);
    }
}
