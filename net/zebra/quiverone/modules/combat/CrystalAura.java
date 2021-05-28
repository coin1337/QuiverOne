package net.zebra.quiverone.modules.combat;

import com.google.common.collect.Streams;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import net.minecraft.class_2828.class_2831;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;
import net.zebra.quiverone.utils.DamageUtils;

public class CrystalAura extends Module {
   private Boolean willKill = false;
   private Boolean willTotemPop = false;
   private double enemyDamage = 0.0D;
   private double selfDamage = 0.0D;
   Boolean placedCrystal;
   public NumberSetting maxSelfDamage = new NumberSetting("MaxSelfDmg", this, 20.0D, 1.0D, 20.0D, 0.1D);
   public NumberSetting minDamage = new NumberSetting("MinDamage", this, 2.0D, 1.0D, 20.0D, 0.1D);
   public BooleanSetting whileMining = new BooleanSetting("WhileMining", this, false);
   public BooleanSetting whileEating = new BooleanSetting("WhileEating", this, false);
   public BooleanSetting packets = new BooleanSetting("SendPackets", this, false);
   public NumberSetting breakRange = new NumberSetting("Break Range", this, 4.0D, 1.0D, 10.0D, 0.1D);
   public NumberSetting placeRange = new NumberSetting("Place Range", this, 4.0D, 1.0D, 10.0D, 0.1D);
   public BooleanSetting place = new BooleanSetting("Place", this, true);
   public ModeSetting placeMode = new ModeSetting("Place", this, "Single", new String[]{"Multi", "Single"});
   public BooleanSetting printSelfDamage = new BooleanSetting("PrintSelfDamage", this, false);
   public BooleanSetting printEnemyDamage = new BooleanSetting("PrintDamage", this, false);
   public BooleanSetting antiSuicide = new BooleanSetting("AntiSuicide", this, true);
   public BooleanSetting antiPop = new BooleanSetting("AntiTotemPop", this, true);

   public CrystalAura() {
      super("Crystal Aura", "Places and breaks crystals", Category.COMBAT, -1, false, true);
      this.addSetting(new Setting[]{this.place, this.breakRange, this.placeRange, this.packets, this.placeMode, this.whileEating, this.whileMining, this.maxSelfDamage, this.minDamage, this.antiPop, this.antiSuicide, this.printEnemyDamage, this.printSelfDamage});
   }

   public void onUpdate() {
      try {
         if (this.place.isEnabled()) {
            this.placedCrystal = false;
            List<class_1297> players = (List)Streams.stream(mc.field_1687.method_18112()).filter((entityx) -> {
               return entityx instanceof class_1657 && (double)mc.field_1724.method_5739(entityx) <= this.placeRange.getValue() && entityx != mc.field_1724;
            }).collect(Collectors.toList());
            if (players.isEmpty() || mc.field_1724 == null || mc.field_1687 == null) {
               return;
            }

            class_1657 player = (class_1657)players.get(0);
            List<class_2338> blocks = this.findCrystalBlocks();
            this.placedCrystal = false;
            Iterator var5 = blocks.iterator();

            while(var5.hasNext()) {
               class_2338 pos = (class_2338)var5.next();
               class_2350 direction = class_2350.field_11043;
               if (mc.field_1724.method_6115() && (mc.field_1724.method_6047().method_19267() || mc.field_1724.method_6079().method_19267()) && !this.whileEating.isEnabled()) {
                  return;
               }

               if (mc.field_1761.method_2923() && !this.whileMining.isEnabled()) {
                  return;
               }

               this.placedCrystal = false;
               this.enemyDamage = (double)DamageUtils.getExplosionDamage(class_243.method_24954(pos), 6.0F, player);
               this.selfDamage = (double)DamageUtils.getExplosionDamage(class_243.method_24954(pos), 6.0F, mc.field_1724);
               this.willKill = DamageUtils.willExplosionKill(class_243.method_24954(pos), 6.0F, mc.field_1724);
               this.willTotemPop = DamageUtils.willExplosionPop(class_243.method_24954(pos), 6.0F, mc.field_1724);
               if (this.printSelfDamage.isEnabled()) {
                  sendMessage("Self Damage: " + this.selfDamage);
               }

               if (this.printEnemyDamage.isEnabled()) {
                  sendMessage("Enemy Damage: " + this.enemyDamage);
               }

               if (this.selfDamage > this.maxSelfDamage.getValue() && this.enemyDamage < this.minDamage.getValue()) {
                  return;
               }

               this.placeCrystal(pos, this.getHand(), direction);
               if (this.placedCrystal = true) {
                  List<class_1297> crystals = (List)Streams.stream(mc.field_1687.method_18112()).filter((entityx) -> {
                     return entityx instanceof class_1511 && (double)mc.field_1724.method_5739(entityx) <= this.breakRange.getValue();
                  }).collect(Collectors.toList());
                  Iterator var8 = crystals.iterator();
                  if (var8.hasNext()) {
                     class_1297 entity = (class_1297)var8.next();
                     if (entity.method_5732() && entity.getClass() == class_1511.class) {
                        mc.field_1761.method_2918(mc.field_1724, entity);
                        mc.field_1724.method_6104(this.getHand());
                     }
                  }

                  if (this.packets.isEnabled()) {
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
                  }
               }

               if (this.placeMode.is("Single")) {
                  return;
               }
            }
         }
      } catch (Exception var10) {
      }

   }

