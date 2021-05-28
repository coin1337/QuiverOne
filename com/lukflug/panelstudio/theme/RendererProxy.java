package com.lukflug.panelstudio.theme;

import com.lukflug.panelstudio.Context;
import java.awt.Color;
import java.awt.Rectangle;

public abstract class RendererProxy implements Renderer {
   public int getHeight(boolean open) {
      return this.getRenderer().getHeight(open);
   }

   public int getOffset() {
      return this.getRenderer().getOffset();
   }

   public int getBorder() {
      return this.getRenderer().getBorder();
   }

   public int getBottomBorder() {
      return this.getRenderer().getBottomBorder();
   }

   public int getLeftBorder(boolean scroll) {
      return this.getRenderer().getLeftBorder(scroll);
   }

   public int getRightBorder(boolean scroll) {
      return this.getRenderer().getRightBorder(scroll);
   }

   public void renderTitle(Context context, String text, boolean focus) {
      this.getRenderer().renderTitle(context, text, focus);
   }

   public void renderTitle(Context context, String text, boolean focus, boolean active) {
      this.getRenderer().renderTitle(context, text, focus, active);
   }

   public void renderTitle(Context context, String text, boolean focus, boolean active, boolean open) {
      this.getRenderer().renderTitle(context, text, focus, active, open);
   }

   public void renderRect(Context context, String text, boolean focus, boolean active, Rectangle rectangle, boolean overlay) {
      this.getRenderer().renderRect(context, text, focus, active, rectangle, overlay);
   }

   public void renderBackground(Context context, boolean focus) {
      this.getRenderer().renderBackground(context, focus);
   }

   public void renderBorder(Context context, boolean focus, boolean active, boolean open) {
      this.getRenderer().renderBorder(context, focus, active, open);
   }

   public int renderScrollBar(Context context, boolean focus, boolean active, boolean scroll, int childHeight, int scrollPosition) {
      return this.getRenderer().renderScrollBar(context, focus, active, scroll, childHeight, scrollPosition);
   }

   public Color getMainColor(boolean focus, boolean active) {
      return this.getRenderer().getMainColor(focus, active);
   }

   public Color getBackgroundColor(boolean focus) {
      return this.getRenderer().getBackgroundColor(focus);
   }

   public Color getFontColor(boolean focus) {
      return this.getRenderer().getFontColor(focus);
   }

   public ColorScheme getDefaultColorScheme() {
      return this.getRenderer().getDefaultColorScheme();
   }

   public void overrideColorScheme(ColorScheme scheme) {
      this.getRenderer().overrideColorScheme(scheme);
   }

   public void restoreColorScheme() {
      this.getRenderer().restoreColorScheme();
   }

   protected abstract Renderer getRenderer();
}
