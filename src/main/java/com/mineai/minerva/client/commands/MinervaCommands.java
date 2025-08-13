package com.mineai.minerva.client.commands;

import com.mineai.minerva.entity.ModEntities;
import com.mineai.minerva.entity.MinervaEntity;
import com.mineai.minerva.MinervaMod;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.phys.Vec3;

public class MinervaCommands {

    public static void register() {
        MinervaMod.COMMAND_DISPATCHER.register(
                Commands.literal("minerva")
                        .then(Commands.literal("summon")
                                .executes(ctx -> summonMinerva(ctx.getSource())))
                        .then(Commands.literal("build")
                                .then(Commands.argument("structure", StringArgumentType.greedyString())
                                        .executes(ctx -> {
                                            String structure = StringArgumentType.getString(ctx, "structure");
                                            MinervaMod.sendChat("Okay, I'll build: " + structure);
                                            buildStructure(structure);
                                            return 1;
                                        })))
                        .then(Commands.argument("prompt", StringArgumentType.greedyString())
                                .executes(ctx -> {
                                    String prompt = StringArgumentType.getString(ctx, "prompt");
                                    MinervaMod.sendChat("You said: " + prompt);
                                    sendPromptToLlm(prompt);
                                    return 1;
                                })));
    }

    private static int summonMinerva(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        Vec3 spawnPos = player.position().add(player.getLookAngle().scale(2)); // 2 blocks ahead

        MinervaEntity minerva = ModEntities.MINERVA_ENTITY.get().create(player.level(), EntitySpawnReason.COMMAND);
        if (minerva != null) {
            minerva.moveRelative(1, spawnPos);
            player.level().addFreshEntity(minerva);
            MinervaMod.sendChat("Hi I'm Minerva! ♥");
        } else {
            MinervaMod.sendChat("I guess that didn't work! You can try to summon me again though ♥");
        }
        return Command.SINGLE_SUCCESS;
    }

    // TODO: These below methods will likely be combined. Placeholders for now to
    // gain idea
    private static int buildStructure(String structure) {
        MinervaMod.sendChat("Building the specifications: " + structure);
        return Command.SINGLE_SUCCESS;
    }

    private static int sendPromptToLlm(String prompt) {
        // Future: send to Python socket/HTTP service
        MinervaMod.sendChat("Prompt sent to Minerva AI: " + prompt);
        return Command.SINGLE_SUCCESS;
    }
}
