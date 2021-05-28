package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Point;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;
import net.zebra.quiverone.utils.JColor;
import net.zebra.quiverone.utils.RenderUtil;

public class TotemCounter extends HUDModule {
   private ColorSetting color = new ColorSetting("Color", this, true);
   private ColorSetting color2 = new ColorSetting("Color2", this, new JColor(0, 0, 0, 255));

   public TotemCounter() {
      super("TotemCounter", "Shows how many totems you have", new Point(575, 500), Category.HUD, true);
   }

   public void populate(Theme theme) {
      this.component = new TotemCounter.TotemRender(theme);
   }

   private class TotemRender extends HUDComponent {
      int totems;

      public TotemRender(Theme theme) {
         super(TotemCounter.this.getName(), theme.getPanelRenderer(), TotemCounter.this.position);
      }

      public void render(Context context) {
         super.render(context);
         int totems = Module.mc.field_1724.field_7514.field_7547.stream().filter((itemStackx) -> {
            return itemStackx.method_7909() == class_1802.field_8288;
         }).mapToInt(class_1799::method_7947).sum() + Module.mc.field_1724.field_7514.field_7544.stream().filter((itemStackx) -> {
            return itemStackx.method_7909() == class_1802.field_8288;
         }).mapToInt(class_1799::method_7947).sum();
         class_4587 matrixStack = new class_4587();
         class_310.method_1551().field_1772.method_1720(matrixStack, String.valueOf(totems), (float)this.position.x, (float)(this.position.y - 7), TotemCounter.this.color.getValue().getRGB());
         class_1799 itemStack = this.getItem();
         RenderUtil.drawItem(itemStack, this.position.x, this.position.y, true);
      }

      private class_1799 getItem() {
         return class_1802.field_8288.method_7854();
      }

      public int getWidth(Interface anInterface) {
         return 70;
      }

      public void getHeight(Context context) {
      }
   }
}
