package com.daqem.uilib.api.widget.skilltree;

import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.gui.GuiGraphics;

public interface ISkillTreeWidget extends IWidget {

    void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY);
}
