package net.zebra.quiverone.modules.combat;

import com.google.common.collect.Streams;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class AnchorAura extends Module {
   public NumberSetting delay = new NumberSetting("Delay", this, 2.0D, 0.0D, 40.0D, 0.1D);
   public NumberSetting range = new NumberSetting("Range", this, 4.0D, 1.0D, 10.0D, 0.1D);
   int anchorSlot = -1;
   int glowStoneSlot = -1;
   int oldSlot = -1;
   int ticks = 0;

   public AnchorAura() {
      super("AnchorAura", "Automatically moves you into a hole", Category.COMBAT, -1, false, true);
   }

   public void onUpdate() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         for(int i = 0; i < 9; ++i) {
            if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_23141)) {
               this.anchorSlot = i;
            } else if (mc.field_1724.field_7514.method_5438(i).method_7909().equals(class_1802.field_8801)) {
               this.glowStoneSlot = i;
            }
         }

         if (this.anchorSlot != -1 && this.glowStoneSlot != -1) {
            if ((double)this.ticks != this.delay.getValue()) {
               ++this.ticks;
            } else {
               this.ticks = 0;

               try {
                  class_1657 player = (class_1657)((List)Streams.stream(mc.field_1687.method_18112()).filter((e) -> {
                     return e instanceof class_1657 && (double)mc.field_1724.method_5739(e) <= this.range.getValue() && e != mc.field_1724;
                  }).collect(Collectors.toList())).get(0);
                  class_2350[] var2 = class_2350.values();
                  int var3 = var2.length;

                  for(int var4 = 0; var4 < var3; ++var4) {
                     class_2350 direction = var2[var4];
                     class_2338 blockPos = null;
                     if (!(player.method_24515().method_10268(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), true) < 6.0D) || mc.field_1687.method_8320(player.method_24515()).method_26204() != class_2246.field_23152 && mc.field_1687.method_8320(player.method_24515()).method_26204() != class_2246.field_10124 && mc.field_1687.method_8320(player.method_24515()).method_26207().method_15800()) {
                        if (player.method_24515().method_10093(direction).method_10268(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), true) < 6.0D && (mc.field_1687.method_8320(player.method_24515().method_10093(direction)).method_26204() == class_2246.field_23152 || mc.field_1687.method_8320(player.method_24515().method_10093(direction)).method_26207().method_15800())) {
                           blockPos = player.method_24515().method_10093(direction);
                        }
                     } else {
                        blockPos = player.method_24515();
                     }

                     if (blockPos != null) {
                        mc.field_1761.method_2896(mc.field_1724, mc.field_1687, class_1268.field_5808, new class_3965(class_243.method_24954(blockPos), class_2350.field_11033, blockPos, false));
                        if (mc.field_1687.method_8320(blockPos).method_26204().equals(class_2246.field_23152)) {
                           mc.field_1724.field_7514.field_7545 = this.glowStoneSlot;
                           mc.field_1761.method_2896(mc.field_1724, mc.field_1687, class_1268.field_5808, new class_3965(class_243.method_24954(blockPos), class_2350.field_11033, blockPos, true));
                           mc.field_1761.method_2896(mc.field_1724, mc.field_1687, class_1268.field_5810, new class_3965(class_243.method_24954(blockPos), class_2350.field_11033, blockPos, true));
                        }

                        mc.field_1724.field_7514.field_7545 = this.oldSlot;
                        break;
                     }
                  }
               } catch (Exception var7) {
               }

            }
         } else {
            this.disable();
         }
      } else {
         this.disable();
      }
   }
}
