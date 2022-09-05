package ollixd123.scander.man.entity;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScandermanEntity extends EndermanEntity implements Predicate<LivingEntity>{

    public boolean EndermanCanSpawn = true;
    public static final Logger LOGGER = LogManager.getLogger("Scanderman Entity");

    public ScandermanEntity(EntityType<? extends EndermanEntity> entityType, World world){
        super(entityType, world);
        this.experiencePoints = 200;
        this.forwardSpeed = 2f;
    }

    public static DefaultAttributeContainer.Builder createScandermanAttributes(){
        return HostileEntity.createHostileAttributes()
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 10.0D)
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0D)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 1500.0D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0D);
    }


    @Override
    public boolean canSpawn(WorldView view){
        return true;
    }

   @Override
   public boolean apply(LivingEntity arg0) {
      // TODO Auto-generated method stub
      return false;
   }

}
