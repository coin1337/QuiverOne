package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;

public class Welcomer extends HUDModule {
   private ColorSetting color = new ColorSetting("Color", this, true);

   public Welcomer() {
      super("Welcomer", "Welcomes you :)", new Point(400, 4), Category.HUD, false);
   }

   public void populate(Theme theme) {
      this.component = new ListComponent(this.getName(), theme.getPanelRenderer(), this.position, new Welcomer.WelcomerList());
   }

   private class WelcomerList implements HUDList {
      private WelcomerList() {
      }

      public int getSize() {
         return 1;
      }

      public String getItem(int i) {
         return "Welcome " + Module.mc.method_1548().method_1676() + ", Looking good!";
      }

      public Color getItemColor(int i) {
         return Welcomer.this.color.getValue();
      }

      public boolean sortUp() {
         return false;
      }

      public boolean sortRight() {
         return false;
      }

      // $FF: synthetic method
      WelcomerList(Object x1) {
         this();
      }
   }
}
