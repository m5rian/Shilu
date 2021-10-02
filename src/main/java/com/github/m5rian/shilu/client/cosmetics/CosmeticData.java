package com.github.m5rian.shilu.client.cosmetics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Keep annotation at runtime
@Target(ElementType.TYPE) // Only addable for Classes, interfaces, annotations and enums.
public @interface CosmeticData {
    CosmeticType type();

    String obj();

    String texture();
}
