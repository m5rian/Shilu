package com.github.m5rian.shilu.client.utilities.obj;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Loader {

    public static Model loadModel(ResourceLocation obj) {
        Model model = new Model();

        try {
            final InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(obj).getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            String line;
            while ((line = reader.readLine()) != null) {
                final String[] split = line.split("\\s+");

                switch (split[0]) {
                    case "v": {
                        final float x = Float.parseFloat(split[1]);
                        final float y = Float.parseFloat(split[2]);
                        final float z = Float.parseFloat(split[3]);

                        model.vertices.add(new Vector3f(x, y, z));
                        break;
                    }
                    case "vn": {
                        final float x = Float.parseFloat(split[1]);
                        final float y = Float.parseFloat(split[2]);
                        final float z = Float.parseFloat(split[3]);

                        model.normals.add(new Vector3f(x, y, z));
                        break;
                    }
                    case "f": {
                        final float vertexX = Float.parseFloat(split[1].split("/")[0]);
                        final float vertexY = Float.parseFloat(split[2].split("/")[0]);
                        final float vertexZ = Float.parseFloat(split[3].split("/")[0]);
                        final Vector3f vertexIndices = new Vector3f(vertexX, vertexY, vertexZ);

                        final float normalX = Float.parseFloat(split[1].split("/")[2]);
                        final float normalY = Float.parseFloat(split[2].split("/")[2]);
                        final float normalZ = Float.parseFloat(split[3].split("/")[2]);
                        final Vector3f normalIndices = new Vector3f(normalX, normalY, normalZ);

                        model.faces.add(new Model.Face(vertexIndices, normalIndices));
                        break;
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

}
