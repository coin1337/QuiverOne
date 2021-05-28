package com.lukflug.panelstudio.theme;

import com.lukflug.panelstudio.Context;
import java.awt.Color;

public abstract class RendererBase implements Renderer {
   protected final int height;
   protected final int offset;
   protected final int border;
   protected final int left;
   protected final int right;
   protected ColorScheme scheme = null;

   public RendererBase(int height, int offset, int border, int left, int right) {
      this.height = height;
      this.offset = offset;
      this.border = border;
      this.left = left;
      this.right = right;
   }

   public int getHeight(boolean open) {
      return this.height;
   }

   public int getOffset() {
      return this.offset;
   }

   public int getBorder() {
      return this.border;
   }

   public int getBottomBorder() {
      return 0;
   }

   public int getLeftBorder(boolean scroll) {
      return scroll ? this.left : 0;
   }

   public int getRightBorder(boolean scroll) {
      return scroll ? this.right : 0;
   }

   public void renderTitle(Context context, String text, boolean focus) {
      this.renderTitle(context, text, focus, false);
   }

   public void renderTitle(Context context, String text, boolean focus, boolean active) {
      this.renderRect(context, text, focus, active, context.getRect(), true);
   }

   public void renderTitle(Context context, String text, boolean focus, boolean active, boolean open) {
      this.renderTitle(context, text, focus, active);
   }

   public int renderScrollBar(Context context, boolean focus, boolean active, boolean scroll, int childHeight, int scrollPosition) {
      return scrollPosition;
   }

   public Color getFontColor(boolean focus) {
      return this.getColorScheme().getFontColor();
   }

   public void overrideColorScheme(ColorScheme scheme) {
      this.scheme = scheme;
   }

   public void restoreColorScheme() {
      this.scheme = null;
   }

   protected ColorScheme getColorScheme() {
      return this.scheme == null ? this.getDefaultColorScheme() : this.scheme;
   }
}
