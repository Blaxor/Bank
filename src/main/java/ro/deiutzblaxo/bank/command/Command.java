package ro.deiutzblaxo.bank.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ro.deiutzblaxo.bank.gui.BankerMenu;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if(sender.isOp()){
            if(args.length != 1){
                sender.sendMessage("use /bank <player>");
                return false;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if(player == null){
                sender.sendMessage("player offline");
                return false;
            }
            player.openInventory(BankerMenu.getBankerMenu(player));



        }


        return false;
    }
}
