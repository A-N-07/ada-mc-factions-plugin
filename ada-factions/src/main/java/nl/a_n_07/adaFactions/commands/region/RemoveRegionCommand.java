package nl.a_n_07.adaFactions.commands.region;

import nl.a_n_07.adaFactions.managers.RegionManager;
import org.bukkit.command.CommandSender;

public class RemoveRegionCommand {
    private RegionManager regionManager;

    public RemoveRegionCommand(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

        public boolean execute(CommandSender sender, String [] args) {
        try {
            regionManager.removeRegion(args[0]);
            sender.sendMessage("Region " + args[0] + " removed");
        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage("Usage: /removeregion <region name>");
        } catch (IllegalArgumentException e) {
            sender.sendMessage(e.getMessage());
        }
        return true;
    }
}
