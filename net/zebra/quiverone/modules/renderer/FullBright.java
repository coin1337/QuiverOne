package net.zebra.quiverone.modules.renderer;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class FullBright extends Module {
   double prevgamma;

   public FullBright() {
      super("FullBright", "Increases the games gamma", Category.RENDERER, -1, false, true);
   }

   public void onEnable() {
      this.prevgamma = mc.field_1690.field_1840;
      mc.field_1690.field_1840 = 16.0D;
   }

   public void onDisable() {
      mc.field_1690.field_1840 = this.prevgamma;
   }
}
