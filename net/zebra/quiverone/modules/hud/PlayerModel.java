package net.zebra.quiverone.modules.hud;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Point;
import net.minecraft.class_1657;
import net.minecraft.class_490;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.Module;

public class PlayerModel extends HUDModule {
   public PlayerModel() {
      super("PlayerModel", "Renders you", new Point(200, 200), Category.HUD, false);
   }

   public void populate(Theme theme) {
      this.component = new PlayerModel.PlayerModelRender(theme);
   }

   private class PlayerModelRender extends HUDComponent {
      public PlayerModelRender(Theme theme) {
         super(PlayerModel.this.getName(), theme.getPanelRenderer(), PlayerModel.this.position);
      }

      public void render(Context context) {
         super.render(context);
         class_1657 player = Module.mc.field_1724;
         if (player != null) {
            class_490.method_2486(this.position.x, this.position.y, 44, 0.0F, 0.0F, player);
         }
      }

      public int getWidth(Interface anInterface) {
         return 44;
      }

      public void getHeight(Context context) {
      }
   }
}
