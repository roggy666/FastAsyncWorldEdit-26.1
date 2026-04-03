package io.papermc.paper.datacomponent;

/**
 * Stub class to satisfy type annotation resolution in mapped server jar.
 * The real class requires JVM 25+ (Paper API 26.1).
 */
public interface DataComponentType {
    interface Valued<T> extends DataComponentType {
    }
    interface NonValued extends DataComponentType {
    }
}
