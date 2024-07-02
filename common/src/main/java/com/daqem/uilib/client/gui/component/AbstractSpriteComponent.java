package com.daqem.uilib.client.gui.component;

import net.minecraft.resources.ResourceLocation;

import java.util.LinkedList;

public abstract class AbstractSpriteComponent<T extends AbstractSpriteComponent<T>> extends AbstractComponent<T> {

    private LinkedList<ResourceLocation> sprites;

    public AbstractSpriteComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height) {
        super(null, x, y, width, height);
        if (sprites.isEmpty()) {
            throw new IllegalArgumentException("Sprites list must contain at least one sprite");
        }
        this.sprites = sprites;
    }

    public ResourceLocation getDefaultSprite() {
        return sprites.getFirst();
    }

    public ResourceLocation getSprite(int index) {
        return sprites.get(index);
    }

    public LinkedList<ResourceLocation> getSprites() {
        return sprites;
    }

    public void setSprites(LinkedList<ResourceLocation> sprites) {
        this.sprites = sprites;
    }
}
