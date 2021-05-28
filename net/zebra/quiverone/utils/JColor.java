package net.zebra.quiverone.utils;

import java.awt.Color;

public class JColor extends Color {
   private static final long serialVersionUID = 1L;

   public JColor(int rgb) {
      super(rgb);
   }

   public JColor(int rgba, boolean hasalpha) {
      super(rgba, hasalpha);
   }

   public JColor(int r, int g, int b) {
      super(r, g, b);
   }

   public JColor(int r, int g, int b, int a) {
      super(r, g, b, a);
   }

   public JColor(Color color) {
      super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
   }

   public JColor(JColor color, int a) {
      super(color.getRed(), color.getGreen(), color.getBlue(), a);
   }

   public static JColor fromHSB(float hue, float saturation, float brightness) {
      return new JColor(Color.getHSBColor(hue, saturation, brightness));
   }

   public float getHue() {
      return RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), (float[])null)[0];
   }

   public float getSaturation() {
      return RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), (float[])null)[1];
   }

   public float getBrightness() {
      return RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), (float[])null)[2];
   }
}
