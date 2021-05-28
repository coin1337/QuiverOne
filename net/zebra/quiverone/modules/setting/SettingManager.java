package net.zebra.quiverone.modules.setting;

import java.util.ArrayList;
import java.util.Iterator;
import net.zebra.quiverone.Main;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.ModuleManager;

public class SettingManager {
   public static ArrayList<Setting> settings;

   public SettingManager() {
      settings = new ArrayList();
   }

   public static void rSetting(Setting in) {
      settings.add(in);
   }

   public static ArrayList<Setting> getSettings() {
      return settings;
   }

   public static ArrayList<Setting> getSettingsByMod(Module mod) {
      ArrayList<Setting> out = new ArrayList();
      Iterator var2 = getSettings().iterator();

      while(var2.hasNext()) {
         Setting s = (Setting)var2.next();
         if (s.parent.equals(mod)) {
            out.add(s);
         }
      }

      if (out.isEmpty()) {
         return null;
      } else {
         return out;
      }
   }

   public static Setting getSettingByName(Module mod, String name) {
      ModuleManager var10000 = Main.instance.moduleManager;
      Iterator var2 = ModuleManager.getModules().iterator();

      while(var2.hasNext()) {
         Module module = (Module)var2.next();
         Iterator var4 = module.getSettings().iterator();

         while(var4.hasNext()) {
            Setting set = (Setting)var4.next();
            if (set.name.equalsIgnoreCase(name) && set.parent == mod) {
               return set;
            }
         }
      }

      return null;
   }
}
