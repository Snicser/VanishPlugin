package nl.snicser.vanish.listeners;

import nl.snicser.vanish.VanishPlugin;
import nl.snicser.vanish.handler.VanishHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


/**
 * This class handles everything when a player joins the server
 */
public class PlayerJoinListener implements Listener {

    private final VanishPlugin plugin;

    public PlayerJoinListener(VanishPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player joinedPlayer = event.getPlayer();

        plugin.getServer().getOnlinePlayers().forEach(player -> {

            if (VanishHandler.getInstance().getPlayersInVanish().contains(player.getUniqueId())) {

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
