package com.acikek.atlasgreece.item;

import com.acikek.atlasgreece.AtlasGreece;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class MoussakaItem extends Item {

    public static final Item INSTANCE = new MoussakaItem();

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(10)
            .saturationModifier(10.0f)
            .alwaysEdible()
            .meat()
            .build();

    public static final Settings SETTINGS = new FabricItemSettings()
            .group(ItemGroup.FOOD)
            .food(FOOD_COMPONENT);

    public MoussakaItem() {
        super(SETTINGS);
    }

    public static BlockPos getTeleportPosition(World dimension, ServerPlayerEntity player) {
        return player.getSpawnPointDimension().getValue().equals(AtlasGreece.DIMENSION_ID)
                ? player.getSpawnPointPosition()
                : dimension.getSpawnPos();
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!AtlasGreece.usePortal && world instanceof ServerWorld serverWorld && user instanceof ServerPlayerEntity serverPlayer) {
            ServerWorld dimension = serverWorld.getServer().getWorld(AtlasGreece.DIMENSION);
            BlockPos teleportPos = getTeleportPosition(dimension, serverPlayer);
            Vec3d pos = Vec3d.ofBottomCenter(teleportPos);
            serverPlayer.teleport(dimension, pos.x, pos.y, pos.z, serverPlayer.getYaw(), serverPlayer.getPitch());
        }
        return super.finishUsing(stack, world, user);
    }

    public static void register() {
        Registry.register(Registry.ITEM, AtlasGreece.id("moussaka"), INSTANCE);
    }
}
