package com.daqem.uilib.api.client.gui.component.event;

import com.daqem.uilib.api.client.gui.event.IMouseReleasable;
import net.minecraft.client.gui.screens.Screen;

public interface OnMouseReleaseEvent<T extends IMouseReleasable<T>> {

    boolean onMouseRelease(T mouseObject, Screen screen, double mouseX, double mouseY, int button);
}
