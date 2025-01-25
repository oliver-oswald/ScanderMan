package ch.ollixd123.scanderman.item;

import ch.ollixd123.scanderman.ScanderMan;
import ch.ollixd123.scanderman.entity.ModEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItem {

    public static final Item SCANDERMAN_SPAWN_EGG = registerItem("scanderman_spawn_egg",
            new SpawnEggItem(ModEntity.SCANDERMAN, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ScanderMan.MOD_ID, name), item);
    }


}
