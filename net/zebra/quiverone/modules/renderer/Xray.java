package net.zebra.quiverone.modules.renderer;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class Xray extends Module {
   public Xray() {
      super("Xray", "Shows ores, portals and storage", Category.RENDERER, -1, false, true);
   }

   public void onEnable() {
      mc.field_1769.method_3279();
   }

   public void onDisable() {
      mc.field_1769.method_3279();
   }
}
