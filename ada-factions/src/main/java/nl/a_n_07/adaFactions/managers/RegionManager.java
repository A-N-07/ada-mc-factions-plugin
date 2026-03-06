package nl.a_n_07.adaFactions.managers;

import nl.a_n_07.adaFactions.models.Region;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class RegionManager {
    private List<Region> regions = new ArrayList<>();

    public boolean findOverlappingRegion(Block block) {
        for (Region region : regions) {
            if (region.contains(block)) return true;
        }
        return false;
    }

    public Region findOverlappingRegion(Region newRegion) {
        for (Region region : regions) {
            if (region.overlaps(newRegion)) return region;
        }
        return null;
    }

    public void addRegion(Region region) {
        Region overlappingRegion = findOverlappingRegion(region);
        if (overlappingRegion == null) {
            regions.add(region);
        } else {
            throw new IllegalArgumentException("New Region named: " + region.getName() + "overlaps with existing region named: " + overlappingRegion.getName());
        }
    }

    public void removeRegion(String name) {
        regions.remove(findRegionByName(name));
    }

    public Region findRegionByName(String name) {
        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(name)) return region;
        }
        throw new IllegalArgumentException("No region with name: " + name + " found");
    }

    public void editRegionName(String oldName, String newName) {
        Region region = findRegionByName(oldName);
        region.setName(newName);
    }

    public List<Region> getRegions() { return regions; }
}
