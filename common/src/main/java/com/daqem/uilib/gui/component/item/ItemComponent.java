package com.daqem.uilib.gui.component.item;

import com.daqem.uilib.gui.component.AbstractComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public class ItemComponent extends AbstractComponent {

    private ItemStack itemStack;
    private boolean decorated;

    public ItemComponent(ItemStack itemStack) {
        this(0, 0, itemStack);
    }

    public ItemComponent(int x, int y, ItemStack itemStack) {
        this(x, y, itemStack, false);
    }

    public ItemComponent(int x, int y, ItemStack itemStack, boolean decorated) {
        super(x, y, 16, 16);

        if (itemStack == null) {
            throw new IllegalArgumentException("ItemStack cannot be null");
        }

        this.itemStack = itemStack;
        this.decorated = decorated;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public boolean isDecorated() {
        return decorated;
    }

    public void setDecorated(boolean decorated) {
        this.decorated = decorated;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        guiGraphics.renderFakeItem(
                getItemStack(),
                getTotalX(),
                getTotalY()
        );
        if (isDecorated()) {
            guiGraphics.renderItemDecorations(
                    Minecraft.getInstance().font,
                    getItemStack(),
                    getTotalX(),
                    getTotalY()
            );
        }
    }
}
