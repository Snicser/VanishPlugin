package nl.snicser.vanish.handler;

import nl.snicser.vanish.VanishPlugin;
import org.bukkit.entity.Player;

import java.util.*;

public class VanishHandler {

    private final VanishPlugin plugin;
    private final Set<UUID> playersInVanish;
    private static VanishHandler instance;

    public VanishHandler(VanishPlugin plugin) {
        this.plugin = plugin;
        this.playersInVanish = new HashSet<>();
        instance = this;
    }

    /**
     * Vanish a player
     *
     * @param player Player to hide
     * @return True if player vanished
     */
    public boolean vanish(Player player) {
        playersInVanish.add(player.getUniqueId());

        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> {

            if (onlinePlayer.canSee(player)) {
                onlinePlayer.hidePlayer(plugin, player);
            }

        });
        return true;
    }

    /**
     * Unvanish a player
     *
     * @param player Player to show
     * @return True if player unvanished
     */
    public boolean unvanish(Player player) {
        playersInVanish.remove(player.getUniqueId());
        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.showPlayer(plugin, player));
        return true;
    }

    /**
     * @return A list of all players that are in vanish
     */
    public Set<UUID> getPlayersInVanish() {
        return playersInVanish;
    }

    /**
     * @return Instance of the class
     */
    public static VanishHandler getInstance() {
        return instance;
    }
}
