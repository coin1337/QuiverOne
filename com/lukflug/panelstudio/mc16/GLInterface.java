package com.lukflug.panelstudio.mc16;

import com.lukflug.panelstudio.Interface;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4493;
import org.lwjgl.opengl.GL11;

public abstract class GLInterface implements Interface {
   private static final float[] MODELVIEW = new float[16];
   private static final float[] PROJECTION = new float[16];
   private static final int[] VIEWPORT = new int[16];
   private static final float[] COORDS = new float[4];
   private Stack<Rectangle> clipRect = new Stack();
   protected boolean clipX;
   protected List<class_2960> textures = new ArrayList();

   public GLInterface(boolean clipX) {
      this.clipX = clipX;
   }

   public void fillTriangle(Point pos1, Point pos2, Point pos3, Color c1, Color c2, Color c3) {
      class_289 tessellator = class_289.method_1348();
      class_287 bufferbuilder = tessellator.method_1349();
      bufferbuilder.method_1328(4, class_290.field_1576);
      bufferbuilder.method_22912((double)pos1.x, (double)pos1.y, (double)this.getZLevel()).method_22915((float)c1.getRed() / 255.0F, (float)c1.getGreen() / 255.0F, (float)c1.getBlue() / 255.0F, (float)c1.getAlpha() / 255.0F).method_1344();
      bufferbuilder.method_22912((double)pos2.x, (double)pos2.y, (double)this.getZLevel()).method_22915((float)c2.getRed() / 255.0F, (float)c2.getGreen() / 255.0F, (float)c2.getBlue() / 255.0F, (float)c2.getAlpha() / 255.0F).method_1344();
      bufferbuilder.method_22912((double)pos3.x, (double)pos3.y, (double)this.getZLevel()).method_22915((float)c3.getRed() / 255.0F, (float)c3.getGreen() / 255.0F, (float)c3.getBlue() / 255.0F, (float)c3.getAlpha() / 255.0F).method_1344();
      tessellator.method_1350();
   }

   public void drawLine(Point a, Point b, Color c1, Color c2) {
      class_289 tessellator = class_289.method_1348();
      class_287 bufferbuilder = tessellator.method_1349();
      bufferbuilder.method_1328(1, class_290.field_1576);
      bufferbuilder.method_22912((double)a.x, (double)a.y, (double)this.getZLevel()).method_22915((float)c1.getRed() / 255.0F, (float)c1.getGreen() / 255.0F, (float)c1.getBlue() / 255.0F, (float)c1.getAlpha() / 255.0F).method_1344();
      bufferbuilder.method_22912((double)b.x, (double)b.y, (double)this.getZLevel()).method_22915((float)c2.getRed() / 255.0F, (float)c2.getGreen() / 255.0F, (float)c2.getBlue() / 255.0F, (float)c2.getAlpha() / 255.0F).method_1344();
      tessellator.method_1350();
   }

   public void fillRect(Rectangle r, Color c1, Color c2, Color c3, Color c4) {
      class_289 tessellator = class_289.method_1348();
      class_287 bufferbuilder = tessellator.method_1349();
      bufferbuilder.method_1328(7, class_290.field_1576);
      bufferbuilder.method_22912((double)r.x, (double)(r.y + r.height), (double)this.getZLevel()).method_22915((float)c4.getRed() / 255.0F, (float)c4.getGreen() / 255.0F, (float)c4.getBlue() / 255.0F, (float)c4.getAlpha() / 255.0F).method_1344();
      bufferbuilder.method_22912((double)(r.x + r.width), (double)(r.y + r.height), (double)this.getZLevel()).method_22915((float)c3.getRed() / 255.0F, (float)c3.getGreen() / 255.0F, (float)c3.getBlue() / 255.0F, (float)c3.getAlpha() / 255.0F).method_1344();
      bufferbuilder.method_22912((double)(r.x + r.width), (double)r.y, (double)this.getZLevel()).method_22915((float)c2.getRed() / 255.0F, (float)c2.getGreen() / 255.0F, (float)c2.getBlue() / 255.0F, (float)c2.getAlpha() / 255.0F).method_1344();
      bufferbuilder.method_22912((double)r.x, (double)r.y, (double)this.getZLevel()).method_22915((float)c1.getRed() / 255.0F, (float)c1.getGreen() / 255.0F, (float)c1.getBlue() / 255.0F, (float)c1.getAlpha() / 255.0F).method_1344();
      tessellator.method_1350();
   }

