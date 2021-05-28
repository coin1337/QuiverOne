package net.zebra.quiverone.modules.movement;

import net.minecraft.class_304;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class AutoSneak extends Module {
   public AutoSneak() {
      super("AutoSneak", "Makes you automatically sneak", Category.MOVEMENT, -1, false, true);
   }

   public void onDisable() {
      class_304 key = mc.field_1690.field_1832;
      key.method_23481(false);
   }

   public void onUpdate() {
      class_304 key = mc.field_1690.field_1832;
      key.method_23481(true);
      if (mc.field_1724.field_3913.field_3910 || mc.field_1724.field_3913.field_3909 || mc.field_1724.field_3913.field_3908 || mc.field_1724.field_3913.field_3906) {
         this.disable();
      }

   }
}
