package io.github.ridglef.walper.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SplashTextResourceSupplier.class)
public class WalperSplash {
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void walper(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("Walper Owns Me And All");
    }
}
