package ch.ollixd123.scanderman;

import ch.ollixd123.scanderman.entity.ModEntity;
import ch.ollixd123.scanderman.entity.custom.ScanderManEntity;
import ch.ollixd123.scanderman.item.ModItem;
import ch.ollixd123.scanderman.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScanderMan implements ModInitializer {
	public static final String MOD_ID = "scander-man";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntity.registerModEntities();
		ModItem.registerModItems();
		ModWorldGeneration.generateModWorldGeneration();
		FabricDefaultAttributeRegistry.register(ModEntity.SCANDERMAN, ScanderManEntity.createAttributes());
	}
}