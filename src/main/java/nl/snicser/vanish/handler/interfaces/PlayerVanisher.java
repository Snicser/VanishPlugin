package nl.snicser.vanish.handler.interfaces;

import org.bukkit.entity.Player;

public interface PlayerVanisher {

    boolean vanish(Player players);
    boolean unvanish(Player player);
}
