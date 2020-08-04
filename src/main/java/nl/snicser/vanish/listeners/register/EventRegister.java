package nl.snicser.vanish.listeners.register;

import nl.snicser.vanish.Vanish;
import nl.snicser.vanish.listeners.PlayerJoinListener;
import nl.snicser.vanish.listeners.PlayerQuitListener;
import org.bukkit.event.Listener;

/**
 * This class is responsible for registering listeners
 */
public class EventRegister {

    private final Vanish plugin;

    public EventRegister(Vanish plugin) {
        this.plugin = plugin;
    }

    /**
     * Register all the listeners
     */
    public void registerAll() {
        registerListener(new PlayerJoinListener(plugin));
        registerListener(new PlayerQuitListener(plugin));
    }

    /**
     * Register a listener
     *
     * @param listener The class the implements Listener
     */
    private void registerListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}
