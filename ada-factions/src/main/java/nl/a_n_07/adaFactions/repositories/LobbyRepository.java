package nl.a_n_07.adaFactions.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import java.io.*;

public class LobbyRepository {

    private final File file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public LobbyRepository(File dataFolder) {
        this.file = new File(dataFolder, "lobby.json");
    }

    public Location getLobbyLocation(Server server) {
        try (Reader reader = new FileReader(file)) {
            JsonObject obj = gson.fromJson(reader, JsonObject.class);
            if (obj == null || !obj.has("world")) return null;

            World world = server.getWorld(obj.get("world").getAsString());
            if (world == null) return null;

            double x = obj.get("x").getAsDouble();
            double y = obj.get("y").getAsDouble();
            double z = obj.get("z").getAsDouble();

            return new Location(world, x, y, z);
        } catch (IOException e) {
            return null;
        }
    }

    public void saveLobby(Location location) {
        JsonObject obj = new JsonObject();
        obj.addProperty("world", location.getWorld().getName());
        obj.addProperty("x", location.getX());
        obj.addProperty("y", location.getY());
        obj.addProperty("z", location.getZ());

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(obj, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearLobby() {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(new JsonObject(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}