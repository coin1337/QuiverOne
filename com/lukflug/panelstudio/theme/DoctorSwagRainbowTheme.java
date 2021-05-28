package com.lukflug.panelstudio.theme;

import com.lukflug.panelstudio.Context;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.function.Supplier;

public class DoctorSwagRainbowTheme implements Theme {
   protected ColorScheme scheme;
   protected Renderer componentRenderer;
   protected Renderer containerRenderer;
   protected Renderer panelRenderer;
   protected Supplier<Boolean> ignoreDisabled;
   protected Supplier<Boolean> buttonRainbow;

   public DoctorSwagRainbowTheme(ColorScheme scheme, int height, Supplier<Boolean> ignoreDisabled, Supplier<Boolean> buttonRainbow) {
      this.scheme = scheme;
      this.panelRenderer = new DoctorSwagRainbowTheme.ComponentRenderer(0, height, 0);
      this.containerRenderer = new DoctorSwagRainbowTheme.ComponentRenderer(1, height, 0);
      this.componentRenderer = new DoctorSwagRainbowTheme.ComponentRenderer(2, height, 0);
      this.ignoreDisabled = ignoreDisabled;
      this.buttonRainbow = buttonRainbow;
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
         if ((this.level == 0 || !active) && (this.level != 1 || !(Boolean)DoctorSwagRainbowTheme.this.ignoreDisabled.get())) {
            overlayColor = this.getBackgroundColor(focus);
            context.getInterface().fillRect(new Rectangle(rectangle.x, rectangle.y, context.getRect().x + context.getRect().width - rectangle.x, rectangle.height), overlayColor, overlayColor, overlayColor, overlayColor);
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
            context.getInterface().drawString(stringPos, "> " + text, this.getFontColor(focus));
         } else {
            context.getInterface().drawString(stringPos, text, this.getFontColor(focus));
         }

      }

      public void renderBackground(Context context, boolean focus) {
         if (this.level == 0) {
            if ((Boolean)DoctorSwagRainbowTheme.this.buttonRainbow.get()) {
               Rectangle rect = context.getRect();

               for(int current = rect.y; current < rect.y + rect.height; current += this.height) {
                  int height = Math.min(this.height, rect.height + rect.y - current);
                  this.renderRainbowRect(new Rectangle(rect.x, current, rect.width, height), context, focus);
               }
            } else {
               this.renderRainbowRect(context.getRect(), context, focus);
            }
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
         return DoctorSwagRainbowTheme.this.scheme;
      }

      protected void renderRainbowRect(Rectangle rect, Context context, boolean focus) {
         Color source = this.getMainColor(focus, true);
         float[] hsb = Color.RGBtoHSB(source.getRed(), source.getGreen(), source.getBlue(), (float[])null);
         float currentHue = hsb[0];
         float targetHue = hsb[0];
         if (this.getColorScheme().getOpacity() != 0) {
            targetHue += (float)rect.height / (float)this.getColorScheme().getOpacity();
         } else {
            context.getInterface().fillRect(rect, source, source, source, source);
         }

         while(currentHue < targetHue) {
            float nextHue = (float)(Math.floor((double)(currentHue * 6.0F)) + 1.0D) / 6.0F;
            if (nextHue > targetHue) {
               nextHue = targetHue;
            }

            Color colorA = Color.getHSBColor(currentHue, hsb[1], hsb[2]);
            Color colorB = Color.getHSBColor(nextHue, hsb[1], hsb[2]);
            int top = Math.round((currentHue - hsb[0]) * (float)this.getColorScheme().getOpacity());
            int bottom = Math.round((nextHue - hsb[0]) * (float)this.getColorScheme().getOpacity());
            context.getInterface().fillRect(new Rectangle(rect.x, rect.y + top, rect.width, bottom - top), colorA, colorA, colorB, colorB);
            currentHue = nextHue;
         }

      }
   }
}
