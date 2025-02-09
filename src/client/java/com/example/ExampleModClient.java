package com.example;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.client.render.*;
import org.joml.Matrix4f;

public class ExampleModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ExampleMod.LOGGER.info("Client initialized");

		// Setup renderer and data
		OptixRenderer optix = new OptixRenderer();
		DataManager dataManager = DataManager.getInstance();
		dataManager.optix = optix;

		ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
			// Start optix

		});

		ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
			// Stop optix


		});

		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			// Load world data
			optix.startRendering();
			dataManager.startDataUpdate();
			dataManager.world = server.getOverworld();
			//optix.loadWorld(server.getWorld().);
		});

		ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
			// Unload world data
			dataManager.world = null;
			dataManager.stopDataUpdate();
			optix.stopRendering();
			//optix.unloadWorld();
		});

		HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
			// Render HUD
			int w = drawContext.getScaledWindowWidth();
			int h = drawContext.getScaledWindowHeight();
			//optix.renderFrame();
			//drawContext.fillGradient(0, 0, w, h, 0xFF0000FF, 0xFFFF00FF);

			// Get the transformation matrix from the matrix stack, alongside the tessellator instance and a new buffer builder.
			Matrix4f transformationMatrix = drawContext.getMatrices().peek().getPositionMatrix();
			Tessellator tessellator = Tessellator.getInstance();

			// Begin a triangle strip buffer using the POSITION_COLOR vertex format.
			BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);

			// Write our vertices, Z doesn't really matter since it's on the HUD.
			buffer.vertex(transformationMatrix, 20, 20, 5).color(0xFF414141);
			buffer.vertex(transformationMatrix, 5, 40, 5).color(0xFF000000);
			buffer.vertex(transformationMatrix, 35, 40, 5).color(0xFF000000);
			buffer.vertex(transformationMatrix, 20, 60, 5).color(0xFF414141);

			// We'll get to this bit in the next section.
			//RenderSystem.setShader(ShaderProgram.create(vertexShader));
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

			// Draw the buffer onto the screen.
			BufferRenderer.drawWithGlobalProgram(buffer.end());
		});

		ClientChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
			// Send chunk to native code
			dataManager.sendChunk(chunk);
		});

		ClientChunkEvents.CHUNK_UNLOAD.register((world, chunk) -> {
			// Unload chunk from native code
			// optix.unloadChunk();
		});

		ServerLifecycleEvents.SERVER_STARTING.register(server -> {
			// Load world data

			//optix.loadWorld(server.getWorld());
		});

	}
}