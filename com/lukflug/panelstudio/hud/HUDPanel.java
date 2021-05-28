package com.lukflug.panelstudio.hud;

import com.lukflug.panelstudio.Animation;
import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.PanelConfig;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.RendererProxy;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class HUDPanel extends DraggableContainer {
   protected Toggleable guiOpen;
   protected FixedComponent component;

   public HUDPanel(FixedComponent component, Renderer renderer, Toggleable open, Animation animation, Toggleable guiOpen, int minBorder) {
      super(component.getTitle(), (String)null, new HUDPanel.HUDRenderer(renderer, guiOpen, minBorder), open, animation, (Toggleable)null, new Point(0, 0), 0);
      this.addComponent(component);
      this.guiOpen = guiOpen;
      this.component = component;
      this.bodyDrag = true;
   }

   public void handleButton(Context context, int button) {
      if (this.guiOpen.isOn()) {
         super.handleButton(context, button);
      }

   }

   public void handleScroll(Context context, int diff) {
      if (this.guiOpen.isOn()) {
         super.handleScroll(context, diff);
      }

   }

   public Point getPosition(Interface inter) {
      this.position = this.component.getPosition(inter);
      this.position.translate(0, -this.renderer.getHeight(this.open.getValue() != 0.0D) - this.renderer.getOffset());
      return super.getPosition(inter);
   }

   public void setPosition(Interface inter, Point position) {
      this.component.setPosition(inter, new Point(position.x, position.y + this.renderer.getHeight(this.open.getValue() != 0.0D) + this.renderer.getOffset()));
   }

   public int getWidth(Interface inter) {
      return this.component.getWidth(inter) + this.renderer.getBorder() * 2 + this.renderer.getLeftBorder(this.scroll) + this.renderer.getRightBorder(this.scroll);
   }

   protected Rectangle getClipRect(Context context, int height) {
      return this.open.getValue() != 1.0D ? super.getClipRect(context, height) : null;
   }

   public void saveConfig(Interface inter, PanelConfig config) {
      this.component.saveConfig(inter, config);
      config.saveState(this.open.isOn());
   }

   public void loadConfig(Interface inter, PanelConfig config) {
      this.component.loadConfig(inter, config);
      if (this.open.isOn() != config.loadState()) {
         this.open.toggle();
      }

   }

   protected static class HUDRenderer extends RendererProxy {
      Renderer renderer;
      protected Toggleable guiOpen;
      protected int minBorder;

      public HUDRenderer(Renderer renderer, Toggleable guiOpen, int minBorder) {
         this.renderer = renderer;
         this.guiOpen = guiOpen;
         this.minBorder = minBorder;
      }

      public int getOffset() {
         return Math.max(this.renderer.getOffset(), this.minBorder);
      }

      public int getBorder() {
         return Math.max(this.renderer.getBorder(), this.minBorder);
      }

      public void renderTitle(Context context, String text, boolean focus) {
         if (this.guiOpen.isOn()) {
            this.renderer.renderTitle(context, text, focus);
         }

      }

      public void renderTitle(Context context, String text, boolean focus, boolean active) {
         if (this.guiOpen.isOn()) {
            this.renderer.renderTitle(context, text, focus, active);
         }

      }

      public void renderTitle(Context context, String text, boolean focus, boolean active, boolean open) {
         if (this.guiOpen.isOn()) {
            this.renderer.renderTitle(context, text, focus, open);
         }

      }

      public void renderRect(Context context, String text, boolean focus, boolean active, Rectangle rectangle, boolean overlay) {
         if (this.guiOpen.isOn()) {
            this.renderer.renderRect(context, text, focus, active, rectangle, overlay);
         }

      }

      public void renderBackground(Context context, boolean focus) {
         if (this.guiOpen.isOn()) {
            this.renderer.renderBackground(context, focus);
         }

      }

      public void renderBorder(Context context, boolean focus, boolean active, boolean open) {
         if (this.guiOpen.isOn()) {
            this.renderer.renderBorder(context, focus, active, open);
         }

      }

      public int renderScrollBar(Context context, boolean focus, boolean active, boolean scroll, int childHeight, int scrollPosition) {
         return this.guiOpen.isOn() ? this.renderer.renderScrollBar(context, focus, active, scroll, childHeight, scrollPosition) : scrollPosition;
      }

      public Color getMainColor(boolean focus, boolean active) {
         return this.guiOpen.isOn() ? this.renderer.getMainColor(focus, active) : new Color(0, 0, 0, 0);
      }

      public Color getBackgroundColor(boolean focus) {
         return this.guiOpen.isOn() ? this.renderer.getBackgroundColor(focus) : new Color(0, 0, 0, 0);
      }

      public Color getFontColor(boolean focus) {
         return this.guiOpen.isOn() ? this.renderer.getFontColor(focus) : new Color(0, 0, 0, 0);
      }

      protected Renderer getRenderer() {
         return this.renderer;
      }
   }
}
