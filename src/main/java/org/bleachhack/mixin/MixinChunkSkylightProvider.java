package org.bleachhack.mixin;

import org.bleachhack.module.ModuleManager;
import org.bleachhack.module.mods.NoRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.chunk.light.ChunkSkyLightProvider;

@Mixin(ChunkSkyLightProvider.class)
public class MixinChunkSkylightProvider {

    @Inject(method = "updateLevel", at = @At("HEAD"), cancellable = true)
    private void updateLevel(long id, long excludedId, int level, CallbackInfoReturnable<Integer> cir) {
        if (ModuleManager.getModule(NoRender.class).isWorldToggled(4)) {
            cir.setReturnValue(0);
        }
    }
}
