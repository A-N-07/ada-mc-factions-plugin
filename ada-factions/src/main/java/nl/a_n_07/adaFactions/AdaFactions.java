package nl.a_n_07.adaFactions;

import nl.a_n_07.adaFactions.commands.faction.FactionCommand;
import nl.a_n_07.adaFactions.commands.region.*;
import nl.a_n_07.adaFactions.listeners.BlockBreakListener;
import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.managers.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AdaFactions extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        copyDefaultFile("factions.json");
        copyDefaultFile("regions.json");
        copyDefaultFile("players.json");

        // Managers
        RegionManager regionManager = new RegionManager();
        FactionManager factionManager = new FactionManager();

        // Listeners
        getServer().getPluginManager().registerEvents(new BlockBreakListener(regionManager),this);

        // Commands
        getCommand("region").setExecutor(new RegionCommand(regionManager));
        getCommand("faction").setExecutor(new FactionCommand(factionManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Welcome to the server!!!!");
    }

    private void copyDefaultFile(String filename) {
        File file = new File(getDataFolder(), filename);
        if (!file.exists()) {
            saveResource(filename,false); // `saveResource()` is a built-in Bukkit method that copies the file from inside the jar to `plugins/AdaFactions/` — but only if it doesn't already exist, so it won't overwrite data on restart.
        }
    }
}