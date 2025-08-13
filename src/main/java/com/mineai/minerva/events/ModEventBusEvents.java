package com.mineai.minerva.events;

import com.mineai.minerva.MinervaMod;
import com.mineai.minerva.client.MinervaModel;
import com.mineai.minerva.client.MinervaRenderer;
import com.mineai.minerva.entity.MinervaEntity;
import com.mineai.minerva.entity.ModEntities;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
//import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinervaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.MINERVA_ENTITY.get(), MinervaRenderer::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MINERVA_ENTITY.get(), MinervaEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                MinervaRenderer.LAYER_LOCATION,
                MinervaModel::createBodyLayer);
    }

    // @SubscribeEvent
    // public static void registerSpawnEvents(SpawnPlacementRegisterEvent event) {
    // event.register(ModEntities.MINERVA_ENTITY.get(), MinervaEntity::canSpawn,
    // SpawnPlacementRegisterEvent.Operation.OR);
    // }
}
