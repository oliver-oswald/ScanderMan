package ch.ollixd123.scanderman;

import ch.ollixd123.scanderman.entity.ModEntity;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScanderMan implements ModInitializer {
	public static final String MOD_ID = "scander-man";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntity.registerModEntities();
	}
}