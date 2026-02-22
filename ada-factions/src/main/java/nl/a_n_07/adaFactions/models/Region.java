package nl.a_n_07.adaFactions.models;

import org.bukkit.block.Block;

public class Region {
    private String name;
    private int minX, minY, minZ;
    private int maxX, maxY, maxZ;

    public Region(String name, int x1, int x2, int y1, int y2, int z1, int z2) {
        this.name = name;
        this.minX = Math.min(x1, x2);
        this.maxX = Math.max(x1, x2);
        this.minY = Math.min(y1, y2);
        this.maxY = Math.max(y1, y2);
        this.minZ = Math.min(z1, z2);
        this.maxZ = Math.max(z1, z2);
    }

    public boolean contains(Block block) {
        return block.getX() >= minX && block.getX() <= maxX
            && block.getY() >= minY && block.getY() <= maxY
            && block.getZ() >= minZ && block.getZ() <= maxZ;
    }

    public String getName() {return name; }

    public void editName(String newName) {this.name = newName;}
}
