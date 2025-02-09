package com.example;

import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;

import java.io.ByteArrayOutputStream;
import java.util.Set;

public class UpdateThread extends Thread {

    public OptixRenderer optix;
    public DataManager dataManager = DataManager.getInstance();

    final private static String[] keysToRemove = new String[] { "Brain", "HurtByTimestamp", "SleepTimer", "Invulnerable", "FallFlying", "PortalCooldown", "UUID", "OnGround", "Fire", "Air", "AbsorptionAmount", "abilities", "FallDistance",
            "DeathTime", "XpSeed", "XpTotal", "Health", "foodSaturationLevel", "ignore_fall_damage_from_current_explosion", "XpLevel", "current_impulse_context_reset_grace_time", "Score", "XpP", "EnderItems", "attributes", "foodLevel",
            "foodExhaustionLevel", "HurtTime", "SelectedItemSlot", "Inventory", "foodTickTimer"};

    public UpdateThread(OptixRenderer optix) {
        this.optix = optix;
    }

    public void run() {
        // Update the data
        DataManager dataManager = DataManager.getInstance();
        OptixRenderer optix = dataManager.optix;
        while (optix.isRendering()) {
            // Update the data
            try {
                // Serialize player data to NBT and then to a byte array
                NbtCompound nbt = new NbtCompound();

                MinecraftClient.getInstance().player.writeNbt(nbt);

                // Remove unnecessary data
                for(String key : keysToRemove) {
                    nbt.remove(key);
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                NbtIo.writeCompressed(nbt, outputStream);
                byte[] nbtData = outputStream.toByteArray();

                // Send to native code
                optix.updateData(nbtData, nbtData.length);

                outputStream.close();
                Thread.sleep(1000);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }
}
