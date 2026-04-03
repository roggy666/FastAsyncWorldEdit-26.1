# FastAsyncWorldEdit (Fork with Minecraft 26.1 Support)

This is an unofficial fork of [FastAsyncWorldEdit](https://github.com/IntellectualSites/FastAsyncWorldEdit) that adds support for **Minecraft 26.1** (new versioning scheme).

## What changed in this fork

### New adapter for MC 26.1
- Created `adapter-26_1` with full NMS support for Minecraft 26.1
- Ported all 26+ API changes: `ChunkPos` records, non-generic `TicketType`, removed moonrise/starlight APIs, new `ServerLevel` constructors, etc.
- Disabled `reobfJar` for 26.1 (Mojang no longer obfuscates server jars)

### Patched paperweight-userdev
- Upstream paperweight `2.0.0-SNAPSHOT` (commit `7d74656`) added dev-bundle v8 support but dropped v7 backward compatibility
- Our [paperweight fork](https://github.com/roggy666/paperweight-26.1) re-adds `DevBundleV7` dispatch so both old (1.21.x) and new (26.1+) adapters work

### Build system adjustments
- Added `mavenLocal()` to `pluginManagement` and adapter repositories for local artifact resolution
- Updated `worldedit-libs/build.gradle.kts` for Gradle 9.4 compatibility with new paperweight
- Temporarily disabled adapters `1_21_4`, `1_21_5`, `1_21_6`, `1_21_9`, `1_21_11` (codebook/ASM incompatibility with JDK 25 or dev-bundle v7 issues)

## Prerequisites

- **JDK 25** (Oracle or Temurin)
- **Git**

## Building

### 1. Build Paper 26.1

Your local Paper fork must be built first to publish the dev-bundle:

```bash
cd Paper
./gradlew publishToMavenLocal
```

This publishes `io.papermc.paper:dev-bundle:26.1-R0.1-SNAPSHOT` to `~/.m2/repository/`.

### 2. Build paperweight from source

Clone and build the [patched paperweight](https://github.com/roggy666/paperweight-26.1) (with DevBundleV7 backward compatibility):

```bash
git clone https://github.com/roggy666/paperweight-26.1.git paperweight
cd paperweight
./gradlew publishToMavenLocal
```

This publishes `io.papermc.paperweight:paperweight-userdev:2.0.0-SNAPSHOT` to `~/.m2/repository/`.

### 3. Build FAWE

```bash
cd FastAsyncWorldEdit
./gradlew build
```

Output jar:
```
worldedit-bukkit/build/libs/FastAsyncWorldEdit-Bukkit-2.15.1-SNAPSHOT.jar
```

## Supported Minecraft versions

| Version | Adapter | Status |
|---------|---------|--------|
| 1.20.2 | adapter-1_20_2 | Upstream |
| 1.20.4 - 1.20.6 | adapter-1_20_4, adapter-1_20_5 | Upstream |
| 1.21 - 1.21.1 | adapter-1_21 | Upstream |
| **26.1** | **adapter-26_1** | **This fork** |

Adapters for 1.21.4, 1.21.5, 1.21.8, 1.21.10, 1.21.11 are temporarily disabled (codebook/ASM or dev-bundle v7 incompatibility with JDK 25).

## Upstream

Based on [IntellectualSites/FastAsyncWorldEdit](https://github.com/IntellectualSites/FastAsyncWorldEdit) (`main` branch, commit `526d114`).
