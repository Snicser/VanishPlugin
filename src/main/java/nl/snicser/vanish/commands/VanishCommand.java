package nl.snicser.vanish.commands;

import nl.snicser.vanish.Vanish;
import nl.snicser.vanish.utils.C;
import nl.snicser.vanish.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {

    private final Vanish plugin;
    private static VanishCommand instance;

    private List<UUID> vanishPlayers;

    public VanishCommand(Vanish plugin) {
        this.plugin = plugin;
        instance = this;

        vanishPlayers = new ArrayList<>();
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
        if (!vanishPlayers.contains(player.getUniqueId())) {

            vanishPlayers.add(player.getUniqueId());

            Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {

                if (!onlinePlayer.hasPermission("vanish.see") || !onlinePlayer.isOp()) {
                    onlinePlayer.hidePlayer(plugin, player);
                } else {
                    onlinePlayer.showPlayer(plugin, player);
                }

            });

            player.sendMessage(Constants.PREFIX + C.TAC("&aJe staat nu in vanish!"));

        } else {
            vanishPlayers.remove(player.getUniqueId());
            Bukkit.getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.showPlayer(plugin, player));
            player.sendMessage(Constants.PREFIX + C.TAC("&aJe staat nu niet meer in vanish!"));
        }

        return true;
    }

    /**
     * @return Returns a list of all players that are in vanish
     */
    public List<UUID> getVanishPlayers() {
        return vanishPlayers;
    }

    /**
     * @return Instance of the class
     */
    public static VanishCommand getInstance() {
        return instance;
    }
}
