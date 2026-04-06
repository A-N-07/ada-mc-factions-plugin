package nl.a_n_07.adaFactions.commands.region;

import nl.a_n_07.adaFactions.managers.RegionManager;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class EditRegionNameCommand {
    private RegionManager regionManager;

    public EditRegionNameCommand(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    public boolean execute(@NotNull CommandSender sender, String[] args) {
        try {
            regionManager.editRegionName(args[0], args[1]);
            sender.sendMessage("Edited region name from: " + args[0] + " to " + args[1]);
        } catch(ArrayIndexOutOfBoundsException e){
            sender.sendMessage("Usage: /region editname <old name> <new name>");
        } catch (IllegalArgumentException e){
            sender.sendMessage(e.getMessage());
        }
        return true;
    }
}
