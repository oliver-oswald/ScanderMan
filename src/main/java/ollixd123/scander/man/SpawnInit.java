package ollixd123.scander.man;

import java.util.function.Predicate;

import com.google.common.base.Preconditions;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;

public class SpawnInit {

    public static int spawnRate = 100;

    public static void addSpawn(Predicate<BiomeSelectionContext> biomselector, SpawnGroup spawnGroup, SpawnSettings.SpawnEntry se){
        Preconditions.checkArgument(se.type.getSpawnGroup() != SpawnGroup.MISC, "");

        Identifier id = Registry.ENTITY_TYPE.getId(se.type);
        Preconditions.checkState(id != Registry.ENTITY_TYPE.getDefaultId(), "");

        BiomeModifications.create(id).add(ModificationPhase.ADDITIONS, biomselector, context -> {
            context.getSpawnSettings().addSpawn(spawnGroup, se);
        });

    }

    private static void normalspawn(){
        Predicate<BiomeSelectionContext> biomeselector = (context) -> {
            Biome.Category category = context.getBiome().getCategory();
            return category != null;
        };

        addSpawn(biomeselector, ScanderMan.SCANDERMAN.getSpawnGroup(), 
            new SpawnSettings.SpawnEntry(ScanderMan.SCANDERMAN, spawnRate, 200, 500));
    }

    public static void init() {
        normalspawn();

    }

}
