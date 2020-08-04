package nl.snicser.vanish.commands.register;

import nl.snicser.vanish.VanishPlugin;
import nl.snicser.vanish.commands.VanishCommand;
import org.bukkit.command.CommandExecutor;

/**
 * This class is responsible for registering commands
 */
public class CommandRegister {

    private final VanishPlugin plugin;

    public CommandRegister(VanishPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Register all the commands
     */
    public void registerAll() {
        registerCommand("vanish", new VanishCommand(plugin));
    }

    /**
     * Register a command
     *
     * @param command The command name
     * @param executor The class the implements CommandExecutor
     */
    private void registerCommand(String command, CommandExecutor executor) {
        plugin.getCommand(command).setExecutor(executor);
    }
}
