package com.daqem.uilib.api.widget;

import com.daqem.uilib.UILib;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.util.ArrayList;
import java.util.List;

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

    default List<Component> getInputValidationErrorsTooltip() {
        if (hasInputValidationErrors()) {
            List<Component> components = new ArrayList<>();
            components.add(UILib.translatable("widget.validation_errors").setStyle(Style.EMPTY.withBold(true).withColor(ChatFormatting.RED)));
            components.addAll(getInputValidationErrors().stream()
                    .map(x -> Component.literal("- ").append(x))
                    .toList());
            return components;
        }
        return null;
    }
}
