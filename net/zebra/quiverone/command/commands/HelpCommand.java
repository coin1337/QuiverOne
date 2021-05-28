package net.zebra.quiverone.command.commands;

import java.util.Iterator;
import net.minecraft.class_124;
import net.zebra.quiverone.Main;
import net.zebra.quiverone.command.Command;
import net.zebra.quiverone.command.CommandManager;
import org.apache.commons.lang3.StringUtils;

public class HelpCommand extends Command {
   public HelpCommand() {
      super(new String[]{"help", "h"});
   }

   public void onCommand(String[] args) {
      String tmp = "Commands (" + CommandManager.commands.size() + "): ";
      sendMessage(Main.mod_name + " " + Main.mod_version + " By " + class_124.field_1067 + class_124.field_1073 + class_124.field_1077 + "ZEBRA944");

      Command command;
      for(Iterator var3 = CommandManager.commands.iterator(); var3.hasNext(); tmp = tmp + StringUtils.capitalize(command.name[0]) + ", ") {
         command = (Command)var3.next();
      }

      sendMessage(tmp.substring(0, tmp.length() - 2));
   }
}
