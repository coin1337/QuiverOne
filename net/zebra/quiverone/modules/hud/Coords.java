package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;

public class Coords extends HUDModule {
   private BooleanSetting sort = new BooleanSetting("Right", this, false);
   private ColorSetting color = new ColorSetting("Color", this, true);

   public Coords() {
      super("Position", "Shows your coords", new Point(1, 500), Category.HUD, true);
   }

   public void populate(Theme theme) {
      this.component = new ListComponent(this.getName(), theme.getPanelRenderer(), this.position, new Coords.CoordsList());
      this.addSetting(new Setting[]{this.sort});
   }

   private class CoordsList implements HUDList {
      private CoordsList() {
      }

      public int getSize() {
         return 1;
      }

      public String getItem(int i) {
         return " x: " + Math.round(Module.mc.field_1724.method_19538().method_10216()) + " y: " + Math.round(Module.mc.field_1724.method_19538().method_10214()) + " z: " + Math.round(Module.mc.field_1724.method_19538().method_10215());
      }

      public Color getItemColor(int i) {
         return Coords.this.color.getValue();
      }

      public boolean sortUp() {
         return false;
      }

      public boolean sortRight() {
         return Coords.this.sort.isEnabled();
      }

      // $FF: synthetic method
      CoordsList(Object x1) {
         this();
      }
   }
}
