package net.zebra.quiverone.gui;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.ColorScheme;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.RendererBase;
import com.lukflug.panelstudio.theme.Theme;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.function.Supplier;

public class NewClickGuiTheme implements Theme {
   protected ColorScheme scheme;
   protected Renderer componentRenderer;
   protected Renderer containerRenderer;
   protected Renderer panelRenderer;
   protected Supplier<Boolean> ignoreDisabled;
   protected Supplier<Boolean> buttonRainbow;

   public NewClickGuiTheme(ColorScheme scheme, int height) {
      this.scheme = scheme;
      this.panelRenderer = new NewClickGuiTheme.ComponentRenderer(0, height, 0);
      this.containerRenderer = new NewClickGuiTheme.ComponentRenderer(1, height, 0);
      this.componentRenderer = new NewClickGuiTheme.ComponentRenderer(2, height, 0);
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

   protected class ComponentRenderer extends RendererBase {
      protected final int level;

      public ComponentRenderer(int level, int height, int border) {
         super(height + 2 * border, border, 0, 0, 0);
         this.level = level;
      }

      public void renderTitle(Context context, String text, boolean focus, boolean active, boolean open) {
         super.renderTitle(context, text, focus, active, open);
         Color color;
         if (this.level != 0) {
            color = this.getFontColor(active);
            Point p1;
            Point p2;
            Point p3;
            if (open) {
               p3 = new Point(context.getPos().x + context.getSize().width - 3, context.getPos().y + context.getSize().height / 4);
               p2 = new Point(context.getPos().x + context.getSize().width - context.getSize().height / 2, context.getPos().y + context.getSize().height * 3 / 4);
               p1 = new Point(context.getPos().x + context.getSize().width - context.getSize().height + 3, context.getPos().y + context.getSize().height / 4);
            } else {
               p3 = new Point(context.getPos().x + context.getSize().width - context.getSize().height * 3 / 4, context.getPos().y + 3);
               p2 = new Point(context.getPos().x + context.getSize().width - context.getSize().height / 4, context.getPos().y + context.getSize().height / 2);
               p1 = new Point(context.getPos().x + context.getSize().width - context.getSize().height * 3 / 4, context.getPos().y + context.getSize().height - 3);
            }

            context.getInterface().drawLine(p1, p2, color, color);
            context.getInterface().drawLine(p2, p3, color, color);
         }

         if (this.level == 0 && open) {
            color = this.getFontColor(focus);
            context.getInterface().fillRect(new Rectangle(context.getRect().x, context.getRect().y + context.getRect().height - 1, context.getRect().width, 1), color, color, color, color);
         }

      }

      public void renderRect(Context context, String text, boolean focus, boolean active, Rectangle rectangle, boolean overlay) {
         Color overlayColor;
         if ((this.level == 0 || !active) && this.level != 1) {
            overlayColor = this.getBackgroundColor(focus);
            context.getInterface().fillRect(new Rectangle(rectangle.x, rectangle.y, context.getRect().x + context.getRect().width - rectangle.x, rectangle.height), overlayColor, overlayColor, overlayColor, overlayColor);
         } else {
            overlayColor = this.getMainColor(focus, active);
            context.getInterface().fillRect(rectangle, overlayColor, overlayColor, overlayColor, overlayColor);
         }

         if (this.level != 0 && overlay) {
            if (context.isHovered()) {
               overlayColor = new Color(0, 0, 0, 64);
            } else {
               overlayColor = new Color(0, 0, 0, 0);
            }

            context.getInterface().fillRect(context.getRect(), overlayColor, overlayColor, overlayColor, overlayColor);
         }

         Point stringPos = new Point(rectangle.getLocation());
         stringPos.translate(0, this.getOffset());
         if (this.level == 0) {
            stringPos = new Point(rectangle.x + rectangle.width / 2 - context.getInterface().getFontWidth(text) / 2, rectangle.y + this.getOffset());
         }

         if (this.level == 2 && overlay) {
            context.getInterface().drawString(stringPos, "  " + text, this.getFontColor(focus));
         } else {
            context.getInterface().drawString(stringPos, text, this.getFontColor(focus));
         }

      }

      public void renderBackground(Context context, boolean focus) {
         Color color = this.getColorScheme().getBackgroundColor();
         if (this.level == 1) {
            context.getInterface().fillRect(context.getRect(), color, color, color, color);
         }

      }

      public void renderBorder(Context context, boolean focus, boolean active, boolean open) {
      }

      public Color getMainColor(boolean focus, boolean active) {
         return active ? this.getColorScheme().getActiveColor() : new Color(0, 0, 0, 0);
      }

      public Color getBackgroundColor(boolean focus) {
         Color color = this.getColorScheme().getBackgroundColor();
         return new Color(color.getRed(), color.getGreen(), color.getBlue());
      }

      public ColorScheme getDefaultColorScheme() {
         return NewClickGuiTheme.this.scheme;
      }
   }
}
