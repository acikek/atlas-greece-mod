package com.acikek.atlasgreece;

import com.acikek.datacriteria.api.DataCriteriaAPI;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

public class GreecePortal {

    public static void register() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.SMOOTH_QUARTZ)
                .lightWithWater()
                .destDimID(AtlasGreece.DIMENSION)
                .tintColor(121, 182, 242)
                .registerPostTPEvent(GreecePortal::handleTeleport)
                .registerPortal();
    }

    public static void handleTeleport(Entity entity) {
        if (entity instanceof ServerPlayerEntity player) {
            DataCriteriaAPI.trigger(AtlasGreece.id("portal"), player);
        }
    }
}
