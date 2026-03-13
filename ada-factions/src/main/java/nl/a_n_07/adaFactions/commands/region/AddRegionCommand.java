package nl.a_n_07.adaFactions.commands.region;

import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.models.Region;
import org.bukkit.command.CommandSender;

public class AddRegionCommand {
    private RegionManager regionManager;

    public AddRegionCommand(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    public boolean execute(CommandSender sender, String[] args) {

        try {
            String name = args[0];
            int x1 = Integer.parseInt(args[1]);
            int x2 = Integer.parseInt(args[2]);
            int y1 = Integer.parseInt(args[3]);
            int y2 = Integer.parseInt(args[4]);
            int z1 = Integer.parseInt(args[5]);
            int z2 = Integer.parseInt(args[6]);

            regionManager.addRegion(new Region(name,x1,x2,y1,y2,z1,z2));
            sender.sendMessage("Region " + name + " successfully added");
        } catch (NumberFormatException e) {
            sender.sendMessage("Coordinates must be an number");
        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage("Usage: /addregion <name> <x1> <x2> <y1> <y2> <z1> <z2>");
        } catch (IllegalArgumentException e) {
            sender.sendMessage(e.getMessage());
        }
        return true;
    }
}
