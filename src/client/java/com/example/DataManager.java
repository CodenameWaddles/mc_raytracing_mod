package com.example;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.chunk.SerializedChunk;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.nbt.NbtIo;
import java.io.ByteArrayOutputStream;

public class DataManager {

    public OptixRenderer optix;
    public ServerWorld world;

    private UpdateThread updateThread;

    final private static String[] keysToRemove = new String[] { "Status", "fabric:attachments", "LastUpdate", "structures", "InhabitedTime", "PostProcessing", "DataVersion"};

    // singleton stuff
    private static volatile DataManager instance;

    private DataManager() {}

    public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager();
                }
            }
        }
        return instance;
    }

    // Data management
    public void sendChunk(WorldChunk chunk) {
        // Serialize the chunk

        NbtCompound nbt = SerializedChunk.fromChunk(world, chunk).serialize();

        for(String key : keysToRemove) {
            nbt.remove(key);
        }

        try {
            // Serialize NBT to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            NbtIo.writeCompressed(nbt, outputStream);
            byte[] nbtData = outputStream.toByteArray();

            //System.out.println("Size : " + nbtData.length);

            // Send to native code
            optix.loadChunk(nbtData, nbtData.length);

            //dataOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startDataUpdate() {
        // Start data update
        updateThread = new UpdateThread(optix);
        updateThread.start();
    }

    public void stopDataUpdate() {
        // Stop data update
        updateThread.interrupt();
        updateThread = null;
    }
}
