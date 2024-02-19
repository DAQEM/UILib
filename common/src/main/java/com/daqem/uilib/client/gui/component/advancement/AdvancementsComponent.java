package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.api.client.gui.component.advancement.IAdvancementTree;
import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import com.daqem.uilib.client.gui.component.TextComponent;
import com.daqem.uilib.client.gui.component.tab.TabComponent;
import com.daqem.uilib.client.gui.component.tab.TabsContainerComponent;
import com.daqem.uilib.client.gui.text.Text;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

import java.util.List;

public class AdvancementsComponent extends AbstractComponent<AdvancementsComponent> {

    private static final int WIDTH = 320;
    private static final int HEIGHT = 180;

    private static final int WINDOW_BORDER_TOP = 18;
    private static final int WINDOW_BORDER_BOTTOM = 9;
    private static final int WINDOW_BORDER_LEFT = 9;
    private static final int WINDOW_BORDER_RIGHT = 9;

    private IAdvancementTree selectedTree;
    private final AdvancementWindowComponent windowComponent;
    private final TabsContainerComponent tabsContainerComponent;
    private AdvancementBackgroundComponent backgroundComponent;
    private AdvancementTreeContainerComponent treeContainerComponent;
    private TextComponent titleComponent;

    private float backgroundFade = 0.0F;

    public AdvancementsComponent(List<IAdvancementTree> advancementTrees) {
        super(null, 0, 0, WIDTH, HEIGHT);

        this.selectedTree = advancementTrees.get(0);
        this.refreshAdvancementTree();

        this.windowComponent = new AdvancementWindowComponent(0, 0, getWidth(), getHeight());
        this.tabsContainerComponent = new TabsContainerComponent(2, -TabComponent.TAB_HEIGHT + 4, advancementTrees, 10, changedObject -> {

            this.removeChild(this.backgroundComponent);
            this.removeChild(this.treeContainerComponent);

            this.selectedTree = (IAdvancementTree) changedObject.getTabInformation();
            this.refreshAdvancementTree();
        });

        this.addChildren(windowComponent, tabsContainerComponent);
    }

    public void refreshAdvancementTree() {
        this.backgroundComponent = new AdvancementBackgroundComponent(WINDOW_BORDER_LEFT, WINDOW_BORDER_TOP, getWidth() - WINDOW_BORDER_LEFT - WINDOW_BORDER_RIGHT, getHeight()- WINDOW_BORDER_TOP - WINDOW_BORDER_BOTTOM, this.selectedTree);
        this.treeContainerComponent = new AdvancementTreeContainerComponent(WINDOW_BORDER_LEFT, WINDOW_BORDER_TOP, getWidth() - WINDOW_BORDER_LEFT - WINDOW_BORDER_RIGHT, getHeight()- WINDOW_BORDER_TOP - WINDOW_BORDER_BOTTOM, this.selectedTree);
        Text title = new Text(Minecraft.getInstance().font, this.selectedTree.getName());
        title.setTextColor(0x404040);
        this.titleComponent = new TextComponent(8, 6, title);

        this.backgroundComponent.setRenderBeforeParent(true);
        this.treeContainerComponent.setRenderBeforeParent(true);

        this.addChildren(backgroundComponent, treeContainerComponent, titleComponent);

        this.titleComponent.setZ(2);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {

    }

    @Override
    public void renderTooltipsBase(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        // Handle the background fade when an advancement is hovered
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(getX(), getY(), 200);
        if (treeContainerComponent.getTreeComponent().getAllAdvancementComponents().stream()
                .anyMatch(advancementComponent -> advancementComponent.isTotalHovered(mouseX, mouseY))) {
            this.backgroundFade = Mth.clamp(this.backgroundFade + 0.04F, 0.0F, 0.25F);
        } else {
            this.backgroundFade = Mth.clamp(this.backgroundFade - 0.06F, 0.0F, 0.25F);
        }
        guiGraphics.fill(backgroundComponent.getX(),
                backgroundComponent.getY(),
                backgroundComponent.getX() + backgroundComponent.getWidth(),
                backgroundComponent.getY() + backgroundComponent.getHeight(),
                (int) (this.backgroundFade * 255.0F) << 24);
        guiGraphics.pose().popPose();

        // Only render tooltips if the background is hovered (for the advancement tree)
        if (this.backgroundComponent.isTotalHovered(mouseX, mouseY)) {
            super.renderTooltipsBase(guiGraphics, mouseX, mouseY, delta);
        }
        // Render tooltips for the tabs
        this.tabsContainerComponent.renderTooltipsBase(guiGraphics, mouseX, mouseY, delta);
    }
}
