package com.daqem.uilib.client.gui.component.tab;

import com.daqem.uilib.api.client.gui.component.tab.ITabInformation;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.ItemComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import java.util.List;

public class TabComponent extends ButtonComponent {

    public static final int TAB_WIDTH = 28;
    public static final int TAB_HEIGHT = 28;

    private final ITabInformation tabInformation;
    private final ItemComponent itemComponent;
    private boolean selected = false;
    private final int defaultY = getY();

    public TabComponent(int x, int y, ITabInformation tabInformation) {
        this(x, y, TAB_WIDTH, TAB_HEIGHT, tabInformation);
    }

    public TabComponent(int x, int y, int width, int height, ITabInformation tabInformation) {
        super(Textures.TAB, x, y, width, height);
        this.itemComponent = new ItemComponent(6, 5, tabInformation.getIcon(), true);
        this.tabInformation = tabInformation;
        this.addChild(itemComponent);

        if (!isSelected()) {
            setZ(-1);
        } else {
            setZ(1);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (isSelected()) {
            setZ(1);
            int targetY = defaultY - 3;
            if (getY() > targetY) {
                setY(getY() - 1);
                setHeight(getHeight() + 1);
            }
        } else {
            setZ(-1);
            if (getY() < defaultY) {
                setY(getY() + 1);
                setHeight(getHeight() - 1);
            }
        }
        super.render(guiGraphics, mouseX, mouseY, delta);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ITabInformation getTabInformation() {
        return tabInformation;
    }

    @Override
    public void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.renderTooltips(guiGraphics, mouseX, mouseY, delta);
        if (this.isTotalHovered(mouseX, mouseY)) {
            guiGraphics.renderTooltip(Minecraft.getInstance().font, tabInformation.getName(), mouseX - 3 - getX(), mouseY + 24 - getY());
        }
    }
}
