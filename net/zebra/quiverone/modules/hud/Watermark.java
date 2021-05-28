package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import net.zebra.quiverone.Main;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;

public class Watermark extends HUDModule {
   public ColorSetting color = new ColorSetting("Color", this, true);

   public Watermark() {
      super("Watermark", "Renders a watermark", new Point(-3, 1), Category.HUD, true);
   }

   public void populate(Theme theme) {
      this.component = new ListComponent(this.getName(), theme.getPanelRenderer(), this.position, new Watermark.WatermarkList());
   }

   private class WatermarkList implements HUDList {
      private WatermarkList() {
      }

      public int getSize() {
         return 1;
      }

      public String getItem(int index) {
         return Main.mod_name + " " + Main.mod_version;
      }

      public Color getItemColor(int index) {
         return Watermark.this.color.getValue();
      }

      public boolean sortUp() {
         return false;
      }

      public boolean sortRight() {
         return false;
      }

      // $FF: synthetic method
      WatermarkList(Object x1) {
         this();
      }
   }
}
