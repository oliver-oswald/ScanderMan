package ollixd123.scander.man.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;
import ollixd123.scander.man.ScanderMan;

@Mixin(SpawnRestriction.class)
public class ExampleMixin {
	@Shadow
	private static <T extends MobEntity> void register(EntityType<T> type, SpawnRestriction.Location location,
		Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {

		}

		static {
			register(ScanderMan.SCANDERMAN, SpawnRestriction.Location.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
		}
}
