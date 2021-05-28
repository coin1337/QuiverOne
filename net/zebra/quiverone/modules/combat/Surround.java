package net.zebra.quiverone.modules.combat;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;

public class Surround extends Module {
   private BooleanSetting sendMessage = new BooleanSetting("Send Message", this, true);
   private BooleanSetting autoDisable = new BooleanSetting("AutoDisable", this, true);
   int obiSlot;
   int oldSlot;

   public Surround() {
      super("Surround", "Surrounds you in obsidian", Category.COMBAT, -1, false, true);
      this.addSetting(new Setting[]{this.autoDisable, this.sendMessage});
   }

   public void onUpdate() {
      if (mc.field_1724 != null) {
         this.oldSlot = mc.field_1724.field_7514.field_7545;
         this.obiSlot = -1;
         if (this.autoDisable.isEnabled() && !mc.field_1724.method_24828()) {
            if (this.sendMessage.isEnabled()) {
               sendMessage("Surround has been " + class_124.field_1079 + "Disabled");
            }

            this.disable();
         } else {
            ArrayList<class_2338> positions = new ArrayList();
            positions.add(mc.field_1724.method_24515().method_10095());
            positions.add(mc.field_1724.method_24515().method_10078());
            positions.add(mc.field_1724.method_24515().method_10072());
            positions.add(mc.field_1724.method_24515().method_10067());

            for(int i = 0; i < 9; ++i) {
               if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8281)) {
                  this.obiSlot = i;
                  break;
               }
            }

            if (this.obiSlot == -1) {
               if (this.sendMessage.isEnabled()) {
                  sendMessage("You have no obsidian");
               }

               this.disable();
            } else {
               Iterator var8 = positions.iterator();

               while(true) {
                  class_2338 pos;
                  do {
                     if (!var8.hasNext()) {
                        return;
                     }

                     pos = (class_2338)var8.next();
                  } while(!mc.field_1687.method_8320(pos).method_26207().method_15800());

                  class_2350[] var4 = class_2350.values();
                  int var5 = var4.length;

                  for(int var6 = 0; var6 < var5; ++var6) {
                     class_2350 direction = var4[var6];
                     if (!mc.field_1687.method_8320(pos.method_10093(direction)).method_26207().method_15800()) {
                        mc.field_1724.field_7514.field_7545 = this.obiSlot;
                        mc.field_1761.method_2896(mc.field_1724, mc.field_1687, class_1268.field_5808, new class_3965(class_243.method_24954(pos), direction, pos, false));
                        mc.field_1724.field_7514.field_7545 = this.oldSlot;
                     }
                  }
               }
            }
         }
      }
   }
}
