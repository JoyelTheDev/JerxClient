/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDev/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package org.bleachhack.mixin;

import org.bleachhack.BleachHack;
import org.bleachhack.event.events.EventRenderScreenBackground;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(Screen.class)
public class MixinScreen {

	@Unique
	private int lastMX;
	@Unique
	private int lastMY;

	@Inject(method = "render", at = @At("HEAD"))
	private void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo callback) {
		lastMX = mouseX;
		lastMY = mouseY;
	}

	@Inject(method = "renderBackground(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At("HEAD"), cancellable = true)
	private void renderBackground(MatrixStack matrices, CallbackInfo callback) {
		EventRenderScreenBackground event = new EventRenderScreenBackground(matrices);
		BleachHack.eventBus.post(event);

		if (event.isCancelled()) {
			callback.cancel();
		}
	}
}
