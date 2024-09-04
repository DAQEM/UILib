package com.daqem.uilib.client.mixin;

import com.daqem.uilib.api.client.gui.IScreenAccessor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenAccessor implements IScreenAccessor {

    @Shadow
    public boolean initialized;

    @Final
    @Shadow
    private List<Renderable> renderables;

    @Unique
    public boolean uilib$isInitialized() {
        return initialized;
    }

    @Unique
    public List<Renderable> uilib$getRenderables() {
        return renderables;
    }
}
