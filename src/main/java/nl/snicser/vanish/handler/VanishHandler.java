package nl.snicser.vanish.handler;

import nl.snicser.vanish.Vanish;
import nl.snicser.vanish.handler.interfaces.PlayerVanisher;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishHandler implements PlayerVanisher {

    private final Vanish plugin;
    private final List<UUID> playersInVanish;
    private static VanishHandler instance;

    public VanishHandler(Vanish plugin) {
        this.plugin = plugin;
        this.playersInVanish = new ArrayList<>();
        instance = this;
    }

    /**
     * @param player Player to hide
     * @return True if player vanished
     */
    @Override
    public boolean vanish(Player player) {
        playersInVanish.add(player.getUniqueId());

        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> {

            if (!onlinePlayer.hasPermission("vanish.see") || !onlinePlayer.isOp()) {
                onlinePlayer.hidePlayer(plugin, player);
            } else {
                onlinePlayer.showPlayer(plugin, player);
            }
        });

        return true;
    }

    /**
     * @param player Player to show
     * @return True if player unvanished
     */
    @Override
    public boolean unvanish(Player player) {
        playersInVanish.remove(player.getUniqueId());
        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.showPlayer(plugin, player));
        return true;
    }

    /**
     * @return A list of all players that are in vanish
     */
    public List<UUID> getPlayersInVanish() {
        return playersInVanish;
    }

    /**
     * @return Instance of the class
     */
    public static VanishHandler getInstance() {
        return instance;
    }
}
