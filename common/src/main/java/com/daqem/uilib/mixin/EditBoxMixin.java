package com.daqem.uilib.mixin;

import com.daqem.uilib.UILib;
import com.daqem.uilib.api.widget.IEditBoxWidget;
import com.daqem.uilib.gui.widget.EditBoxWidget;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EditBox.class)
public abstract class EditBoxMixin extends AbstractWidget implements IEditBoxWidget {

    @Shadow protected abstract void updateTextPosition();

    public EditBoxMixin(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
    }

    @Override
    public void uilib$updateTextPosition() {
        updateTextPosition();
    }

    @Redirect(
            method = "renderWidget(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIII)V"
            )
    )
    private void suppressBlitSprite(GuiGraphics instance, RenderPipeline pipeline, ResourceLocation sprite, int x, int y, int width, int height) {
        if (((EditBox) (Object) this) instanceof EditBoxWidget editBoxWidget) {
            if (editBoxWidget.hasInputValidationErrors()) {
                instance.blitSprite(pipeline, UILib.getId("widget/text_field_error"), x, y, width, height);
                return;
            }
        }
        instance.blitSprite(pipeline, sprite, x, y, width, height);
    }
}
