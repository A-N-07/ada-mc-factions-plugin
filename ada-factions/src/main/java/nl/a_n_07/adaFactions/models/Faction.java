package nl.a_n_07.adaFactions.models;

import java.util.List;

public class Faction {
    private String name;
    private List<AdaPlayer> members;

    public void addPlayer(AdaPlayer adaPlayer) {
        members.add(adaPlayer);
    }

    public void removePlayer(AdaPlayer adaPlayer) {
        members.remove(adaPlayer);
    }

    public Faction(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdaPlayer> getMembers() {
        return members;
    }
}
