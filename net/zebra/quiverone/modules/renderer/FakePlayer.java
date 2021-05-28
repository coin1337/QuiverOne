package net.zebra.quiverone.modules.renderer;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.class_745;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class FakePlayer extends Module {
   public FakePlayer() {
      super("FakePlayer", "Spawns a client side player", Category.RENDERER, -1, false, true);
   }

   public void onEnable() {
      class_745 player = new class_745(mc.field_1687, new GameProfile(UUID.fromString("0f75a81d-70e5-43c5-b892-f33c524284f2"), "ZebraHax"));
      player.method_5719(mc.field_1724);
      player.method_5847(mc.field_1724.field_6241);
      mc.field_1687.method_2942(-100, player);
   }

   public void onDisable() {
      mc.field_1687.method_2945(-100);
   }
}
