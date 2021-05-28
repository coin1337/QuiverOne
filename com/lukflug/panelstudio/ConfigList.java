package com.lukflug.panelstudio;

public interface ConfigList {
   void begin(boolean var1);

   void end(boolean var1);

   PanelConfig addPanel(String var1);

   PanelConfig getPanel(String var1);
}