   public void drawRect(Rectangle r, Color c1, Color c2, Color c3, Color c4) {
      class_289 tessellator = class_289.method_1348();
      class_287 bufferbuilder = tessellator.method_1349();
      bufferbuilder.method_1328(2, class_290.field_1576);
      bufferbuilder.method_22912((double)r.x, (double)(r.y + r.height), (double)this.getZLevel()).method_22915((float)c4.getRed() / 255.0F, (float)c4.getGreen() / 255.0F, (float)c4.getBlue() / 255.0F, (float)c4.getAlpha() / 255.0F).method_1344();
      bufferbuilder.method_22912((double)(r.x + r.width), (double)(r.y + r.height), (double)this.getZLevel()).method_22915((float)c3.getRed() / 255.0F, (float)c3.getGreen() / 255.0F, (float)c3.getBlue() / 255.0F, (float)c3.getAlpha() / 255.0F).method_1344();
      bufferbuilder.method_22912((double)(r.x + r.width), (double)r.y, (double)this.getZLevel()).method_22915((float)c2.getRed() / 255.0F, (float)c2.getGreen() / 255.0F, (float)c2.getBlue() / 255.0F, (float)c2.getAlpha() / 255.0F).method_1344();
      bufferbuilder.method_22912((double)r.x, (double)r.y, (double)this.getZLevel()).method_22915((float)c1.getRed() / 255.0F, (float)c1.getGreen() / 255.0F, (float)c1.getBlue() / 255.0F, (float)c1.getAlpha() / 255.0F).method_1344();
      tessellator.method_1350();
   }

   public synchronized int loadImage(String name) {
      try {
         class_2960 rl = new class_2960(this.getResourcePrefix() + name);
         if (!this.textures.contains(rl)) {
            class_310.method_1551().method_1531().method_18168(rl, (Executor)null).get();
            this.textures.add(rl);
         }

         return this.textures.indexOf(rl);
      } catch (ExecutionException var3) {
         var3.printStackTrace();
         return 0;
      } catch (InterruptedException var4) {
         var4.printStackTrace();
         return 0;
      }
   }

   public void drawImage(Rectangle r, int rotation, boolean parity, int image) {
      if (image != 0) {
         int[][] texCoords = new int[][]{{0, 1}, {1, 1}, {1, 0}, {0, 0}};

         int temp1;
         int temp2;
         for(temp1 = 0; temp1 < rotation % 4; ++temp1) {
            temp2 = texCoords[3][0];
            int temp2 = texCoords[3][1];
            texCoords[3][0] = texCoords[2][0];
            texCoords[3][1] = texCoords[2][1];
            texCoords[2][0] = texCoords[1][0];
            texCoords[2][1] = texCoords[1][1];
            texCoords[1][0] = texCoords[0][0];
            texCoords[1][1] = texCoords[0][1];
            texCoords[0][0] = temp2;
            texCoords[0][1] = temp2;
         }

         if (parity) {
            temp1 = texCoords[3][0];
            temp2 = texCoords[3][1];
            texCoords[3][0] = texCoords[0][0];
            texCoords[3][1] = texCoords[0][1];
            texCoords[0][0] = temp1;
            texCoords[0][1] = temp2;
            temp1 = texCoords[2][0];
            temp2 = texCoords[2][1];
            texCoords[2][0] = texCoords[1][0];
            texCoords[2][1] = texCoords[1][1];
            texCoords[1][0] = temp1;
            texCoords[1][1] = temp2;
         }

         class_289 tessellator = class_289.method_1348();
         class_287 bufferbuilder = tessellator.method_1349();
         class_310.method_1551().method_1531().method_22813((class_2960)this.textures.get(image));
         class_4493.method_21910();
         bufferbuilder.method_1328(7, class_290.field_1585);
         bufferbuilder.method_22912((double)r.x, (double)(r.y + r.height), (double)this.getZLevel()).method_22913((float)texCoords[0][0], (float)texCoords[0][1]).method_1344();
         bufferbuilder.method_22912((double)(r.x + r.width), (double)(r.y + r.height), (double)this.getZLevel()).method_22913((float)texCoords[1][0], (float)texCoords[1][1]).method_1344();
         bufferbuilder.method_22912((double)(r.x + r.width), (double)r.y, (double)this.getZLevel()).method_22913((float)texCoords[2][0], (float)texCoords[2][1]).method_1344();
         bufferbuilder.method_22912((double)r.x, (double)r.y, (double)this.getZLevel()).method_22913((float)texCoords[3][0], (float)texCoords[3][1]).method_1344();
         tessellator.method_1350();
         class_4493.method_21912();
      }
   }

