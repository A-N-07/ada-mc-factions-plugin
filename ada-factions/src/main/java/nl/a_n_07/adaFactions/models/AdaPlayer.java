package nl.a_n_07.adaFactions.models;

import java.util.UUID;

public class AdaPlayer {
    private String name;
    private final UUID uuid;
    private String factionName;

    public AdaPlayer(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFactionName() { return factionName; }

    public void setFactionName(String factionName) { this.factionName = factionName; }
}