   private void placeCrystal(class_2338 block, class_1268 hand, class_2350 direction) {
      mc.field_1761.method_2896(mc.field_1724, mc.field_1687, hand, new class_3965(class_243.method_24954(block), direction, block, false));
      this.placedCrystal = true;
      new class_2338(block);
   }

   private boolean canPlaceCrystalOn(class_2338 blockPos) {
      class_2338 airBlock1 = blockPos.method_10069(0, 1, 1);
      class_2338 airBlock2 = blockPos.method_10069(0, 2, 1);
      return mc.field_1687.method_8320(blockPos).method_26204() == class_2246.field_9987 || mc.field_1687.method_8320(blockPos).method_26204() == class_2246.field_10540 && mc.field_1687.method_8320(airBlock1).method_26204() == class_2246.field_10124 && mc.field_1687.method_8320(airBlock2).method_26204() == class_2246.field_10124;
   }

   public class_1268 getHand() {
      if (mc.field_1724.method_6047().method_7909() == class_1802.field_8301) {
         return class_1268.field_5808;
      } else {
         return mc.field_1724.method_6079().method_7909() == class_1802.field_8301 ? class_1268.field_5810 : class_1268.field_5808;
      }
   }

   private List<class_2338> findCrystalBlocks() {
      List<class_2338> positions = new ArrayList();
      positions.addAll((Collection)this.getSphere(this.getPlayerPos(), (float)this.placeRange.getValue(), (int)this.placeRange.getValue(), false, true, 0).stream().filter(this::canPlaceCrystalOn).collect(Collectors.toList()));
      return positions;
   }

   private class_2338 getPlayerPos() {
      return new class_2338(Math.floor(mc.field_1724.method_19538().field_1352), Math.floor(mc.field_1724.method_19538().field_1351), Math.floor(mc.field_1724.method_19538().field_1350));
   }

   public List<class_2338> getSphere(class_2338 loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
      List<class_2338> circleblocks = new ArrayList();
      int cx = loc.method_10263();
      int cy = loc.method_10264();
      int cz = loc.method_10260();

      for(int x = cx - (int)r; (float)x <= (float)cx + r; ++x) {
         for(int z = cz - (int)r; (float)z <= (float)cz + r; ++z) {
            for(int y = sphere ? cy - (int)r : cy; (float)y < (sphere ? (float)cy + r : (float)(cy + h)); ++y) {
               double dist = (double)((cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0));
               if (dist < (double)(r * r) && (!hollow || !(dist < (double)((r - 1.0F) * (r - 1.0F))))) {
                  class_2338 l = new class_2338(x, y + plus_y, z);
                  circleblocks.add(l);
               }
            }
         }
      }

      return circleblocks;
   }
}
