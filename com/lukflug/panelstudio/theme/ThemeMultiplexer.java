package com.lukflug.panelstudio.theme;

public abstract class ThemeMultiplexer implements Theme {
   protected final Renderer panelRenderer = new ThemeMultiplexer.PanelRenderer();
   protected final Renderer containerRenderer = new ThemeMultiplexer.ContainerRenderer();
   protected final Renderer componentRenderer = new ThemeMultiplexer.ComponentRenderer();

   public Renderer getPanelRenderer() {
      return this.panelRenderer;
   }

   public Renderer getContainerRenderer() {
      return this.containerRenderer;
   }

   public Renderer getComponentRenderer() {
      return this.componentRenderer;
   }

   protected abstract Theme getTheme();

   protected class ComponentRenderer extends RendererProxy {
      protected Renderer getRenderer() {
         return ThemeMultiplexer.this.getTheme().getComponentRenderer();
      }
   }

   protected class ContainerRenderer extends RendererProxy {
      protected Renderer getRenderer() {
         return ThemeMultiplexer.this.getTheme().getContainerRenderer();
      }
   }

   protected class PanelRenderer extends RendererProxy {
      protected Renderer getRenderer() {
         return ThemeMultiplexer.this.getTheme().getPanelRenderer();
      }
   }
}
