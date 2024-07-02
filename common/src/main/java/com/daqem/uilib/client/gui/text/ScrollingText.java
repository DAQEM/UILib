package com.daqem.uilib.client.gui.text;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class ScrollingText extends AbstractText<ScrollingText> {

    private Direction direction = Direction.LEFT;
    private float speed = 0.50F;
    private int delay = 50;
    private boolean defaultCentered = false;

    private int delayCounter = 0;
    private float scrollCounter = 0;

    private boolean s2sSide = false;
    private int s2sDelay = 0;

    public ScrollingText(Font font, Component text, int x, int y) {
        super(font, text, x, y);
    }

    public ScrollingText(Font font, Component text, int x, int y, int width, int height) {
        super(font, text, x, y, width, height);
    }

    public ScrollingText(Font font, Component text, int x, int y, Direction direction) {
        super(font, text, x, y);
        this.direction = direction;
    }

    public ScrollingText(Font font, Component text, int x, int y, int width, int height, Direction direction) {
        super(font, text, x, y, width, height);
        this.direction = direction;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        int xOffset = 0;
        int yOffset = 0;

        int textWidth = getFont().width(getText());
        int spaceWidth = textWidth - getWidth();

        if (defaultCentered && textWidth < getWidth()) {
            xOffset = (getWidth() - textWidth) / 2;
        } else {
            if (delayCounter < delay) {
                delayCounter++;
            } else {
                switch (direction) {
                    case LEFT -> {
                        xOffset = (int) -scrollCounter;
                        scrollCounter += speed;
                        if (scrollCounter >= getFont().width(getText())) {
                            scrollCounter = -getWidth();
                        }
                        if (scrollCounter == 0) {
                            delayCounter = 0;
                        }
                    }
                    case RIGHT -> {
                        xOffset = (int) scrollCounter;
                        scrollCounter += speed;
                        if (scrollCounter >= getFont().width(getText())) {
                            scrollCounter = -getWidth();
                        }
                        if (scrollCounter == 0) {
                            delayCounter = 0;
                        }
                    }
                    case UP -> {
                        yOffset = (int) -scrollCounter;
                        scrollCounter += speed;
                        if (scrollCounter >= getFont().lineHeight) {
                            scrollCounter = -getHeight();
                        }
                        if (scrollCounter == 0) {
                            delayCounter = 0;
                        }
                    }
                    case DOWN -> {
                        yOffset = (int) scrollCounter;
                        scrollCounter += speed;
                        if (scrollCounter >= getFont().lineHeight) {
                            scrollCounter = -getHeight();
                        }
                        if (scrollCounter == 0) {
                            delayCounter = 0;
                        }
                    }
                    case SIDE_TO_SIDE -> {
                        xOffset = (int) -scrollCounter;

                        if (s2sDelay < delay) {
                            s2sDelay++;
                        } else {
                            scrollCounter += (s2sSide ? -speed : speed);

                            if (!s2sSide && scrollCounter >= spaceWidth) {
                                s2sSide = true;
                                s2sDelay = 0;
                            } else if (s2sSide && scrollCounter <= 0) {
                                s2sSide = false;
                                s2sDelay = 0;
                            }
                        }
                    }
                }
            }
        }

        graphics.enableScissor(getTotalX(), getTotalY(), getTotalX() + getWidth(), getTotalY() + getHeight());
        graphics.drawString(getFont(), getText(), xOffset, yOffset, getTextColor(), isShadow());
        graphics.disableScissor();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isDefaultCentered() {
        return defaultCentered;
    }

    public void setDefaultCentered(boolean defaultCentered) {
        this.defaultCentered = defaultCentered;
    }

    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        SIDE_TO_SIDE,
    }
}
