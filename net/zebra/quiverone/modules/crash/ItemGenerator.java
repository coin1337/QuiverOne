package net.zebra.quiverone.modules.crash;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Random;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2522;
import net.minecraft.class_2873;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class ItemGenerator extends Module {
   NumberSetting stackSize = new NumberSetting("Size", this, 64.0D, 1.0D, 64.0D, 1.0D);
   NumberSetting speed = new NumberSetting("Speed", this, 1.0D, 1.0D, 36.0D, 1.0D);
   private final Random random = new Random();
   class_1792 item;

   public ItemGenerator() {
      super("ItemGenerator", "Grabs items from creative and drops them", Category.CRASH, -1, false, true);
      this.addSetting(new Setting[]{this.speed});
   }

   public void onUpdate() {
      int stacks = this.speed.getPrecision();

      int i;
      for(i = 9; i < 9 + stacks; ++i) {
         this.item = (class_1792)class_2378.field_11142.method_10240(this.random);
         class_1799 stack = new class_1799(this.item, this.stackSize.getPrecision());
         stack.method_7980(this.createNBT());
         class_2873 packet = new class_2873(i, stack);
         mc.field_1724.field_3944.method_2883(packet);
      }

      for(i = 9; i < 9 + stacks; ++i) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7795, mc.field_1724);
      }

   }

   private class_2487 createNBT() {
      try {
         return class_2522.method_10718("{display:{Lore:['\"§ඞ\"','\"§rzebrahack\"','\"§rsus在顶部ez\"'],Name:'{\"text\":\"§rZebrahack在顶部sus\"}'},EntityTag:{CustomName:\"TEST\",id:\"item\",CustomNameVisible:1}}");
      } catch (CommandSyntaxException var2) {
         throw new RuntimeException(var2);
      }
   }
}
