package net.zebra.quiverone.modules.world;

import net.minecraft.class_1934;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;

public class Gamemode extends Module {
   public ModeSetting mode = new ModeSetting("Mode", this, "Creative", new String[]{"Creative", "Survival", "Spectator", "Adventure"});

   public Gamemode() {
      super("Gamemode", "ClientSide gamemode", Category.WORLD, -1, false, true);
      this.addSetting(new Setting[]{this.mode});
   }

   public void onEnable() {
      if (this.mode.is("Creative")) {
         mc.field_1724.method_7336(class_1934.field_9220);
      }

      if (this.mode.is("Survival")) {
         mc.field_1724.method_7336(class_1934.field_9215);
      }

      if (this.mode.is("Adventure")) {
         mc.field_1724.method_7336(class_1934.field_9216);
      }

      if (this.mode.is("Spectator")) {
         mc.field_1724.method_7336(class_1934.field_9219);
      }

   }

   public void onDisable() {
   }
}
