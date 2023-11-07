package com.daqem.uilib.api.client.gui.background;

import com.daqem.uilib.api.client.gui.IRenderable;
import com.daqem.uilib.api.client.gui.color.IColorManipulatable;
import com.daqem.uilib.api.client.gui.color.IColorManipulator;
import net.minecraft.client.gui.GuiGraphics;

public interface IBackground<T extends IBackground<T>> extends IRenderable<T>, IColorManipulatable {
}
