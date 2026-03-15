package nl.a_n_07.adaFactions.commands.faction;

import nl.a_n_07.adaFactions.managers.FactionManager;
import org.bukkit.command.CommandSender;

public class EditNameFactionCommand {
    private FactionManager factionManager;

    public EditNameFactionCommand(FactionManager factionManager) {
        this.factionManager = factionManager;
    }

    public boolean execute(CommandSender sender, String[] args) {
        try {
            factionManager.editFactionName(args[0], args[1]);
            sender.sendMessage("Edited faction name from: " + args[0] + " to " + args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage("Usage: /faction editname <old name> <new name>");
        } catch (IllegalArgumentException e) {
            sender.sendMessage(e.getMessage());
        }
        return true;
    }
}
