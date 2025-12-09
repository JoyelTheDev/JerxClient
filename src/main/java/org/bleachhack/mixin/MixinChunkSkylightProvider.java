package org.bleachhack.mixin;

import org.bleachhack.module.ModuleManager;
import org.bleachhack.module.mods.NoRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.level.lighting.SkyLightEngine;

@Mixin(SkyLightEngine.class)
public class MixinSkyLightEngine {

    @Inject(
        method = "propagateLevel(JJI)I",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onPropagateLevel(long id, long excludedId, int level, CallbackInfoReturnable<Integer> cir) {
        if (ModuleManager.getModule(NoRender.class).isWorldToggled(4)) {
            cir.setReturnValue(0);
        }
    }
}
