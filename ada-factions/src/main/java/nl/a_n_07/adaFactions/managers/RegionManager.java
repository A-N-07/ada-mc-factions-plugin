package nl.a_n_07.adaFactions.managers;

import nl.a_n_07.adaFactions.models.Region;
import nl.a_n_07.adaFactions.repositories.RegionRepository;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import java.util.List;

public class RegionManager {
    private List<Region> regions;
    private final RegionRepository regionRepository;

    public RegionManager(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
        regions = this.regionRepository.loadAll();
    }

    public Region getRegionAt(Block block) {
        for (Region region : regions) {
            if (region.contains(block)) {
                return region;
            }
        }
        return null;
    }

    public Region findOverlappingRegionByRegion(Region newRegion) {
        for (Region region : regions) {
            if (region.overlaps(newRegion)) return region;
        }
        return null;
    }

    public void addRegion(Region region) {
        if (regionExists(region.getName())){
            throw new IllegalArgumentException("The region named " + region.getName() + " already exists!");
        }

        Region overlappingRegion = findOverlappingRegionByRegion(region);
        if (overlappingRegion != null) {
            throw new IllegalArgumentException("New Region named: " + region.getName() + " overlaps with existing region named: " + overlappingRegion.getName());
        }

        regions.add(region);
        regionRepository.save(region);
    }

    public void removeRegion(String name) {
        Region region = getRegionByName(name);
        regions.remove(region);
        regionRepository.delete(name);

    }

    public Region getRegionByName(String name) {
        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(name)) return region;
        }
        throw new IllegalArgumentException("No region with name: " + name + " found");
    }

    public Region getRegionByFactionName(String factionName) {
        for (Region region : regions) {
            if (factionName.equalsIgnoreCase(region.getFactionName())) return region;
        }
        throw new IllegalArgumentException("No region with faction name: " + factionName + " found");
    }

    public void editRegionName(String oldName, String newName) {
        Region region = getRegionByName(oldName);
        region.setName(newName);
    }

    public void updateRegion(Region region) {
        regionRepository.update(region);
    }

    public boolean regionExists(String name) {
        for (Region region : regions) {
            if (region.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public Location getSpawnInRegion(Region region, World world) {
        int x = (region.getMinX() + region.getMaxX()) / 2;
        int z = (region.getMinZ() + region.getMaxZ()) / 2;
        int y = world.getHighestBlockYAt(x, z) + 1;
        return new Location(world, x + 0.5, y, z + 0.5);
    }

    public List<Region> getRegions() {
        return regions;
    }
    public void setRegions(List<Region> regions) { this.regions = regions;}
}
