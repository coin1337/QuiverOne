package net.zebra.quiverone.modules.movement;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class JetPack extends Module {
   public JetPack() {
      super("JetPack", "Fly as if you have a jetpack", Category.MOVEMENT, -1, false, true);
   }

   public void onUpdate() {
      mc.field_1724.field_7503.field_7478 = false;
      if (mc.field_1690.field_1903.method_1434()) {
         mc.field_1724.method_6043();
      }

   }
}
