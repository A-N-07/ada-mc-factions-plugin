package nl.a_n_07.adaFactions.managers;

import nl.a_n_07.adaFactions.models.Region;
import nl.a_n_07.adaFactions.repositories.RegionRepository;
import org.bukkit.block.Block;
import java.util.List;

public class RegionManager {
    private final List<Region> regions;
    private final RegionRepository regionRepository;

    public RegionManager(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
        regions = this.regionRepository.loadAll();
    }

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
        if (regionExists(region.getName())){
            throw new IllegalArgumentException("The region named " + region.getName() + " already exists!");
        }

        Region overlappingRegion = findOverlappingRegion(region);
        if (overlappingRegion != null) {
            throw new IllegalArgumentException("New Region named: " + region.getName() + " overlaps with existing region named: " + overlappingRegion.getName());
        }

        regions.add(region);
        regionRepository.save(region);
    }

    public void removeRegion(String name) {
        Region region = findRegionByName(name);
        regions.remove(region);
        regionRepository.delete(name);

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

    public boolean regionExists(String name) {
        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public List<Region> getRegions() {
        return regions;
    }
}
