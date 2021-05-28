package com.lukflug.panelstudio;

import java.awt.Point;

public interface PanelConfig {
   void savePositon(Point var1);

   Point loadPosition();

   void saveState(boolean var1);

   boolean loadState();
}
