package net.zebra.quiverone.modules.movement;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;

public class NoSlow extends Module {
   public BooleanSetting handSwing = new BooleanSetting("Hand Swing", this, true);
   public BooleanSetting eating = new BooleanSetting("Eating", this, true);
   public BooleanSetting swiming = new BooleanSetting("Water", this, true);

   public NoSlow() {
      super("No Slow", "No slow down when doing tasks", Category.MOVEMENT, -1, false, true);
      this.addSetting(new Setting[]{this.handSwing, this.swiming, this.eating});
   }

   public void onUpdate() {
      boolean var10000;
      if (this.handSwing.isEnabled() && mc.field_1724.field_6252) {
         mc.field_1724.field_6250 = 5.0F;
         if ((!(mc.field_1724.field_3913.field_3905 > 0.0F) || !(mc.field_1724.field_3913.field_3907 > 0.0F)) && (!(mc.field_1724.field_3913.field_3905 > 0.0F) || mc.field_1724.method_5715())) {
            var10000 = false;
         } else {
            var10000 = true;
         }

         mc.field_1724.method_5728(true);
      }

      if (this.swiming.isEnabled() && mc.field_1724.method_5869()) {
         mc.field_1724.field_6250 = 5.0F;
         if ((!(mc.field_1724.field_3913.field_3905 > 0.0F) || !(mc.field_1724.field_3913.field_3907 > 0.0F)) && (!(mc.field_1724.field_3913.field_3905 > 0.0F) || mc.field_1724.method_5715())) {
            var10000 = false;
         } else {
            var10000 = true;
         }

         mc.field_1724.method_5728(true);
      }

      if (this.eating.isEnabled()) {
      }

   }
}
