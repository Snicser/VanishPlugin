package nl.snicser.vanish.listeners;

import nl.snicser.vanish.Vanish;
import nl.snicser.vanish.commands.VanishCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


/**
 * This class handles everything when a player joins the server
 */
public class PlayerJoinListener implements Listener {

    private final Vanish plugin;

    public PlayerJoinListener(Vanish plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player joinedPlayer = event.getPlayer();

        Bukkit.getOnlinePlayers().forEach(player -> {

            if (VanishCommand.getInstance().getVanishPlayers().contains(player.getUniqueId())) {

                // Checking if the joining player has permissions to see the vanish player
                if (joinedPlayer.hasPermission("vanish.see") || joinedPlayer.isOp()) {
                    joinedPlayer.showPlayer(plugin, player);
                } else {
                    joinedPlayer.hidePlayer(plugin, player);
                }
            }
        });
    }
}
