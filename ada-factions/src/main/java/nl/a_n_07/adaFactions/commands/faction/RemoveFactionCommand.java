package nl.a_n_07.adaFactions.commands.faction;

import nl.a_n_07.adaFactions.managers.FactionManager;
import org.bukkit.command.CommandSender;

public class RemoveFactionCommand {
    private FactionManager factionManager;

    public RemoveFactionCommand(FactionManager factionmanager) {
        this.factionManager = factionmanager;
    }

    public boolean execute(CommandSender sender, String[] args) {
        try {
            factionManager.removeFaction(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage("Usage: /factions remove <faction name>");
        } catch (IllegalArgumentException e) {
            sender.sendMessage(e.getMessage());
        }
        return true;
    }
}
