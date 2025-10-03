package com.daqem.uilib.mixin;

import net.minecraft.client.gui.components.AbstractScrollArea;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractScrollArea.class)
public interface AbstractScrollAreaAccessor {

    @Accessor("scrolling")
    boolean uilib$isScrolling();
}
