package com.daqem.uilib.gui.widget;

import com.daqem.uilib.UILib;
import com.daqem.uilib.api.widget.IEditBoxWidget;
import com.daqem.uilib.api.widget.IInputValidatable;
import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.tooltip.BelowOrAboveWidgetTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.MenuTooltipPositioner;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EditBoxWidget extends EditBox implements IWidget, IInputValidatable {

    private List<Component> inputValidationErrors = new ArrayList<>();

    public EditBoxWidget(Font font, int width, int height, Component title) {
        super(font, width, height, title);
    }

    public EditBoxWidget(Font font, int x, int y, int width, int height, Component title) {
        super(font, x, y, width, height, title);
    }

    public EditBoxWidget(Font font, int x, int y, int width, int height, @Nullable EditBox editBox, Component title) {
        super(font, x, y, width, height, editBox, title);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Had to add this because the text position wasn't updating correctly when set with an initial value
        if (this instanceof IEditBoxWidget editBoxWidget) {
            editBoxWidget.uilib$updateTextPosition();
        }

        List<Component> components = this.validateInput(getValue());
        if (components != null && !components.isEmpty()) {
            setInputValidationErrors(components);
        } else {
            clearInputValidationErrors();
        }

        super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);

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

    private ClientTooltipPositioner createTooltipPositioner(ScreenRectangle screenRectangle, boolean hovering, boolean focused) {
        return !hovering && focused && Minecraft.getInstance().getLastInputType().isKeyboard()
                ? new BelowOrAboveWidgetTooltipPositioner(screenRectangle)
                : new MenuTooltipPositioner(screenRectangle);
    }
}
