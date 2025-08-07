package com.mineai.minerva;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinervaMod.MODID);
    
    public static final ResourceKey<EntityType<?>> MINERVA_KEY =
            ResourceKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), ResourceLocation.fromNamespaceAndPath(MinervaMod.MODID, "minerva")
);

    public static final RegistryObject<EntityType<EntityMinerva>> MINERVA =
    	    ENTITIES.register("minerva", 
    	        (java.util.function.Supplier<EntityType<EntityMinerva>>) () ->
    	            EntityType.Builder.<EntityMinerva>of(EntityMinerva::new, MobCategory.CREATURE)
    	                .sized(0.6f, 1.8f)
    	                .build(MINERVA_KEY)
    	    );

}
 