package com.example;

public class OptixRenderer {

    // Load the native libraries
    static {
        // Load dependencies necessary for our renderer
        System.loadLibrary("glad");
        System.loadLibrary("glfw3");
        System.loadLibrary("kernel32");
        System.loadLibrary("msvcp140d");
        System.loadLibrary("ucrtbased");
        System.loadLibrary("vcruntime140d");
        System.loadLibrary("vcruntime140_1d");
        System.loadLibrary("zlibd1");

        // The rendering library
        System.loadLibrary("optixrenderer");
    }

    // Native methods
    public native void startRendering();
    public native void stopRendering();
    public native void renderFrame();

    // Update Data (players, camera, entities ect) \\
    public native void updateData(byte[] updateData, int size);

    // Data management \\

    // Chunks
    public native void loadChunk(byte[] chunkData, int size);
    public native void unloadChunk();
    public native void updateChunk();

    // Entities
    public native void loadEntity();
    public native void unloadEntity();
    public native void updateEntity();

    public native boolean isRendering();
}
