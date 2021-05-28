package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;

public class ServerIP extends HUDModule {
   private ColorSetting color = new ColorSetting("Color", this, true);

   public ServerIP() {
      super("ServerIP", "Displays the server info", new Point(-3, 10), Category.HUD, true);
   }

   public void populate(Theme theme) {
      this.component = new ListComponent(this.getName(), theme.getPanelRenderer(), this.position, new ServerIP.ServerInfoList());
   }

   private class ServerInfoList implements HUDList {
      private ServerInfoList() {
      }

      public int getSize() {
         return 1;
      }

      public String getItem(int i) {
         return "Server IP: " + ServerIP.this.getIP();
      }

      public Color getItemColor(int i) {
         return ServerIP.this.color.getValue();
      }

      public boolean sortUp() {
         return false;
      }

      public boolean sortRight() {
         return false;
      }

      // $FF: synthetic method
      ServerInfoList(Object x1) {
         this();
      }
   }
}
