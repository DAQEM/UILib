package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.api.client.gui.component.advancement.IAdvancementTree;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;

public class AdvancementTreeContainerComponent extends AbstractComponent<AdvancementTreeContainerComponent> {

    private final AdvancementTreeComponent treeComponent;

    public AdvancementTreeContainerComponent(int x, int y, int width, int height, IAdvancementTree advancementTree) {
        super(null, x, y, width, height);

         this.treeComponent = new AdvancementTreeComponent(0, 0, width, height, advancementTree);

        this.addChild(treeComponent);

        treeComponent.centerVertically();
        treeComponent.setY(treeComponent.getY() - (AdvancementComponent.SIZE / 2));
        treeComponent.setX(treeComponent.getX() + AdvancementTreeComponent.PADDING);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
    }

    @Override
    public void renderBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        guiGraphics.pose().pushPose();
        guiGraphics.enableScissor(getTotalX(), getTotalY(), getTotalX() + getWidth(), getTotalY() + getHeight());
        super.renderBase(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.disableScissor();
        guiGraphics.pose().popPose();
    }

    @Override
    public void renderTooltipsBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        guiGraphics.pose().pushPose();
        super.renderTooltipsBase(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.pose().popPose();
    }

    public AdvancementTreeComponent getTreeComponent() {
        return treeComponent;
    }
}
