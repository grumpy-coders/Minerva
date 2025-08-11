/*
 * package com.mineai.minerva.events;
 * 
 * import com.mineai.minerva.MinervaMod;
 * import com.mineai.minerva.client.MinervaRenderer;
 * import com.mineai.minerva.entity.MinervaEntity;
 * import com.mineai.minerva.entity.ModEntities;
 * 
 * import net.minecraftforge.client.event.EntityRenderersEvent;
 * import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
 * import net.minecraftforge.fml.common.Mod;
 * 
 * @Mod.EventBusSubscriber(modid = MinervaMod.MODID, bus =
 * Mod.EventBusSubscriber.Bus.MOD)
 * public class ClientModEvents {
 * 
 * @SubscribeEvent
 * public static void registerRenderers(EntityRenderersEvent.RegisterRenderers
 * event) {
 * event.registerEntityRenderer(ModEntities.MINERVA_ENTITY.get(),
 * MinervaRenderer::new);
 * }
 * 
 * @SubscribeEvent
 * public static void
 * registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
 * {
 * event.registerLayerDefinition(Minerva, MinervaRenderer::new);
 * }
 * }
 */