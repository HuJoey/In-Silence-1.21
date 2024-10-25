package net.hujoe.insilence;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.hujoe.insilence.block.ModBlocks;
import net.hujoe.insilence.entity.ModEntities;
import net.hujoe.insilence.server.RakeManager;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.server.command.CommandManager.literal;

public class Insilence implements ModInitializer {
	public static final String MOD_ID = "in-silence";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ModEntities.registerModEntities();
		ModBlocks.registerModBlocks();

		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("rake")
				.requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
				.executes(commandContext -> {
					ServerCommandSource source = commandContext.getSource();
					Entity sender = source.getEntity();
					if (sender != null) {
						RakeManager.getRakeManager().toggleUser(sender.getNameForScoreboard());
					}
					commandContext.getSource().sendFeedback(() -> Text.literal("You Toggled Rake"), false);
					return 1;
				}))));
	}
}