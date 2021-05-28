package com.lukflug.panelstudio.tabgui;

import com.lukflug.panelstudio.Animation;
import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.PanelConfig;
import java.awt.Point;

public class TabGUI extends TabGUIContainer implements FixedComponent {
   protected Point position;
   protected int width;

   public TabGUI(String title, TabGUIRenderer renderer, Animation animation, Point position, int width) {
      super(title, renderer, animation);
      this.position = position;
      this.width = width;
   }

   public Point getPosition(Interface inter) {
      return new Point(this.position);
   }

   public void setPosition(Interface inter, Point position) {
      this.position = position;
   }

   public int getWidth(Interface inter) {
      return this.width;
   }

   public void saveConfig(Interface inter, PanelConfig config) {
      config.savePositon(this.position);
   }

   public void loadConfig(Interface inter, PanelConfig config) {
      Point pos = config.loadPosition();
      if (pos != null) {
         this.position = pos;
      }

   }
}
