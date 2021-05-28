package net.zebra.quiverone.mixin;

import net.minecraft.class_2561;
import net.minecraft.class_2588;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_4587;
import net.minecraft.class_526;
import net.zebra.quiverone.Main;
import net.zebra.quiverone.modules.hud.Coords;
import net.zebra.quiverone.modules.setting.settings.ColorSetting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_442.class})
public abstract class TitleScreenMixin extends class_437 {
   public ColorSetting Rainbow = new ColorSetting("Rainbow", new Coords(), true);
   public class_310 mc = class_310.method_1551();
   @Mutable
   @Shadow
   @Final
   private static class_2960 field_2583;
   private static class_2960 EDITION_TITLE_TEXTURE;
   private String splashText;
   private int copyrightTextWidth;

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   private void render(class_4587 matrices, int mouseX, int mouseY, float delta, CallbackInfo info) {
      field_2583 = new class_2960("quiver", "zebrahacktitle.png");
      this.splashText = Main.splash_text;
      class_310.method_1551().field_1772.method_1720(matrices, Main.mod_name + " " + Main.mod_version + " By ZEBRA944", 2.0F, 2.0F, this.Rainbow.getValue().getRGB());
      class_310.method_1551().field_1772.method_1720(matrices, "Welcome " + this.mc.method_1548().method_1676() + ", Looking good!", 400.0F, 8.0F, this.Rainbow.getValue().getRGB());
   }

   protected TitleScreenMixin(class_2561 title) {
      super(title);
   }

   @Inject(
      at = {@At("RETURN")},
      method = {"initWidgetsNormal"}
   )
   private void quiverButton(int y, int spacingY, CallbackInfo info) {
      this.method_25411(new class_4185(this.field_22789 / 2 - 100 + 205, y, 100, 20, new class_2588("ViaVersion"), (buttonWidget) -> {
         class_310.method_1551().method_1507(new class_526(this));
      }));
      this.method_25411(new class_4185(this.field_22789 / 2 - 100 + 310, y, 100, 20, new class_2588("AltManager"), (buttonWidget) -> {
         class_310.method_1551().method_1507(new class_526(this));
      }));
   }
}
