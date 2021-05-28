package net.zebra.quiverone.mixin;

import net.minecraft.class_746;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_746.class})
public class ClientPlayerEntityMixin {
   @Inject(
      at = {@At("HEAD")},
      method = {"tick"}
   )
   private void preTick(CallbackInfo info) {
      ModuleManager.modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
   }
}
