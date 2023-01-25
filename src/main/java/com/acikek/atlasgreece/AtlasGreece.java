package com.acikek.atlasgreece;

import com.acikek.atlasgreece.item.MoussakaItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AtlasGreece implements ModInitializer {

    public static final String ID = "atlasgreece";

    public static final Logger LOGGER = LogManager.getLogger(ID);

    public static final Identifier DIMENSION_ID = id("greece");
    public static final RegistryKey<World> DIMENSION = RegistryKey.of(RegistryKeys.WORLD, DIMENSION_ID);

    public static Identifier id(String path) {
        return new Identifier(ID, path);
    }

    public static final SoundEvent DIMENSION_ENTER = SoundEvent.of(id("dimension_enter"));

    @Override
    public void onInitialize() {
        LOGGER.info("Καλώς ήρθες στην Ελλάδα!");
        MoussakaItem.register();
        GreecePortal.register();
        Registry.register(Registries.SOUND_EVENT, DIMENSION_ENTER.getId(), DIMENSION_ENTER);
        FabricLoader.getInstance().getModContainer(ID).ifPresent(AtlasGreece::registerPack);
    }

    public static void registerPack(ModContainer mod) {
        ResourceManagerHelper.registerBuiltinResourcePack(
                id("atlas-greece"), mod, Text.translatable("pack.atlasgreece.dimension"),
                ResourcePackActivationType.ALWAYS_ENABLED
        );
    }
}
