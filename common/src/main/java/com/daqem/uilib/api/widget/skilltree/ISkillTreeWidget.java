package com.daqem.uilib.api.widget.skilltree;

import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface ISkillTreeWidget extends IWidget {

    void renderTooltips(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY);
}
