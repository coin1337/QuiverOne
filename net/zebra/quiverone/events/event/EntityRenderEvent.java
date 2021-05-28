package net.zebra.quiverone.events.event;

import net.minecraft.class_1297;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.zebra.quiverone.events.QuiverEvent;

public class EntityRenderEvent extends QuiverEvent {
   public static class PostAll extends EntityRenderEvent {
   }

   public static class PreAll extends EntityRenderEvent {
   }

   public static class Single extends EntityRenderEvent {
      protected class_1297 entity;
      protected class_4587 matrix;
      protected class_4597 vertex;

      public class_1297 getEntity() {
         return this.entity;
      }

      public class_4587 getMatrix() {
         return this.matrix;
      }

      public class_4597 getVertex() {
         return this.vertex;
      }

      public static class Label extends EntityRenderEvent.Single {
         public Label(class_1297 entity, class_4587 matrix, class_4597 vertex) {
            this.entity = entity;
            this.matrix = matrix;
            this.vertex = vertex;
         }

         public void setMatrix(class_4587 matrix) {
            this.matrix = matrix;
         }

         public void setVertex(class_4597 vertex) {
            this.vertex = vertex;
         }
      }

      public static class Post extends EntityRenderEvent.Single {
         public Post(class_1297 entity, class_4587 matrix, class_4597 vertex) {
            this.entity = entity;
            this.matrix = matrix;
            this.vertex = vertex;
         }
      }

      public static class Pre extends EntityRenderEvent.Single {
         public Pre(class_1297 entity, class_4587 matrix, class_4597 vertex) {
            this.entity = entity;
            this.matrix = matrix;
            this.vertex = vertex;
         }

         public void setMatrix(class_4587 matrix) {
            this.matrix = matrix;
         }

         public void setVertex(class_4597 vertex) {
            this.vertex = vertex;
         }

         public void setEntity(class_1297 entity) {
            this.entity = entity;
         }
      }
   }
}
