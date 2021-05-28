package net.zebra.quiverone.modules.world;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;

public class NoWeather extends Module {
   private ModeSetting weather = new ModeSetting("Type", this, "Clear", new String[]{"Clear", "Rain", "Thunder"});

   public NoWeather() {
      super("No Weather", "Removes Weather", Category.WORLD, -1, false, true);
      this.addSetting(new Setting[]{this.weather});
   }

   public void onEnable() {
      super.onEnable();
      if (this.weather.is("Clear")) {
         mc.field_1687.method_8519(0.0F);
      }

      if (this.weather.is("Rain")) {
         mc.field_1687.method_8519(1.0F);
      }

      if (this.weather.is("Thunder")) {
         mc.field_1687.method_8519(2.0F);
      }

   }
}
