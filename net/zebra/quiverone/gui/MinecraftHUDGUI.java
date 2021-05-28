package net.zebra.quiverone.gui;

import com.lukflug.panelstudio.hud.HUDClickGUI;
import net.minecraft.class_310;

public abstract class MinecraftHUDGUI extends MinecraftGUI {
   protected boolean hudEditor = false;

   public void enterGUI() {
      this.hudEditor = false;
      super.enterGUI();
   }

   public void method_25419() {
      this.hudEditor = false;
      super.method_25419();
   }

   public void enterHUDEditor() {
      this.hudEditor = true;
      if (this.getHUDGUI().isOn()) {
         this.getHUDGUI().toggle();
      }

      class_310.method_1551().method_1507(this);
   }

   public void render() {
      if (!this.getHUDGUI().isOn() && !this.hudEditor) {
         this.renderGUI();
      }

   }

   public void handleKeyEvent(int scancode) {
      if (scancode != 1 && !this.getHUDGUI().isOn() && !this.hudEditor) {
         this.getHUDGUI().handleKey(scancode);
      }

   }

   protected abstract HUDClickGUI getHUDGUI();

   protected com.lukflug.panelstudio.ClickGUI getGUI() {
      return this.getHUDGUI();
   }
}
