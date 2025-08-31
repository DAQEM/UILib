package com.daqem.uilib.test.component.sprite;

import com.daqem.uilib.gui.component.sprite.AbstractSpriteComponent;
import net.minecraft.resources.ResourceLocation;

public class TestSpriteComponent extends AbstractSpriteComponent {

    public TestSpriteComponent(int x, int y, int width, int height) {
        super(x, y, width, height, ResourceLocation.withDefaultNamespace("recipe_book/overlay_recipe"));
    }
}
