package nl.a_n_07.adaFactions.listeners;

import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.managers.PlayerManager;
import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.models.AdaPlayer;
import nl.a_n_07.adaFactions.models.Faction;
import nl.a_n_07.adaFactions.models.Region;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    private final RegionManager regionManager;
    private final PlayerManager playerManager;
    private final FactionManager factionManager;

    public BlockBreakListener(RegionManager regionManager, PlayerManager playerManager, FactionManager factionManager) {
        this.regionManager = regionManager;
        this.playerManager = playerManager;
        this.factionManager = factionManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        AdaPlayer adaPlayer = playerManager.getPlayer(player.getUniqueId());
        Block block = event.getBlock();
        Region region = regionManager.getRegionAt(block);

        if (region == null) return;

        String regionFactionName = region.getFactionName();

        if (regionFactionName == null) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Block break cancelled");
            return;
        }

        Faction regionFaction = factionManager.getFactionByName(regionFactionName);
        boolean isMember = regionFaction.getMembers().stream()
                .anyMatch(m -> m.getUuid().equals(adaPlayer.getUuid()));

        if (!isMember) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Block break cancelled");
        }
    }
}
