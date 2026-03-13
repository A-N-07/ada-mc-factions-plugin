package nl.a_n_07.adaFactions.commands.region;

import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.models.Region;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class  ListRegionsCommand {
    private RegionManager regionManager;

    public ListRegionsCommand(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    public boolean execute(CommandSender sender, String[] args) {
        List<Region> regions = regionManager.getRegions();
        for (Region region : regions) {
            sender.sendMessage(region.getName()+ " coords: " + region.getMinX()+ " " + region.getMaxX() + " " + region.getMinY()+ " " + region.getMaxY()  + " " + region.getMinZ()+ " " + region.getMaxZ());
        }
        return true;
    }
}
