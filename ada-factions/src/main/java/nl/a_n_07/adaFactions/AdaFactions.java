package nl.a_n_07.adaFactions;

import nl.a_n_07.adaFactions.commands.AddRegionCommand;
import nl.a_n_07.adaFactions.commands.DeleteRegionCommand;
import nl.a_n_07.adaFactions.commands.EditRegionNameCommand;
import nl.a_n_07.adaFactions.commands.RemoveRegionCommand;
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
        

        // Commands
        getCommand("addregion").setExecutor(new AddRegionCommand(regionManager));
        getCommand("removeregion").setExecutor(new RemoveRegionCommand(regionManager));
        getCommand("editregionname").setExecutor(new EditRegionNameCommand(regionManager));
        getCommand("deleteregion").setExecutor(new DeleteRegionCommand());
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