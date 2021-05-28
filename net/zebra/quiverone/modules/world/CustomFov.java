package net.zebra.quiverone.modules.world;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class CustomFov extends Module {
   private NumberSetting fov = new NumberSetting("Fov", this, 30.0D, 0.0D, 200.0D, 1.0D);
   private double prevFOV;

   public CustomFov() {
      super("CustomFov", "Zooms in", Category.WORLD, -1, false, true);
      this.addSetting(new Setting[]{this.fov});
   }

   public void onEnable() {
      this.prevFOV = mc.field_1690.field_1826;
      mc.field_1690.field_1826 = this.fov.getValue();
   }

   public void onUpdate() {
      this.prevFOV = mc.field_1690.field_1826;
      mc.field_1690.field_1826 = this.fov.getValue();
   }

   public void onDisable() {
      mc.field_1690.field_1826 = this.prevFOV;
   }
}
