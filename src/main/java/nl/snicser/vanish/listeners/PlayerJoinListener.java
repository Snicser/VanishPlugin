package nl.snicser.vanish.listeners;

import nl.snicser.vanish.VanishPlugin;
import nl.snicser.vanish.handler.VanishHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;


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
        for (UUID uuid : VanishHandler.getInstance().getPlayersInVanish()) {
            Player player = plugin.getServer().getPlayer(uuid);

            if (player != null) {
                VanishHandler.getInstance().vanish(player);
            }
        }
    }
}
