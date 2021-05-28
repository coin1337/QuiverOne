package net.zebra.quiverone.modules.hud;

import com.google.common.collect.Streams;
import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1748;
import net.minecraft.class_1774;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1829;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_490;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;
import net.zebra.quiverone.utils.Friends;
import net.zebra.quiverone.utils.JColor;
import net.zebra.quiverone.utils.RenderUtil;

public class EnemyModel extends HUDModule {
   private ColorSetting nameColor = new ColorSetting("Name Color", this, true);

   public EnemyModel() {
      super("EnemyModel", "Renders info about the enemy", new Point(450, 200), Category.HUD, false);
   }

   public void populate(Theme theme) {
      this.component = new EnemyModel.EnemyModelRender(theme);
   }

   private class EnemyModelRender extends HUDComponent {
      class_1657 playerEntity;

      public EnemyModelRender(Theme theme) {
         super(EnemyModel.this.getName(), theme.getPanelRenderer(), EnemyModel.this.position);
      }

      public void render(Context context) {
         super.render(context);
         class_4587 matrixStack = new class_4587();
         List<class_1297> filtered = (List)Streams.stream(Module.mc.field_1687.method_18112()).filter((e) -> {
            return e instanceof class_1657 && e != Module.mc.field_1724;
         }).collect(Collectors.toList());
         Boolean isfriend = false;

         for(Iterator var5 = filtered.iterator(); var5.hasNext(); isfriend = Friends.getInstance().isFriend(this.playerEntity.method_7334().getName())) {
            class_1297 entity = (class_1297)var5.next();
            this.sort(entity, entity);
            this.playerEntity = (class_1657)entity;
            this.sort(this.playerEntity, this.playerEntity);
         }

         if (this.playerEntity != null) {
            RenderUtil.drawRect(matrixStack, this.position.x + 40, this.position.y + 25, this.position.x - 40, this.position.y - 105, (new JColor(44, 47, 51, 255)).getRGB());
            class_490.method_2486(this.position.x, this.position.y, 44, 0.0F, 0.0F, this.playerEntity);
            RenderUtil.drawRect(matrixStack, this.position.x + 40, this.position.y - 90, this.position.x - 40, this.position.y - 105, (new JColor(44, 47, 51, 255)).getRGB());
            String name = this.playerEntity.method_7334().getName();
            float var10003 = (float)(this.position.x - 35);
            class_310.method_1551().field_1772.method_1720(matrixStack, name, var10003, (float)(this.position.y - 93), EnemyModel.this.nameColor.getValue().getRGB());
            int health = Math.round(this.playerEntity.method_6032() + this.playerEntity.method_6067());
            Color healthColor = Color.GREEN;
            if (health < 10) {
               healthColor = Color.RED;
            }

            if (health < 15) {
               healthColor = Color.YELLOW;
            }

            class_310.method_1551().field_1772.method_1729(matrixStack, health + "H", (float)(this.position.x - 35), (float)(this.position.y - 15), healthColor.getRGB());
            int distance = Math.round(this.playerEntity.method_5739(Module.mc.field_1724));
            Color distanceColor = Color.GREEN;
            if (distance < 20) {
               distanceColor = Color.YELLOW;
            }

            if (distance < 10) {
               distanceColor = Color.RED;
            }

            class_310.method_1551().field_1772.method_1729(matrixStack, distance + "Bl", (float)(this.position.x - 35), (float)(this.position.y + 3), distanceColor.getRGB());
            boolean threat = false;

            for(int position = 5; position >= 0; --position) {
               class_1799 itemStack = this.getItem(position);
               if (itemStack.method_7909() instanceof class_1829 || itemStack.method_7909() instanceof class_1748 || itemStack.method_7909() instanceof class_1774 || itemStack.method_7909() == class_1802.field_8288 || itemStack.method_7909() == class_1802.field_8367 || itemStack.method_7909() == class_1802.field_8281 || itemStack.method_7909() == class_1802.field_8463 || itemStack.method_7909() instanceof class_1743) {
                  threat = true;
               }
            }

            if (threat && !isfriend) {
               var10003 = (float)this.position.x;
               class_310.method_1551().field_1772.method_1729(matrixStack, "THREAT", var10003, (float)(this.position.y + 3), Color.RED.getRGB());
            }

            if (!threat && !isfriend) {
               var10003 = (float)this.position.x;
               class_310.method_1551().field_1772.method_1729(matrixStack, "NewFag", var10003, (float)(this.position.y + 3), Color.GREEN.getRGB());
            }

            filtered.clear();
            if (isfriend) {
               var10003 = (float)this.position.x;
               class_310.method_1551().field_1772.method_1729(matrixStack, "Friend", var10003, (float)(this.position.y + 3), Color.CYAN.getRGB());
            }
         }

      }

      private void sort(class_1297 e1, class_1297 e2) {
         Double.compare((double)e1.method_5739(Module.mc.field_1724), (double)e2.method_5739(Module.mc.field_1724));
      }

      private int invertSort(int sort) {
         if (sort == 0) {
            return 0;
         } else {
            return sort > 0 ? -1 : 1;
         }
      }

      private class_1799 getItem(int i) {
         if (this.playerEntity == null) {
            return class_1799.field_8037;
         } else if (i == 5) {
            return this.playerEntity.method_6047();
         } else {
            return i == 4 ? this.playerEntity.method_6079() : this.playerEntity.field_7514.method_7372(i);
         }
      }

      public int getWidth(Interface anInterface) {
         return 70;
      }

      public void getHeight(Context context) {
      }
   }
}
