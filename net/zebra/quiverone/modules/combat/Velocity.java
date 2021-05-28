package net.zebra.quiverone.modules.combat;

import java.util.function.Predicate;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.class_2664;
import net.minecraft.class_2743;
import net.zebra.quiverone.events.event.PacketEvent;
import net.zebra.quiverone.modules.Category;
import net.zebra.quiverone.modules.Module;

public class Velocity extends Module {
   @EventHandler
   private final Listener<PacketEvent.Receive> packetListener = new Listener((event) -> {
      if (event.getPacket() instanceof class_2743 && ((class_2743)event.getPacket()).method_11818() == mc.field_1724.method_5628()) {
         event.cancel();
      } else if (event.getPacket() instanceof class_2664) {
         event.cancel();
      }

   }, new Predicate[0]);

   public Velocity() {
      super("Velocity", "Cancels knockback packets", Category.COMBAT, -1, false, true);
   }
}
