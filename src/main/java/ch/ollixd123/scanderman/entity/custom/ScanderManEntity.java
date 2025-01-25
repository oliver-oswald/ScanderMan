package ch.ollixd123.scanderman.entity.custom;

import ch.ollixd123.scanderman.entity.ModEntity;
import com.google.common.base.Predicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class ScanderManEntity extends EndermanEntity implements Predicate<LivingEntity> {
    public ScanderManEntity(EntityType<? extends EndermanEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();

        this.goalSelector.clear(p -> true);
        this.targetSelector.clear(p -> true);

        this.goalSelector.add(0, new FollowPlayerGoal(this, 3D, 16.0F, 0.5F));
        this.goalSelector.add(1, new ThrowDirtBlockAtPlayerGoal(this, 2D, 2D));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 200.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3F)
                .add(EntityAttributes.FOLLOW_RANGE, 64.0)
                .add(EntityAttributes.STEP_HEIGHT, 1.0);
    }

    @Override
    public boolean isAngry() {
        // Never gets angry at players
        return false;
    }

    @Override
    public boolean isProvoked() {
        // Disable anger from being looked at
        return false;
    }

    @Override
    public void tickMovement() {
        // Skip any default anger behavior in the parent class
        this.setAngerTime(0);
        super.tickMovement();
    }

    @Override
    public boolean apply(LivingEntity input) {
        return false;
    }

    @Override
    public boolean test(LivingEntity input) {
        return Predicate.super.test(input);
    }

    static class FollowPlayerGoal extends Goal {
        private final EndermanEntity enderman;
        private PlayerEntity targetPlayer;
        private final double speed;
        private final float maxDistance;
        private final float minDistance;

        public FollowPlayerGoal(EndermanEntity enderman, double speed, float maxDistance, float minDistance) {
            this.enderman = enderman;
            this.speed = speed;
            this.maxDistance = maxDistance;
            this.minDistance = minDistance;
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            // Find the nearest player within range
            this.targetPlayer = this.enderman.getWorld().getClosestPlayer(this.enderman, maxDistance);
            return this.targetPlayer != null && this.targetPlayer.squaredDistanceTo(this.enderman) > minDistance * minDistance;
        }

        @Override
        public boolean shouldContinue() {
            return this.targetPlayer != null && this.targetPlayer.squaredDistanceTo(this.enderman) > minDistance * minDistance;
        }

        @Override
        public void start() {
            // Start following the player
            this.enderman.getNavigation().startMovingTo(this.targetPlayer, this.speed);
        }

        @Override
        public void stop() {
            this.targetPlayer = null;
            this.enderman.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (this.targetPlayer != null) {
                this.enderman.getLookControl().lookAt(this.targetPlayer);
                this.enderman.getNavigation().startMovingTo(this.targetPlayer, this.speed);
            }
        }
    }

    static class ThrowDirtBlockAtPlayerGoal extends Goal {
        private final EndermanEntity enderman;
        private PlayerEntity targetPlayer;
        private final double throwRange;
        private final double cooldown;
        private long lastThrowTime;

        public ThrowDirtBlockAtPlayerGoal(EndermanEntity enderman, double throwRange, double cooldown) {
            this.enderman = enderman;
            this.throwRange = throwRange;
            this.cooldown = cooldown * 1000; // Convert cooldown to milliseconds
            this.setControls(EnumSet.of(Control.LOOK));
        }

        @Override
        public boolean canStart() {
            this.targetPlayer = this.enderman.getWorld().getClosestPlayer(this.enderman, throwRange);
            return this.targetPlayer != null && this.enderman.squaredDistanceTo(this.targetPlayer) <= throwRange * throwRange;
        }

        @Override
        public boolean shouldContinue() {
            return false; // Only throw once per activation
        }

        @Override
        public void start() {
            if (this.targetPlayer != null && canThrow()) {
                Vec3d direction = this.targetPlayer.getPos().subtract(this.enderman.getPos()).normalize();
                ServerWorld serverWorld = (ServerWorld) this.enderman.getWorld();

                // Simulate throwing a dirt block (you can customize the item here)
                serverWorld.spawnEntity(new net.minecraft.entity.ItemEntity(
                        serverWorld,
                        this.enderman.getX(),
                        this.enderman.getY() + 1.5,
                        this.enderman.getZ(),
                        new net.minecraft.item.ItemStack(Items.DIRT)
                ));

                this.lastThrowTime = System.currentTimeMillis();
            }
        }

        private boolean canThrow() {
            return System.currentTimeMillis() - lastThrowTime >= cooldown;
        }
    }
}
