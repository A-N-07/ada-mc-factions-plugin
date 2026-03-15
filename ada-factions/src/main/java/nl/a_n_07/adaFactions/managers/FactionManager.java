package nl.a_n_07.adaFactions.managers;

import nl.a_n_07.adaFactions.models.Faction;

import java.util.ArrayList;
import java.util.List;

public class FactionManager {
    private List<Faction> factions = new ArrayList<>();

    public void addFaction(Faction faction) {
        factions.add(faction);
    }

    public void removeFaction(String name) {
        Faction faction = findFactionByName(name);
        factions.remove(faction);
    }

    public Faction findFactionByName(String name) {
        for (Faction faction : factions) {
            if (faction.getName().equals(name)) {
                return faction;
            }
        }
        throw new IllegalArgumentException("Faction with name " + name + " not found");
    }

    public void editFactionName(String oldName, String newName) {
        Faction faction = findFactionByName(oldName);
        faction.setName(newName);
    }

    public boolean factionExists(String name) {
        for (Faction faction : factions) {
            if (faction.getName().equalsIgnoreCase(name));
            return true;
        }
        return false;
    }

    public List<Faction> getFactions() {
        return factions;
    }
}
