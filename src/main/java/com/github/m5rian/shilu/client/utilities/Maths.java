package com.github.m5rian.shilu.client.utilities;

public class Maths {
    public static float interpolate(float yaw1, float yaw2, float percent) {
        float f = (yaw1 + (yaw2 - yaw1) * percent) % 360;

        if (f < 0) {
            f += 360;
        }

        return f;
    }
}
