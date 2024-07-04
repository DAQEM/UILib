package com.daqem.uilib.client.screen.test;

import com.daqem.uilib.client.gui.component.io.TextBoxComponent;
import net.minecraft.network.chat.Component;

import java.util.List;

public class TestStringTextBoxComponent extends TextBoxComponent {

    public TestStringTextBoxComponent(int x, int y, int width, int height, String value) {
        super(x, y, width, height, value);
    }

    @Override
    public List<Component> validateInput(String input) {
        List<Component> errors = super.validateInput(input);
        if (input.length() < 5) {
            errors.add(Component.literal("Input must be at least 5 characters long"));
        }
        return errors;
    }
}
