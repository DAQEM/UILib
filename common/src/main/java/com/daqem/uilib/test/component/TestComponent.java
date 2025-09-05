package com.daqem.uilib.test.component;

import com.daqem.uilib.gui.component.AbstractComponent;
import com.daqem.uilib.gui.component.item.ItemComponent;
import com.daqem.uilib.gui.component.sprite.SpriteComponent;
import com.daqem.uilib.test.component.sprite.TestSpriteComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class TestComponent extends AbstractComponent {

    public TestComponent(int number) {
        super(0, 0, 200, 200);

        TestSpriteComponent spriteComponent = new TestSpriteComponent(-10, -10, 220, 220);

        SpriteComponent slotSpriteComponent = new SpriteComponent(0, 0, 18, 18, ResourceLocation.withDefaultNamespace("container/slot"));
        ItemComponent itemComponent = new ItemComponent(1, 1, Items.BARRIER.getDefaultInstance().copyWithCount(16), true);

        this.addComponent(spriteComponent);
        this.addComponent(slotSpriteComponent);
        this.addComponent(itemComponent);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
    }
}
