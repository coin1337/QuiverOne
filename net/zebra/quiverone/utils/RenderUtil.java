package net.zebra.quiverone.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1159;
import net.minecraft.class_1160;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_308;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4581;
import net.minecraft.class_4587;
import net.minecraft.class_4588;

public class RenderUtil extends class_332 {
   public static class_310 mc = class_310.method_1551();

   public static void drawItem(class_1799 itemStack, int x, int y, boolean overlay) {
      RenderSystem.disableLighting();
      RenderSystem.disableDepthTest();
      class_308.method_22890();
      mc.method_1480().method_4010(itemStack, x, y);
      if (overlay) {
         mc.method_1480().method_4022(mc.field_1772, itemStack, x, y, (String)null);
      }

      class_308.method_1450();
      class_308.method_1450();
      RenderSystem.enableDepthTest();
   }

   public static void drawItem(class_1799 itemStack, int x, int y, double scale, boolean overlay) {
      RenderSystem.pushMatrix();
      RenderSystem.scaled(scale, scale, 1.0D);
      drawItem(itemStack, x, y, overlay);
      RenderSystem.popMatrix();
   }

   public static void drawRect(class_4587 matrices, int x1, int y1, int x2, int y2, int color) {
      method_25294(matrices, x1, y1, x2, y2, color);
   }

   public static void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha, float width) {
      setup();
      class_4587 matrix = matrixFrom(x1, y1, z1);
      class_289 tessellator = RenderSystem.renderThreadTesselator();
      class_287 buffer = tessellator.method_1349();
      RenderSystem.disableCull();
      RenderSystem.lineWidth(width);
      buffer.method_1328(3, class_290.field_1576);
      RenderUtil.Vertexer.vertexLine(matrix, buffer, 0.0F, 0.0F, 0.0F, (float)(x2 - x1), (float)(y2 - y1), (float)(z2 - z1), red, green, blue, alpha);
      tessellator.method_1350();
      RenderSystem.enableCull();
      cleanup();
   }

   public static class_4587 matrixFrom(double x, double y, double z) {
      class_4587 matrix = new class_4587();
      class_4184 camera = class_310.method_1551().field_1773.method_19418();
      matrix.method_22907(class_1160.field_20703.method_23214(camera.method_19329()));
      matrix.method_22907(class_1160.field_20705.method_23214(camera.method_19330() + 180.0F));
      matrix.method_22904(x - camera.method_19326().field_1352, y - camera.method_19326().field_1351, z - camera.method_19326().field_1350);
      return matrix;
   }

   public static void setup() {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableTexture();
   }

   public static void cleanup() {
      RenderSystem.disableBlend();
      RenderSystem.enableTexture();
   }

   public static void drawFill(class_2338 blockPos, float red, float green, float blue, float alpha) {
      drawFill(new class_238(blockPos), red, green, blue, alpha);
   }

   public static void drawFill(class_238 box, float red, float green, float blue, float alpha) {
      setup();
      class_4587 matrix = matrixFrom(box.field_1323, box.field_1322, box.field_1321);
      class_289 tessellator = class_289.method_1348();
      class_287 buffer = tessellator.method_1349();
      buffer.method_1328(7, class_290.field_1576);
      RenderUtil.Vertexer.vertexBoxQuads(matrix, buffer, Boxes.moveToZero(box), red, green, blue, alpha);
      tessellator.method_1350();
      cleanup();
   }

   public static class Vertexer {
      public static void vertexBoxQuads(class_4587 matrix, class_4588 vertexConsumer, class_238 box, float red, float green, float blue, float alpha) {
         float x1 = (float)box.field_1323;
         float y1 = (float)box.field_1322;
         float z1 = (float)box.field_1321;
         float x2 = (float)box.field_1320;
         float y2 = (float)box.field_1325;
         float z2 = (float)box.field_1324;
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y1, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y1, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y1, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y1, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y1, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y2, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y2, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y1, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y1, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y2, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y2, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y1, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y1, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y2, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y2, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y1, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y1, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y2, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y2, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y1, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y2, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y2, z2).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x2, y2, z1).method_22915(red, green, blue, alpha).method_1344();
         vertexConsumer.method_22918(matrix.method_23760().method_23761(), x1, y2, z1).method_22915(red, green, blue, alpha).method_1344();
      }

      public static void vertexBoxLines(class_4587 matrix, class_4588 vertexConsumer, class_238 box, float red, float green, float blue, float alpha) {
         float x1 = (float)box.field_1323;
         float y1 = (float)box.field_1322;
         float z1 = (float)box.field_1321;
         float x2 = (float)box.field_1320;
         float y2 = (float)box.field_1325;
         float z2 = (float)box.field_1324;
         vertexLine(matrix, vertexConsumer, x1, y1, z1, x2, y1, z1, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x2, y1, z1, x2, y1, z2, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x2, y1, z2, x1, y1, z2, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x1, y1, z2, x1, y1, z1, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x1, y1, z1, x1, y2, z1, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x2, y1, z1, x2, y2, z1, red, green, blue, 0.0F);
         vertexLine(matrix, vertexConsumer, x2, y1, z1, x2, y2, z1, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x2, y1, z2, x2, y2, z2, red, green, blue, 0.0F);
         vertexLine(matrix, vertexConsumer, x2, y1, z2, x2, y2, z2, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x1, y1, z2, x1, y2, z2, red, green, blue, 0.0F);
         vertexLine(matrix, vertexConsumer, x1, y1, z2, x1, y2, z2, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x1, y2, z1, x2, y2, z1, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x2, y2, z1, x2, y2, z2, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x2, y2, z2, x1, y2, z2, red, green, blue, alpha);
         vertexLine(matrix, vertexConsumer, x1, y2, z2, x1, y2, z1, red, green, blue, alpha);
      }

      public static void vertexLine(class_4587 matrix, class_4588 vertexConsumer, float x1, float y1, float z1, float x2, float y2, float z2, float red, float green, float blue, float alpha) {
         class_1159 model = matrix.method_23760().method_23761();
         class_4581 normal = matrix.method_23760().method_23762();
         class_1160 normalVec = getNormal(normal, x1, y1, z1, x2, y2, z2);
         vertexConsumer.method_22918(model, x1, y1, z1).method_22915(red, green, blue, alpha).method_23763(normal, normalVec.method_4943(), normalVec.method_4945(), normalVec.method_4947()).method_1344();
         vertexConsumer.method_22918(model, x2, y2, z2).method_22915(red, green, blue, alpha).method_23763(normal, normalVec.method_4943(), normalVec.method_4945(), normalVec.method_4947()).method_1344();
      }

      public static class_1160 getNormal(class_4581 normal, float x1, float y1, float z1, float x2, float y2, float z2) {
         float xNormal = x2 - x1;
         float yNormal = y2 - y1;
         float zNormal = z2 - z1;
         float normalSqrt = class_3532.method_15355(xNormal * xNormal + yNormal * yNormal + zNormal * zNormal);
         return new class_1160(xNormal / normalSqrt, yNormal / normalSqrt, zNormal / normalSqrt);
      }
   }
}
