package nl.snicser.vanish.listeners;

import nl.snicser.vanish.commands.VanishCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This class handles everything when the player leaves the server
 */
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        VanishCommand.getInstance().getVanishPlayers().remove(event.getPlayer().getUniqueId());
    }
}
