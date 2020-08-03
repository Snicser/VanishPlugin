package nl.snicser.vanish;

import nl.snicser.vanish.commands.VanishCommand;
import nl.snicser.vanish.commands.register.CommandRegister;
import nl.snicser.vanish.listeners.register.EventRegister;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vanish extends JavaPlugin {

    private CommandRegister commandRegister;
    private EventRegister eventRegister;

    @Override
    public void onEnable() {
        initialize();

        commandRegister.registerAll();
        eventRegister.registerAll();
    }

    @Override
    public void onDisable() {
        VanishCommand.getInstance().getVanishPlayers().clear();
    }

    private void initialize() {
        commandRegister = new CommandRegister(this);
        eventRegister = new EventRegister(this);
    }
}
