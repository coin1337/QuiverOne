package net.zebra.quiverone.utils;

import net.minecraft.class_1267;
import net.minecraft.class_1280;
import net.minecraft.class_1282;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_1890;
import net.minecraft.class_1927;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_5134;
import net.minecraft.class_1927.class_4179;

public class DamageUtils {
   private static final class_310 mc = class_310.method_1551();

   public static float getAttackDamage(class_1657 attacker, class_1309 target) {
      float cooldown = attacker.method_7261(0.5F);
      float damage = (float)attacker.method_26825(class_5134.field_23721) * (0.2F + cooldown * cooldown * 0.8F);
      float enchDamage = class_1890.method_8218(attacker.method_6047(), target.method_6046()) * cooldown;
      if (damage <= 0.0F && enchDamage <= 0.0F) {
         return 0.0F;
      } else {
         if ((double)cooldown > 0.9D && attacker.field_6017 > 0.0F && !attacker.method_24828() && !attacker.method_6101() && !attacker.method_5799() && !attacker.method_6059(class_1294.field_5919) && !attacker.method_5765() && !attacker.method_5624() && target instanceof class_1309) {
            damage *= 1.5F;
         }

         damage += enchDamage;
         damage = class_1280.method_5496(damage, (float)target.method_6096(), (float)target.method_26825(class_5134.field_23725));
         int protAmount;
         if (target.method_6059(class_1294.field_5907)) {
            protAmount = 25 - (target.method_6112(class_1294.field_5907).method_5578() + 1) * 5;
            float resistance_1 = damage * (float)protAmount;
            damage = Math.max(resistance_1 / 25.0F, 0.0F);
         }

         if (damage <= 0.0F) {
            damage = 0.0F;
         } else {
            protAmount = class_1890.method_8219(target.method_5661(), class_1282.method_5532(attacker));
            if (protAmount > 0) {
               damage = class_1280.method_5497(damage, (float)protAmount);
            }
         }

         return damage;
      }
   }

   public static float getExplosionDamage(class_243 explosionPos, float power, class_1309 target) {
      if (mc.field_1687.method_8407() == class_1267.field_5801) {
         return 0.0F;
      } else {
         class_1927 explosion = new class_1927(mc.field_1687, (class_1297)null, explosionPos.field_1352, explosionPos.field_1351, explosionPos.field_1350, power, false, class_4179.field_18687);
         double maxDist = (double)(power * 2.0F);
         if (!mc.field_1687.method_8335((class_1297)null, new class_238((double)class_3532.method_15357(explosionPos.field_1352 - maxDist - 1.0D), (double)class_3532.method_15357(explosionPos.field_1351 - maxDist - 1.0D), (double)class_3532.method_15357(explosionPos.field_1350 - maxDist - 1.0D), (double)class_3532.method_15357(explosionPos.field_1352 + maxDist + 1.0D), (double)class_3532.method_15357(explosionPos.field_1351 + maxDist + 1.0D), (double)class_3532.method_15357(explosionPos.field_1350 + maxDist + 1.0D))).contains(target)) {
            return 0.0F;
         } else {
            if (!target.method_5659() && !target.method_5655()) {
               double distExposure = (double)class_3532.method_15368(target.method_5707(explosionPos)) / maxDist;
               if (distExposure <= 1.0D) {
                  double xDiff = target.method_23317() - explosionPos.field_1352;
                  double yDiff = target.method_23320() - explosionPos.field_1351;
                  double zDiff = target.method_23321() - explosionPos.field_1350;
                  double diff = (double)class_3532.method_15368(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
                  if (diff != 0.0D) {
                     double exposure = (double)class_1927.method_17752(explosionPos, target);
                     double finalExposure = (1.0D - distExposure) * exposure;
                     float toDamage = (float)Math.floor((finalExposure * finalExposure + finalExposure) / 2.0D * 7.0D * maxDist + 1.0D);
                     if (target instanceof class_1657) {
                        if (mc.field_1687.method_8407() == class_1267.field_5805) {
                           toDamage = Math.min(toDamage / 2.0F + 1.0F, toDamage);
                        } else if (mc.field_1687.method_8407() == class_1267.field_5807) {
                           toDamage = toDamage * 3.0F / 2.0F;
                        }
                     }

                     toDamage = class_1280.method_5496(toDamage, (float)target.method_6096(), (float)target.method_5996(class_5134.field_23725).method_6194());
                     int protAmount;
                     if (target.method_6059(class_1294.field_5907)) {
                        protAmount = 25 - (target.method_6112(class_1294.field_5907).method_5578() + 1) * 5;
                        float resistance_1 = toDamage * (float)protAmount;
                        toDamage = Math.max(resistance_1 / 25.0F, 0.0F);
                     }

                     if (toDamage <= 0.0F) {
                        toDamage = 0.0F;
                     } else {
                        protAmount = class_1890.method_8219(target.method_5661(), explosion.method_8349());
                        if (protAmount > 0) {
                           toDamage = class_1280.method_5497(toDamage, (float)protAmount);
                        }
                     }

                     return toDamage;
                  }
               }
            }

            return 0.0F;
         }
      }
   }

   public static boolean willExplosionKill(class_243 explosionPos, float power, class_1309 target) {
      if (target.method_6047().method_7909() != class_1802.field_8288 && target.method_6079().method_7909() != class_1802.field_8288) {
         return getExplosionDamage(explosionPos, power, target) >= target.method_6032() + target.method_6067();
      } else {
         return false;
      }
   }

   public static boolean willExplosionPop(class_243 explosionPos, float power, class_1309 target) {
      if (target.method_6047().method_7909() != class_1802.field_8288 && target.method_6079().method_7909() != class_1802.field_8288) {
         return false;
      } else {
         return getExplosionDamage(explosionPos, power, target) >= target.method_6032() + target.method_6067();
      }
   }

   public static boolean willExplosionPopOrKill(class_243 explosionPos, float power, class_1309 target) {
      return getExplosionDamage(explosionPos, power, target) >= target.method_6032() + target.method_6067();
   }
}
