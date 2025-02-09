package com.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class SharedMemoryBuffer {

    String bufferName;
    MappedByteBuffer mappedBuffer;
    RandomAccessFile file;

    public SharedMemoryBuffer(String name, int size) throws IOException {
        // Create or open shared memory file
        file = new RandomAccessFile(name + ".dat", "rw");
        // Set memory size
        file.setLength(size);
        // Create MappedByteBuffer for shared memory
        FileChannel channel = file.getChannel();
        mappedBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());
        mappedBuffer.order(ByteOrder.LITTLE_ENDIAN);
        bufferName = name;

        // initiate shared memory
        mappedBuffer.putInt(0, 42);

        System.out.println("JAVA : Initialized shared memory : " + bufferName);
    }

    public float readFloat(int index) {
        return mappedBuffer.getFloat(index);
    }

    public int readInt(int index) {
        return mappedBuffer.getInt(index);
    }

    public void writeFloat(int index, float value) {
        mappedBuffer.putFloat(index, value);
    }

    public void writeInt(int index, int value) {
        mappedBuffer.putInt(index, value);
    }


    public void cleanupData() throws IOException {
        System.out.println("JAVA : Cleaning up shared memory : " + bufferName);
        mappedBuffer.clear();
        file.close();
    }
}
