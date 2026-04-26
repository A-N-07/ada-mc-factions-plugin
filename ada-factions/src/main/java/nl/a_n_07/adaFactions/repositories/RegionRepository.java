package nl.a_n_07.adaFactions.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import nl.a_n_07.adaFactions.models.Region;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegionRepository {

    private final File file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public RegionRepository(File dataFolder) {
        this.file = new File(dataFolder, "regions.json");
    }

    public List<Region> loadAll() {
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Region>>(){}.getType();
            List<Region> regions = gson.fromJson(reader, listType);
            return regions != null ? regions : new ArrayList<>();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load regions.json", e);
        }
    }

    public void save(Region region) {
        List<Region> regions = loadAll();
        regions.add(region);
        writeToFile(regions);
    }

    public void update(Region region) {
        List<Region> regions = loadAll();
        for (int i = 0; i < regions.size(); i++) {
            if (regions.get(i).getName().equals(region.getName())) {
                regions.set(i, region);
                break;
            }
        }
        writeToFile(regions);
    }

    public void delete(String name) {
        List<Region> regions = loadAll();
        regions.removeIf(r -> r.getName().equals(name));
        writeToFile(regions);
    }

    private void writeToFile(List<Region> regions) {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(regions, writer);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write to regions.json", e);
        }
    }
}