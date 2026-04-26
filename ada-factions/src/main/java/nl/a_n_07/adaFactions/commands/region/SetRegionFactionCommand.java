package nl.a_n_07.adaFactions.commands.region;

import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.models.Faction;
import nl.a_n_07.adaFactions.models.Region;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class SetRegionFactionCommand {
    RegionManager regionManager;
    FactionManager factionManager;

    public SetRegionFactionCommand(RegionManager regionManager, FactionManager factionManager) {
        this.regionManager = regionManager;
        this.factionManager = factionManager;
    }

    public boolean execute(CommandSender sender, String[] args) {
        try{
            Region region = regionManager.getRegionByName(args[0]);
            Faction faction = factionManager.getFactionByName(args[1]);
            region.setFactionName(faction.getName());
            regionManager.updateRegion(region);
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }


        return true;
    }
}
