package net.zebra.quiverone.modules.setting.settings;

import java.awt.Color;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.utils.JColor;

public class ColorSetting extends Setting implements com.lukflug.panelstudio.settings.ColorSetting {
   private boolean rainbow;
   private JColor value;

   public ColorSetting(String name, Module parent, JColor value) {
      this.name = name;
      this.parent = parent;
      this.value = value;
   }

   public ColorSetting(String name, Module parent, boolean rainbow) {
      this.name = name;
      this.parent = parent;
      this.rainbow = rainbow;
   }

   public JColor getValue() {
      return this.rainbow ? JColor.fromHSB((float)(System.currentTimeMillis() % 7200L) / 7200.0F, 0.5F, 1.0F) : this.value;
   }

   public static int rainbow(int delay) {
      double rainbowState = Math.ceil((double)(System.currentTimeMillis() + (long)delay) / 20.0D);
      rainbowState %= 360.0D;
      return Color.getHSBColor((float)(rainbowState / 360.0D), 0.5F, 1.0F).getRGB();
   }

   public void setValue(boolean rainbow, JColor value) {
      this.rainbow = rainbow;
      this.value = value;
   }

   public long toInteger() {
      return (long)(this.value.getRGB() & -1);
   }

   public void fromInteger(long number) {
      this.value = new JColor(Math.toIntExact(number & -1L), true);
   }

   public JColor getColor() {
      return this.value;
   }

   public boolean getRainbow() {
      return this.rainbow;
   }

   public void setValue(Color value) {
      this.setValue(this.getRainbow(), new JColor(value));
   }

   public void setRainbow(boolean rainbow) {
      this.rainbow = rainbow;
   }
}
