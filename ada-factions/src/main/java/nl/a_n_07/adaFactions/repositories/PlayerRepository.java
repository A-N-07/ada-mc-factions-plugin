package nl.a_n_07.adaFactions.repositories;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import nl.a_n_07.adaFactions.models.AdaPlayer;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerRepository {

    private final File file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public PlayerRepository(File dataFolder) {
        this.file = new File(dataFolder, "players.json");
    }

    public List<AdaPlayer> loadAll() {
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<AdaPlayer>>(){}.getType();
            List<AdaPlayer> players = gson.fromJson(reader, listType);
            return players != null ? players : new ArrayList<>();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load players.json", e);
        }
    }

    public void save(AdaPlayer player) {
        List<AdaPlayer> players = loadAll();
        players.add(player);
        writeToFile(players);
    }

    public void update(AdaPlayer player) {
        List<AdaPlayer> players = loadAll();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUuid().equals(player.getUuid())) {
                players.set(i, player);
                break;
            }
        }
        writeToFile(players);
    }

    public void delete(AdaPlayer player) {
        List<AdaPlayer> players = loadAll();
        UUID uuid = player.getUuid();
        players.removeIf(p -> p.getUuid().equals(uuid));
        writeToFile(players);
    }

    private void writeToFile(List<AdaPlayer> players) {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(players, writer);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write to players.json", e);
        }
    }
}