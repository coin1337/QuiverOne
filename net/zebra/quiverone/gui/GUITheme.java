package net.zebra.quiverone.gui;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.ColorScheme;
import com.lukflug.panelstudio.theme.DescriptionRenderer;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.RendererBase;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import net.zebra.quiverone.utils.JColor;

public class GUITheme implements Theme {
   protected ColorScheme scheme;
   protected Renderer componentRenderer;
   protected Renderer containerRenderer;
   protected Renderer panelRenderer;
   protected DescriptionRenderer descriptionRenderer;

   public GUITheme(ColorScheme scheme, int height, int border) {
      this.scheme = scheme;
      this.panelRenderer = new GUITheme.ComponentRenderer(0, height, border);
      this.containerRenderer = new GUITheme.ComponentRenderer(1, height, border);
      this.componentRenderer = new GUITheme.ComponentRenderer(2, height, border);
   }

   public Renderer getPanelRenderer() {
      return this.panelRenderer;
   }

   public Renderer getContainerRenderer() {
      return this.containerRenderer;
   }

   public Renderer getComponentRenderer() {
      return this.componentRenderer;
   }

   public DescriptionRenderer getDescription() {
      return this.descriptionRenderer;
   }

   protected class ComponentRenderer extends RendererBase {
      protected final int level;
      protected final int border;

      public ComponentRenderer(int level, int height, int border) {
         super(height + 1, 0, 0, 0, 0);
         this.level = level;
         this.border = border;
      }

      public void renderRect(Context context, String text, boolean focus, boolean active, Rectangle rectangle, boolean overlay) {
         Color color = this.getMainColor(focus, active);
         Color color2 = this.getBackgroundColor(focus);
         if (this.level == 1 && !active) {
            context.getInterface().fillRect(context.getRect(), color2, color2, color2, color2);
         } else {
            context.getInterface().fillRect(rectangle, color, color, color, color);
         }

         if (overlay) {
            Color overlayColor;
            if (context.isHovered()) {
               overlayColor = new Color(255, 255, 255, 64);
            } else {
               overlayColor = new Color(255, 255, 255, 0);
            }

            context.getInterface().fillRect(context.getRect(), overlayColor, overlayColor, overlayColor, overlayColor);
         }

         Point stringPos = new Point(rectangle.getLocation());
         stringPos.translate(0, this.border);
         context.getInterface().drawString(stringPos, text, new JColor(255, 255, 255, 255));
      }

      public void renderBackground(Context context, boolean focus) {
         Color color = this.getDefaultColorScheme().getBackgroundColor();
         context.getInterface().fillRect(context.getRect(), color, color, color, color);
      }

      public void renderBorder(Context context, boolean focus, boolean active, boolean open) {
         Color color = this.getDefaultColorScheme().getFontColor();
         if (this.level == 0 && open) {
            context.getInterface().fillRect(new Rectangle(context.getPos(), new Dimension(1, context.getSize().height)), color, color, color, color);
            context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x + context.getSize().width - 1, context.getPos().y), new Dimension(1, context.getSize().height)), color, color, color, color);
         }

      }

      public Color getMainColor(boolean focus, boolean active) {
         Color color;
         if (active && this.level > 0) {
            color = this.getColorScheme().getActiveColor();
         } else {
            color = this.getColorScheme().getBackgroundColor();
         }

         if (!active && this.level < 2) {
            color = this.getColorScheme().getInactiveColor();
         }

         if (active && this.level < 1) {
            color = this.getColorScheme().getFontColor();
         }

         color = new Color(color.getRed(), color.getGreen(), color.getBlue(), this.getColorScheme().getOpacity());
         return color;
      }

      public Color getBackgroundColor(boolean focus) {
         Color color = this.getColorScheme().getInactiveColor();
         color = new Color(color.getRed(), color.getGreen(), color.getBlue(), this.getColorScheme().getOpacity());
         return color;
      }

      public ColorScheme getDefaultColorScheme() {
         return GUITheme.this.scheme;
      }
   }
}
