package com.daqem.uilib.mixin;

import com.daqem.uilib.UILib;
import com.daqem.uilib.api.widget.IEditBoxWidget;
import com.daqem.uilib.gui.widget.EditBoxWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EditBox.class)
public abstract class EditBoxMixin extends AbstractWidget implements IEditBoxWidget {

    @Shadow protected abstract void updateTextPosition();

    @Unique
    private GuiGraphics uilib$guiGraphics;

    public EditBoxMixin(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
    }

    @Override
    public void uilib$updateTextPosition() {
        updateTextPosition();
    }

    @Inject(method = "renderWidget", at = @At("HEAD"))
    private void uilib$captureGuiGraphics(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        this.uilib$guiGraphics = guiGraphics;
    }

    @Redirect(
            method = "renderWidget(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/EditBox;isBordered()Z"
            )
    )
    private boolean uilib$redirectIsBordered(EditBox instance) {
        if (instance instanceof EditBoxWidget editBoxWidget && editBoxWidget.hasInputValidationErrors()) {
            if (this.uilib$guiGraphics != null) {
                this.uilib$guiGraphics.blitSprite(
                        RenderPipelines.GUI_TEXTURED,
                        UILib.getId("widget/text_field_error"),
                        this.getX(),
                        this.getY(),
                        this.getWidth(),
                        this.getHeight()
                );
            }
            return false;
        }
        return instance.isBordered();
    }
}