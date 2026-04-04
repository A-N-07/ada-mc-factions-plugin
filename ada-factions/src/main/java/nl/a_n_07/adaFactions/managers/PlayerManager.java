package nl.a_n_07.adaFactions.managers;

import nl.a_n_07.adaFactions.models.AdaPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {
    private List<AdaPlayer> adaPlayers = new ArrayList<>();

    public void addPlayer(AdaPlayer adaPlayer) {
        if (playerExists(adaPlayer.getUuid())) return;
        adaPlayers.add(adaPlayer);
    }

    public boolean playerExists(UUID uuid) {
        for (AdaPlayer adaPlayer : adaPlayers) {
            if (adaPlayer.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public AdaPlayer getPlayer(UUID uuid) {
        for (AdaPlayer adaPlayer : adaPlayers) {
            if (adaPlayer.getUuid().equals(uuid)) {
                return adaPlayer;
            }
        }
        throw new IllegalStateException("Player " + uuid + " not found — they should have been registered on join");
    }
}
