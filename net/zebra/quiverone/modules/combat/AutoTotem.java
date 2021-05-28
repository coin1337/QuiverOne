package net.zebra.quiverone.modules.combat;

import net.minecraft.class_1713;
import net.minecraft.class_1802;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class AutoTotem extends Module {
   public NumberSetting health = new NumberSetting("Health", this, 36.0D, 0.1D, 36.0D, 0.1D);
   public ModeSetting mode = new ModeSetting("Mode", this, "Crystal", new String[]{"Crystal", "Totem", "Ench Gapple", "Gapple"});

   public AutoTotem() {
      super("AutoTotem", "Automatically puts a totem in your offhand", Category.COMBAT, -1, false, true);
      this.addSetting(new Setting[]{this.health, this.mode});
   }

   public void onUpdate() {
      if (mc.field_1724 != null) {
         Boolean found = false;
         int i;
         if (this.mode.is("Crystal")) {
            for(i = 9; i <= 36; ++i) {
               if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8301)) {
                  found = true;
                  break;
               }
            }

            if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8301) && found) {
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, mc.field_1724);
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, mc.field_1724);
            }

            if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8288) && (double)(mc.field_1724.method_6032() + mc.field_1724.method_6067()) < this.health.getValue()) {
               for(i = 9; i <= 36; ++i) {
                  if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8288)) {
                     found = true;
                     break;
                  }
               }

               if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8288) && found) {
                  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, mc.field_1724);
                  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, mc.field_1724);
               }
            }
         }

         if (this.mode.is("Ench Gapple")) {
            for(i = 9; i <= 36; ++i) {
               if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8367)) {
                  found = true;
                  break;
               }
            }

            if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8367) && found) {
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, mc.field_1724);
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, mc.field_1724);
            }

            if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8288) && (double)(mc.field_1724.method_6032() + mc.field_1724.method_6067()) < this.health.getValue()) {
               for(i = 9; i <= 36; ++i) {
                  if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8288)) {
                     found = true;
                     break;
                  }
               }

               if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8288) && found) {
                  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, mc.field_1724);
                  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, mc.field_1724);
               }
            }
         }

         if (this.mode.is("Gapple")) {
            for(i = 9; i <= 36; ++i) {
               if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8463)) {
                  found = true;
                  break;
               }
            }

            if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8463) && found) {
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, mc.field_1724);
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, mc.field_1724);
            }

            if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8288) && (double)(mc.field_1724.method_6032() + mc.field_1724.method_6067()) < this.health.getValue()) {
               for(i = 9; i <= 36; ++i) {
                  if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8288)) {
                     found = true;
                     break;
                  }
               }

               if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8288) && found) {
                  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, mc.field_1724);
                  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, mc.field_1724);
               }
            }
         }

         if (this.mode.is("Totem") && !mc.field_1724.method_6079().method_7909().equals(class_1802.field_8288) && mc.field_1724.method_6032() + mc.field_1724.method_6067() < 36.0F) {
            for(i = 9; i <= 36; ++i) {
               if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8288)) {
                  found = true;
                  break;
               }
            }

            if (!mc.field_1724.method_6079().method_7909().equals(class_1802.field_8288) && found) {
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, mc.field_1724);
               mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, mc.field_1724);
            }
         }

      }
   }
}
