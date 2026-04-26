package nl.a_n_07.adaFactions.models;

import org.bukkit.block.Block;

public class Region {
    private String name;
    private int minX, minY, minZ;
    private int maxX, maxY, maxZ;
    private boolean lobby = false;
    private String factionName;

    public Region(String name, int x1, int x2, int y1, int y2, int z1, int z2) {
        this.name = name;
        this.minX = Math.min(x1, x2);
        this.maxX = Math.max(x1, x2);
        this.minY = Math.min(y1, y2);
        this.maxY = Math.max(y1, y2);
        this.minZ = Math.min(z1, z2);
        this.maxZ = Math.max(z1, z2);

        if (minX < 0) minX--;
        if (maxX < 0) maxX--;
        if (minY < 0) minY--;
        if (maxY < 0) maxY--;
        if (minZ < 0) minZ--;
        if (maxZ < 0) maxZ--;
    }

    public boolean contains(Block block) {
        return block.getX() >= minX && block.getX() <= maxX
            && block.getY() >= minY && block.getY() <= maxY
            && block.getZ() >= minZ && block.getZ() <= maxZ;
    }

    public boolean overlaps(Region other) {
        return minX < other.maxX && maxX > other.minX
                && minY < other.maxY && maxY > other.minY
                && minZ < other.maxZ && maxZ > other.minZ;
    }

    public String getName() {return name; }

    public void setName(String name) { this.name = name; }

    public boolean isLobby() {return lobby;}

    public void setLobby(boolean lobby) { this.lobby = lobby; }

    public int getMinX() {return minX;}

    public void setMinX(int minX) {this.minX = minX;}

    public int getMinY() {return minY;}

    public void setMinY(int minY) {this.minY = minY;}

    public int getMinZ() {return minZ;}

    public void setMinZ(int minZ) {this.minZ = minZ;}

    public int getMaxX() {return maxX;}

    public void setMaxX(int maxX) {this.maxX = maxX;}

    public int getMaxY() {return maxY;}

    public void setMaxY(int maxY) {this.maxY = maxY;}

    public int getMaxZ() {return maxZ;}

    public void setMaxZ(int maxZ) {this.maxZ = maxZ;}

    public String getFactionName() { return factionName; }

    public void setFactionName(String factionName) { this.factionName = factionName; }
}
