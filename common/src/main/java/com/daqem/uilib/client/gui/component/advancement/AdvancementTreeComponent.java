package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.api.client.gui.component.advancement.IAdvancementTree;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;

import java.util.List;

public class AdvancementTreeComponent extends AbstractComponent<AdvancementTreeComponent> {

    public static final int PADDING = 30;

    private final IAdvancementTree advancementTree;
    private AdvancementComponent rootAdvancementComponent;

    public AdvancementTreeComponent(int x, int y, int width, int height, IAdvancementTree advancementTree) {
        super(null, x, y, width, height);
        this.advancementTree = advancementTree;

        run();

        int maxWidth = getAllAdvancementComponents().stream().mapToInt(AdvancementComponent::getTotalX).max().orElse(0) + AdvancementComponent.SIZE;
        int maxHeight = getAllAdvancementComponents().stream().mapToInt(AdvancementComponent::getTotalY).max().orElse(0) + AdvancementComponent.SIZE;

        setWidth(maxWidth);
        setHeight(maxHeight);

        setOnDragEvent((draggedObject, screen, mouseX, mouseY, button, dragX, dragY) -> {
            if (getParent() != null) {
                int parentWidth = getParent().getWidth();
                int parentHeight = getParent().getHeight();

                int newX = getX() + (int) dragX;
                int newY = getY() + (int) dragY;

                if ((newX - PADDING) <= 0 && (newX + getWidth() + PADDING) >= parentWidth) {
                    setX(newX);
                }

                if ((newY - PADDING) <= 0 && (newY + getHeight() + PADDING) >= parentHeight) {
                    setY(newY);
                }
            }
            return false;
        });
    }

    public List<AdvancementComponent> getAllAdvancementComponents() {
        return rootAdvancementComponent.getAllAdvancementChildren();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
    }

    public void run() {
        advancementTree.getRoot().ifPresent(advancement -> {
            rootAdvancementComponent = new AdvancementComponent(advancement, null, null, 0, this.getX());
            this.addChild(rootAdvancementComponent);
            rootAdvancementComponent.firstWalk();
            float f = rootAdvancementComponent.secondWalk(0.0F, 0, rootAdvancementComponent.getY());
            if (f < 0.0F) {
                rootAdvancementComponent.thirdWalk(-f);
            }

            rootAdvancementComponent.finalizePosition();
        });
    }
}
