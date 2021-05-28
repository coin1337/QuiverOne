package net.zebra.quiverone.modules.crash;

import net.minecraft.class_124;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.hud.Notifications;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class TestModule extends Module {
   private BooleanSetting autoDisable = new BooleanSetting("AutoDisable", this, true);
   private BooleanSetting sendMessage = new BooleanSetting("Send Message", this, true);
   public NumberSetting speed = new NumberSetting("Speed", this, 100.0D, 1.0D, 300.0D, 1.0D);

   public TestModule() {
      super("Test Module", "Attempts to lag the server", Category.CRASH, -1, false, true);
      this.addSetting(new Setting[]{this.speed, this.autoDisable, this.sendMessage});
   }

   public void onUpdate() {
      if (this.sendMessage.isEnabled()) {
         Notifications.instance.message("Attempted to lag " + class_124.field_1079 + this.getIP() + class_124.field_1061 + " using" + class_124.field_1077 + " Test Module");
         sendMessage("Attempted to lag " + class_124.field_1079 + this.getIP() + class_124.field_1061 + " using" + class_124.field_1077 + " Test Module");
      }

      if (this.autoDisable.isEnabled()) {
         this.disable();
      }

   }
}
