package net.zebra.quiverone.gui;

import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.hud.HUDClickGUI;
import com.lukflug.panelstudio.hud.HUDPanel;
import com.lukflug.panelstudio.settings.BooleanComponent;
import com.lukflug.panelstudio.settings.ColorSetting;
import com.lukflug.panelstudio.settings.EnumComponent;
import com.lukflug.panelstudio.settings.KeybindComponent;
import com.lukflug.panelstudio.settings.KeybindSetting;
import com.lukflug.panelstudio.settings.NumberComponent;
import com.lukflug.panelstudio.settings.NumberSetting;
import com.lukflug.panelstudio.settings.SimpleToggleable;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.DescriptionRenderer;
import com.lukflug.panelstudio.theme.FixedDescription;
import com.lukflug.panelstudio.theme.MouseDescription;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import net.minecraft.class_310;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.HUDModule;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.ModuleManager;
import net.zebra.quiverone.modules.renderer.ClickGuiModule;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;

public class ClickGUI extends MinecraftHUDGUI {
   private final Toggleable colorToggle;
   private final MinecraftGUI.GUIInterface guiInterface;
   private final Theme theme;
   private final HUDClickGUI gui;
   protected Renderer componentRenderer;
   public static final int WIDTH = 100;
   public static final int HEIGHT = 12;
   public static final int DISTANCE = 10;
   public static final int HUD_BORDER = 4;
   private static final class_310 mc = class_310.method_1551();

   public ClickGUI() {
      SettingsColorScheme scheme = new SettingsColorScheme(ClickGuiModule.INSTANCE.enabledColor, ClickGuiModule.INSTANCE.backgroundColor, ClickGuiModule.INSTANCE.settingBackgroundColor, ClickGuiModule.INSTANCE.outlineColor, ClickGuiModule.INSTANCE.CategoryColor, ClickGuiModule.INSTANCE.opacity);
      this.theme = new NewClickGuiTheme(scheme, 12);
      this.colorToggle = new SimpleToggleable(false);
      this.guiInterface = new MinecraftGUI.GUIInterface(true) {
         protected String getResourcePrefix() {
            return "quiver:gui/";
         }

         public void drawString(Point pos, String s, Color c) {
            if (ClickGUI.this.matrixStack != null) {
               end();
               float var10003 = (float)(pos.x + 2);
               class_310.method_1551().field_1772.method_1720(ClickGUI.this.matrixStack, s, var10003, (float)(pos.y + 2), c.getRGB());
               begin();
            }
         }

         public int getFontWidth(String s) {
            return class_310.method_1551().field_1772.method_1727(s) + 4;
         }

         public int getFontHeight() {
            class_310.method_1551().field_1772.getClass();
            return 9 + 4;
         }
      };
      this.gui = new HUDClickGUI(this.guiInterface, (DescriptionRenderer)(ClickGuiModule.INSTANCE.description.is("mouse") ? new MouseDescription(new Point(5, 0)) : new FixedDescription(new Point(0, 0)))) {
         public void handleScroll(int diff) {
            super.handleScroll(diff);
            if (ClickGuiModule.INSTANCE.scrollMode.is("screen")) {
               Iterator var2 = this.components.iterator();

               while(var2.hasNext()) {
                  FixedComponent component = (FixedComponent)var2.next();
                  if (!this.hudComponents.contains(component)) {
                     Point p = component.getPosition(ClickGUI.this.guiInterface);
                     p.translate(0, -diff);
                     component.setPosition(ClickGUI.this.guiInterface, p);
                  }
               }
            }

         }
      };
      Toggleable hudToggle = new Toggleable() {
         public void toggle() {
            ClickGUI.this.render();
         }

         public boolean isOn() {
            return ClickGUI.this.hudEditor;
         }
      };
      Iterator var3 = ModuleManager.getModules().iterator();

      while(var3.hasNext()) {
         Module module = (Module)var3.next();
         if (module instanceof HUDModule) {
            ((HUDModule)module).populate(this.theme);
            this.gui.addHUDComponent(new HUDPanel(((HUDModule)module).getComponent(), this.theme.getPanelRenderer(), module, new SettingsAnimation(ClickGuiModule.INSTANCE.animationSpeed), hudToggle, 4));
         }
      }

      Point pos = new Point(10, 10);
      Category[] var12 = Category.values();
      int var5 = var12.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Category category = var12[var6];
         DraggableContainer panel = new DraggableContainer(category.name, (String)null, this.theme.getPanelRenderer(), new SimpleToggleable(false), new SettingsAnimation(ClickGuiModule.INSTANCE.animationSpeed), (Toggleable)null, new Point(pos), 100) {
            protected int getScrollHeight(int childHeight) {
               return ClickGuiModule.INSTANCE.scrollMode.is("Screen") ? childHeight : Math.min(childHeight, Math.max(48, ClickGUI.this.field_22790 - this.getPosition(ClickGUI.this.guiInterface).y - this.renderer.getHeight(this.open.getValue() != 0.0D) - 12));
            }
         };
         this.gui.addComponent(panel);
         pos.translate(110, 0);
         Iterator var9 = ModuleManager.getModulesByCategory(category).iterator();

         while(var9.hasNext()) {
            Module module = (Module)var9.next();
            this.addModule(panel, module);
         }
      }

   }

   private void addModule(CollapsibleContainer panel, Module module) {
      CollapsibleContainer container = new CollapsibleContainer(module.getName(), module.getDescription(), this.theme.getContainerRenderer(), new SimpleToggleable(false), new SettingsAnimation(ClickGuiModule.INSTANCE.animationSpeed), module);
      if (!module.getName().equals("Esp2dHelper")) {
         panel.addComponent(container);
         Iterator var4 = module.getSettings().iterator();

         while(var4.hasNext()) {
            Setting property = (Setting)var4.next();
            if (property instanceof BooleanSetting) {
               container.addComponent(new BooleanComponent(property.name, (String)null, this.theme.getComponentRenderer(), (BooleanSetting)property));
            } else if (property instanceof NumberSetting) {
               container.addComponent(new NumberComponent(property.name, (String)null, this.theme.getComponentRenderer(), (NumberSetting)property, ((NumberSetting)property).getMinimumValue(), ((NumberSetting)property).getMinimumValue()));
            } else if (property instanceof ModeSetting) {
               container.addComponent(new EnumComponent(property.name, (String)null, this.theme.getComponentRenderer(), (ModeSetting)property));
            } else if (property instanceof ColorSetting) {
               container.addComponent(new SyncableColorComponent(this.theme, (net.zebra.quiverone.modules.setting.settings.ColorSetting)property, this.colorToggle, new SettingsAnimation(ClickGuiModule.INSTANCE.animationSpeed)));
            } else if (property instanceof KeybindSetting) {
               container.addComponent(new KeybindComponent(this.theme.getComponentRenderer(), (KeybindSetting)property));
            }
         }
      }

   }

   protected MinecraftGUI.GUIInterface getInterface() {
      return this.guiInterface;
   }

   protected int getScrollSpeed() {
      return (int)ClickGuiModule.INSTANCE.scrolls.getValue();
   }

   protected HUDClickGUI getHUDGUI() {
      return this.gui;
   }
}
