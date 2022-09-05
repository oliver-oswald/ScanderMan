package ollixd123.scander.man;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ollixd123.scander.man.entity.CubeEntity;
import ollixd123.scander.man.entity.ScandermanEntity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScanderMan implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("scanderman");

	public static final Identifier MY_SOUND_ID1 = new Identifier("scanderman:scanderman_damage");
    public static SoundEvent SCANDERMAN_DAMAGE = new SoundEvent(MY_SOUND_ID1);

	public static final Identifier MY_SOUND_ID2 = new Identifier("scanderman:scanderman_death");
    public static SoundEvent SCANDERMAN_DEATH = new SoundEvent(MY_SOUND_ID2);

	public static final EntityType<ScandermanEntity> SCANDERMAN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("scanderman", "scanderman"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ScandermanEntity::new).dimensions(EntityDimensions.fixed(0.6f, 2.9f)).build()
    );

	public static final EntityType<CubeEntity> CUBE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("scanderman", "cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 75f)).build()
    );

	public static final Item CUBE_SPAWN_EGG = new SpawnEggItem(CUBE, 16711680, 0, new Item.Settings().group(ItemGroup.MISC));

	public static final Item SCANDERMAN_SPAWN_EGG = new SpawnEggItem(SCANDERMAN, 16711935, 0, new Item.Settings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.createMobAttributes());

		FabricDefaultAttributeRegistry.register(SCANDERMAN, ScandermanEntity.createMobAttributes());

		Registry.register(Registry.ITEM, new Identifier("scanderman","cube_spawn_egg"), CUBE_SPAWN_EGG);

		Registry.register(Registry.ITEM, new Identifier("scanderman","scanderman_spawn_egg"), SCANDERMAN_SPAWN_EGG);

		SpawnInit.init();

		
	}
}
