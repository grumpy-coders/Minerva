package com.mineai.minerva.events;

import com.mineai.minerva.EntityInit;
import com.mineai.minerva.MinervaEntity;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class CommonModEvents {

	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event) {
		event.put(EntityInit.MINERVA_ENTITY.get(), MinervaEntity.createAttributes().build());
	}
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		//event.registerLayerDefinition(EntityInit.MINERVA_ENTITY.get(), MinervaRenderer::new);
	}
}
