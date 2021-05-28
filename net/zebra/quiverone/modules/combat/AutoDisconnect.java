package net.zebra.quiverone.modules.combat;

import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class AutoDisconnect extends Module {
   private NumberSetting health = new NumberSetting("Health", this, 10.0D, 0.1D, 36.0D, 0.1D);
   private BooleanSetting disable = new BooleanSetting("Disable", this, true);

   public AutoDisconnect() {
      super("AutoDisconnect", "Automatically disconnects at the specified health", Category.COMBAT, -1, false, true);
      this.addSetting(new Setting[]{this.health, this.disable});
   }

   public void onUpdate() {
      if (mc.field_1724 != null) {
         if ((double)(mc.field_1724.method_6032() + mc.field_1724.method_6067()) < this.health.getValue()) {
            mc.field_1687.method_8525();
            if (this.disable.isEnabled()) {
               this.disable();
            }
         }

      }
   }
}
