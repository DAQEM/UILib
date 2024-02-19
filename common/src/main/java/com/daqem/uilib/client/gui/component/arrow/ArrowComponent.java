package com.daqem.uilib.client.gui.component.arrow;

import com.daqem.uilib.client.gui.component.AbstractComponent;
import net.minecraft.client.gui.GuiGraphics;

public class ArrowComponent extends AbstractComponent<ArrowComponent> {

    private final Direction direction;
    private final int color;
    private final int size;
    private final int thickness;

    public ArrowComponent(int x, int y, int size, Direction direction, int color, int thickness) {
        super(null, x, y, size, size);

        this.direction = direction;
        this.color = color;
        this.size = size;
        this.thickness = thickness;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        switch (direction) {
            case RIGHT -> {
                for (int i = 0; i < size; i++) {
                    if (i < size / 2) {
                        graphics.hLine(i + size / 4 + (thickness - 1), i + size / 4 - thickness / 2, size - i - 1, color);
                    } else {
                        graphics.hLine(size - i - 1 + size / 4 + (thickness - 1), size - i - 1 + size / 4 - thickness / 2, size - i - 1, color);
                    }
                }
            }
            case LEFT -> {
                for (int i = 0; i < size; i++) {
                    if (i < size / 2) {
                        graphics.hLine(size - i - 1 - size / 4 - (thickness - 1), size - i - 1 - size / 4 + thickness / 2, size - i - 1, color);
                    } else {
                        graphics.hLine(i - size / 4 - (thickness - 1), i - size / 4 + thickness / 2, size - i - 1, color);
                    }
                }
            }
        }
    }

    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
}
