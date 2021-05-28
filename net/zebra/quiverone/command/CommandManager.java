package net.zebra.quiverone.command;

import java.util.ArrayList;
import java.util.Comparator;
import net.zebra.quiverone.command.commands.HelpCommand;
import net.zebra.quiverone.command.commands.ToggleCommand;

public class CommandManager {
   public static ArrayList<Command> commands;

   public static void loadCommands() {
      commands = new ArrayList();
      commands.add(new HelpCommand());
      commands.add(new ToggleCommand());
      commands.sort(Comparator.comparing((object) -> {
         return object.name[0];
      }));
   }
}
