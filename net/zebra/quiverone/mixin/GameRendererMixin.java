package net.zebra.quiverone.mixin;

import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_757.class})
public class GameRendererMixin {
   @Inject(
      at = {@At("HEAD")},
      method = {"renderHand"},
      cancellable = true
   )
   private void renderHand(class_4587 matrixStack_1, class_4184 camera, float tickDelta, CallbackInfo info) {
      ModuleManager.modules.stream().filter(Module::isEnabled).forEach(Module::onRender);
   }
}
