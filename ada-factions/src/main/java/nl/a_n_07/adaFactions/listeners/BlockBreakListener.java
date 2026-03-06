package nl.a_n_07.adaFactions.listeners;

import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.models.Region;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    private RegionManager regionManager;

    public BlockBreakListener(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = (Player) event.getPlayer();

        Block block = event.getBlock();
        player.sendMessage("Block coords: " + block.getX() + " " + block.getY() + " " + block.getZ());

        if (regionManager.findOverlappingRegion(block)) {
            player.sendMessage(ChatColor.RED + "Block break cancelled");
            event.setCancelled(true);
        }
    }
}
