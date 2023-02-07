package com.acikek.atlasgreece;

import com.acikek.atlasgreece.item.MoussakaItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AtlasGreece implements ModInitializer {

    public static final String ID = "atlasgreece";

    public static final Logger LOGGER = LogManager.getLogger(ID);

    public static final Identifier DIMENSION = id("greece");

    public static Identifier id(String path) {
        return new Identifier(ID, path);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Καλώς ήρθες στην Ελλάδα!");
        MoussakaItem.register();
        GreecePortal.register();
        FabricLoader.getInstance().getModContainer(ID).ifPresent(AtlasGreece::registerPack);
    }

    public static void registerPack(ModContainer mod) {
        ResourceManagerHelper.registerBuiltinResourcePack(
                id("atlas-greece"), mod, Text.translatable("pack.atlasgreece.dimension"),
                ResourcePackActivationType.ALWAYS_ENABLED
        );
    }
}
