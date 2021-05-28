package net.zebra.quiverone.gui;

import com.lukflug.panelstudio.Animation;
import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.FocusableComponent;
import com.lukflug.panelstudio.settings.ColorComponent;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.Theme;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;

public class SyncableColorComponent extends ColorComponent {
   public SyncableColorComponent(Theme theme, ColorSetting setting, Toggleable colorToggle, Animation animation) {
      super(setting.name, (String)null, theme.getContainerRenderer(), animation, theme.getComponentRenderer(), setting, true, true, colorToggle);
   }

   private class SyncButton extends FocusableComponent {
      public SyncButton(Renderer renderer) {
         super("Sync Color", (String)null, renderer);
      }

      public void render(Context context) {
         super.render(context);
         this.renderer.overrideColorScheme(SyncableColorComponent.this.overrideScheme);
         this.renderer.renderTitle(context, this.title, this.hasFocus(context), false);
         this.renderer.restoreColorScheme();
      }
   }
}