   private void transform(float[] matrix) {
      float[] coords = new float[]{0.0F, 0.0F, 0.0F, 0.0F};

      int i;
      for(i = 0; i < 4; ++i) {
         for(int j = 0; j < 4; ++j) {
            coords[i] += matrix[i + 4 * j] * COORDS[j];
         }
      }

      for(i = 0; i < 4; ++i) {
         COORDS[i] = coords[i];
      }

   }

   private void project(float x, float y, float z, float w) {
      COORDS[0] = x;
      COORDS[1] = y;
      COORDS[2] = z;
      COORDS[3] = w;
      this.transform(MODELVIEW);
      this.transform(PROJECTION);

      for(int i = 0; i < 4; ++i) {
         COORDS[i] = (COORDS[i] / COORDS[3] + 1.0F) / 2.0F;
      }

      COORDS[0] = COORDS[0] * (float)VIEWPORT[2] + (float)VIEWPORT[0];
      COORDS[1] = COORDS[1] * (float)VIEWPORT[3] + (float)VIEWPORT[1];
   }

   protected void scissor(Rectangle r) {
      if (r == null) {
         GL11.glScissor(0, 0, 0, 0);
         GL11.glEnable(3089);
      } else {
         this.project((float)r.x, (float)r.y, this.getZLevel(), 1.0F);
         float x1 = COORDS[0];
         float y1 = COORDS[1];
         this.project((float)(r.x + r.width), (float)(r.y + r.height), this.getZLevel(), 1.0F);
         float x2 = COORDS[0];
         float y2 = COORDS[1];
         if (!this.clipX) {
            x1 = (float)VIEWPORT[0];
            x2 = x1 + (float)VIEWPORT[2];
         }

         GL11.glScissor(Math.round(Math.min(x1, x2)), Math.round(Math.min(y1, y2)), Math.round(Math.abs(x2 - x1)), Math.round(Math.abs(y2 - y1)));
         GL11.glEnable(3089);
      }
   }

   public void window(Rectangle r) {
      if (this.clipRect.isEmpty()) {
         this.scissor(r);
         this.clipRect.push(r);
      } else {
         Rectangle top = (Rectangle)this.clipRect.peek();
         if (top == null) {
            this.scissor((Rectangle)null);
            this.clipRect.push((Object)null);
         } else {
            int x1 = Math.max(r.x, top.x);
            int y1 = Math.max(r.y, top.y);
            int x2 = Math.min(r.x + r.width, top.x + top.width);
            int y2 = Math.min(r.y + r.height, top.y + top.height);
            if (x2 > x1 && y2 > y1) {
               Rectangle rect = new Rectangle(x1, y1, x2 - x1, y2 - y1);
               this.scissor(rect);
               this.clipRect.push(rect);
            } else {
               this.scissor((Rectangle)null);
               this.clipRect.push((Object)null);
            }
         }
      }

   }

   public void restore() {
      if (!this.clipRect.isEmpty()) {
         this.clipRect.pop();
         if (this.clipRect.isEmpty()) {
            GL11.glDisable(3089);
         } else {
            this.scissor((Rectangle)this.clipRect.peek());
         }
      }

   }

   public void getMatrices() {
      GL11.glGetFloatv(2982, MODELVIEW);
      GL11.glGetFloatv(2983, PROJECTION);
      GL11.glGetIntegerv(2978, VIEWPORT);
   }

   public static void begin() {
      class_4493.method_22056();
      class_4493.method_21912();
      class_4493.method_21950(770, 771, 1, 0);
      class_4493.method_22083(7425);
      class_4493.method_22013(2.0F);
   }

   public static void end() {
      class_4493.method_22083(7424);
      class_4493.method_21910();
      class_4493.method_22053();
   }

   protected abstract float getZLevel();

   protected abstract String getResourcePrefix();
}
