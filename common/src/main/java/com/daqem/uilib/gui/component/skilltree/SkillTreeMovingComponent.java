package com.daqem.uilib.gui.component.skilltree;

import com.daqem.uilib.api.skilltree.ISkillTree;
import com.daqem.uilib.api.skilltree.ISkillTreeItem;
import com.daqem.uilib.api.widget.skilltree.ISkillTreeItemWidget;
import com.daqem.uilib.gui.component.EmptyComponent;
import net.minecraft.client.gui.GuiGraphics;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SkillTreeMovingComponent extends EmptyComponent {

    private final ISkillTree skillTree;
    private final List<ISkillTreeItemWidget> itemWidgets;
    private final Map<ISkillTreeItem, ISkillTreeItemWidget> itemWidgetsMap;

    public SkillTreeMovingComponent(ISkillTree skillTree) {
        super(0, 0, 0, 0);
        this.skillTree = skillTree;
        this.itemWidgets = skillTree.getItems().stream().map(item -> {
            ISkillTreeItemWidget itemWidget = item.createWidget();
            itemWidget.setX((int) (skillTree.getHorizontalMargin() + item.getX() * (skillTree.getSkillTreeItemWidth() + skillTree.getHorizontalSpacing())));
            itemWidget.setY((int) (skillTree.getVerticalMargin() + item.getY() * (skillTree.getSkillTreeItemHeight() + skillTree.getVerticalSpacing())));
            return itemWidget;
        }).toList();
        this.itemWidgetsMap = this.itemWidgets.stream().collect(Collectors.toMap(ISkillTreeItemWidget::getSkillTreeItem, item -> item));

        this.setWidth(this.itemWidgets.stream().mapToInt(ISkillTreeItemWidget::getX).max().orElse(0) + skillTree.getSkillTreeItemWidth() + skillTree.getHorizontalMargin());
        this.setHeight(this.itemWidgets.stream().mapToInt(ISkillTreeItemWidget::getY).max().orElse(0) + skillTree.getSkillTreeItemHeight() + skillTree.getVerticalMargin());
        this.addWidgets(this.itemWidgets);
    }

    public void setXOffset(int xOffset) {
        // Apply offset to all item widgets
        for (ISkillTreeItemWidget itemWidget : itemWidgets) {
            itemWidget.setX(itemWidget.getX() + xOffset);
        }
    }

    public void setYOffset(int yOffset) {
        // Apply offset to all item widgets
        for (ISkillTreeItemWidget itemWidget : itemWidgets) {
            itemWidget.setY(itemWidget.getY() + yOffset);
        }
    }

    @Override
    public void renderBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        this.renderConnections(guiGraphics);
        super.renderBase(guiGraphics, mouseX, mouseY, partialTick, parentWidth, parentHeight);
    }

    public void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        for (ISkillTreeItemWidget itemWidget : itemWidgets) {
            if (itemWidget.isMouseOver(mouseX, mouseY)) {
                itemWidget.renderTooltips(guiGraphics, mouseX, mouseY);
            }
        }
    }

    private void renderConnections(GuiGraphics guiGraphics) {
        for (ISkillTreeItemWidget itemWidget : itemWidgets) {
            if (itemWidget.getSkillTreeItem() == this.skillTree.getRoot()) continue;
            ISkillTreeItemWidget parent = this.itemWidgetsMap.get(itemWidget.getSkillTreeItem().getParent());
            int parentX = parent.getX() + parent.getWidth() / 2;
            int parentY = parent.getY() + parent.getHeight() / 2;
            int childX = itemWidget.getX() + itemWidget.getWidth() / 2;
            int childY = itemWidget.getY() + itemWidget.getHeight() / 2;
            if (parentY == childY) {
                guiGraphics.hLine(parentX, childX, parentY - 1, 0xFF000000);
                guiGraphics.hLine(parentX, childX, parentY + 1, 0xFF000000);
                guiGraphics.hLine(parentX, childX, parentY, 0xFFFFFFFF);
            } else {
                int midX = (parentX + childX) / 2;
                guiGraphics.fill(parentX, parentY - 1, midX, parentY + 2, 0xFF000000);
                guiGraphics.fill(midX - 1, parentY, midX + 2, childY, 0xFF000000);
                guiGraphics.fill(midX - 1, childY - 1, childX + 1, childY + 2, 0xFF000000);
                guiGraphics.hLine(parentX, midX, parentY, 0xFFFFFFFF);
                guiGraphics.vLine(midX, parentY, childY, 0xFFFFFFFF);
                guiGraphics.hLine(midX, childX, childY, 0xFFFFFFFF);
            }
        }
    }
}
