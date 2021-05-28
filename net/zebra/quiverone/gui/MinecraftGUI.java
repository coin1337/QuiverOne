package net.zebra.quiverone.gui;

import java.awt.Point;
import net.minecraft.class_2585;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public abstract class MinecraftGUI extends class_437 {
   private Point mouse = new Point();
   private boolean lButton = false;
   private boolean rButton = false;
   protected class_4587 matrixStack = null;

   public MinecraftGUI() {
      super(new class_2585("PanelStudio GUI"));
   }

   public void enterGUI() {
      class_310.method_1551().method_1507(this);
      this.getGUI().enter();
   }

   public void method_25419() {
      this.getGUI().exit();
      super.method_25419();
   }

   protected void renderGUI() {
      this.getInterface().getMatrices();
      GLInterface.begin();
      this.getGUI().render();
      GLInterface.end();
   }

   public void method_25394(class_4587 matrices, int mouseX, int mouseY, float partialTicks) {
      this.matrixStack = matrices;
      this.mouse = new Point(Math.round((float)mouseX), Math.round((float)mouseY));
      this.renderGUI();
   }

   public boolean method_25401(double mouseX, double mouseY, double scroll) {
      if (!super.method_25401(mouseX, mouseY, scroll)) {
         this.mouse = new Point((int)Math.round(mouseX), (int)Math.round(mouseY));
         if (scroll != 0.0D) {
            if (scroll > 0.0D) {
               this.getGUI().handleScroll(-this.getScrollSpeed());
            } else {
               this.getGUI().handleScroll(this.getScrollSpeed());
            }
         }
      }

      return true;
   }

   public boolean method_25402(double mouseX, double mouseY, int clickedButton) {
      if (!super.method_25406(mouseX, mouseY, clickedButton)) {
         this.mouse = new Point((int)Math.round(mouseX), (int)Math.round(mouseY));
         switch(clickedButton) {
         case 0:
            this.lButton = true;
            break;
         case 1:
            this.rButton = true;
         }

         this.getGUI().handleButton(clickedButton);
      }

      return true;
   }

   public boolean method_25406(double mouseX, double mouseY, int releaseButton) {
      if (!super.method_25406(mouseX, mouseY, releaseButton)) {
         this.mouse = new Point((int)Math.round(mouseX), (int)Math.round(mouseY));
         switch(releaseButton) {
         case 0:
            this.lButton = false;
            break;
         case 1:
            this.rButton = false;
         }

         this.getGUI().handleButton(releaseButton);
      }

      return true;
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (!super.method_25404(keyCode, scanCode, modifiers)) {
         this.getGUI().handleKey(keyCode);
      }

      return true;
   }

   public boolean method_25421() {
      return false;
   }

   protected abstract com.lukflug.panelstudio.ClickGUI getGUI();

   protected abstract MinecraftGUI.GUIInterface getInterface();

   protected abstract int getScrollSpeed();

   public abstract class GUIInterface extends GLInterface {
      public GUIInterface(boolean clipX) {
         super(clipX);
      }

      public boolean getButton(int button) {
         switch(button) {
         case 0:
            return MinecraftGUI.this.lButton;
         case 1:
            return MinecraftGUI.this.rButton;
         default:
            return false;
         }
      }

      public Point getMouse() {
         return new Point(MinecraftGUI.this.mouse);
      }

      protected float getZLevel() {
         return (float)MinecraftGUI.this.method_25305();
      }
   }
}
