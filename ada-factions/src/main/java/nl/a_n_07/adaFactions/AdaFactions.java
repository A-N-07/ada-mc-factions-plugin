package nl.a_n_07.adaFactions;

import nl.a_n_07.adaFactions.commands.faction.FactionCommand;
import nl.a_n_07.adaFactions.commands.lobby.LobbyCommand;
import nl.a_n_07.adaFactions.commands.player.PlayerCommand;
import nl.a_n_07.adaFactions.commands.region.*;
import nl.a_n_07.adaFactions.listeners.BlockBreakListener;
import nl.a_n_07.adaFactions.listeners.BlockPlaceListener;
import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.managers.PlayerManager;
import nl.a_n_07.adaFactions.managers.RegionManager;
import nl.a_n_07.adaFactions.managers.LobbyManager;
import nl.a_n_07.adaFactions.models.AdaPlayer;
import nl.a_n_07.adaFactions.repositories.FactionRepository;
import nl.a_n_07.adaFactions.repositories.PlayerRepository;
import nl.a_n_07.adaFactions.repositories.RegionRepository;
import nl.a_n_07.adaFactions.repositories.LobbyRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.Objects;

public final class AdaFactions extends JavaPlugin implements Listener {

    private PlayerManager playerManager;
    private LobbyManager lobbyManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Copy default files
        copyDefaultFile("factions.json");
        copyDefaultFile("regions.json");
        copyDefaultFile("players.json");
        copyDefaultFile("spawns.json");

        // Repositories
        PlayerRepository playerRepository = new PlayerRepository(getDataFolder());
        FactionRepository factionRepository = new FactionRepository(getDataFolder());
        RegionRepository regionRepository = new RegionRepository(getDataFolder());
        LobbyRepository lobbyRepository = new LobbyRepository(getDataFolder());

        // Managers
        RegionManager regionManager = new RegionManager(regionRepository);
        FactionManager factionManager = new FactionManager(factionRepository);
        playerManager = new PlayerManager(playerRepository);
        lobbyManager = new LobbyManager(lobbyRepository, getServer());

        // Listeners
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(regionManager, playerManager, factionManager),this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(regionManager, playerManager, factionManager),this);

        // Commands
        Objects.requireNonNull(getCommand("region")).setExecutor(new RegionCommand(regionManager, factionManager));
        Objects.requireNonNull(getCommand("faction")).setExecutor(new FactionCommand(factionManager));
        Objects.requireNonNull(getCommand("player")).setExecutor(new PlayerCommand(playerManager, factionManager, regionManager));
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new LobbyCommand(lobbyManager));
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

            if (lobbyManager.hasLobby()) {
                player.teleport(lobbyManager.getLobbyLocation());
            }
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