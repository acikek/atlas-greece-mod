package com.acikek.atlasgreece.item;

import com.acikek.atlasgreece.AtlasGreece;
import com.acikek.atlasgreece.Destination;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Map;

public class MoussakaItem extends Item {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(10)
            .saturationModifier(10.0f)
            .alwaysEdible()
            .meat()
            .build();

    public static final Settings SETTINGS = new FabricItemSettings().food(FOOD_COMPONENT);

    public static final Item INSTANCE = new MoussakaItem();

    public MoussakaItem() {
        super(SETTINGS);
    }

    public static BlockPos getReturnPosition(ServerPlayerEntity player) {
        World overworld = player.server.getOverworld();
        return player.getSpawnPointDimension() == World.OVERWORLD && player.getSpawnPointPosition() != null
                ? player.getSpawnPointPosition()
                : overworld.getSpawnPos();
    }

    public static void teleport(ServerPlayerEntity player, BlockPos blockPos, RegistryKey<World> dimension) {
        ServerWorld world = player.server.getWorld(dimension);
        Vec3d pos = Vec3d.ofBottomCenter(blockPos);
        player.teleport(world, pos.x, pos.y, pos.z, player.getYaw(), player.getPitch());
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayer) {
            if (serverPlayer.getWorld().getRegistryKey().getValue().equals(AtlasGreece.DIMENSION_ID)) {
                teleport(serverPlayer, getReturnPosition(serverPlayer), World.OVERWORLD);
            }
            else {
                Map.Entry<String, Destination> destination = Destination.getDestination(serverPlayer);
                teleport(serverPlayer, destination.getValue().pos(), AtlasGreece.DIMENSION);
                serverPlayer.sendMessage(destination.getValue().getSpawnText(destination.getKey()).styled(style -> style.withItalic(true).withFormatting(Formatting.GRAY)));
            }
        }
        return super.finishUsing(stack, world, user);
    }

    public static void register() {
        Registry.register(Registries.ITEM, AtlasGreece.id("moussaka"), INSTANCE);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries ->
            entries.addAfter(Items.COOKED_RABBIT, INSTANCE)
        );
    }
}
