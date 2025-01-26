package ch.ollixd123.scanderman.item;

import ch.ollixd123.scanderman.ScanderMan;
import ch.ollixd123.scanderman.entity.ModEntity;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItem {

    public static final Item SCANDERMAN_SPAWN_EGG = registerItem("scanderman_spawn_egg",
            new SpawnEggItem(ModEntity.SCANDERMAN, new Item.Settings().registryKey(RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(ScanderMan.MOD_ID, "scanderman_spawn_egg"))))
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ScanderMan.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ScanderMan.LOGGER.info("Registering Mod Items for " + ScanderMan.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(SCANDERMAN_SPAWN_EGG);
        });
    }

}
