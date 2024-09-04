package com.daqem.uilib.api.client.gui;

import net.minecraft.client.gui.components.Renderable;

import java.util.List;

public interface IScreenAccessor {
    boolean uilib$isInitialized();
    List<Renderable> uilib$getRenderables();
}
