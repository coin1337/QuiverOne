package net.zebra.quiverone.utils;

import java.util.ArrayDeque;
import java.util.Queue;

public class Pool<T> {
   private final Queue<T> items = new ArrayDeque();
   private final Producer<T> producer;

   public Pool(Producer<T> producer) {
      this.producer = producer;
   }

   public synchronized T get() {
      return this.items.size() > 0 ? this.items.poll() : this.producer.create();
   }

   public synchronized void free(T obj) {
      this.items.offer(obj);
   }
}
