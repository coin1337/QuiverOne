package net.zebra.quiverone.modules.movement;

import net.minecraft.class_746;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class Speed extends Module {
   public ModeSetting mode = new ModeSetting("Mode", this, "BunnyHop", new String[]{"BunnyHop"});
   public NumberSetting speed = new NumberSetting("Speed", this, 1.0D, 0.0D, 50.0D, 0.1D);

   public Speed() {
      super("Speed", "Go faster", Category.MOVEMENT, -1, false, true);
      this.addSetting(new Setting[]{this.speed, this.mode});
   }

   public void onUpdate() {
      if (this.mode.is("BunnyHop")) {
         class_746 player = mc.field_1724;
         if (!player.method_24828() || player.method_5715()) {
            return;
         }

         player.method_6043();
         player.method_6125((float)this.speed.getPrecision());
      }

   }
}
