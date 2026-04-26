package nl.a_n_07.adaFactions.models;

import java.util.ArrayList;
import java.util.List;

public class Faction {
    private String name;
    private List<AdaPlayer> members;

    public Faction(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addPlayer(AdaPlayer adaPlayer) {
        if (members == null) members = new ArrayList<>();
        members.add(adaPlayer);
    }

    public void removePlayer(AdaPlayer adaPlayer) {
        members.remove(adaPlayer);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdaPlayer> getMembers() {
        if (members == null) members = new ArrayList<>();
        return members;
    }
}
