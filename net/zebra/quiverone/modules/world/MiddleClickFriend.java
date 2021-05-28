package net.zebra.quiverone.modules.world;

import java.util.Optional;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_863;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;
import net.zebra.quiverone.modules.setting.settings.NumberSetting;
import net.zebra.quiverone.utils.Friends;
import org.lwjgl.glfw.GLFW;

public class MiddleClickFriend extends Module {
   private NumberSetting range = new NumberSetting("range", this, 60.0D, 1.0D, 100.0D, 1.0D);
   public boolean pressed = false;

   public MiddleClickFriend() {
      super("MiddleClickFriend", "Adds a friend when you middle click them", Category.WORLD, -1, true, true);
   }

   public void onUpdate() {
      if (mc.field_1724 != null) {
         if (GLFW.glfwGetMouseButton(mc.method_22683().method_4490(), 2) == 1 && !this.pressed) {
            this.pressed = true;
            Optional<class_1297> lookingAt = class_863.method_23101(mc.field_1724, 6);
            if (lookingAt.isPresent()) {
               if (Friends.getInstance().isFriend(((class_1297)lookingAt.get()).method_5477().method_10851())) {
                  Friends.getInstance().removeFriend(((class_1297)lookingAt.get()).method_5477().method_10851());
                  sendMessage("Removed " + class_124.field_1077 + ((class_1297)lookingAt.get()).method_5477().method_10851() + class_124.field_1061 + " from your friends list");
               } else if (!Friends.getInstance().isFriend(((class_1297)lookingAt.get()).method_5477().method_10851())) {
                  Friends.getInstance().addFriend(((class_1297)lookingAt.get()).method_5477().method_10851());
                  sendMessage("Added " + class_124.field_1077 + ((class_1297)lookingAt.get()).method_5477().method_10851() + class_124.field_1061 + " to your friends list");
               }
            }
         } else if (GLFW.glfwGetMouseButton(mc.method_22683().method_4490(), 2) == 0) {
            this.pressed = false;
         }

      }
   }
}
