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
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

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
            Component title
    ) {
        super(font, x, y, width, height, placeholder, title);
    }

    public MultiLineEditBoxWidget(
            Component placeholder,
            Component title
    ) {
        super(Minecraft.getInstance().font, 0, 0, 200, 50, placeholder, title);
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
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
            if (minecraft.screen != null) {
                minecraft.screen.setTooltipForNextRenderPass(
                        Language.getInstance().getVisualOrder(new ArrayList<>(tooltip)),
                        this.createTooltipPositioner(getRectangle(), isHovered(), isFocused()),
                        isFocused()
                );
            }
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
    protected void renderBackground(@NotNull GuiGraphics guiGraphics) {
        if (hasInputValidationErrors()) {
            guiGraphics.blitSprite(UILib.getId("widget/text_field_error"), getX(), getY(), getWidth(), getHeight());
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
