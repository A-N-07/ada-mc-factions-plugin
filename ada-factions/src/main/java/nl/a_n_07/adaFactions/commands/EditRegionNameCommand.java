package nl.a_n_07.adaFactions.commands;

import nl.a_n_07.adaFactions.managers.RegionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class EditRegionNameCommand implements CommandExecutor {
    private RegionManager regionManager;

    public EditRegionNameCommand(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        try{
            regionManager.editRegionName(args[0], args[1]);
            sender.sendMessage("Edited region name from: " + args[0] + " to " + args[1]);
        } catch(ArrayIndexOutOfBoundsException e){
            sender.sendMessage("Usage: /editregionname <name>");
        }
        return true;
    }
}
