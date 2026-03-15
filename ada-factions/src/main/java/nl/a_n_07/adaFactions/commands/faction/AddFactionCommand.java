package nl.a_n_07.adaFactions.commands.faction;

import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.models.Faction;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class AddFactionCommand {
    private FactionManager factionManager;

    public AddFactionCommand(FactionManager factionManager) { this.factionManager = factionManager; }

    public boolean execute(CommandSender sender, String[] args) {
        if (factionManager.factionExists(args[0])) {
            sender.sendMessage(ChatColor.RED + "Faction " + args[0] + " already exists.");
            return true;
        }

        try {
            factionManager.addFaction(new Faction(args[1]));
            sender.sendMessage("Faction " + args[0] + " successfully added.");
        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage("Usage: /faction add <name>");
        } catch (IllegalArgumentException e) {
            sender.sendMessage(e.getMessage());
        }
        return true;
    }
}
