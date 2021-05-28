package net.zebra.quiverone.utils;

import java.util.Iterator;
import java.util.List;
import net.minecraft.class_1657;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.zebra.quiverone.modules.Module;

public class HoleUtil {
   public static boolean isInHole(class_1657 entity, class_2338 pos) {
      List<class_2338> blocks = null;
      ((List)blocks).add(entity.method_24515().method_10095().method_10095());
      ((List)blocks).add(entity.method_24515().method_10072().method_10072());
      ((List)blocks).add(entity.method_24515().method_10078().method_10078());
      ((List)blocks).add(entity.method_24515().method_10067().method_10067());
      int i = 0;
      Iterator var4 = ((List)blocks).iterator();

      while(true) {
         class_2338 blockPos;
         do {
            if (!var4.hasNext()) {
               return i == 4;
            }

            blockPos = (class_2338)var4.next();
         } while(Module.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_9987 && Module.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_10540);

         ++i;
      }
   }

   public static boolean isInBedrockHole(class_1657 entity, class_2338 blockPos) {
      class_2338[] array = new class_2338[]{blockPos.method_10095(), blockPos.method_10072(), blockPos.method_10078(), blockPos.method_10067(), blockPos.method_10074()};
      int i = 0;
      class_2338[] var4 = array;
      int var5 = array.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         class_2338 block = var4[var6];
         if (Module.mc.field_1687.method_8320(block).method_26204() == class_2246.field_10540) {
            ++i;
         }
      }

      return i == 4;
   }

   public static Boolean isInObsidianHole(class_1657 entity, class_2338 blockPos) {
      class_2338[] array = new class_2338[]{entity.method_24515().method_10095().method_10095(), entity.method_24515().method_10072().method_10072(), entity.method_24515().method_10078().method_10078(), entity.method_24515().method_10067().method_10067(), entity.method_24515().method_10074().method_10074()};
      int i = 0;
      class_2338[] var4 = array;
      int var5 = array.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         class_2338 block = var4[var6];
         if (Module.mc.field_1687.method_8320(block).method_26204() == class_2246.field_10540) {
            ++i;
         }
      }

      return i == 4;
   }
}
