package net.zebra.quiverone.modules.movement;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class AutoSprint extends Module {
   public AutoSprint() {
      super("Sprint", "Makes you constantly sprint when you move.", Category.MOVEMENT, -1, false, true);
   }

   public void onUpdate() {
      boolean var10000;
      if ((!(mc.field_1724.field_3913.field_3905 > 0.0F) || !(mc.field_1724.field_3913.field_3907 > 0.0F)) && (!(mc.field_1724.field_3913.field_3905 > 0.0F) || mc.field_1724.method_5715())) {
         var10000 = false;
      } else {
         var10000 = true;
      }

      mc.field_1724.method_5728(true);
   }
}
