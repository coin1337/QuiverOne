package com.lukflug.panelstudio;

import com.lukflug.panelstudio.settings.NumberSetting;

public class SettingsAnimation extends Animation {
   protected final NumberSetting speed;

   public SettingsAnimation(NumberSetting speed) {
      this.speed = speed;
   }

   protected int getSpeed() {
      return (int)this.speed.getNumber();
   }
}
