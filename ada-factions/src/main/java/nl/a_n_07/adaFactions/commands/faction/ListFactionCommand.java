package nl.a_n_07.adaFactions.commands.faction;

import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.models.Faction;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ListFactionCommand {
    private FactionManager factionManager;

    public ListFactionCommand(FactionManager factionManager) {
        this.factionManager = factionManager;
    }

    public boolean execute(CommandSender sender, String[] args) {
        List <Faction> factions = factionManager.getFactions();
        for (Faction faction : factions) {
            sender.sendMessage("Name: " + faction.getName());
        }
        return true;
    }
}
