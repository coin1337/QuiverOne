package net.zebra.quiverone.utils;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_2828.class_2831;

public class Rotations {
   public static float serverYaw;
   public static float serverPitch;
   public static int rotationTimer;
   private static final class_310 mc = class_310.method_1551();
   private static final Pool<Rotations.Rotation> rotationPool = new Pool(() -> {
      return new Rotations.Rotation();
   });
   private static final List<Rotations.Rotation> rotations = new ArrayList();
   private static float preYaw;
   private static float prePitch;
   private static int i = 0;
   private static Rotations.Rotation lastRotation;
   private static int lastRotationTimer;
   private static boolean sentLastRotation;

   public static void rotate(double yaw, double pitch, int priority, boolean clientSide, Runnable callback) {
      Rotations.Rotation rotation = (Rotations.Rotation)rotationPool.get();
      rotation.set(yaw, pitch, priority, clientSide, callback);

      int i;
      for(i = 0; i < rotations.size() && priority <= ((Rotations.Rotation)rotations.get(i)).priority; ++i) {
      }

      rotations.add(i, rotation);
   }

   public static void rotate(double yaw, double pitch, int priority, Runnable callback) {
      rotate(yaw, pitch, priority, false, callback);
   }

   public static void rotate(double yaw, double pitch, Runnable callback) {
      rotate(yaw, pitch, 0, callback);
   }

   public static void rotate(double yaw, double pitch) {
      rotate(yaw, pitch, 0, (Runnable)null);
   }

   private static void setupMovementPacketRotation(Rotations.Rotation rotation) {
      setClientRotation(rotation);
      setCamRotation(rotation.yaw, rotation.pitch);
   }

   private static void setClientRotation(Rotations.Rotation rotation) {
      preYaw = mc.field_1724.field_6031;
      prePitch = mc.field_1724.field_5965;
      mc.field_1724.field_6031 = (float)rotation.yaw;
      mc.field_1724.field_5965 = (float)rotation.pitch;
   }

   private static void resetPreRotation() {
      mc.field_1724.field_6031 = preYaw;
      mc.field_1724.field_5965 = prePitch;
   }

   public static double getYaw(class_1297 entity) {
      return (double)(mc.field_1724.field_6031 + class_3532.method_15393((float)Math.toDegrees(Math.atan2(entity.method_23321() - mc.field_1724.method_23321(), entity.method_23317() - mc.field_1724.method_23317())) - 90.0F - mc.field_1724.field_6031));
   }

   public static double getYaw(class_243 pos) {
      return (double)(mc.field_1724.field_6031 + class_3532.method_15393((float)Math.toDegrees(Math.atan2(pos.method_10215() - mc.field_1724.method_23321(), pos.method_10216() - mc.field_1724.method_23317())) - 90.0F - mc.field_1724.field_6031));
   }

   public static double getPitch(class_243 pos) {
      double diffX = pos.method_10216() - mc.field_1724.method_23317();
      double diffY = pos.method_10214() - (mc.field_1724.method_23318() + (double)mc.field_1724.method_18381(mc.field_1724.method_18376()));
      double diffZ = pos.method_10215() - mc.field_1724.method_23321();
      double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
      return (double)(mc.field_1724.field_5965 + class_3532.method_15393((float)(-Math.toDegrees(Math.atan2(diffY, diffXZ))) - mc.field_1724.field_5965));
   }

   public static double getPitch(class_1297 entity, Target target) {
      double y;
      if (target == Target.Head) {
         y = entity.method_23320();
      } else if (target == Target.Body) {
         y = entity.method_23318() + (double)(entity.method_17682() / 2.0F);
      } else {
         y = entity.method_23318();
      }

      double diffX = entity.method_23317() - mc.field_1724.method_23317();
      double diffY = y - (mc.field_1724.method_23318() + (double)mc.field_1724.method_18381(mc.field_1724.method_18376()));
      double diffZ = entity.method_23321() - mc.field_1724.method_23321();
      double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
      return (double)(mc.field_1724.field_5965 + class_3532.method_15393((float)(-Math.toDegrees(Math.atan2(diffY, diffXZ))) - mc.field_1724.field_5965));
   }

   public static double getPitch(class_1297 entity) {
      return getPitch(entity, Target.Body);
   }

   public static double getYaw(class_2338 pos) {
      return (double)(mc.field_1724.field_6031 + class_3532.method_15393((float)Math.toDegrees(Math.atan2((double)pos.method_10260() + 0.5D - mc.field_1724.method_23321(), (double)pos.method_10263() + 0.5D - mc.field_1724.method_23317())) - 90.0F - mc.field_1724.field_6031));
   }

   public static double getPitch(class_2338 pos) {
      double diffX = (double)pos.method_10263() + 0.5D - mc.field_1724.method_23317();
      double diffY = (double)pos.method_10264() + 0.5D - (mc.field_1724.method_23318() + (double)mc.field_1724.method_18381(mc.field_1724.method_18376()));
      double diffZ = (double)pos.method_10260() + 0.5D - mc.field_1724.method_23321();
      double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
      return (double)(mc.field_1724.field_5965 + class_3532.method_15393((float)(-Math.toDegrees(Math.atan2(diffY, diffXZ))) - mc.field_1724.field_5965));
   }

   public static void setCamRotation(double yaw, double pitch) {
      serverYaw = (float)yaw;
      serverPitch = (float)pitch;
      rotationTimer = 0;
   }

   private static class Rotation {
      public double yaw;
      public double pitch;
      public int priority;
      public boolean clientSide;
      public Runnable callback;

      private Rotation() {
      }

      public void set(double yaw, double pitch, int priority, boolean clientSide, Runnable callback) {
         this.yaw = yaw;
         this.pitch = pitch;
         this.priority = priority;
         this.clientSide = clientSide;
         this.callback = callback;
      }

      public void sendPacket() {
         Rotations.mc.method_1562().method_2883(new class_2831((float)this.yaw, (float)this.pitch, Rotations.mc.field_1724.method_24828()));
         this.runCallback();
      }

      public void runCallback() {
         if (this.callback != null) {
            this.callback.run();
         }

      }

      // $FF: synthetic method
      Rotation(Object x0) {
         this();
      }
   }
}
