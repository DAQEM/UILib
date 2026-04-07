package com.daqem.uilib.api.widget.skilltree;

import com.daqem.uilib.api.skilltree.ISkillTreeItem;
import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface ISkillTreeItemWidget extends IWidget {

    void extractTooltips(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY);

    ISkillTreeItem getSkillTreeItem();
}
