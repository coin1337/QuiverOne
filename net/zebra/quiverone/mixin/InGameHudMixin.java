package net.zebra.quiverone.mixin;

import net.minecraft.class_329;
import net.minecraft.class_4587;
import net.zebra.quiverone.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_329.class})
public class InGameHudMixin {
   @Inject(
      at = {@At("RETURN")},
      method = {"render"},
      cancellable = true
   )
   public void render(class_4587 matrixStack, float float_1, CallbackInfo info) {
      Main.instance.clickGUI.render();
   }
}
