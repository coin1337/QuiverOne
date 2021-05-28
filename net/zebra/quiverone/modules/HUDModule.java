package net.zebra.quiverone.modules;

import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Point;

public abstract class HUDModule extends Module {
   protected FixedComponent component;
   protected Point position;

   public HUDModule(String title, String description, Point defaultPos, Category category, Boolean isToggled) {
      super(title, description, category, -1, isToggled, false);
      this.position = defaultPos;
   }

   public abstract void populate(Theme var1);

   public FixedComponent getComponent() {
      return this.component;
   }

   public void resetPosition() {
   }
}
