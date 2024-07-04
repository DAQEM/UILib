package com.daqem.uilib.client.screen.test;

import com.daqem.uilib.client.gui.component.io.MultiLineTextBoxComponent;
import net.minecraft.network.chat.Component;

import java.util.LinkedList;
import java.util.List;

public class TestIntegerMultiLineTextBoxComponent extends MultiLineTextBoxComponent {

    public TestIntegerMultiLineTextBoxComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public List<Component> validateInput(String input) {
        List<String> lines = List.of(input.split("\n"));
        List<Component> errors = new LinkedList<>();
        for (String line : lines) {
            try {
                Integer.parseInt(line);
            } catch (NumberFormatException e) {
                errors.add(Component.literal("Input (" + line + ") must be an integer"));
            }
        }
        return errors;
    }
}
