package com.daqem.uilib.gui.component.skilltree;

import com.daqem.uilib.api.skilltree.ISkillTree;
import com.daqem.uilib.gui.component.EmptyComponent;
import com.daqem.uilib.gui.widget.skilltree.SkillTreeWidget;
import net.minecraft.client.gui.GuiGraphics;

public class SkillTreeComponent extends EmptyComponent {

    private final SkillTreeWidget skillTreeWidget;

    public SkillTreeComponent(int x, int y, int width, int height, ISkillTree skillTree) {
        super(x, y, width, height);

        skillTree.runPositioner();

        this.skillTreeWidget = new SkillTreeWidget(width, height);
        SkillTreeMovingComponent skillTreeMovingComponent = new SkillTreeMovingComponent(skillTree);

        if (skillTreeMovingComponent.getWidth() < width) {
            skillTreeMovingComponent.setXOffset((width - skillTreeMovingComponent.getWidth()) / 2);
            skillTreeMovingComponent.setWidth(width);
        }
        if (skillTreeMovingComponent.getHeight() < height) {
            skillTreeMovingComponent.setYOffset((height - skillTreeMovingComponent.getHeight()) / 2);
            skillTreeMovingComponent.setHeight(height);
        }

        this.skillTreeWidget.addComponent(skillTreeMovingComponent);
        this.addWidget(skillTreeWidget);
    }

    @Override
    public void renderBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        super.renderBase(guiGraphics, mouseX, mouseY, partialTick, parentWidth, parentHeight);
        this.skillTreeWidget.renderTooltips(guiGraphics, mouseX, mouseY);
    }
}
