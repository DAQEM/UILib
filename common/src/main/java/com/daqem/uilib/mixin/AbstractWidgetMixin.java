package com.daqem.uilib.mixin;

import com.daqem.uilib.api.widget.IWidget;
import com.daqem.uilib.gui.AbstractScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractWidget.class)
public abstract class AbstractWidgetMixin implements Renderable, GuiEventListener, LayoutElement, NarratableEntry, IWidget {

    @Shadow
    private int x;

    @Shadow
    private int y;

    private int uilib$parentX;
    private int uilib$parentY;

    @Override
    public int uilib$getParentX() {
        return this.uilib$parentX;
    }

    @Override
    public int uilib$getParentY() {
        return this.uilib$parentY;
    }

    @Override
    public void uilib$updateParentPosition(int parentX, int parentY) {
        this.uilib$parentX = parentX;
        this.uilib$parentY = parentY;
    }

    @Inject(method = "getX()I", at = @At("RETURN"), cancellable = true)
    private void uilib$modifyGetX(CallbackInfoReturnable<Integer> cir) {
        if (Minecraft.getInstance().screen instanceof AbstractScreen) {
            cir.setReturnValue(this.x + this.uilib$parentX);
        }
    }

    @Inject(method = "getY()I", at = @At("RETURN"), cancellable = true)
    private void uilib$modifyGetY(CallbackInfoReturnable<Integer> cir) {
        if (Minecraft.getInstance().screen instanceof AbstractScreen) {
            cir.setReturnValue(this.y + this.uilib$parentY);
        }
    }
}
