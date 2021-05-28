package net.zebra.quiverone.modules;

import java.util.ArrayList;
import java.util.Iterator;
import net.zebra.quiverone.modules.combat.AnchorAura;
import net.zebra.quiverone.modules.combat.AutoDisconnect;
import net.zebra.quiverone.modules.combat.AutoTotem;
import net.zebra.quiverone.modules.combat.BedAura;
import net.zebra.quiverone.modules.combat.Burrow;
import net.zebra.quiverone.modules.combat.CrystalAura;
import net.zebra.quiverone.modules.combat.KillAura;
import net.zebra.quiverone.modules.combat.Surround;
import net.zebra.quiverone.modules.combat.TriggerBot;
import net.zebra.quiverone.modules.combat.Velocity;
import net.zebra.quiverone.modules.crash.BookCrash;
import net.zebra.quiverone.modules.crash.IllegalPosition;
import net.zebra.quiverone.modules.crash.IllegalsGenerator;
import net.zebra.quiverone.modules.crash.ItemGenerator;
import net.zebra.quiverone.modules.crash.PlayerCrash;
import net.zebra.quiverone.modules.crash.Position;
import net.zebra.quiverone.modules.crash.SpawnEgg;
import net.zebra.quiverone.modules.crash.TestModule;
import net.zebra.quiverone.modules.crash.Vertical;
import net.zebra.quiverone.modules.crash.VerticalTP;
import net.zebra.quiverone.modules.hud.ActiveModules;
import net.zebra.quiverone.modules.hud.ArmorHud;
import net.zebra.quiverone.modules.hud.ArrayListHud;
import net.zebra.quiverone.modules.hud.Coords;
import net.zebra.quiverone.modules.hud.CrystalCounter;
import net.zebra.quiverone.modules.hud.EnemyModel;
import net.zebra.quiverone.modules.hud.HUDEditor;
import net.zebra.quiverone.modules.hud.InventoryViewer;
import net.zebra.quiverone.modules.hud.KillAuraInfo;
import net.zebra.quiverone.modules.hud.Notifications;
import net.zebra.quiverone.modules.hud.ObsidianCounter;
import net.zebra.quiverone.modules.hud.PlayerModel;
import net.zebra.quiverone.modules.hud.ServerIP;
import net.zebra.quiverone.modules.hud.TotemCounter;
import net.zebra.quiverone.modules.hud.Watermark;
import net.zebra.quiverone.modules.hud.Welcomer;
import net.zebra.quiverone.modules.movement.AutoSneak;
import net.zebra.quiverone.modules.movement.AutoSprint;
import net.zebra.quiverone.modules.movement.AutoWalk;
import net.zebra.quiverone.modules.movement.Flight;
import net.zebra.quiverone.modules.movement.JetPack;
import net.zebra.quiverone.modules.movement.NoSlow;
import net.zebra.quiverone.modules.movement.Scaffold;
import net.zebra.quiverone.modules.movement.Speed;
import net.zebra.quiverone.modules.movement.Step;
import net.zebra.quiverone.modules.renderer.ClickGuiModule;
import net.zebra.quiverone.modules.renderer.Esp;
import net.zebra.quiverone.modules.renderer.FakePlayer;
import net.zebra.quiverone.modules.renderer.FullBright;
import net.zebra.quiverone.modules.renderer.NoRender;
import net.zebra.quiverone.modules.renderer.Tracers;
import net.zebra.quiverone.modules.world.AutoRespawn;
import net.zebra.quiverone.modules.world.CustomFov;
import net.zebra.quiverone.modules.world.Gamemode;
import net.zebra.quiverone.modules.world.MiddleClickFriend;
import net.zebra.quiverone.modules.world.NoFall;
import net.zebra.quiverone.modules.world.NoWeather;
import net.zebra.quiverone.modules.world.Spammer;

public class ModuleManager {
   public static ArrayList<Module> modules;

   public ModuleManager() {
      modules = new ArrayList();
   }

   public void loadModules() {
      this.addModule(new AnchorAura());
      this.addModule(new AutoDisconnect());
      this.addModule(new AutoTotem());
      this.addModule(new BedAura());
      this.addModule(new Burrow());
      this.addModule(new CrystalAura());
      this.addModule(new KillAura());
      this.addModule(new Surround());
      this.addModule(new TriggerBot());
      this.addModule(new Velocity());
      this.addModule(new AutoSprint());
      this.addModule(new AutoWalk());
      this.addModule(new AutoSneak());
      this.addModule(new Flight());
      this.addModule(new JetPack());
      this.addModule(new NoSlow());
      this.addModule(new Speed());
      this.addModule(new Step());
      this.addModule(new Scaffold());
      this.addModule(new ActiveModules());
      this.addModule(new ArmorHud());
      this.addModule(new ArrayListHud());
      this.addModule(new Coords());
      this.addModule(new HUDEditor());
      this.addModule(new InventoryViewer());
      this.addModule(new KillAuraInfo());
      this.addModule(new EnemyModel());
      this.addModule(new Notifications());
      this.addModule(new ServerIP());
      this.addModule(new PlayerModel());
      this.addModule(new CrystalCounter());
      this.addModule(new ObsidianCounter());
      this.addModule(new TotemCounter());
      this.addModule(new Watermark());
      this.addModule(new Welcomer());
      this.addModule(new ClickGuiModule());
      this.addModule(new Esp());
      this.addModule(new FullBright());
      this.addModule(new NoRender());
      this.addModule(new FakePlayer());
      this.addModule(new Tracers());
      this.addModule(new AutoRespawn());
      this.addModule(new Gamemode());
      this.addModule(new CustomFov());
      this.addModule(new MiddleClickFriend());
      this.addModule(new NoFall());
      this.addModule(new NoWeather());
      this.addModule(new Spammer());
      this.addModule(new BookCrash());
      this.addModule(new IllegalPosition());
      this.addModule(new IllegalsGenerator());
      this.addModule(new ItemGenerator());
      this.addModule(new PlayerCrash());
      this.addModule(new Position());
      this.addModule(new SpawnEgg());
      this.addModule(new TestModule());
      this.addModule(new Vertical());
      this.addModule(new VerticalTP());
   }

   private void addModule(Module mod) {
      modules.add(mod);
   }

   public static ArrayList<Module> getModules() {
      return modules;
   }

   public static boolean isModuleEnabled(String name) {
      Iterator var1 = modules.iterator();
      if (var1.hasNext()) {
         Module module = (Module)var1.next();
         return module.getName() == name;
      } else {
         return false;
      }
   }

   public static Module getModuleByName(String name) {
      Iterator var1 = modules.iterator();

      Module mod;
      do {
         if (!var1.hasNext()) {
            return null;
         }

         mod = (Module)var1.next();
      } while(mod.getName() != name);

      return mod;
   }

   public static ArrayList<Module> getModulesByCategory(Category c) {
      ArrayList<Module> mods = new ArrayList();
      Iterator var2 = modules.iterator();

      while(var2.hasNext()) {
         Module mod = (Module)var2.next();
         if (mod.getCategory().equals(c)) {
            mods.add(mod);
         }
      }

      return mods;
   }

   public static ArrayList<String> getModuleNames() {
      ArrayList<String> names = new ArrayList();
      Iterator var1 = modules.iterator();

      while(var1.hasNext()) {
         Module module = (Module)var1.next();
         names.add(module.getName());
      }

      return names;
   }
}
