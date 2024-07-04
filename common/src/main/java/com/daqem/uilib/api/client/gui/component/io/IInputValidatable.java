package com.daqem.uilib.api.client.gui.component.io;

import com.daqem.uilib.client.UILibClient;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IInputValidatable {

    List<Component> getInputValidationErrors();

    void setInputValidationErrors(List<Component> errors);

    default List<Component> validateInput(String input) {
        return new ArrayList<>();
    }

    default boolean hasInputValidationErrors() {
        return !getInputValidationErrors().isEmpty();
    }

    default void clearInputValidationErrors() {
        getInputValidationErrors().clear();
    }

    default void renderInputValidationErrorsTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
        if (hasInputValidationErrors()) {
            List<Component> components = new ArrayList<>();
            components.add(UILibClient.translatable("gui.text_box.validation_errors").setStyle(Style.EMPTY.withBold(true).withColor(ChatFormatting.RED)));
            components.addAll(getInputValidationErrors().stream().map(x -> Component.literal("- ").append(x)).toList());
            graphics.renderTooltip(Minecraft.getInstance().font, components, Optional.empty(), mouseX, mouseY);
        }
    }
}
