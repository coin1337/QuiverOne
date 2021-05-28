package net.zebra.quiverone.modules.combat;

import com.google.common.collect.Streams;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1829;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;
import net.zebra.quiverone.utils.Friends;

public class KillAura extends Module {
   public static Boolean inUse = false;
   public BooleanSetting onlyPlayers = new BooleanSetting("Only Players", this, true);
   public BooleanSetting switchItem = new BooleanSetting("Auto Weapon", this, false);
   public NumberSetting range = new NumberSetting("Range", this, 4.0D, 1.0D, 10.0D, 0.1D);
   public BooleanSetting multiAura = new BooleanSetting("Multi Aura", this, true);
   private BooleanSetting attackFriends = new BooleanSetting("Attack Friends", this, false);

   public KillAura() {
      super("Aura", "Makes you kill entitys within range", Category.COMBAT, 75, false, true);
      this.addSetting(new Setting[]{this.onlyPlayers, this.switchItem, this.range, this.multiAura, this.attackFriends});
   }

   public void onUpdate() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (!(mc.field_1724.method_7261(0.0F) < 1.0F)) {
            try {
               List filtered;
               if (this.onlyPlayers.isEnabled()) {
                  filtered = (List)Streams.stream(mc.field_1687.method_18112()).filter((e) -> {
                     return e instanceof class_1657 && (double)mc.field_1724.method_5739(e) <= this.range.getValue() && e != mc.field_1724;
                  }).collect(Collectors.toList());
               } else {
                  filtered = (List)Streams.stream(mc.field_1687.method_18112()).filter((e) -> {
                     return e instanceof class_1297 && (double)mc.field_1724.method_5739(e) <= this.range.getValue() && e != mc.field_1724;
                  }).collect(Collectors.toList());
               }

               inUse = false;
               Iterator var2 = filtered.iterator();

               do {
                  class_1297 entity;
                  Boolean isfriend;
                  do {
                     do {
                        do {
                           do {
                              do {
                                 if (!var2.hasNext()) {
                                    return;
                                 }

                                 entity = (class_1297)var2.next();
                              } while(entity == null);

                              inUse = false;
                              isfriend = Friends.getInstance().isFriend(entity.method_5820());
                              if (this.attackFriends.isEnabled()) {
                                 isfriend = false;
                              }
                           } while(!entity.method_5709());
                        } while(!entity.method_5732());
                     } while(entity.getClass() == class_1511.class);
                  } while(isfriend);

                  if (this.switchItem.isEnabled()) {
                     for(int i = 0; i < 9; ++i) {
                        if (mc.field_1724.field_7514.method_5438(i).method_7909() instanceof class_1829) {
                           mc.field_1724.field_7514.field_7545 = i;
                        }
                     }
                  }

                  mc.field_1761.method_2918(mc.field_1724, entity);
                  mc.field_1724.method_6104(class_1268.field_5808);
                  inUse = true;
               } while(this.multiAura.isEnabled());
            } catch (Exception var6) {
            }

         }
      }
   }
}
