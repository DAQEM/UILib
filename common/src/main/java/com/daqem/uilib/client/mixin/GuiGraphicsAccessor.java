package com.daqem.uilib.client.mixin;

import com.daqem.uilib.api.client.gui.IGuiGraphics;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsAccessor implements IGuiGraphics {

    @Shadow
    @Final
    private MultiBufferSource.BufferSource bufferSource;

    @Override
    public MultiBufferSource.BufferSource uilib$getBufferSource() {
        return bufferSource;
    }
}
