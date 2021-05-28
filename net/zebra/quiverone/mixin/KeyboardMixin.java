package net.zebra.quiverone.mixin;

import java.util.Iterator;
import net.minecraft.class_309;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_309.class})
public class KeyboardMixin {
   @Inject(
      at = {@At("HEAD")},
      method = {"onKey"}
   )
   private void onKey(long windowHandle, int keyCode, int scanCode, int action, int modifiers, CallbackInfo info) {
      Iterator var8 = ModuleManager.getModules().iterator();

      while(var8.hasNext()) {
         Module module = (Module)var8.next();
         class_437 screen = class_310.method_1551().field_1755;
         if (screen != null) {
            return;
         }

         if (module.getKeyCode() == keyCode) {
            module.toggle();
         }
      }

   }
}
