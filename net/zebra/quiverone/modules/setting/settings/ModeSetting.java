package net.zebra.quiverone.modules.setting.settings;

import com.lukflug.panelstudio.settings.EnumSetting;
import java.util.Arrays;
import java.util.List;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;

public class ModeSetting extends Setting implements EnumSetting {
   public int index;
   public List<String> modes;

   public ModeSetting(String name, Module parent, String defaultMode, String... modes) {
      this.name = name;
      this.parent = parent;
      this.modes = Arrays.asList(modes);
      this.index = this.modes.indexOf(defaultMode);
   }

   public String getMode() {
      return (String)this.modes.get(this.index);
   }

   public void setMode(String mode) {
      this.index = this.modes.indexOf(mode);
   }

   public boolean is(String mode) {
      return this.index == this.modes.indexOf(mode);
   }

   public void cycle() {
      if (this.index < this.modes.size() - 1) {
         ++this.index;
      } else {
         this.index = 0;
      }

   }

   public String getValueName() {
      return (String)this.modes.get(this.index);
   }

   public void increment() {
      if (this.index < this.modes.size() - 1) {
         ++this.index;
      } else {
         this.index = 0;
      }

   }
}
