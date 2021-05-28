package net.zebra.quiverone.mixin;

import java.util.Iterator;
import net.minecraft.class_2338;
import net.minecraft.class_2394;
import net.minecraft.class_2680;
import net.minecraft.class_702;
import net.minecraft.class_703;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_702.class})
public class ParticleManagerMixin {
   @Inject(
      method = {"addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onAddParticle(class_2394 parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfoReturnable<class_703> info) {
      Iterator var15 = ModuleManager.getModules().iterator();

      while(var15.hasNext()) {
         Module module = (Module)var15.next();
         if (module.getName().equals("NoRender") && module.isOn()) {
            info.cancel();
         }
      }

   }

   @Inject(
      method = {"addBlockBreakParticles"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onAddBlockBreakParticles(class_2338 blockPos, class_2680 state, CallbackInfo info) {
      Iterator var4 = ModuleManager.getModules().iterator();

      while(var4.hasNext()) {
         Module module = (Module)var4.next();
         if (module.getName().equals("NoRender") && module.isOn()) {
            info.cancel();
         }
      }

   }
}
