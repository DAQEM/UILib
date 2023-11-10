package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.IconComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.gui.GuiGraphics;

public class TestTabComponent extends ButtonComponent {

    private static final int TAB_WIDTH = 28;
    private static final int TAB_HEIGHT = 24;

    private boolean selected;
    private final int defaultY;
    private final IconComponent iconComponent;

    public TestTabComponent(boolean selected, int x, int y, IconComponent iconComponent) {
        super(Textures.TAB, x, y, TAB_WIDTH, TAB_HEIGHT);
        this.selected = selected;
        this.defaultY = y;
        this.iconComponent = iconComponent;
    }

    @Override
    public void startRenderable() {
        if (!isSelected()) {
            setZ(-1);
        } else {
            setZ(1);
        }
        iconComponent.setX(4);
        iconComponent.setY(1);
        this.addChild(iconComponent);
        setOnClickAction((button, screen, mouseY, mouseX) -> {
            if (getParent() instanceof TestBackgroundComponent backgroundComponent) {
                if (backgroundComponent.getLeftTabs().getTabComponents().contains(this)) {
                    backgroundComponent.getLeftTabs().deselectAll();
                } else if (backgroundComponent.getRightTabs().getTabComponents().contains(this)) {
                    backgroundComponent.getRightTabs().deselectAll();
                }
            }
            if (!isSelected()) {
                setSelected(true);
            }

        });
        super.startRenderable();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
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
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
