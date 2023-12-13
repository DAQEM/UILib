package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.client.gui.component.AbstractComponent;
import com.daqem.uilib.client.gui.component.ItemComponent;
import com.daqem.uilib.client.gui.component.TextComponent;
import com.daqem.uilib.client.gui.text.TruncatedText;
import com.daqem.uilib.client.gui.text.multiline.MultiLineText;
import net.minecraft.advancements.FrameType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AdvancementHoverComponent extends AbstractComponent<AdvancementHoverComponent> {

    private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation("textures/gui/advancements/widgets.png");
    private static final int MIN_WIDTH = 100;
    private static final int MAX_WIDTH = 160;
    private static final int TITLE_X = 28;
    private static final int TITLE_Y = 8;
    private final ItemComponent itemComponent;
    private final MultiLineText description;
    private final TextComponent descriptionComponent;
    private final AdvancementHoverBarComponent hoverBarComponent;
    private final AdvancementIconComponent iconComponent;
    private final boolean hasStaticHeight;

    public AdvancementHoverComponent(int x, int y, Font font, ItemStack itemStack, Component title, Component description, AdvancementWidgetType type, FrameType frameType) {
        this(x, y, Mth.clamp(TITLE_X + font.width(title), MIN_WIDTH, MAX_WIDTH), font, itemStack, title, description, type, frameType);
    }

    public AdvancementHoverComponent(int x, int y, int width, Font font, ItemStack itemStack, Component title, Component description, AdvancementWidgetType type, FrameType frameType) {
        this(x, y, width, -1, font, itemStack, title, description, type, frameType);
    }

    public AdvancementHoverComponent(int x, int y, int width, int height, Font font, ItemStack itemStack, Component title, Component description, AdvancementWidgetType type, FrameType frameType) {
        super(null, x, y, 0, 0);
        setText(new TruncatedText(font, title, TITLE_X, TITLE_Y, width - TITLE_X - 3, font.lineHeight));
        setWidth(width);
        setHeight(height);

        this.itemComponent = new ItemComponent(5, 5, itemStack, true);
        this.description = new MultiLineText(Minecraft.getInstance().font, description, 0, 0, getWidth() - 3);
        this.descriptionComponent = new TextComponent(0, 27, this.description);
        this.hoverBarComponent = new AdvancementHoverBarComponent(-4, 0, getWidth(), type);
        this.iconComponent = new AdvancementIconComponent(0, 0, type, frameType);
        this.hasStaticHeight = height != -1;

        if (!hasStaticHeight) {
            setHeight(32 + this.description.getLines().size() * 9);
        }
    }

    @Override
    public void startRenderable() {
        addChildren(itemComponent, descriptionComponent, hoverBarComponent, iconComponent);
        super.startRenderable();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        if (!hasStaticHeight) {
            setHeight(32 + this.description.getLines().size() * 9);
        }

        graphics.blitNineSliced(WIDGETS_LOCATION, -4, 16, getWidth(), getHeight() - 16, 10, 200, 26, 0, 52);
    }
}
