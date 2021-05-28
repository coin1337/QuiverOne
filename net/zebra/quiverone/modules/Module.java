package net.zebra.quiverone.modules;

import com.lukflug.panelstudio.settings.Toggleable;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.class_124;
import net.minecraft.class_2585;
import net.minecraft.class_310;
import net.zebra.quiverone.Main;
import net.zebra.quiverone.modules.setting.Setting;

public class Module implements Toggleable {
   public static class_310 mc = class_310.method_1551();
   private boolean isDrawn;
   private boolean isToggled;
   private String name;
   private String description;
   private String displayText;
   private Category category;
   private int keyCode;
   private ArrayList<Setting> settings = new ArrayList();

   public Module(String name, String description, Category category, int keyCode, boolean isToggled, boolean isDrawn) {
      this.name = name;
      this.description = description;
      this.category = category;
      this.keyCode = keyCode;
      this.isToggled = isToggled;
      this.isDrawn = isDrawn;
   }

   public Module(String name, Category category) {
      this.name = name;
      this.category = category;
   }

   public static void sendMessage(String message) {
      mc.field_1724.method_7353(new class_2585(class_124.field_1061 + "[" + Main.mod_name + "] " + message), false);
   }

   public String getIP() {
      if (mc.method_1542()) {
         return "SinglePlayer";
      } else {
         try {
            return mc.method_1558().field_3761;
         } catch (Exception var2) {
            return "MainMenu";
         }
      }
   }

   public void disable() {
      this.isToggled = false;
   }

   public void enable() {
      this.isToggled = true;
   }

   public void addSetting(Setting... settings) {
      this.settings.addAll(Arrays.asList(settings));
   }

   public void setToggled(boolean toggled) {
      if (toggled) {
         this.enable();
      } else {
         this.disable();
      }

   }

   public void addSettings(Setting... settings) {
      this.settings.addAll(Arrays.asList(settings));
   }

   public ArrayList<Setting> getSettings() {
      return this.settings;
   }

   public int getKeyCode() {
      return this.keyCode;
   }

   public String getName() {
      return this.name;
   }

   public String getDescription() {
      return this.description;
   }

   public boolean isDrawn() {
      return this.isDrawn;
   }

   public Category getCategory() {
      return this.category;
   }

   public String getDisplayText() {
      return this.displayText;
   }

   public void setCategory(Category category) {
      this.category = category;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setDisplayText(String displayText) {
      this.displayText = displayText;
   }

   public void setDrawn(boolean drawn) {
      this.isDrawn = drawn;
   }

   public void setKeyCode(int keyCode) {
      this.keyCode = keyCode;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setSettings(ArrayList<Setting> settings) {
      this.settings = settings;
   }

   public boolean isToggled() {
      return this.isToggled;
   }

   public void onEnable() {
   }

   public void onRender() {
   }

   public void onWorldRender() {
   }

   public void onDisable() {
   }

   public void onUpdate() {
   }

   public void toggle() {
      this.isToggled = !this.isToggled;
      if (this.isToggled) {
         this.onEnable();
      } else {
         this.onDisable();
      }

   }

   public boolean isOn() {
      return this.isToggled;
   }

   public boolean isEnabled() {
      return this.isToggled;
   }
}
