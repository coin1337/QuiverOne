package me.zero.alpine.bus.type;

import me.zero.alpine.bus.EventBus;

public interface AttachableEventBus extends EventBus {
   void attach(EventBus var1);

   void detach(EventBus var1);
}
