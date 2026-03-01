package nl.a_n_07.adaFactions.commands;

import nl.a_n_07.adaFactions.managers.RegionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RemoveRegionCommand implements CommandExecutor {
    private RegionManager regionManager;

    public RemoveRegionCommand(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
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
