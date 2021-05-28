package net.zebra.quiverone.modules.movement;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class Step extends Module {
   float prevStepHeight;

   public Step() {
      super("Step", "Allows you to walk over blocks", Category.MOVEMENT, -1, false, true);
   }

   public void onEnable() {
      if (mc.field_1724 != null) {
         this.prevStepHeight = mc.field_1724.field_6013;
         mc.field_1724.field_6013 = 1.5F;
      }
   }

   public void onDisable() {
      mc.field_1724.field_6013 = this.prevStepHeight;
   }
}
