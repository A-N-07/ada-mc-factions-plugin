package nl.a_n_07.adaFactions.commands.region;

import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.models.Region;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class AddRegionCommand {
    private RegionManager regionManager;

    public AddRegionCommand(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 7) {
            sender.sendMessage("Usage: /region add <name> <x1> <x2> <y1> <y2> <z1> <z2>");
            return true;
        }

        if (regionManager.regionExists(args[0])) {
            sender.sendMessage(ChatColor.RED + "Region already exists!");
            return true;
        }

        try {
            String name = args[0];
            int x1 = Integer.parseInt(args[1]);
            int x2 = Integer.parseInt(args[2]);
            int y1 = Integer.parseInt(args[3]);
            int y2 = Integer.parseInt(args[4]);
            int z1 = Integer.parseInt(args[5]);
            int z2 = Integer.parseInt(args[6]);

            regionManager.addRegion(new Region(name, x1, x2, y1, y2, z1, z2));
            sender.sendMessage("Region " + name + " successfully added");
        } catch (NumberFormatException e) {
            sender.sendMessage("Coordinates must be a number");
        } catch (IllegalArgumentException e) {
            sender.sendMessage(e.getMessage());
        }
        return true;
    }
}
