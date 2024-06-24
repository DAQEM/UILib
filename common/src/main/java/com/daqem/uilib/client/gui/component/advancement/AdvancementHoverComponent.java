package com.daqem.uilib.client.gui.component.advancement;

import com.daqem.uilib.api.client.gui.text.IText;
import com.daqem.uilib.client.gui.component.AbstractComponent;
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

import java.util.List;

public class AdvancementHoverComponent extends AbstractComponent<AdvancementHoverComponent> {

    private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation("textures/gui/advancements/widgets.png");
    private static final int MIN_WIDTH = 140;
    private static final int MAX_WIDTH = 260;
    private static final int TITLE_X = 28;
    private static final int TITLE_Y = 8;
    private final List<MultiLineText> descriptions;
    private final List<TextComponent> descriptionComponents;
    private final AdvancementHoverBarComponent hoverBarComponent;
    private final AdvancementIconComponent iconComponent;
    private final boolean hasStaticHeight;

    public AdvancementHoverComponent(int x, int y, Font font, ItemStack itemStack, Component title, List<Component> descriptions, AdvancementWidgetType type, FrameType frameType) {
        this(x, y, Mth.clamp(TITLE_X + font.width(title), MIN_WIDTH, MAX_WIDTH), font, itemStack, title, descriptions, type, frameType);
    }

    public AdvancementHoverComponent(int x, int y, int width, Font font, ItemStack itemStack, Component title, List<Component> descriptions, AdvancementWidgetType type, FrameType frameType) {
        this(x, y, width, -1, font, itemStack, title, descriptions, type, frameType);
    }

    public AdvancementHoverComponent(int x, int y, int width, int height, Font font, ItemStack itemStack, Component title, List<Component> descriptions, AdvancementWidgetType type, FrameType frameType) {
        super(null, x, y, 0, 0);
        setText(new TruncatedText(font, title, TITLE_X, TITLE_Y, width - TITLE_X - 3, font.lineHeight));
        setWidth(width);
        setHeight(height);

        this.descriptions = descriptions.stream().map(description -> new MultiLineText(Minecraft.getInstance().font, description, 0, 0, getWidth() - 3)).toList();
        this.descriptionComponents = this.descriptions.stream().map(description -> new TextComponent(0, 27, description)).toList();

        TextComponent previous = null;
        for (TextComponent descriptionComponent : descriptionComponents) {
            if (previous != null) {
                IText<?> previousText = previous.getText();
                if (previousText instanceof MultiLineText multiLineText) {
                    descriptionComponent.setY(previous.getY() + multiLineText.getLines().size() * 9);
                }
            }
            previous = descriptionComponent;
        }

        this.hoverBarComponent = new AdvancementHoverBarComponent(-4, 0, getWidth(), type);
        this.iconComponent = new AdvancementIconComponent(0, 0, itemStack, type, frameType);
        this.hasStaticHeight = height != -1;

        if (!hasStaticHeight) {
            setHeight(32 + getDescriptionHeight());
        }

        descriptionComponents.forEach(this::addChild);
        addChildren(hoverBarComponent, iconComponent);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        if (!hasStaticHeight) {
            setHeight(32 + getDescriptionHeight());
        }

        graphics.blitNineSliced(WIDGETS_LOCATION, -4, 16, getWidth(), getHeight() - 16, 10, 200, 26, 0, 52);
    }

    private int getDescriptionHeight() {
        return this.descriptions.stream().map(MultiLineText::getLines).mapToInt(List::size).sum() * 9;
    }
}
