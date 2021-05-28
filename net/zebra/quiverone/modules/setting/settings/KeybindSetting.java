package net.zebra.quiverone.modules.setting.settings;

import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import org.lwjgl.glfw.GLFW;

public class KeybindSetting extends Setting implements com.lukflug.panelstudio.settings.KeybindSetting {
   Module module;
   public int code;

   public int getKey() {
      return this.module.getKeyCode();
   }

   public String getKeyName() {
      return GLFW.glfwGetKeyName(this.code, this.code);
   }

   public void setKey(int key) {
      this.code = this.module.getKeyCode();
   }
}
