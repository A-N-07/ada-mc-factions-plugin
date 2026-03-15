package nl.a_n_07.adaFactions.models;

import java.util.List;

public class Faction {
    private String name;
    private List<Player> members;

    public void addPlayer(Player player) {
        members.add(player);
    }

    public void removePlayer(Player player) {
        members.remove(player);
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

    public List<Player> getMembers() {
        return members;
    }
}
