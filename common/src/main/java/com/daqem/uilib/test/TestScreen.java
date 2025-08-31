package com.daqem.uilib.test;

import com.daqem.uilib.UILib;
import com.daqem.uilib.api.background.IBackground;
import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.gui.AbstractScreen;
import com.daqem.uilib.gui.background.DarkenedBackground;
import com.daqem.uilib.test.component.TestComponent;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class TestScreen extends AbstractScreen {

    private final List<IComponent> components = new ArrayList<>();
    private int activeComponentIndex = 0;

    public TestScreen() {
        super(UILib.translatable("screen.test.title"));

        IBackground background = new DarkenedBackground();

        this.setBackground(background);
    }

    @Override
    public void init() {
        IComponent testComponent1 = new TestComponent(1);
        IComponent testComponent2 = new TestComponent(2);
        IComponent testComponent3 = new TestComponent(3);

        components.add(testComponent1);
        components.add(testComponent2);
        components.add(testComponent3);

        testComponent1.center();
        testComponent2.center();
        testComponent3.center();

        this.addComponent(components.get(activeComponentIndex));

        super.init();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_LEFT) {
            this.clear();
            activeComponentIndex = (activeComponentIndex - 1 + components.size()) % components.size();
            this.addComponent(components.get(activeComponentIndex));
            super.init();
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_RIGHT) {
            this.clear();
            activeComponentIndex = (activeComponentIndex + 1) % components.size();
            this.addComponent(components.get(activeComponentIndex));
            super.init();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
