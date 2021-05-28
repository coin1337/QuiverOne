package net.zebra.quiverone.modules.combat;

import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class TriggerBot extends Module {
   private class_1297 target;

   public TriggerBot() {
      super("TriggerBot", "Attacks entitys in your crosshair", Category.COMBAT, -1, false, true);
   }

   public void onUpdate() {
      this.target = null;
      if (!(mc.field_1724.method_6032() <= 0.0F) && !(mc.field_1724.method_7261(0.5F) < 1.0F)) {
         if (mc.field_1692 instanceof class_1309) {
            if (!(((class_1309)mc.field_1692).method_6032() <= 0.0F)) {
               this.target = mc.field_1692;
               this.attack(this.target);
            }
         }
      }
   }

   private void attack(class_1297 entity) {
      mc.field_1761.method_2918(mc.field_1724, entity);
      mc.field_1724.method_6104(class_1268.field_5808);
   }
}
