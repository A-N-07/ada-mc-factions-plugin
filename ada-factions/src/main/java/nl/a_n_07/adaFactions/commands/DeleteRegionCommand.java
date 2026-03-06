package nl.a_n_07.adaFactions.commands;

import nl.a_n_07.adaFactions.managers.RegionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DeleteRegionCommand implements CommandExecutor {
    private RegionManager regionManager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        sender.sendMessage("Did you mean to delete the region: " + args[0] +  "?");
        sender.sendMessage("If so try: /removeregion <name> ");
        sender.sendMessage("This is the command that removes regions :)");
        return true;
    }
}
