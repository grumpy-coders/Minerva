package com.mineai.minerva.events;

import com.mineai.minerva.EntityInit;
import com.mineai.minerva.MinervaEntity;
import com.mineai.minerva.MinervaMod;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinervaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event) {
		event.put(EntityInit.MINERVA_ENTITY.get(), MinervaEntity.createAttributes().build());
	}
	
	@SubscribeEvent
	public static void registerSpawnEvents(SpawnPlacementRegisterEvent event) {
		event.register(EntityInit.MINERVA_ENTITY.get(), MinervaEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
	}
}
