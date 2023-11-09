package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.gui.GuiGraphics;

public class TestTabButtonComponent extends ButtonComponent {

    private boolean selected;

    public TestTabButtonComponent(boolean selected, int x, int y) {
        super(Textures.TAB, x, y, 24, 23);
        this.selected = selected;
    }

    @Override
    public void start() {
        setOnClickAction((button, screen, mouseY, mouseX) -> {
            if (getParent() instanceof TestBackgroundComponent backgroundComponent) {
                if (backgroundComponent.getLeftTabs().getComponents().contains(this)) {
                    backgroundComponent.getLeftTabs().deselectAll();
                } else if (backgroundComponent.getRightTabs().getComponents().contains(this)) {
                    backgroundComponent.getRightTabs().deselectAll();
                }
            }
            if (!isSelected()) {
                setSelected(true);
            }

        });
        super.start();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (isSelected()) {
            setZ(1);
        } else {
            setZ(-1);
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
