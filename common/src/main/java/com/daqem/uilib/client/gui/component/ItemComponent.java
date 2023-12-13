package com.daqem.uilib.client.gui.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public class ItemComponent extends AbstractComponent<ItemComponent> {

    private final ItemStack itemStack;
    private final boolean decorated;

    public ItemComponent(int x, int y, ItemStack itemStack, boolean decorated) {
        super(null, x, y, 16, 16);
        this.itemStack = itemStack;
        this.decorated = decorated;
    }

    public ItemComponent(int x, int y, int width, int height, ItemStack itemStack, boolean decorated) {
        super(null, x, y, width, height);
        this.itemStack = itemStack;
        this.decorated = decorated;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        graphics.renderFakeItem(itemStack, 0, 0);
        if (decorated) {
            graphics.renderItemDecorations(Minecraft.getInstance().font, itemStack, 0, 0);
        }
    }
}
