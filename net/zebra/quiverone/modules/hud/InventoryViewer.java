package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Point;
import java.util.Iterator;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.utils.RenderUtil;

public class InventoryViewer extends HUDModule {
   public InventoryViewer() {
      super("Inventory", "Renders your inventory items", new Point(2, 20), Category.HUD, false);
   }

   public void populate(Theme theme) {
      this.component = new InventoryViewer.InvRenderer(theme);
   }

   private class InvRenderer extends HUDComponent {
      public InvRenderer(Theme theme) {
         super(InventoryViewer.this.getName(), theme.getPanelRenderer(), InventoryViewer.this.position);
      }

      public void render(Context context) {
         super.render(context);
         int ypos = 0;
         int xpos = 0;
         int distance = 0;
         new class_4587();

         class_1799 itemStack;
         for(Iterator var6 = Module.mc.field_1724.field_7514.field_7547.iterator(); var6.hasNext(); RenderUtil.drawItem(itemStack, this.position.x + xpos, this.position.y - ypos, false)) {
            itemStack = (class_1799)var6.next();
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

            if (distance == 5) {
               xpos = 100;
            }

            if (distance == 6) {
               xpos = 120;
            }

            if (distance == 7) {
               xpos = 140;
            }

            if (distance == 8) {
               xpos = 160;
            }

            if (distance == 9 && ypos == 20) {
               xpos = 180;
               ypos = 40;
               distance = 0;
            }

            if (distance == 9 && ypos == 40) {
               break;
            }

            if (distance == 9) {
               xpos = 180;
               distance = 0;
               ypos = 20;
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
