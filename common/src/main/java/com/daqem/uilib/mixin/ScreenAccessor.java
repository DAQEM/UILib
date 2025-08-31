package com.daqem.uilib.mixin;

import com.daqem.uilib.api.screen.IScreenAccessor;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenAccessor implements IScreenAccessor {

    @Shadow
    @Final
    private List<Renderable> renderables;

    @Shadow
    @Final
    private List<GuiEventListener> children;

    @Shadow
    @Final
    private List<NarratableEntry> narratables;

    @Shadow
    protected abstract void removeWidget(GuiEventListener listener);

    @Shadow
    protected abstract void renderBlurredBackground(GuiGraphics guiGraphics);

    @Shadow
    protected abstract void renderPanorama(GuiGraphics guiGraphics, float partialTick);

    @Override
    public List<Renderable> uilib$getRenderables() {
        return this.renderables;
    }

    @Override
    public List<GuiEventListener> uilib$getChildren() {
        return this.children;
    }

    @Override
    public List<NarratableEntry> uilib$getNarratables() {
        return this.narratables;
    }

    @Override
    public void uilib$removeWidget(GuiEventListener widget) {
        this.removeWidget(widget);
    }

    @Override
    public void uilib$renderBlurredBackground(GuiGraphics guiGraphics) {
        this.renderBlurredBackground(guiGraphics);
    }

    @Override
    public void uilib$renderPanoramaBackground(GuiGraphics guiGraphics, float partialTicks) {
        this.renderPanorama(guiGraphics, partialTicks);
    }
}
