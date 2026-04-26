package nl.a_n_07.adaFactions.managers;

import nl.a_n_07.adaFactions.repositories.LobbyRepository;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;

import java.util.Map;

public class LobbyManager {

    private final LobbyRepository lobbyRepository;
    private Location lobbyLocation;
    private int savedSpawnRadius;

    public LobbyManager(LobbyRepository lobbyRepository, Server server) {
        this.lobbyRepository = lobbyRepository;
        this.lobbyLocation = lobbyRepository.getLobbyLocation(server);
    }

    public void setLobby(Location location) {
        if (hasLobby()) unsetLobby();
        savedSpawnRadius = location.getWorld().getGameRuleValue(GameRule.SPAWN_RADIUS);
        this.lobbyLocation = location;
        lobbyRepository.saveLobby(location);
    }

    public void unsetLobby() {
        if (lobbyLocation != null){
            lobbyLocation.getWorld().setGameRule(GameRule.SPAWN_RADIUS, savedSpawnRadius);
        }
        this.lobbyLocation = null;
        lobbyRepository.clearLobby();
    }

    public Location getLobbyLocation() {
        return lobbyLocation;
    }

    public boolean hasLobby() {
        return lobbyLocation != null;
    }
}