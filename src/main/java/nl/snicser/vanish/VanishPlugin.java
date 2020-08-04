package nl.snicser.vanish;

import nl.snicser.vanish.commands.register.CommandRegister;
import nl.snicser.vanish.handler.VanishHandler;
import nl.snicser.vanish.listeners.register.EventRegister;
import nl.snicser.vanish.utils.C;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanishPlugin extends JavaPlugin {

    private CommandRegister commandRegister;
    private EventRegister eventRegister;
    private BossBar bossBar;

    @Override
    public void onEnable() {
        initialize();

        commandRegister.registerAll();
        eventRegister.registerAll();

        bossBar = getServer().createBossBar(C.TAC("&aJe staat in vanish"), BarColor.YELLOW, BarStyle.SOLID);
    }

    @Override
    public void onDisable() {
        VanishHandler.getInstance().getPlayersInVanish().clear();
    }

    private void initialize() {
        commandRegister = new CommandRegister(this);
        eventRegister = new EventRegister(this);
    }

    /**
     * @return A bossbar
     */
    public BossBar getBossBar() {
        return bossBar;
    }
}
