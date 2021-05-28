package net.zebra.quiverone.modules.world;

import net.minecraft.class_437;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class AutoRespawn extends Module {
   public AutoRespawn() {
      super("AutoRespawn", "Automatically respawns you if you die", Category.WORLD, -1, false, true);
   }

   public void onUpdate() {
      if (mc.field_1724.method_29504()) {
         mc.field_1724.method_7331();
         mc.method_1507((class_437)null);
      }

   }
}
