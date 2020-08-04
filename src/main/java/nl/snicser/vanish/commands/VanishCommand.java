package nl.snicser.vanish.commands;

import nl.snicser.vanish.VanishPlugin;
import nl.snicser.vanish.utils.C;
import nl.snicser.vanish.utils.Constants;
import nl.snicser.vanish.handler.VanishHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private final VanishPlugin plugin;
    private final VanishHandler vanishHandler;

    public VanishCommand(VanishPlugin plugin) {
        this.plugin = plugin;
        this.vanishHandler = new VanishHandler(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Constants.PREFIX + "Alleen spelers kunnen zich zelf zichtbaar of ontzichtbaar maken.");
            return false;
        }

        Player player = (Player) sender;

        // Checking if the player has permission to execute this command
        if (!player.hasPermission("vanish.use") || !player.isOp()) {
            player.sendMessage(Constants.PREFIX + C.TAC("&cJe hebt geen toestemming om dit commando uit te voeren!"));
            return false;
        }

        // Checking if they entered more arguments then 0
        if (args.length > 0) {
            player.sendMessage(Constants.PREFIX + C.TAC("&fGebruik: &c/vanish"));
            return false;
        }

        // Handle player vanish
        if (!vanishHandler.getPlayersInVanish().contains(player.getUniqueId())) {

            vanishHandler.vanish(player);
            player.sendMessage(Constants.PREFIX + C.TAC("&aJe staat nu in vanish!"));
            plugin.getBossBar().addPlayer(player);
        } else {
            vanishHandler.unvanish(player);
            player.sendMessage(Constants.PREFIX + C.TAC("&aJe staat nu niet meer in vanish!"));
            plugin.getBossBar().removePlayer(player);
        }

        return true;
    }
}
