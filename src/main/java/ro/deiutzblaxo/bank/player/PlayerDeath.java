package ro.deiutzblaxo.bank.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import ro.deiutzblaxo.bank.Main;
import ro.deiutzblaxo.bank.config.MESSAGE;
import ro.deiutzblaxo.bank.config.MessagesManager;
import ro.deiutzblaxo.bank.utils.Utils;

import java.util.logging.Level;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        Main.getInstance().getLogger().log(Level.INFO,player.getDisplayName() +" died and lose" + Utils.roundAvoid(Main.getInstance().getEconomy().getBalance(player)/2,2));
        player.sendMessage(MessagesManager.translate(Main.getInstance().getMessagesManager().get(MESSAGE.MONEY_LOSE_DEATH).replace("{amount}",(Utils.roundAvoid(Main.getInstance().getEconomy().getBalance(player)/2,2)+""))));
        Main.getInstance().getEconomy().withdrawPlayer(player,Main.getInstance().getEconomy().getBalance(player)/2);
    }
}
