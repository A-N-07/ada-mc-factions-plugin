package nl.a_n_07.adaFactions.managers;

import nl.a_n_07.adaFactions.models.AdaPlayer;
import nl.a_n_07.adaFactions.models.Faction;
import nl.a_n_07.adaFactions.repositories.FactionRepository;
import java.util.List;

public class FactionManager {
    private final List<Faction> factions;
    private final FactionRepository factionRepository;

    public FactionManager(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
        this.factions = factionRepository.loadAll();
    }

    public void addFaction(Faction faction) {
        factions.add(faction);
        factionRepository.save(faction);
    }

    public void removeFaction(String name) {
        Faction faction = getFactionByName(name);
        factions.remove(faction);
        factionRepository.delete(name);
    }

    public Faction getFactionByName(String name) {
        for (Faction faction : factions) {
            if (faction.getName().equals(name)) {
                return faction;
            }
        }
        throw new IllegalArgumentException("Faction with name " + name + " not found");
    }

    public void editFactionName(String oldName, String newName) {
        Faction faction = getFactionByName(oldName);
        faction.setName(newName);
    }

    public void updateFaction(Faction faction) {
        factionRepository.update(faction);
    }

    public boolean factionExists(String name) {
        for (Faction faction : factions) {
            if (faction.getName().equalsIgnoreCase(name)){ return true; }
        }
        return false;
    }

    public Faction getFactionByPlayer(AdaPlayer adaPlayer) {
        for (Faction faction : factions) {
            for (AdaPlayer member : faction.getMembers()) {
                if (member.getUuid().equals(adaPlayer.getUuid())) return faction;
            }
        }
        return null;
    }

    public List<Faction> getFactions() {
        return factions;
    }


}
