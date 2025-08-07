package com.mineai.minerva.events;

import com.mineai.minerva.EntityInit;
import com.mineai.minerva.MinervaMod;
import com.mineai.minerva.client.renderer.MinervaRenderer;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class ClientModEvents {

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EntityInit.MINERVA_ENTITY.get(), MinervaRenderer::new);
	}
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		//event.registerLayerDefinition(EntityInit.MINERVA_ENTITY.get(), MinervaRenderer::new);
	}
}
