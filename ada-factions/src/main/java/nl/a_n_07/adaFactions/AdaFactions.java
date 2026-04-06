package nl.a_n_07.adaFactions;

import nl.a_n_07.adaFactions.commands.faction.FactionCommand;
import nl.a_n_07.adaFactions.commands.player.PlayerCommand;
import nl.a_n_07.adaFactions.commands.region.*;
import nl.a_n_07.adaFactions.listeners.BlockBreakListener;
import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.managers.PlayerManager;
import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.models.AdaPlayer;
import nl.a_n_07.adaFactions.repositories.FactionRepository;
import nl.a_n_07.adaFactions.repositories.PlayerRepository;
import nl.a_n_07.adaFactions.repositories.RegionRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public final class AdaFactions extends JavaPlugin implements Listener {

    private RegionManager regionManager;
    private FactionManager factionManager;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Copy default files
        copyDefaultFile("factions.json");
        copyDefaultFile("regions.json");
        copyDefaultFile("players.json");

        // Repositories
        PlayerRepository playerRepository = new PlayerRepository(getDataFolder());
        FactionRepository factionRepository = new FactionRepository(getDataFolder());
        RegionRepository regionRepository = new RegionRepository(getDataFolder());

        // Managers
        regionManager = new RegionManager(regionRepository);
        factionManager = new FactionManager(factionRepository);
        playerManager = new PlayerManager(playerRepository);

        // Listeners
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(regionManager),this);

        // Commands
        getCommand("region").setExecutor(new RegionCommand(regionManager));
        getCommand("faction").setExecutor(new FactionCommand(factionManager));
        getCommand("player").setExecutor(new PlayerCommand(playerManager, factionManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        AdaPlayer adaPlayer = new AdaPlayer(player.getName(), player.getUniqueId());

        if (!playerManager.playerExists(adaPlayer.getUuid())) {
            playerManager.addPlayer(adaPlayer);
        }

        player.sendMessage("Welcome to the server!!!!");
    }

    private void copyDefaultFile(String filename) {
        File file = new File(getDataFolder(), filename);
        if (!file.exists()) {
            saveResource(filename,false); // `saveResource()` is a built-in Bukkit method that copies the file from inside the jar to `plugins/AdaFactions/` — but only if it doesn't already exist, so it won't overwrite data on restart.
        }
    }
}