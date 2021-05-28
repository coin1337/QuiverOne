package com.lukflug.panelstudio.hud;

import java.awt.Color;

public interface HUDList {
   int getSize();

   String getItem(int var1);

   Color getItemColor(int var1);

   boolean sortUp();

   boolean sortRight();
}
