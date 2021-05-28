package net.zebra.quiverone.modules.renderer;

import net.zebra.quiverone.Main;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;
import net.zebra.quiverone.utils.JColor;

public class ClickGuiModule extends Module {
   public static ClickGuiModule INSTANCE;
   public NumberSetting animationSpeed = new NumberSetting("Animation Speed", this, 150.0D, 0.0D, 1000.0D, 50.0D);
   public NumberSetting scrolls = new NumberSetting("Scroll Speed", this, 10.0D, 0.0D, 100.0D, 1.0D);
   public ModeSetting scrollMode = new ModeSetting("Scroll", this, "Container", new String[]{"Container", "Screen"});
   public ModeSetting description = new ModeSetting("Description Type", this, "mouse", new String[]{"mouse", "fixed"});
   public ColorSetting enabledColor = new ColorSetting("EnabledColor", this, new JColor(208, 43, 41, 255));
   public ColorSetting backgroundColor = new ColorSetting("Background Color", this, new JColor(44, 47, 51, 255));
   public ColorSetting settingBackgroundColor = new ColorSetting("Setting Color", this, new JColor(25, 29, 32, 255));
   public ColorSetting outlineColor = new ColorSetting("Border Color", this, new JColor(255, 0, 0, 255));
   public ColorSetting CategoryColor = new ColorSetting("Category Color", this, new JColor(255, 255, 255, 255));
   public NumberSetting opacity = new NumberSetting("Opacity", this, 255.0D, 0.0D, 255.0D, 5.0D);

   public ClickGuiModule() {
      super("ClickGUI", "Shows the ClickGUI", Category.RENDERER, 344, false, false);
      this.addSetting(new Setting[]{this.scrollMode, this.enabledColor, this.backgroundColor, this.settingBackgroundColor});
      INSTANCE = this;
   }

   public void onEnable() {
      super.onEnable();
      Main.instance.clickGUI.enterGUI();
   }

   public void onDisable() {
      super.onDisable();
   }

   public static Module getClickGuiModule() {
      return INSTANCE;
   }
}
