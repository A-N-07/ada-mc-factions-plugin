package nl.a_n_07.adaFactions.utils;

import org.bukkit.command.CommandSender;

public class PermissionUtil {
    public static boolean hasOpPermission(CommandSender sender) {
        if (!sender.hasPermission("adafactions.op")) {
            sender.sendMessage("No permission.");
            return false;
        }
        return true;
    }

    public static boolean hasModPermission(CommandSender sender) {
        if (!sender.hasPermission("adafactions.mod")) {
            sender.sendMessage("No permission.");
            return false;
        }
        return true;
    }
}
