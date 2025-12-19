package com.daqem.uilib.test.client.gui.sprite;

import com.daqem.uilib.gui.component.sprite.AbstractSpriteComponent;
import net.minecraft.resources.Identifier;

public class TestSpriteComponent extends AbstractSpriteComponent {

    public TestSpriteComponent(int x, int y, int width, int height) {
        super(x, y, width, height, Identifier.withDefaultNamespace("recipe_book/overlay_recipe"));
    }
}
