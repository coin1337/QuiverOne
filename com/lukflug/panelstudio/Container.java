package com.lukflug.panelstudio;

import com.lukflug.panelstudio.theme.Renderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Container extends FocusableComponent {
   protected List<Component> components = new ArrayList();
   private String tempDescription;

   public Container(String title, String description, Renderer renderer) {
      super(title, description, renderer);
   }

   public void addComponent(Component component) {
      this.components.add(component);
   }

   public void render(Context context) {
      this.tempDescription = null;
      this.doComponentLoop(context, (subContext, component) -> {
         component.render(subContext);
         if (subContext.isHovered() && subContext.getDescription() != null) {
            this.tempDescription = subContext.getDescription();
         }

      });
      if (this.tempDescription == null) {
         this.tempDescription = this.description;
      }

      context.setDescription(this.tempDescription);
   }

   public void handleButton(Context context, int button) {
      this.getHeight(context);
      this.updateFocus(context, button);
      this.doComponentLoop(context, (subContext, component) -> {
         component.handleButton(subContext, button);
         if (subContext.focusReleased()) {
            context.releaseFocus();
         }

      });
   }

   public void handleKey(Context context, int scancode) {
      this.doComponentLoop(context, (subContext, component) -> {
         component.handleKey(subContext, scancode);
      });
   }

   public void handleScroll(Context context, int diff) {
      this.doComponentLoop(context, (subContext, component) -> {
         component.handleScroll(subContext, diff);
      });
   }

   public void getHeight(Context context) {
      this.doComponentLoop(context, (subContext, component) -> {
         component.getHeight(subContext);
      });
   }

   public void enter(Context context) {
      this.doComponentLoop(context, (subContext, component) -> {
         component.enter(subContext);
      });
   }

   public void exit(Context context) {
      this.doComponentLoop(context, (subContext, component) -> {
         component.exit(subContext);
      });
   }

   public void releaseFocus() {
      super.releaseFocus();
      Iterator var1 = this.components.iterator();

      while(var1.hasNext()) {
         Component component = (Component)var1.next();
         component.releaseFocus();
      }

   }

   protected void handleFocus(Context context, boolean focus) {
      if (!focus) {
         this.releaseFocus();
      }

   }

   protected Context getSubContext(Context context, int posy) {
      return new Context(context, this.renderer.getBorder(), this.renderer.getBorder(), posy, this.hasFocus(context), true);
   }

   protected void doComponentLoop(Context context, Container.LoopFunction function) {
      int posy = this.renderer.getOffset();

      Context subContext;
      for(Iterator var4 = this.components.iterator(); var4.hasNext(); posy += subContext.getSize().height + this.renderer.getOffset()) {
         Component component = (Component)var4.next();
         subContext = this.getSubContext(context, posy);
         function.loop(subContext, component);
      }

      context.setHeight(posy);
   }

   protected interface LoopFunction {
      void loop(Context var1, Component var2);
   }
}
