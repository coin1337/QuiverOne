package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;

public class Notifications extends HUDModule {
   public static Notifications instance = new Notifications();
   String messages;
   private ColorSetting color = new ColorSetting("Color", this, true);

   public Notifications() {
      super("Notifications", "Renders Notifications", new Point(500, 300), Category.HUD, false);
   }

   public void message(String message) {
      message = this.messages;
   }

   public void populate(Theme theme) {
      this.component = new ListComponent(this.getName(), theme.getPanelRenderer(), this.position, new Notifications.NotificationsList());
   }

   private class NotificationsList implements HUDList {
      private NotificationsList() {
      }

      public int getSize() {
         return 1;
      }

      public String getItem(int i) {
         return Notifications.this.messages;
      }

      public Color getItemColor(int i) {
         return Notifications.this.color.getValue();
      }

      public boolean sortUp() {
         return true;
      }

      public boolean sortRight() {
         return false;
      }

      // $FF: synthetic method
      NotificationsList(Object x1) {
         this();
      }
   }
}
