package net.zebra.quiverone.modules.movement;

import net.minecraft.class_1268;
import net.minecraft.class_1747;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class Scaffold extends Module {
   public Scaffold() {
      super("Scaffold", "Places blocks under you", Category.MOVEMENT, -1, false, true);
   }

   public void onUpdate() {
      int original_slot = mc.field_1724.field_7514.field_7545;

      for(int i = 0; i < 9; ++i) {
         if (mc.field_1724.field_7514.method_5438(i).method_7909() instanceof class_1747) {
            mc.field_1724.field_7514.field_7545 = i;
         }
      }

      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_2338 pos = mc.field_1724.method_24515().method_10074();
         if (mc.field_1687.method_8320(pos).method_26207().method_15800()) {
            mc.field_1761.method_2896(mc.field_1724, mc.field_1687, class_1268.field_5808, new class_3965(class_243.method_24954(pos), class_2350.field_11033, pos, false));
            mc.field_1724.method_6104(class_1268.field_5808);
         }

         mc.field_1724.field_7514.field_7545 = original_slot;
      } else {
         this.disable();
      }
   }
}
