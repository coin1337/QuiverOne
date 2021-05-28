package net.zebra.quiverone.modules.setting.settings;

import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;

public class NumberSetting extends Setting implements com.lukflug.panelstudio.settings.NumberSetting {
   public double value;
   public double minimun;
   public double maximum;
   public double increment;

   public NumberSetting(String name, Module parent, double value, double minimun, double maximum, double increment) {
      this.name = name;
      this.parent = parent;
      this.value = value;
      this.minimun = minimun;
      this.maximum = maximum;
      this.increment = increment;
   }

   public double getValue() {
      return this.value;
   }

   public void setValue(double value) {
      double precision = 1.0D / this.increment;
      this.value = (double)Math.round(Math.max(this.minimun, Math.min(this.maximum, value)) * precision) / precision;
   }

   public void increment(boolean positive) {
      this.setValue(this.getValue() + (double)(positive ? 1 : -1) * this.increment);
   }

   public double getMinimun() {
      return this.minimun;
   }

   public void setMinimun(double minimun) {
      this.minimun = minimun;
   }

   public double getMaximum() {
      return this.maximum;
   }

   public void setMaximum(double maximum) {
      this.maximum = maximum;
   }

   public double getIncrement() {
      return this.increment;
   }

   public void setIncrement(double increment) {
      this.increment = increment;
   }

   public double getMaximumValue() {
      return this.maximum;
   }

   public double getMinimumValue() {
      return this.minimun;
   }

   public double getNumber() {
      return this.value;
   }

   public int getPrecision() {
      return 1;
   }

   public void setNumber(double value) {
      double precision = 1.0D / this.increment;
      this.value = (double)Math.round(Math.max(this.minimun, Math.min(this.maximum, value)) * precision) / precision;
   }
}
