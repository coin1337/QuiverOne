package net.zebra.quiverone.modules.world;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class Spammer extends Module {
   public Spammer() {
      super("Spammer", "Spams chat with messages", Category.WORLD, -1, false, true);
   }

   public void onEnable() {
      mc.field_1724.method_3142("Thas is a retard!  Default Message!");
      this.disable();
   }
}
