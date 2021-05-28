package net.zebra.quiverone.modules;

public enum Category {
   COMBAT("Combat"),
   RENDERER("Renderer"),
   MOVEMENT("Movement"),
   WORLD("World"),
   CRASH("Crash"),
   HUD("Hud");

   public String name;
   public int moduleIndex;

   private Category(String name) {
      this.name = name;
   }
}
