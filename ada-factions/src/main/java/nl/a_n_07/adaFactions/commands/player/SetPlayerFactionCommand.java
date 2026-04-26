package nl.a_n_07.adaFactions.commands.player;

import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.managers.PlayerManager;
import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.models.AdaPlayer;
import nl.a_n_07.adaFactions.models.Faction;
import nl.a_n_07.adaFactions.models.Region;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPlayerFactionCommand{
    PlayerManager playerManager;
    FactionManager factionManager;
    RegionManager regionManager;

    public SetPlayerFactionCommand(PlayerManager playerManager, FactionManager factionManager, RegionManager regionManager) {
        this.playerManager = playerManager;
        this.factionManager = factionManager;
        this.regionManager = regionManager;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Usage: /player setfaction <name>");
            return true;
        }

        String factionName = args[0];
        Player player = (Player) sender;
        AdaPlayer adaPlayer = playerManager.getPlayer(player.getUniqueId());

        if (adaPlayer.getFactionName() != null) {
            sender.sendMessage(ChatColor.RED + "You have already chosen a faction!");
            return true;
        }

        try {
            Faction faction = factionManager.getFactionByName(factionName);
            Region region = regionManager.getRegionByFactionName(factionName);
            Location spawn = regionManager.getSpawnInRegion(region, player.getWorld());
            faction.addPlayer(adaPlayer);
            factionManager.updateFaction(faction);
            player.teleport(spawn);
            faction.addPlayer(adaPlayer);
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }
        return true;
    }
}
