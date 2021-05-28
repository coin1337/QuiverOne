package net.zebra.quiverone.modules.movement;

import net.minecraft.class_243;
import net.minecraft.class_746;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class Flight extends Module {
   public ModeSetting mode = new ModeSetting("Mode", this, "Vanilla", new String[]{"Vanilla", "Creative"});
   public NumberSetting flyingSpeed = new NumberSetting("Speed", this, 1.0D, 0.0D, 10.0D, 0.1D);

   public Flight() {
      super("Flight", "Makes you creative fly", Category.MOVEMENT, -1, false, true);
      this.addSetting(new Setting[]{this.mode, this.flyingSpeed});
   }

   public void onDisable() {
      mc.field_1724.field_7503.field_7478 = false;
   }

   public void onUpdate() {
      class_746 player = mc.field_1724;
      if (this.mode.is("Vanilla")) {
         player.field_7503.field_7479 = false;
         player.field_6281 = (float)this.flyingSpeed.getPrecision();
         player.method_18800(0.0D, 0.0D, 0.0D);
         class_243 velcity = player.method_18798();
         if (mc.field_1690.field_1903.method_1434()) {
            player.method_18799(velcity.method_1031(0.0D, this.flyingSpeed.getValue(), 0.0D));
         }

         if (mc.field_1690.field_1832.method_1434()) {
            player.method_18799(velcity.method_1023(0.0D, this.flyingSpeed.getValue(), 0.0D));
         }
      }

      if (this.mode.is("Creative")) {
         mc.field_1724.field_7503.field_7478 = true;
      }

   }
}
