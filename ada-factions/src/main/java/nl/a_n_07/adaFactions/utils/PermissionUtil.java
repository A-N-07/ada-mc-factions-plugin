package nl.a_n_07.adaFactions.utils;

import org.bukkit.command.CommandSender;

public class PermissionUtil {
    public static boolean hasModPermission(CommandSender sender) {
        return sender.isOp() || sender.hasPermission("adafactions.mod");
    }

    public static boolean hasOpPermission(CommandSender sender) {
        return sender.isOp() || sender.hasPermission("adafactions.op");
    }
}
