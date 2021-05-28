package net.zebra.quiverone.mixin;

import net.minecraft.class_1041;
import net.minecraft.class_310;
import net.zebra.quiverone.Main;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_310.class})
public class MinecraftClientMixin {
   @Shadow
   @Final
   private class_1041 field_1704;

   @Inject(
      at = {@At("RETURN")},
      method = {"updateWindowTitle"}
   )
   private void updateWindowTitle(CallbackInfo info) {
      this.field_1704.method_24286(Main.mod_name + " | Version " + Main.mod_version);
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"run"}
   )
   private void run(CallbackInfo info) {
      Main.instance.run_client();
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"stop"}
   )
   public void stop(CallbackInfo info) {
      Main.instance.stop_client();
   }
}
