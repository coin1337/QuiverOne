package me.zero.alpine.event.type;

public interface ICancellable {
   void cancel();

   boolean isCancelled();
}
