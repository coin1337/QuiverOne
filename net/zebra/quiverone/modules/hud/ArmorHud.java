package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.utils.RenderUtil;

public class ArmorHud extends HUDModule {
   public ArmorHud() {
      super("ArmorHud", "Displays armor durability", new Point(470, 452), Category.HUD, true);
   }

   public void populate(Theme theme) {
      this.component = new ArmorHud.ArmorRenderer(theme);
   }

   private class ArmorRenderer extends HUDComponent {
      public ArmorRenderer(Theme theme) {
         super(ArmorHud.this.getName(), theme.getPanelRenderer(), ArmorHud.this.position);
      }

      public void render(Context context) {
         super.render(context);
         int ypos = 0;
         int xpos = 0;
         int distance = 0;
         class_4587 matrixStack = new class_4587();

         int damage;
         Color color;
         for(Iterator var6 = Module.mc.field_1724.field_7514.field_7548.iterator(); var6.hasNext(); class_310.method_1551().field_1772.method_1720(matrixStack, damage + "%", (float)(this.position.x + xpos), (float)(this.position.y - 7 - ypos), color.getRGB())) {
            class_1799 itemStack = (class_1799)var6.next();
            ++distance;
            if (distance == 1) {
               xpos = 20;
            }

            if (distance == 2) {
               xpos = 40;
            }

            if (distance == 3) {
               xpos = 60;
            }

            if (distance == 4) {
               xpos = 80;
            }

            if (Module.mc.field_1724.method_5869()) {
               ypos = 10;
            }

            RenderUtil.drawItem(itemStack, this.position.x + xpos, this.position.y - ypos, true);
            if (itemStack.method_7936() == 0) {
               damage = 100;
            } else {
               damage = itemStack.method_7919() * 100 / itemStack.method_7936();
            }

            color = Color.GREEN;
            if (damage > 65) {
               color = Color.YELLOW;
            }

            if (damage > 85) {
               color = Color.RED;
            }
         }

      }

      public int getWidth(Interface anInterface) {
         return 70;
      }

      public void getHeight(Context context) {
      }
   }
}
