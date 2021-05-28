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
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;

public class IllegalsGenerator extends Module {
   public ModeSetting mode = new ModeSetting("Item", this, "CommandBlock", new String[]{"CommandBlock", "DragonEgg", "CommandMinecart", "Spawner", "Barrier"});

   public IllegalsGenerator() {
      super("IllegalsGenerator", "Spawns in illegal items", Category.CRASH, -1, false, true);
      this.addSetting(new Setting[]{this.mode});
   }

   public void onEnable() {
      if (!mc.field_1724.field_7503.field_7477) {
         sendMessage("Creative mode only");
         this.disable();
      } else {
         String illgealItem = null;
         if (this.mode.is("DragonEgg")) {
            illgealItem = "dragon_egg";
         }

         if (this.mode.is("CommandMinecart")) {
            illgealItem = "command_block_minecart";
         }

         if (this.mode.is("CommandBlock")) {
            illgealItem = "command_block";
         }

         if (this.mode.is("Spawner")) {
            illgealItem = "spawner";
         }

         if (this.mode.is("Barrier")) {
            illgealItem = "barrier";
         }

         class_1792 item = (class_1792)class_2378.field_11142.method_10223(new class_2960(illgealItem));
         class_1799 stack = new class_1799(item, 1);
         stack.method_7980(this.createNBT());
         this.placeStackInHotbar(stack);
         this.disable();
      }
   }

   private class_2487 createNBT() {
      try {
         return class_2522.method_10718("{display:{Lore:['\"§ඞ\"','\"§rzebrahack\"','\"§rsus在顶部ez\"'],Name:'{\"text\":\"§rZebrahack在顶部sus\"}'},EntityTag:{CustomName:\"TEST\",id:\"item\",CustomNameVisible:1}}");
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
