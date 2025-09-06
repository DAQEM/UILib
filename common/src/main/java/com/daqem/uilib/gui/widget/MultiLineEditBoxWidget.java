package com.daqem.uilib.gui.widget;

import com.daqem.uilib.UILib;
import com.daqem.uilib.api.widget.IInputValidatable;
import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.tooltip.BelowOrAboveWidgetTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.MenuTooltipPositioner;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class MultiLineEditBoxWidget extends MultiLineEditBox implements IWidget, IInputValidatable {

    private List<Component> inputValidationErrors = new ArrayList<>();

    public MultiLineEditBoxWidget(
            Font font,
            int x,
            int y,
            int width,
            int height,
            Component placeholder,
            Component title,
            int textColor,
            boolean textShadow,
            int cursorColor,
            boolean showBackground,
            boolean showDecorations
    ) {
        super(font, x, y, width, height, placeholder, title, textColor, textShadow, cursorColor, showBackground, showDecorations);
    }

    public MultiLineEditBoxWidget(
            Font font,
            int x,
            int y,
            int width,
            int height,
            Component placeholder,
            Component title,
            boolean showBackground,
            boolean showDecorations
    ) {
        super(font, x, y, width, height, placeholder, title, 0xFFE0E0E0, false, 0xFFD0D0D0, showBackground, showDecorations);
    }

    public MultiLineEditBoxWidget(
            Font font,
            int x,
            int y,
            int width,
            int height,
            Component placeholder,
            Component title,
            int textColor,
            boolean textShadow,
            int cursorColor
    ) {
        super(font, x, y, width, height, placeholder, title, textColor, textShadow, cursorColor, true, true);
    }

    public MultiLineEditBoxWidget(
            Font font,
            int x,
            int y,
            int width,
            int height,
            Component placeholder,
            Component title
    ) {
        super(font, x, y, width, height, placeholder, title, 0xFFE0E0E0, false, 0xFFD0D0D0, true, true);
    }

    public MultiLineEditBoxWidget(
            Component placeholder,
            Component title
    ) {
        super(Minecraft.getInstance().font, 0, 0, 200, 50, placeholder, title, 0xFFE0E0E0, false, 0xFFD0D0D0, true, true);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);

        List<Component> components = this.validateInput(getValue());
        if (components != null && !components.isEmpty()) {
            setInputValidationErrors(components);
        } else {
            clearInputValidationErrors();
        }

        List<Component> tooltip = getInputValidationErrorsTooltip();
        Minecraft minecraft = Minecraft.getInstance();
        if (tooltip != null && (isHovered() || isFocused() && minecraft.getLastInputType().isKeyboard())) {
            guiGraphics.setTooltipForNextFrame(
                    minecraft.font,
                    Language.getInstance().getVisualOrder(new ArrayList<>(tooltip)),
                    this.createTooltipPositioner(getRectangle(), isHovered(), isFocused()),
                    mouseX,
                    mouseY,
                    isFocused()
            );
        }
    }

    @Override
    public List<Component> getInputValidationErrors() {
        if (this.inputValidationErrors == null) {
            this.inputValidationErrors = new ArrayList<>();
        }
        return this.inputValidationErrors;
    }

    @Override
    public void setInputValidationErrors(List<Component> errors) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        this.inputValidationErrors = errors;
    }

    @Override
    protected void renderBackground(GuiGraphics guiGraphics) {
        if (hasInputValidationErrors()) {
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, UILib.getId("widget/text_field_error"), getX(), getY(), getWidth(), getHeight());
        } else {
            super.renderBackground(guiGraphics);
        }
    }

    private ClientTooltipPositioner createTooltipPositioner(ScreenRectangle screenRectangle, boolean hovering, boolean focused) {
        return !hovering && focused && Minecraft.getInstance().getLastInputType().isKeyboard()
                ? new BelowOrAboveWidgetTooltipPositioner(screenRectangle)
                : new MenuTooltipPositioner(screenRectangle);
    }
}
