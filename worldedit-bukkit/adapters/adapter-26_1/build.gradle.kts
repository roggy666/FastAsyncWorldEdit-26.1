import io.papermc.paperweight.userdev.PaperweightUserDependenciesExtension

plugins {
    id("buildlogic.adapter")
}

dependencies {
    // https://repo.papermc.io/service/rest/repository/browse/maven-public/io/papermc/paper/dev-bundle/
    the<PaperweightUserDependenciesExtension>().paperDevBundle("26.1-R0.1-SNAPSHOT")
    compileOnly(libs.paperLib)

    // MC 26.1 unbundled these libraries from the server jar - add them explicitly
    compileOnly("com.google.code.gson:gson:2.13.2")
    compileOnly("com.google.guava:guava:33.5.0-jre")
    compileOnly("it.unimi.dsi:fastutil:8.5.18")
    compileOnly("org.apache.logging.log4j:log4j-api:2.25.2")
    compileOnly("com.mojang:datafixerupper:9.0.19")
    compileOnly("com.mojang:authlib:7.0.63")
    compileOnly("com.mojang:brigadier:1.3.10")
    compileOnly("org.jspecify:jspecify:1.0.0")
    compileOnly("org.jetbrains:annotations:26.0.2")
    compileOnly("org.apache.commons:commons-lang3:3.17.0")
    // Bukkit API is no longer bundled into the mapped server jar in 26.1
    compileOnly("org.spigotmc:spigot-api:26.1-R0.1-SNAPSHOT")
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:-classfile")
}

// Disable test compilation - type annotation issues with JDK 25 classfiles
tasks.named("compileTestJava") {
    enabled = false
}
tasks.named("test") {
    enabled = false
}

// MC 26.1 no longer provides reobf mappings - disable reobfJar
tasks.named("reobfJar") {
    enabled = false
}
tasks.named("assemble") {
    setDependsOn(dependsOn.filterNot { it.toString().contains("reobf") })
    dependsOn("jar")
}
