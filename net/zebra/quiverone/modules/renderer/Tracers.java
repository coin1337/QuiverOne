package net.zebra.quiverone.modules.renderer;

import java.util.Iterator;
import net.minecraft.class_1297;
import net.minecraft.class_1429;
import net.minecraft.class_1511;
import net.minecraft.class_1542;
import net.minecraft.class_1569;
import net.minecraft.class_1657;
import net.minecraft.class_1688;
import net.minecraft.class_1690;
import net.minecraft.class_243;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;
import net.zebra.quiverone.utils.RenderUtil;

public class Tracers extends Module {
   public NumberSetting opacitys = new NumberSetting("Opactiy", this, 0.75D, 0.0D, 1.0D, 0.01D);
   public NumberSetting widths = new NumberSetting("Width", this, 1.5D, 0.1D, 5.0D, 0.1D);
   public BooleanSetting player = new BooleanSetting("Players", this, true);
   public BooleanSetting animals = new BooleanSetting("Animals", this, true);
   public BooleanSetting monsters = new BooleanSetting("Monsters", this, true);
   public BooleanSetting items = new BooleanSetting("Items", this, true);
   public BooleanSetting crystals = new BooleanSetting("Crystals", this, true);
   public BooleanSetting vehicles = new BooleanSetting("Vehicles", this, true);

   public Tracers() {
      super("Tracers", "Draws a line to players", Category.RENDERER, -1, false, true);
      this.addSetting(new Setting[]{this.widths, this.opacitys, this.player, this.animals, this.monsters, this.items, this.crystals, this.vehicles});
   }

   public void onRender() {
      float width = (float)this.widths.getPrecision();
      float opacity = (float)this.opacitys.getPrecision();
      Iterator var3 = mc.field_1687.method_18112().iterator();

      while(var3.hasNext()) {
         class_1297 e = (class_1297)var3.next();
         class_243 vec = e.method_19538();
         class_243 vec2 = (new class_243(0.0D, 0.0D, 75.0D)).method_1037(-((float)Math.toRadians((double)mc.field_1773.method_19418().method_19329()))).method_1024(-((float)Math.toRadians((double)mc.field_1773.method_19418().method_19330()))).method_1019(mc.field_1719.method_19538().method_1031(0.0D, (double)mc.field_1719.method_18381(mc.field_1719.method_18376()), 0.0D));
         float[] col = null;
         if (e instanceof class_1657 && e != mc.field_1724 && e != mc.field_1719 && this.player.isEnabled()) {
            col = new float[16711680];
         } else if (e instanceof class_1569 && this.monsters.isEnabled()) {
            col = new float[0];
         } else if (e instanceof class_1429 && this.animals.isEnabled()) {
            col = new float[0];
         } else if (e instanceof class_1542 && this.items.isEnabled()) {
            col = new float[0];
         } else if (e instanceof class_1511 && this.crystals.isEnabled()) {
            col = new float[0];
         } else if ((e instanceof class_1690 || e instanceof class_1688) && this.vehicles.isEnabled()) {
            col = new float[0];
         }

         if (col != null) {
            RenderUtil.drawLine(vec2.field_1352, vec2.field_1351, vec2.field_1350, vec.field_1352, vec.field_1351, vec.field_1350, col[0], col[1], col[2], opacity, width);
            RenderUtil.drawLine(vec.field_1352, vec.field_1351, vec.field_1350, vec.field_1352, vec.field_1351 + (double)e.method_17682() * 0.9D, vec.field_1350, col[0], col[1], col[2], opacity, width);
         }
      }

   }
}
