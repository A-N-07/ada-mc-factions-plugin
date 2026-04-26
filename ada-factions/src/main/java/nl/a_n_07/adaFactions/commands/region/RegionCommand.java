package nl.a_n_07.adaFactions.commands.region;

import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.utils.PermissionUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.Arrays;

public class RegionCommand implements CommandExecutor {
    private RegionManager regionManager;
    private FactionManager factionManager;

    public RegionCommand(RegionManager regionManager, FactionManager factionManager) {
        this.regionManager = regionManager;
        this.factionManager = factionManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!PermissionUtil.hasModPermission(sender)) return true;
        if (args.length == 0) return false;

        switch (args[0].toLowerCase()) {
            case "add": return new AddRegionCommand(regionManager).execute(sender, Arrays.copyOfRange(args, 1, args.length));
            case "remove": return new RemoveRegionCommand(regionManager).execute(sender, Arrays.copyOfRange(args, 1, args.length));
            case "editname": return new EditRegionNameCommand(regionManager).execute(sender, Arrays.copyOfRange(args, 1, args.length));
            case "list": return new ListRegionsCommand(regionManager).execute(sender, Arrays.copyOfRange(args, 1, args.length));
            case "setfaction": return new SetRegionFactionCommand(regionManager, factionManager).execute(sender,Arrays.copyOfRange(args, 1, args.length));
            default:
                sender.sendMessage(ChatColor.RED + "Unknown command");
                return true;
        }
    }
}
