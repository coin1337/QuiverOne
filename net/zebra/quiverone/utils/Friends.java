package net.zebra.quiverone.utils;

import java.util.ArrayList;

public class Friends {
   private static Friends instance = null;
   private final ArrayList<String> friends = new ArrayList();

   private Friends() {
   }

   public static Friends getInstance() {
      if (instance == null) {
         instance = new Friends();
      }

      return instance;
   }

   public void addFriend(String name) {
      this.friends.add(name);
   }

   public void removeFriend(String name) {
      this.friends.remove(name);
   }

   public boolean isFriend(String name) {
      return this.friends.contains(name);
   }
}
