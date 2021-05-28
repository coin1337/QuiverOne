package com.lukflug.panelstudio;

import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.Renderer;
import java.awt.Point;

public class DraggableContainer extends CollapsibleContainer implements FixedComponent {
   protected boolean dragging = false;
   protected Point attachPoint;
   protected Point position;
   protected int width;
   protected boolean bodyDrag = false;

   public DraggableContainer(String title, String description, Renderer renderer, Toggleable open, Animation animation, Toggleable toggle, Point position, int width) {
      super(title, description, renderer, open, animation, toggle);
      this.position = position;
      this.width = width;
   }

   public void handleButton(Context context, int button) {
      if (this.bodyDrag) {
         super.handleButton(context, button);
      } else {
         context.setHeight(this.renderer.getHeight(this.open.getValue() != 0.0D));
      }

      if (context.isClicked() && button == 0) {
         this.dragging = true;
         this.attachPoint = context.getInterface().getMouse();
      } else if (!context.getInterface().getButton(0) && this.dragging) {
         Point mouse = context.getInterface().getMouse();
         this.dragging = false;
         Point p = this.getPosition(context.getInterface());
         p.translate(mouse.x - this.attachPoint.x, mouse.y - this.attachPoint.y);
         this.setPosition(context.getInterface(), p);
      }

      if (!this.bodyDrag) {
         super.handleButton(context, button);
      }

   }

   public Point getPosition(Interface inter) {
      if (this.dragging) {
         Point point = new Point(this.position);
         point.translate(inter.getMouse().x - this.attachPoint.x, inter.getMouse().y - this.attachPoint.y);
         return point;
      } else {
         return this.position;
      }
   }

   public void setPosition(Interface inter, Point position) {
      this.position = new Point(position);
   }

   public int getWidth(Interface inter) {
      return this.width;
   }

   protected void handleFocus(Context context, boolean focus) {
      if (focus) {
         context.requestFocus();
      }

   }

   public void saveConfig(Interface inter, PanelConfig config) {
      config.savePositon(this.position);
      config.saveState(this.open.isOn());
   }

   public void loadConfig(Interface inter, PanelConfig config) {
      Point pos = config.loadPosition();
      if (pos != null) {
         this.position = pos;
      }

      if (this.open.isOn() != config.loadState()) {
         this.open.toggle();
      }

   }
}
