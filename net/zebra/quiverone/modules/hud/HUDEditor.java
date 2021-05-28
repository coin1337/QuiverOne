package net.zebra.quiverone.modules.hud;

import net.zebra.quiverone.Main;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class HUDEditor extends Module {
   public HUDEditor() {
      super("Hud Editor", "Allows you to drag hud components", Category.HUD, -1, false, false);
   }

   public void onEnable() {
      Main.instance.clickGUI.enterHUDEditor();
      this.disable();
   }
}
