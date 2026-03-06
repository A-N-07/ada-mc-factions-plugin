package nl.a_n_07.adaFactions.commands;

import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.models.Region;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShowRegionsCommand implements CommandExecutor {
    private RegionManager regionManager;

    public ShowRegionsCommand(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        List<Region> regions = regionManager.getRegions();
        for (Region region : regions) {
            sender.sendMessage(region.getName()+ " coords: " + region.getMinX()+ " " + region.getMaxX() + " " + region.getMinY()+ " " + region.getMaxY()  + " " + region.getMinZ()+ " " + region.getMaxZ());
        }
        return true;
    }
}
