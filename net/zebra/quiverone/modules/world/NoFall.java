package net.zebra.quiverone.modules.world;

import net.minecraft.class_2828;
import net.minecraft.class_746;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class NoFall extends Module {
   public NoFall() {
      super("NoFall", "Stops you from taking fall damage", Category.WORLD, -1, false, true);
   }

   public void onUpdate() {
      class_746 player = mc.field_1724;
      if (!(player.field_6017 <= (float)(player.method_6128() ? 1 : 2))) {
         if (!player.method_6128() || !player.method_5715() || this.isFallingFastEnoughToCauseDamage(player)) {
            player.field_3944.method_2883(new class_2828(true));
         }
      }
   }

   private boolean isFallingFastEnoughToCauseDamage(class_746 player) {
      return player.method_18798().field_1351 < -0.5D;
   }
}
