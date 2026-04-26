package nl.a_n_07.adaFactions.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import nl.a_n_07.adaFactions.models.Faction;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FactionRepository {

    private final File file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public FactionRepository(File dataFolder) {
        this.file = new File(dataFolder, "factions.json");
    }

    public List<Faction> loadAll() {
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Faction>>(){}.getType();
            List<Faction> factions = gson.fromJson(reader, listType);
            return factions != null ? factions : new ArrayList<>();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load factions.json", e);
        }
    }

    public void save(Faction faction) {
        List<Faction> factions = loadAll();
        factions.add(faction);
        writeToFile(factions);
    }

    public void update(Faction faction) {
        List<Faction> factions = loadAll();
        for (int i = 0; i < factions.size(); i++) {
            if (factions.get(i).getName().equals(faction.getName())) {
                factions.set(i, faction);
                break;
            }
        }
        writeToFile(factions);
    }

    public void delete(String name) {
        List<Faction> factions = loadAll();
        factions.removeIf(f -> f.getName().equals(name));
        writeToFile(factions);
    }

    private void writeToFile(List<Faction> factions) {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(factions, writer);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write to factions.json", e);
        }
    }
}