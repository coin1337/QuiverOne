package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.ModuleManager;
import net.zebra.quiverone.modules.combat.KillAura;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;

public class ActiveModules extends HUDModule {
   private BooleanSetting sortUp = new BooleanSetting("Sort Up", this, true);
   private BooleanSetting sortRight = new BooleanSetting("Sort Right", this, true);
   private ActiveModules.ActiveModuleList list = new ActiveModules.ActiveModuleList();
   public ColorSetting colorSetting = new ColorSetting("Color", this, true);

   public ActiveModules() {
      super("ActiveModules", "Shows modules currently in use", new Point(400, 400), Category.HUD, true);
      this.addSetting(new Setting[]{this.sortRight, this.sortUp});
   }

   public void populate(Theme theme) {
      this.component = new ListComponent(this.getName(), theme.getPanelRenderer(), this.position, this.list);
   }

   public void onUpdate() {
      this.list.activeModules.clear();
      Iterator var1 = ModuleManager.getModules().iterator();

      while(var1.hasNext()) {
         Module module = (Module)var1.next();
         if (KillAura.inUse && module.getName().equalsIgnoreCase("KillAura")) {
            this.list.activeModules.add(module);
         }
      }

   }

   private class ActiveModuleList implements HUDList {
      public ArrayList<Module> activeModules;

      private ActiveModuleList() {
         this.activeModules = new ArrayList();
      }

      public int getSize() {
         return this.activeModules.size();
      }

      public String getItem(int i) {
         Module module = (Module)this.activeModules.get(i);
         return module.getName();
      }

      public Color getItemColor(int i) {
         return ActiveModules.this.colorSetting.getValue();
      }

      public boolean sortUp() {
         return ActiveModules.this.sortUp.isEnabled();
      }

      public boolean sortRight() {
         return ActiveModules.this.sortRight.isEnabled();
      }

      // $FF: synthetic method
      ActiveModuleList(Object x1) {
         this();
      }
   }
}
