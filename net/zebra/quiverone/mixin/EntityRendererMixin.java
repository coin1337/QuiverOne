package net.zebra.quiverone.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import net.zebra.quiverone.Main;
import net.zebra.quiverone.events.event.EntityRenderEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_897.class})
public abstract class EntityRendererMixin<T extends class_1297> {
   @Inject(
      method = {"renderLabelIfPresent"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void renderLabelIfPresent(T entity, class_2561 text, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo info) {
      EntityRenderEvent.Single.Label event = new EntityRenderEvent.Single.Label(entity, matrices, vertexConsumers);
      Main.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         info.cancel();
      }

   }
}
