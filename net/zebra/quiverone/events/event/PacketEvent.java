package net.zebra.quiverone.events.event;

import net.minecraft.class_2596;
import net.zebra.quiverone.events.QuiverEvent;

public class PacketEvent extends QuiverEvent {
   private final class_2596 packet;

   public PacketEvent(class_2596 packet) {
      this.packet = packet;
   }

   public class_2596 getPacket() {
      return this.packet;
   }

   public static class Send extends PacketEvent {
      public Send(class_2596 packet) {
         super(packet);
      }
   }

   public static class Receive extends PacketEvent {
      public Receive(class_2596 packet) {
         super(packet);
      }
   }
}
