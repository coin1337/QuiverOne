package net.zebra.quiverone.command;

import net.zebra.quiverone.modules.Module;

public class Command {
   public static String prefix = ",";
   public String[] name;

   public Command(String[] name) {
      this.name = name;
   }

   public void onCommand(String[] args) {
   }

   public static void sendMessage(String message) {
      try {
         Module.sendMessage(message);
      } catch (Exception var2) {
      }

   }
}
