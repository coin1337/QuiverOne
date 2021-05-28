package net.zebra.quiverone.modules.setting.settings;

import com.lukflug.panelstudio.settings.Toggleable;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;

public class BooleanSetting extends Setting implements Toggleable {
   public boolean enabled;

   public BooleanSetting(String name, Module parent, boolean enabled) {
      this.name = name;
      this.parent = parent;
      this.enabled = enabled;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public void toggle() {
      this.enabled = !this.enabled;
   }

   public boolean isOn() {
      return this.isEnabled();
   }
}
