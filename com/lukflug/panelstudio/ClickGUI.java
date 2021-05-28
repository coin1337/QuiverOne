package com.lukflug.panelstudio;

import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.DescriptionRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClickGUI implements PanelManager {
   protected List<FixedComponent> components = new ArrayList();
   protected List<FixedComponent> permanentComponents = new ArrayList();
   protected Interface inter;
   protected DescriptionRenderer descriptionRenderer;

   public ClickGUI(Interface inter, DescriptionRenderer descriptionRenderer) {
      this.inter = inter;
      this.descriptionRenderer = descriptionRenderer;
   }

   public List<FixedComponent> getComponents() {
      return this.permanentComponents;
   }

   public void addComponent(FixedComponent component) {
      this.components.add(component);
      this.permanentComponents.add(component);
   }

   public void showComponent(FixedComponent component) {
      if (!this.components.contains(component)) {
         this.components.add(component);
         component.enter(this.getContext(component, false));
      }

   }

   public void hideComponent(FixedComponent component) {
      if (!this.permanentComponents.contains(component) && this.components.remove(component)) {
         component.exit(this.getContext(component, false));
      }

   }

   public void render() {
      List<FixedComponent> components = new ArrayList();
      Iterator var2 = this.components.iterator();

      while(var2.hasNext()) {
         FixedComponent component = (FixedComponent)var2.next();
         components.add(component);
      }

      Context descriptionContext = null;
      int highest = 0;
      FixedComponent focusComponent = null;

      int i;
      FixedComponent component;
      Context context;
      for(i = components.size() - 1; i >= 0; --i) {
         component = (FixedComponent)components.get(i);
         context = this.getContext(component, true);
         component.getHeight(context);
         if (context.isHovered()) {
            highest = i;
            break;
         }
      }

      for(i = 0; i < components.size(); ++i) {
         component = (FixedComponent)components.get(i);
         context = this.getContext(component, i >= highest);
         component.render(context);
         if (context.foucsRequested()) {
            focusComponent = component;
         }

         if (context.isHovered() && context.getDescription() != null) {
            descriptionContext = context;
         }
      }

      if (focusComponent != null && this.components.remove(focusComponent)) {
         this.components.add(focusComponent);
      }

      if (descriptionContext != null && this.descriptionRenderer != null) {
         this.descriptionRenderer.renderDescription(descriptionContext);
      }

   }

   public void handleButton(int button) {
      this.doComponentLoop((context, component) -> {
         component.handleButton(context, button);
      });
   }

   public void handleKey(int scancode) {
      this.doComponentLoop((context, component) -> {
         component.handleKey(context, scancode);
      });
   }

   public void handleScroll(int diff) {
      this.doComponentLoop((context, component) -> {
         component.handleScroll(context, diff);
      });
   }

   public void enter() {
      this.doComponentLoop((context, component) -> {
         component.enter(context);
      });
   }

   public void exit() {
      this.doComponentLoop((context, component) -> {
         component.exit(context);
      });
   }

   public void saveConfig(ConfigList config) {
      config.begin(false);
      Iterator var2 = this.getComponents().iterator();

      while(var2.hasNext()) {
         FixedComponent component = (FixedComponent)var2.next();
         PanelConfig cf = config.addPanel(component.getTitle());
         if (cf != null) {
            component.saveConfig(this.inter, cf);
         }
      }

      config.end(false);
   }

   public void loadConfig(ConfigList config) {
      config.begin(true);
      Iterator var2 = this.getComponents().iterator();

      while(var2.hasNext()) {
         FixedComponent component = (FixedComponent)var2.next();
         PanelConfig cf = config.getPanel(component.getTitle());
         if (cf != null) {
            component.loadConfig(this.inter, cf);
         }
      }

      config.end(true);
   }

   protected Context getContext(FixedComponent component, boolean highest) {
      return new Context(this.inter, component.getWidth(this.inter), component.getPosition(this.inter), true, highest);
   }

   public Toggleable getComponentToggleable(final FixedComponent component) {
      return new Toggleable() {
         public void toggle() {
            if (this.isOn()) {
               ClickGUI.this.hideComponent(component);
            } else {
               ClickGUI.this.showComponent(component);
            }

         }

         public boolean isOn() {
            return ClickGUI.this.components.contains(component);
         }
      };
   }

   protected void doComponentLoop(ClickGUI.LoopFunction function) {
      List<FixedComponent> components = new ArrayList();
      Iterator var3 = this.components.iterator();

      FixedComponent focusComponent;
      while(var3.hasNext()) {
         focusComponent = (FixedComponent)var3.next();
         components.add(focusComponent);
      }

      boolean highest = true;
      focusComponent = null;

      for(int i = components.size() - 1; i >= 0; --i) {
         FixedComponent component = (FixedComponent)components.get(i);
         Context context = this.getContext(component, highest);
         function.loop(context, component);
         if (context.isHovered()) {
            highest = false;
         }

         if (context.foucsRequested()) {
            focusComponent = component;
         }
      }

      if (focusComponent != null && this.components.remove(focusComponent)) {
         this.components.add(focusComponent);
      }

   }

   protected interface LoopFunction {
      void loop(Context var1, FixedComponent var2);
   }
}
