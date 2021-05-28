package net.zebra.quiverone.modules.renderer;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;

public class NoRender extends Module {
   public BooleanSetting blockBreaking = new BooleanSetting("BlockBreaking", this, true);

   public NoRender() {
      super("NoRender", "Stops rendering trash", Category.RENDERER, -1, false, true);
      this.addSetting(new Setting[0]);
   }
}
