package net.zebra.quiverone;

import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import net.zebra.quiverone.command.CommandManager;
import net.zebra.quiverone.gui.ClickGUI;
import net.zebra.quiverone.modules.ModuleManager;
import net.zebra.quiverone.modules.cape.CapeManager;
import net.zebra.quiverone.whitelist.Whitelist;

public enum Main {
   instance;

   public static String mod_name = "ZebraHack";
   public static String mod_version = "1.0.0 ALPHA";
   public static String splash_text = "Pwning Villagers654";
   public static EventBus EVENT_BUS;
   public ModuleManager moduleManager;
   public CapeManager capeManager;
   public ClickGUI clickGUI;

   public void run_client() {
      this.capeManager = new CapeManager();
      Whitelist.verify();
      EVENT_BUS = new EventManager();
      this.moduleManager = new ModuleManager();
      this.moduleManager.loadModules();
      CommandManager.loadCommands();
      this.clickGUI = new ClickGUI();
   }

   public void stop_client() {
   }
}
