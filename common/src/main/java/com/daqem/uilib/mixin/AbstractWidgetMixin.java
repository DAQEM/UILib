package com.daqem.uilib.mixin;

import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.api.component.IComponentsParent;
import com.daqem.uilib.api.screen.IScreen;
import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.narration.NarratableEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractWidget.class)
public abstract class AbstractWidgetMixin implements Renderable, GuiEventListener, LayoutElement, NarratableEntry, IWidget {

    @Shadow
    private int x;

    @Shadow
    private int y;

    @Unique
    private int uilib$parentX;
    @Unique
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

        if (this instanceof IComponentsParent componentsParent) {
            for (IComponent component : componentsParent.getComponents()) {
                component.updateParentPosition(getX(), getY(), getWidth(), getHeight());
            }
        }
    }

    @Inject(method = "getX()I", at = @At("RETURN"), cancellable = true)
    private void uilib$modifyGetX(CallbackInfoReturnable<Integer> cir) {
        if (Minecraft.getInstance().screen instanceof IScreen) {
            cir.setReturnValue(this.x + this.uilib$parentX);
        }
    }

    @Inject(method = "getY()I", at = @At("RETURN"), cancellable = true)
    private void uilib$modifyGetY(CallbackInfoReturnable<Integer> cir) {
        if (Minecraft.getInstance().screen instanceof IScreen) {
            cir.setReturnValue(this.y + this.uilib$parentY);
        }
    }
}
