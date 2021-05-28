package net.zebra.quiverone.modules.combat;

import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class Burrow extends Module {
   class_2338 playerPos;
   int oldSlot;

   public Burrow() {
      super("Burrow", "Glitches you inside a block", Category.COMBAT, -1, false, true);
   }

   public void onEnable() {
      this.oldSlot = mc.field_1724.field_7514.field_7545;
      this.playerPos = mc.field_1724.method_24515();
      boolean found = false;

      for(int i = 0; i < 9; ++i) {
         if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8281)) {
            mc.field_1724.field_7514.field_7545 = i;
            found = true;
            break;
         }
      }

      if (!mc.field_1687.method_8320(this.playerPos).method_26204().equals(class_2246.field_10540) && found) {
         mc.field_1724.method_6043();
      } else {
         this.disable();
      }
   }

   public void onUpdate() {
      if (mc.field_1724 != null) {
         if (mc.field_1724.method_23318() > (double)this.playerPos.method_10264() + 1.04D) {
            mc.field_1761.method_2896(mc.field_1724, mc.field_1687, class_1268.field_5808, new class_3965(class_243.method_24954(this.playerPos), class_2350.field_11033, this.playerPos, false));
            mc.field_1724.method_6104(class_1268.field_5808);
            mc.field_1724.method_6043();
            this.disable();
            mc.field_1724.field_7514.field_7545 = this.oldSlot;
         }

      }
   }
}
