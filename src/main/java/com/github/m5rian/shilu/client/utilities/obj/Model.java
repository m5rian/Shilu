package com.github.m5rian.shilu.client.utilities.obj;

import com.github.m5rian.shilu.client.utilities.Obj;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Model {

    public List<Vector3f> vertices = new ArrayList<>();
    public List<Vector3f> normals = new ArrayList<>();
    public List<Face> faces = new ArrayList<>();

    public void render() {
        GL11.glMaterialf(GL_FRONT, GL_SHININESS, 120);
        GL11.glBegin(GL_TRIANGLES);

        for (Face face : this.faces) {
            final Vector3f n1 = this.normals.get((int) face.normal.x - 1);
            glNormal3f(n1.x, n1.y, n1.z);
            final Vector3f v1 = this.vertices.get((int) face.vertex.x - 1);
            glVertex3f(v1.x, v1.y, v1.z);

            final Vector3f n2 = this.normals.get((int) face.normal.y - 1);
            glNormal3f(n2.x, n2.y, n2.z);
            final Vector3f v2 = this.vertices.get((int) face.vertex.y - 1);
            glVertex3f(v2.x, v2.y, v2.z);

            final Vector3f n3 = this.normals.get((int) face.normal.z - 1);
            glNormal3f(n3.x, n3.y, n3.z);
            final Vector3f v3 = this.vertices.get((int) face.vertex.z - 1);
            glVertex3f(v3.x, v3.y, v3.z);
        }

        GL11.glEnd();
    }

    static class Face {
        public Vector3f vertex;
        public Vector3f normal;

        public Face(Vector3f vertex, Vector3f normal) {
            this.vertex = vertex;
            this.normal = normal;
        }
    }

}
