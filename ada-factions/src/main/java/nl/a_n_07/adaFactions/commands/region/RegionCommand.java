package nl.a_n_07.adaFactions.commands.region;

import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.utils.PermissionUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RegionCommand implements CommandExecutor {
    private RegionManager regionManager;

    public RegionCommand(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!PermissionUtil.hasModPermission(sender)) return true;
        if (args.length == 0) return false;

        switch (args[0].toLowerCase()) {
            case "add": return new AddRegionCommand(regionManager).execute(sender, args);
            case "remove": return new RemoveRegionCommand(regionManager).execute(sender, args);
            case "editname": return new EditRegionNameCommand(regionManager).execute(sender, args);
            case "list": return new ListRegionsCommand(regionManager).execute(sender, args);
            default:
                sender.sendMessage(ChatColor.RED + "Unknown command");
                return true;
        }
    }
}
