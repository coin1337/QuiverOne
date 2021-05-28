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
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;

public class ArrayListHud extends HUDModule {
   private BooleanSetting sortUp = new BooleanSetting("Sort Up", this, true);
   private BooleanSetting sortRight = new BooleanSetting("Sort Right", this, true);
   private ArrayListHud.ModuleList list = new ArrayListHud.ModuleList();
   private ColorSetting colorSetting = new ColorSetting("Color", this, true);

   public ArrayListHud() {
      super("ArrayList", "Shows enabled modules", new Point(300, 300), Category.HUD, true);
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
         if (module.isOn() && module.isDrawn()) {
            this.list.activeModules.add(module);
         }
      }

   }

   private class ModuleList implements HUDList {
      public ArrayList<Module> activeModules;

      private ModuleList() {
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
         return ArrayListHud.this.colorSetting.getValue();
      }

      public boolean sortUp() {
         return ArrayListHud.this.sortUp.isEnabled();
      }

      public boolean sortRight() {
         return ArrayListHud.this.sortRight.isEnabled();
      }

      // $FF: synthetic method
      ModuleList(Object x1) {
         this();
      }
   }
}
