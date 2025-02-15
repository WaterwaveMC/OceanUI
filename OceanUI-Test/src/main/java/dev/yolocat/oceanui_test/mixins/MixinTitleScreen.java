package dev.yolocat.oceanui_test.mixins;

import dev.yolocat.oceanui_test.ocean.OceanScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {

    @Inject(at = @At("HEAD"), method = "init()V", cancellable = true)
    private void init(CallbackInfo ci) {
        MinecraftClient.getInstance().setScreen(new OceanScreen());
        ci.cancel();
    }

}
