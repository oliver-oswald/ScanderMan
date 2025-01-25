package ch.ollixd123.scanderman.entity;

import ch.ollixd123.scanderman.ScanderMan;
import ch.ollixd123.scanderman.entity.custom.ScanderManEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntity {

    public static final EntityType<ScanderManEntity> SCANDERMAN = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(ScanderMan.MOD_ID, "scanderman"),
            EntityType.Builder.create(ScanderManEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.6F, 2.9F)
                    .eyeHeight(2.55F)
                    .passengerAttachments(2.80625F)
                    .build());

    public static void registerModEntities() {
        ScanderMan.LOGGER.info("Registering Mod Entity for " + ScanderMan.MOD_ID);
    }
}
