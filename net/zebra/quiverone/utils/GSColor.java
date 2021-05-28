package net.zebra.quiverone.utils;

import java.awt.Color;
import net.minecraft.class_4493;

public class GSColor extends Color {
   private static final long serialVersionUID = 1L;

   public GSColor(int rgb) {
      super(rgb);
   }

   public GSColor(int rgba, boolean hasalpha) {
      super(rgba, hasalpha);
   }

   public GSColor(int r, int g, int b) {
      super(r, g, b);
   }

   public GSColor(int r, int g, int b, int a) {
      super(r, g, b, a);
   }

   public GSColor(Color color) {
      super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
   }

   public GSColor(GSColor color, int a) {
      super(color.getRed(), color.getGreen(), color.getBlue(), a);
   }

   public static GSColor fromHSB(float hue, float saturation, float brightness) {
      return new GSColor(Color.getHSBColor(hue, saturation, brightness));
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

   public void glColor() {
      class_4493.method_21943((float)this.getRed() / 255.0F, (float)this.getGreen() / 255.0F, (float)this.getBlue() / 255.0F, (float)this.getAlpha() / 255.0F);
   }
}
