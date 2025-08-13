package com.mineai.minerva;

import com.mineai.minerva.client.commands.MinervaCommands;
import com.mineai.minerva.entity.ModEntities;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(MinervaMod.MODID)
public final class MinervaMod {
	public static final String MODID = "minerva";
	public static CommandDispatcher<CommandSourceStack> COMMAND_DISPATCHER;
	public static ChatComponent chatWindow;
	public static final boolean IS_LOGGING_ENABLED = true;

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
			.create(Registries.CREATIVE_MODE_TAB, MODID);

	// Minerva Spawn Egg TODO: doesn't WORK UNLESS WE DUPE SOME CODE FROM THE FORGE
	// API FOR MOB SPAWN
	// public static final RegistryObject<Item> MINERVA_SPAWN_EGG =
	// ITEMS.register("minerva_spawn_egg",
	// () -> new SpawnEggItem(ModEntities.MINERVA_ENTITY, new Item.Properties()));

	public MinervaMod(FMLJavaModLoadingContext context) {
		var modBusGroup = context.getModBusGroup();

		// Register entity + items
		ModEntities.ENTITY_TYPES.register(modBusGroup);
		ITEMS.register(modBusGroup);
		BLOCKS.register(modBusGroup);
		CREATIVE_MODE_TABS.register(modBusGroup);
	}

	@SubscribeEvent
	public static void onRegisterCommands(RegisterCommandsEvent event) {
		COMMAND_DISPATCHER = event.getDispatcher();
		MinervaCommands.register();
	}

	public static void sendChat(String message) {
		// CHATGPT and YOUTUBE DINT HELP. FORGE API CHAT WAY IS BAD, SO IM DOING
		// SOMETHING ELSE
		chatWindow.addMessage(Component.literal(message));
	}

	@SubscribeEvent
	private void commonSetup(final FMLCommonSetupEvent event) {
		if (IS_LOGGING_ENABLED) {
			LogUtils.getLogger().info("MinervaMod Common Setup");
		}
	}

	@SubscribeEvent
	private static void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS && IS_LOGGING_ENABLED) {
			LogUtils.getLogger().info("MinervaMod: SPAWN_EGGS tab detected, adding Minerva Spawn Egg");
		}
	}

	@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
	public static class ClientModEvents {
		// Private constructor to prevent instantiation
		private ClientModEvents() {
		}

		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			Minecraft minecraft = Minecraft.getInstance();

			chatWindow = new ChatComponent(minecraft);

			if (IS_LOGGING_ENABLED) {
				LogUtils.getLogger().info("MinervaMod Chat Window created");
			}

		}
	}
}
