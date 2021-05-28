package net.zebra.quiverone.modules.combat;

import com.google.common.collect.Streams;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1748;
import net.minecraft.class_2244;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3545;
import net.minecraft.class_3965;
import net.minecraft.class_2828.class_2831;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class BedAura extends Module {
   public NumberSetting range = new NumberSetting("Range", this, 4.0D, 0.1D, 10.0D, 0.1D);
   public NumberSetting delay = new NumberSetting("Delay", this, 10.0D, 0.0D, 40.0D, 1.0D);
   int bedSlot = -1;
   int oldSlot = -1;
   int ticks = 0;

   public BedAura() {
      super("BedAura", "Automatically places and destroys beds", Category.COMBAT, -1, false, true);
      this.addSetting(new Setting[]{this.range, this.delay});
   }

   public void onUpdate() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         for(int i = 0; i < 9; ++i) {
            if (mc.field_1724.field_7514.method_5438(i).method_7909() instanceof class_1748) {
               this.bedSlot = i;
            }
         }

         if (this.bedSlot == -1) {
            this.disable();
         } else if ((double)this.ticks != this.delay.getValue()) {
            ++this.ticks;
         } else {
            this.ticks = 0;
            if (mc.field_1724 != null && mc.field_1687 != null) {
               List<class_1297> players = (List)Streams.stream(mc.field_1687.method_18112()).filter((e) -> {
                  return e instanceof class_1657 && (double)mc.field_1724.method_5739(e) <= this.range.getValue() && e != mc.field_1724;
               }).collect(Collectors.toList());
               if (!players.isEmpty()) {
                  class_1657 player = (class_1657)players.get(0);
                  ArrayList<class_3545<class_2338, class_2350>> positions = new ArrayList();
                  positions.add(new class_3545(player.method_24515().method_10095().method_10084(), class_2350.field_11035));
                  positions.add(new class_3545(player.method_24515().method_10078().method_10084(), class_2350.field_11039));
                  positions.add(new class_3545(player.method_24515().method_10072().method_10084(), class_2350.field_11043));
                  positions.add(new class_3545(player.method_24515().method_10067().method_10084(), class_2350.field_11034));
                  positions.sort(Comparator.comparing((object) -> {
                     return ((class_2338)object.method_15442()).method_10268(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), true);
                  }));
                  Iterator var4 = positions.iterator();

                  while(var4.hasNext()) {
                     class_3545<class_2338, class_2350> pair = (class_3545)var4.next();
                     class_2338 blockPos = (class_2338)pair.method_15442();
                     class_2350 direction = (class_2350)pair.method_15441();
                     this.oldSlot = mc.field_1724.field_7514.field_7545;
                     mc.field_1724.field_7514.field_7545 = this.bedSlot;
                     if (mc.field_1687.method_8320(blockPos).method_26207().method_15800()) {
                        if (mc.field_1687.method_8320(blockPos.method_10093(direction)).method_26204() instanceof class_2244) {
                           mc.field_1761.method_2896(mc.field_1724, mc.field_1687, class_1268.field_5808, new class_3965(class_243.method_24954(blockPos.method_10093(direction)), class_2350.field_11033, blockPos.method_10093(direction), true));
                        }

                        if (!(mc.field_1687.method_8320(blockPos).method_26204() instanceof class_2244)) {
                           if (direction == class_2350.field_11043) {
                              mc.field_1724.field_3944.method_2883(new class_2831(-180.0F, mc.field_1724.field_5965, true));
                           }

                           if (direction == class_2350.field_11034) {
                              mc.field_1724.field_3944.method_2883(new class_2831(-90.0F, mc.field_1724.field_5965, true));
                           }

                           if (direction == class_2350.field_11035) {
                              mc.field_1724.field_3944.method_2883(new class_2831(0.0F, mc.field_1724.field_5965, true));
                           }

                           if (direction == class_2350.field_11039) {
                              mc.field_1724.field_3944.method_2883(new class_2831(90.0F, mc.field_1724.field_5965, true));
                           }

                           mc.field_1761.method_2896(mc.field_1724, mc.field_1687, class_1268.field_5808, new class_3965(class_243.method_24954(blockPos), class_2350.field_11033, blockPos, false));
                        }

                        if (mc.field_1687.method_8320(blockPos).method_26204() instanceof class_2244) {
                           mc.field_1761.method_2896(mc.field_1724, mc.field_1687, class_1268.field_5808, new class_3965(class_243.method_24954(blockPos), class_2350.field_11033, blockPos, true));
                        }

                        mc.field_1724.field_7514.field_7545 = this.oldSlot;
                        break;
                     }
                  }

               }
            } else {
               this.disable();
            }
         }
      } else {
         this.disable();
      }
   }
}
