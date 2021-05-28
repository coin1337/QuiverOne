package net.zebra.quiverone.modules.movement;

import net.minecraft.class_304;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class AutoWalk extends Module {
   public AutoWalk() {
      super("AutoWalk", "Automatically walks for you", Category.MOVEMENT, 71, false, true);
   }

   public void onDisable() {
      class_304 key = mc.field_1690.field_1894;
      key.method_23481(false);
   }

   public void onEnable() {
      class_304 key = mc.field_1690.field_1894;
      key.method_23481(true);
      if (mc.field_1724.field_3913.field_3910 || mc.field_1724.field_3913.field_3909 || mc.field_1724.field_3913.field_3908 || mc.field_1724.field_3913.field_3906) {
         key.method_23481(false);
      }

   }
}
