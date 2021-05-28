package com.lukflug.panelstudio;

import com.lukflug.panelstudio.theme.Renderer;

public class FocusableComponent implements Component {
   protected String title;
   protected String description;
   protected Renderer renderer;
   private boolean focus = false;

   public FocusableComponent(String title, String description, Renderer renderer) {
      this.title = title;
      this.renderer = renderer;
      this.description = description;
   }

   public String getTitle() {
      return this.title;
   }

   public void render(Context context) {
      context.setHeight(this.renderer.getHeight(false));
      context.setDescription(this.description);
   }

   public void handleKey(Context context, int scancode) {
      context.setHeight(this.renderer.getHeight(false));
   }

   public void handleButton(Context context, int button) {
      context.setHeight(this.renderer.getHeight(false));
      this.updateFocus(context, button);
   }

   public void getHeight(Context context) {
      context.setHeight(this.renderer.getHeight(false));
   }

   public void handleScroll(Context context, int diff) {
      context.setHeight(this.renderer.getHeight(false));
   }

   public void enter(Context context) {
      context.setHeight(this.renderer.getHeight(false));
   }

   public void exit(Context context) {
      context.setHeight(this.renderer.getHeight(false));
   }

   public boolean hasFocus(Context context) {
      return context.hasFocus() && this.focus;
   }

   public void releaseFocus() {
      this.focus = false;
   }

   protected void updateFocus(Context context, int button) {
      if (context.getInterface().getButton(button)) {
         this.focus = context.isHovered();
         this.handleFocus(context, this.focus && context.hasFocus());
      }

   }

   protected void handleFocus(Context context, boolean focus) {
   }
}
