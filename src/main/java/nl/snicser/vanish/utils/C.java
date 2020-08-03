package nl.snicser.vanish.utils;

import org.bukkit.ChatColor;

/**
 * Shortcut class for ChatColor.translateAlternateColorCodes
 */
public final class C {

    private C() {

    }

    /**
     * @param input Text you want to translate with color codes
     * @return Text containing the ChatColor.COLOR_CODE color code character.
     */
    public static String TAC(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
