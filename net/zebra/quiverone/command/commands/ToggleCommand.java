package net.zebra.quiverone.command.commands;

import net.zebra.quiverone.command.Command;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.ModuleManager;

public class ToggleCommand extends Command {
   public ToggleCommand() {
      super(new String[]{"toggle", "t"});
   }

   public void onCommand(String[] args) {
      Module module = ModuleManager.getModuleByName(args[1]);
      if (module != null) {
         module.toggle();
         sendMessage("Toggled " + module);
      } else {
         sendMessage("ERROR: Module not found | Usage " + Command.prefix + "toggle <Module>");
      }

   }
}
