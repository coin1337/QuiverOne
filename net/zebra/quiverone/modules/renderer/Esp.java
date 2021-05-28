package net.zebra.quiverone.modules.renderer;

import java.awt.Color;
import java.util.Iterator;
import net.minecraft.class_1297;
import net.zebra.quiverone.events.event.EntityRenderEvent;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.utils.RenderUtil;

public class Esp extends Module {
   private static final Color CHEST = new Color(0.0F, 0.0F, 0.89F, 0.5F);
   private static final float[] ENDER_CHEST = new float[0];
   private static final Color DISPENSER = new Color(0.65F, 0.65F, 0.65F, 0.5F);
   private static final Color SHULKER = new Color(1.0F, 0.45F, 0.55F, 0.5F);

   public Esp() {
      super("Esp", "Highlights blocks, entities, players", Category.RENDERER, -1, false, true);
   }

   public void onRender(EntityRenderEvent entityRenderEvent) {
      float color = 255.0F;
      Iterator var3 = mc.field_1687.method_18112().iterator();

      while(var3.hasNext()) {
         class_1297 e = (class_1297)var3.next();
         RenderUtil.drawFill(e.method_5829(), color, color, color, color);
      }

   }
}
