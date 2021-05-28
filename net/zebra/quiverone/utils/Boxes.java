package net.zebra.quiverone.utils;

import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2350.class_2351;

public class Boxes {
   public static class_243 getMinVec(class_238 box) {
      return new class_243(box.field_1323, box.field_1322, box.field_1321);
   }

   public static class_243 getMaxVec(class_238 box) {
      return new class_243(box.field_1320, box.field_1325, box.field_1324);
   }

   public static class_238 moveToZero(class_238 box) {
      return box.method_997(getMinVec(box).method_22882());
   }

   public static double getCornerLength(class_238 box) {
      return getMinVec(box).method_1022(getMaxVec(box));
   }

   public static double getAxisLength(class_238 box, class_2351 axis) {
      return box.method_990(axis) - box.method_1001(axis);
   }
}
