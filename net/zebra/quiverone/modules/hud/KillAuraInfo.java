package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import net.minecraft.class_124;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.ModuleManager;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;

public class KillAuraInfo extends HUDModule {
   private ModeSetting mode = new ModeSetting("Mode", this, "Left", new String[]{"Left", "Right"});
   private ColorSetting color = new ColorSetting("Color", this, true);
   String text;
   Boolean isOn;

   public KillAuraInfo() {
      super("AuraInfo", "Shows information about the kill aura", new Point(-1, 10), Category.HUD, false);
   }

   public void populate(Theme theme) {
      this.component = new ListComponent(this.getName(), theme.getPanelRenderer(), this.position, new KillAuraInfo.KillAuraInfoList());
   }

   public void onUpdate() {
      this.isOn = ModuleManager.isModuleEnabled("KillAura");
      if (this.isOn) {
         this.text = class_124.field_1060 + "On";
      } else {
         this.text = class_124.field_1061 + "Off";
      }

   }

   private class KillAuraInfoList implements HUDList {
      private KillAuraInfoList() {
      }

      public int getSize() {
         return 1;
      }

      public String getItem(int i) {
         return "KA: " + KillAuraInfo.this.text;
      }

      public Color getItemColor(int i) {
         return KillAuraInfo.this.color.getValue();
      }

      public boolean sortUp() {
         return false;
      }

      public boolean sortRight() {
         return KillAuraInfo.this.mode.is("Right");
      }

      // $FF: synthetic method
      KillAuraInfoList(Object x1) {
         this();
      }
   }
}
