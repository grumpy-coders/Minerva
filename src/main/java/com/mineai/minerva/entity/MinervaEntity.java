package com.mineai.minerva.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;


public class MinervaEntity extends PathfinderMob {

    public static final EntityDataAccessor<String> CUSTOM_NAME =
        SynchedEntityData.defineId(MinervaEntity.class, EntityDataSerializers.STRING);

    //BOILER PLATE ENTITY CONSTRUCTORS
    
    public MinervaEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.setNoAi(false);
        this.canBeLeashed();
        this.isLeashed();
        this.isPushable();
    }
    
    public MinervaEntity(Level level, double x, double y, double z) {
        this(ModEntities.MINERVA_ENTITY.get(), level);
        setPos(x, y, z);
        
    }
    
    public MinervaEntity(Level level, BlockPos blockPosition) {
    		this(level, blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }

    // === Register AI Attributes like Health, Speed ===
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.3D)
            .add(Attributes.ENTITY_INTERACTION_RANGE, 999.99D);
    }
    
    public static boolean canSpawn(EntityType<?> type, ServerLevelAccessor level, EntitySpawnReason entitySpawnReason, BlockPos pos, RandomSource random) {
    		//return level.getBlockState(pos.below()).getMaterial().isSolid() && !level.isNight();
    		return true; //TODO: if we want to have conditions around spawning, for now it can always spawn
    }

    @Override
    protected void registerGoals() {
        // Example AI goals here — required: we need to add goals for any entity that exists
    	//TODO: WE CAN ADD OUR OWN AI GOALS
    	this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        //this.goalSelector.addGoal(4, new );
    }
    
    //TODO: BRAINS should be leveraged for our AI project??
}
