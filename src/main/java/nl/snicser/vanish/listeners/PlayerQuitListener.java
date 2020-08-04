package nl.snicser.vanish.listeners;

import nl.snicser.vanish.VanishPlugin;
import nl.snicser.vanish.handler.VanishHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This class handles everything when the player leaves the server
 */
public class PlayerQuitListener implements Listener {

    private final VanishPlugin plugin;

    public PlayerQuitListener(VanishPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        plugin.getBossBar().removePlayer(player);
        VanishHandler.getInstance().getPlayersInVanish().remove(player.getUniqueId());
    }
}
