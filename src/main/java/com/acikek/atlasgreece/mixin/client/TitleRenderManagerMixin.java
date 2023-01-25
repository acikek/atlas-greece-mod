package com.acikek.atlasgreece.mixin.client;

import com.acikek.atlasgreece.AtlasGreece;
import com.yungnickyoung.minecraft.travelerstitles.render.TitleRenderManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleRenderManager.class)
public class TitleRenderManagerMixin {

    private World atlasgreece$newWorld;

    @Inject(method = "updateDimensionTitle", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/entity/player/PlayerEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"))
    private void atlasgreece$captureWorldChange(World world, PlayerEntity player, boolean isPlayerUnderground, CallbackInfo ci) {
        atlasgreece$newWorld = world;
    }

    @ModifyArg(method = "updateDimensionTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"))
    private SoundEvent atlasgreece$customDimensionSound(SoundEvent sound) {
        return atlasgreece$newWorld != null && atlasgreece$newWorld.getRegistryKey().getValue().equals(AtlasGreece.DIMENSION_ID)
                ? AtlasGreece.DIMENSION_ENTER
                : sound;
    }
}
