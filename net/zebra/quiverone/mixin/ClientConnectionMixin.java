package net.zebra.quiverone.mixin;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.concurrent.Future;
import net.minecraft.class_2535;
import net.minecraft.class_2596;
import net.minecraft.class_2797;
import net.zebra.quiverone.Main;
import net.zebra.quiverone.command.Command;
import net.zebra.quiverone.command.CommandManager;
import net.zebra.quiverone.events.event.PacketEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_2535.class})
public class ClientConnectionMixin {
   boolean found = false;

   @Inject(
      method = {"channelRead0"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void IchannelRead0(ChannelHandlerContext context, class_2596<?> packet, CallbackInfo callback) {
      PacketEvent.Receive event = new PacketEvent.Receive(packet);
      Main.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         callback.cancel();
      }

   }

   @Inject(
      method = {"send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void send(class_2596<?> packet, GenericFutureListener<? extends Future<? super Void>> genericFutureListener_1, CallbackInfo callback) {
      if (packet instanceof class_2797 && ((class_2797)packet).method_12114().startsWith(Command.prefix)) {
         String[] args = ((class_2797)packet).method_12114().substring(1).split(" ");
         CommandManager.commands.forEach((command) -> {
            String[] var3 = command.name;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               String name = var3[var5];
               if (args[0].equalsIgnoreCase(name)) {
                  command.onCommand(args);
               }

               this.found = true;
            }

         });
         if (!this.found) {
            Command.sendMessage("Command not found! Do " + Command.prefix + "help for a list of commands.");
         }

         callback.cancel();
      }

      PacketEvent.Send event = new PacketEvent.Send(packet);
      Main.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         callback.cancel();
      }

   }
}
