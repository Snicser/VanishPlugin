package nl.snicser.vanish;

import nl.snicser.vanish.commands.VanishCommand;
import nl.snicser.vanish.handler.VanishHandler;
import nl.snicser.vanish.listeners.PlayerJoinListener;
import nl.snicser.vanish.listeners.PlayerQuitListener;
import nl.snicser.vanish.utils.C;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanishPlugin extends JavaPlugin {

    private BossBar bossBar;

    @Override
    public void onEnable() {
        getCommand("vanish").setExecutor(new VanishCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);

        bossBar = getServer().createBossBar(C.TAC("&aJe staat in vanish"), BarColor.YELLOW, BarStyle.SOLID);
    }

    @Override
    public void onDisable() {
        VanishHandler.getInstance().getPlayersInVanish().clear();
    }

    /**
     * @return A bossbar
     */
    public BossBar getBossBar() {
        return bossBar;
    }
}
