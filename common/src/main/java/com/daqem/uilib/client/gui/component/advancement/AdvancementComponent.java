package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.api.client.gui.component.advancement.IAdvancement;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import net.minecraft.advancements.FrameType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class AdvancementComponent extends AbstractComponent<AdvancementComponent> {

    public static final int SIZE = 26;
    public static final int SPACING = 2;

    private final @Nullable AdvancementComponent parent;
    private final AdvancementComponent previousSibling;

    private final int childIndex;
    private AdvancementComponent ancestor;
    private @Nullable AdvancementComponent thread;

    private final AdvancementIconComponent iconComponent;
    private final AdvancementHoverComponent hoverComponent;

    private float mod;
    private float change;
    private float shift;
    
    private float posX;
    private float posY;

    public AdvancementComponent(IAdvancement advancement, @Nullable AdvancementComponent parent, AdvancementComponent previousSibling, int childIndex, int x) {
        super(null, x, -1, SIZE, SIZE);
        this.parent = parent;
        this.addChildren(getAdvancementChildren(advancement));
        this.previousSibling = previousSibling;

        this.childIndex = childIndex;
        this.ancestor = this;

        AdvancementWidgetType widgetType = advancement.isObtained() ? AdvancementWidgetType.OBTAINED : AdvancementWidgetType.UNOBTAINED;

        this.iconComponent = new AdvancementIconComponent(0, 0, advancement.getIcon(), widgetType, advancement.getFrameType());
        this.hoverComponent = new AdvancementHoverComponent(0, 0, Minecraft.getInstance().font,
                advancement.getIcon(),
                advancement.getName(),
                advancement.getDescription(),
                widgetType,
                advancement.getFrameType());

        addChildren(iconComponent);
    }

    @Override
    public void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (this.iconComponent.isHovered(mouseX - getTotalX(), mouseY - getTotalY())) {
            this.hoverComponent.renderBase(guiGraphics, mouseX, mouseY, delta);
        }
    }

    public AdvancementHoverComponent getHoverComponent() {
        return hoverComponent;
    }

    public AdvancementIconComponent getIconComponent() {
        return iconComponent;
    }

    private List<IComponent<?>> getAdvancementChildren(IAdvancement advancement) {
        List<IComponent<?>> children = new ArrayList<>();
        AdvancementComponent previousSibling = null;

        for (IAdvancement child : advancement.getChildren()) {
            AdvancementComponent advancementComponent = new AdvancementComponent(child, this, previousSibling, children.size(), (int) (this.posX + 1));
            previousSibling = advancementComponent;
            children.add(advancementComponent);
        }

        return children;
    }

    private List<AdvancementComponent> getAdvancementChildren() {
        return this.getChildren().stream()
                .filter(AdvancementComponent.class::isInstance)
                .map(AdvancementComponent.class::cast)
                .toList();
    }

    public List<AdvancementComponent> getAllAdvancementChildren() {
        List<AdvancementComponent> advancementComponents = new ArrayList<>();
        advancementComponents.add(this);
        for (AdvancementComponent advancementComponent : getAdvancementChildren()) {
            advancementComponents.addAll(advancementComponent.getAllAdvancementChildren());
        }
        return advancementComponents;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        for (AdvancementComponent advancementChild : this.getAdvancementChildren()) {
            this.drawConnectivity(graphics,
                    1,
                    0,
                    advancementChild.getX() + 1,
                    advancementChild.getY(),
                    false);
        }

        for (AdvancementComponent advancementChild : this.getAdvancementChildren()) {
            this.drawConnectivity(graphics,
                    1,
                    0,
                    advancementChild.getX() + 1,
                    advancementChild.getY(),
                    true);
        }
    }

    public void firstWalk() {
        if (this.getAdvancementChildren().isEmpty()) {
            if (this.previousSibling != null) {
                this.posY = this.previousSibling.posY + 1.0F;
            } else {
                this.posY = 0.0F;
            }

        } else {
            AdvancementComponent treeNodePosition = null;

            AdvancementComponent treeNodePosition2;
            for(Iterator<AdvancementComponent> var2 = this.getAdvancementChildren().iterator(); var2.hasNext(); treeNodePosition = treeNodePosition2.apportion(treeNodePosition == null ? treeNodePosition2 : treeNodePosition)) {
                treeNodePosition2 = var2.next();
                treeNodePosition2.firstWalk();
            }

            this.executeShifts();
            float f = (this.getAdvancementChildren().get(0).posY + this.getAdvancementChildren().get(this.getAdvancementChildren().size() - 1).posY) / 2.0F;
            if (this.previousSibling != null) {
                this.posY = this.previousSibling.posY + 1.0F;
                this.mod = this.posY - f;
            } else {
                this.posY = f;
            }

        }
    }

    public float secondWalk(float f, int i, float g) {
        this.posY += f;
        this.posX = i;
        if (this.posY < g) {
            g = this.posY;
        }

        AdvancementComponent treeNodePosition;
        for(Iterator<AdvancementComponent> var4 = this.getAdvancementChildren().iterator(); var4.hasNext(); g = treeNodePosition.secondWalk(f + this.mod, i + 1, g)) {
            treeNodePosition = var4.next();
        }

        return g;
    }

    public void thirdWalk(float f) {
        this.posY += f;

        for (AdvancementComponent treeNodePosition : this.getAdvancementChildren()) {
            treeNodePosition.thirdWalk(f);
        }
    }

    public void finalizePosition() {
        this.posY = this.posY - (this.parent == null ? 0 : getTotalPosY() - this.posY);
        this.setX(SIZE + SPACING);
        this.setY((int) (this.posY * (float) (SIZE + SPACING)));

        if (this.parent == null) {
            this.setX(this.getX() - SIZE - SPACING);
        }

        if (!this.getAdvancementChildren().isEmpty()) {
            for (AdvancementComponent treeNodePosition : this.getAdvancementChildren()) {
                treeNodePosition.finalizePosition();
            }
        }
    }

    private float getTotalPosY() {
        return this.posY + (this.parent == null ? 0 : this.parent.getTotalPosY());
    }

    private void executeShifts() {
        float f = 0.0F;
        float g = 0.0F;

        for(int i = this.getAdvancementChildren().size() - 1; i >= 0; --i) {
            AdvancementComponent treeNodePosition = this.getAdvancementChildren().get(i);
            treeNodePosition.posY += f;
            treeNodePosition.mod += f;
            g += treeNodePosition.change;
            f += treeNodePosition.shift + g;
        }
    }

    private AdvancementComponent apportion(AdvancementComponent treeNodePosition) {
        if (this.previousSibling != null && this.parent != null) {
            AdvancementComponent treeNodePosition2 = this;
            AdvancementComponent treeNodePosition3 = this;
            AdvancementComponent treeNodePosition4 = this.previousSibling;
            AdvancementComponent treeNodePosition5 = this.parent.getAdvancementChildren().get(0);
            float f = this.mod;
            float g = this.mod;
            float h = treeNodePosition4.mod;

            float i;
            for (i = treeNodePosition5.mod; treeNodePosition4.nextOrThread() != null && treeNodePosition2.previousOrThread() != null; g += treeNodePosition3.mod) {
                treeNodePosition4 = treeNodePosition4.nextOrThread();
                treeNodePosition2 = treeNodePosition2.previousOrThread();
                treeNodePosition5 = treeNodePosition5.previousOrThread();
                treeNodePosition3 = treeNodePosition3.nextOrThread();
                Objects.requireNonNull(treeNodePosition3).ancestor = this;
                float j = Objects.requireNonNull(treeNodePosition4).posY + h - (Objects.requireNonNull(treeNodePosition2).posY + f) + 1.0F;
                if (j > 0.0F) {
                    treeNodePosition4.getAncestor(this, treeNodePosition).moveSubtree(this, j);
                    f += j;
                    g += j;
                }

                h += treeNodePosition4.mod;
                f += treeNodePosition2.mod;
                i += Objects.requireNonNull(treeNodePosition5).mod;
            }

            if (treeNodePosition4.nextOrThread() != null && treeNodePosition3.nextOrThread() == null) {
                treeNodePosition3.thread = treeNodePosition4.nextOrThread();
                treeNodePosition3.mod += h - g;
            } else {
                if (treeNodePosition2.previousOrThread() != null && treeNodePosition5.previousOrThread() == null) {
                    treeNodePosition5.thread = treeNodePosition2.previousOrThread();
                    treeNodePosition5.mod += f - i;
                }

                treeNodePosition = this;
            }

        }
        return treeNodePosition;
    }

    @Nullable
    private AdvancementComponent previousOrThread() {
        if (this.thread != null) {
            return this.thread;
        } else {
            return !this.getAdvancementChildren().isEmpty() ? this.getAdvancementChildren().get(0) : null;
        }
    }

    @Nullable
    private AdvancementComponent nextOrThread() {
        if (this.thread != null) {
            return this.thread;
        } else {
            return !this.getAdvancementChildren().isEmpty() ? this.getAdvancementChildren().get(this.getAdvancementChildren().size() - 1) : null;
        }
    }

    private AdvancementComponent getAncestor(AdvancementComponent treeNodePosition, AdvancementComponent treeNodePosition2) {
        return this.ancestor != null && Objects.requireNonNull(treeNodePosition.parent).getAdvancementChildren().contains(this.ancestor) ? this.ancestor : treeNodePosition2;
    }

    private void moveSubtree(AdvancementComponent treeNodePosition, float f) {
        float g = (float)(treeNodePosition.childIndex - this.childIndex);
        if (g != 0.0F) {
            treeNodePosition.change -= f / g;
            this.change += f / g;
        }

        treeNodePosition.shift += f;
        treeNodePosition.posY += f;
        treeNodePosition.mod += f;
    }

    private void drawConnectivity(GuiGraphics guiGraphics, int xFrom, int yFrom, int xTo, int yTo, boolean isWhite) {
        int spacing = 2;
        int centerHeight = 24 / 2;
        int xOffset = 24;
        int xFromOffset = xFrom + xOffset;
        int xToOffset = xTo - 2;
        int yFromCenter = yFrom + centerHeight;
        int yToCenter = yTo + centerHeight;
        int color = isWhite ? 0xFFFFFFFF : 0xFF000000;

        if (isWhite) {
            guiGraphics.hLine(xFromOffset, xFromOffset + spacing, yFromCenter, color);
            guiGraphics.vLine(xFromOffset + spacing, yFromCenter, yToCenter, color);
            guiGraphics.hLine(xToOffset, xToOffset + spacing, yToCenter, color);
        } else {
            guiGraphics.hLine(xFromOffset, xToOffset + 1, yFromCenter - 1, color);
            guiGraphics.hLine(xFromOffset, xToOffset + 1, yFromCenter, color);
            guiGraphics.hLine(xFromOffset, xToOffset + 1, yFromCenter + 1, color);

            guiGraphics.vLine(xToOffset - 1, yToCenter, yFromCenter, color);
            guiGraphics.vLine(xToOffset + 1, yToCenter, yFromCenter, color);

            guiGraphics.hLine(xToOffset - 1, xToOffset + spacing, yToCenter - 1, color);
            guiGraphics.hLine(xToOffset - 1, xToOffset + spacing, yToCenter, color);
            guiGraphics.hLine(xToOffset - 1, xToOffset + spacing, yToCenter + 1, color);
        }
    }
}
