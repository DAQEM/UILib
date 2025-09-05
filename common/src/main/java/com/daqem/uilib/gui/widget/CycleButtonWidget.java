package com.daqem.uilib.gui.widget;

import com.daqem.uilib.api.widget.IWidget;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Function;

public class CycleButtonWidget<T> extends CycleButton<T> implements IWidget {

    public CycleButtonWidget(int x, int y, int width, int height, Component name, int index, T value, ValueListSupplier<T> values, Function<T, Component> valueStringifier, Function<CycleButton<T>, MutableComponent> narrationProvider, OnValueChange<T> onValueChange, OptionInstance.TooltipSupplier<T> tooltipSupplier, boolean displayOnlyValue) {
        super(x, y, width, height, displayOnlyValue ? valueStringifier.apply(value) : CommonComponents.optionNameValue(name, valueStringifier.apply(value)), name, index, value, values, valueStringifier, narrationProvider, onValueChange, tooltipSupplier, displayOnlyValue);
    }

    public CycleButtonWidget(int x, int y, int width, int height, Component name, int index, T value, ValueListSupplier<T> values, Function<T, Component> valueStringifier) {
        super(x, y, width, height, CommonComponents.optionNameValue(name, valueStringifier.apply(value)), name, index, value, values, valueStringifier, CycleButton::createDefaultNarrationMessage, (cycleButton, object) -> {
        }, object -> null, false);
    }
}
