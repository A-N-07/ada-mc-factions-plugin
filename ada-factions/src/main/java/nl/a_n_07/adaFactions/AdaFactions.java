package nl.a_n_07.adaFactions;

import nl.a_n_07.adaFactions.commands.*;
import nl.a_n_07.adaFactions.listeners.BlockBreakListener;
import nl.a_n_07.adaFactions.managers.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdaFactions extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);

        // Managers
        RegionManager regionManager = new RegionManager();

        // Listeners
        getServer().getPluginManager().registerEvents(new BlockBreakListener(regionManager),this);

        // Commands
        getCommand("addregion").setExecutor(new AddRegionCommand(regionManager));
        getCommand("removeregion").setExecutor(new RemoveRegionCommand(regionManager));
        getCommand("editregionname").setExecutor(new EditRegionNameCommand(regionManager));
        getCommand("deleteregion").setExecutor(new DeleteRegionCommand());
        getCommand("showregions").setExecutor(new ShowRegionsCommand(regionManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Welcome to the server!!!!");
    }
}