package com.acikek.atlasgreece;

import com.acikek.atlasgreece.item.MoussakaItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AtlasGreece implements ModInitializer {

    public static final String ID = "atlasgreece";

    public static final Logger LOGGER = LogManager.getLogger(ID);

    public static final Identifier DIMENSION_ID = id("greece");
    public static final RegistryKey<World> DIMENSION = RegistryKey.of(Registry.WORLD_KEY, DIMENSION_ID);

    public static Identifier id(String path) {
        return new Identifier(ID, path);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Καλώς ήρθες στην Ελλάδα!");
        MoussakaItem.register();
        FabricLoader.getInstance().getModContainer(ID).ifPresent(AtlasGreece::registerPack);
        registerPortal();
    }

    public static void registerPack(ModContainer mod) {
        ResourceManagerHelper.registerBuiltinResourcePack(
                id("dimension"), mod, "Atlas Greece", ResourcePackActivationType.ALWAYS_ENABLED
        );
    }

    public static void registerPortal() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.SMOOTH_QUARTZ)
                .lightWithWater()
                .destDimID(AtlasGreece.DIMENSION_ID)
                .tintColor(121, 182, 242)
                .registerPortal();
    }
}
