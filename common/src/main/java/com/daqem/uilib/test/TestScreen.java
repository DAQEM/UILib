package com.daqem.uilib.test;

import com.daqem.uilib.UILib;
import com.daqem.uilib.api.background.IBackground;
import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.gui.AbstractScreen;
import com.daqem.uilib.gui.background.DarkenedBackground;
import com.daqem.uilib.gui.widget.ButtonWidget;
import com.daqem.uilib.test.component.*;
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
        IComponent testComponent0 = new TestSkillTreeComponent();
        IComponent testComponent1 = new TestTextsComponent(1);
        IComponent testComponent2 = new TestWidgetsComponent(2);
        IComponent testComponent3 = new TestComponent(3);
        TestScrollWidgetComponent testComponent4 = new TestScrollWidgetComponent(3);

        components.clear();
        components.add(testComponent0);
        components.add(testComponent1);
        components.add(testComponent2);
        components.add(testComponent3);
        components.add(testComponent4);

        testComponent0.center();
        testComponent1.center();
        testComponent2.center();
        testComponent3.center();
        testComponent4.center();

        this.addComponent(components.get(activeComponentIndex));
        this.addWidget(new ButtonWidget(10, this.height - 30, 50, 20, UILib.translatable("screen.test.button.previous"), button -> previousComponent()));
        this.addWidget(new ButtonWidget(this.width - 60, this.height - 30, 50, 20, UILib.translatable("screen.test.button.next"), button -> nextComponent()));

        super.init();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_LEFT) {
            previousComponent();
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_RIGHT) {
            nextComponent();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void nextComponent() {
        this.removeComponent(components.get(activeComponentIndex));
        activeComponentIndex = (activeComponentIndex + 1) % components.size();
        this.addComponent(components.get(activeComponentIndex));
        super.init();
    }

    private void previousComponent() {
        this.removeComponent(components.get(activeComponentIndex));
        activeComponentIndex = (activeComponentIndex - 1 + components.size()) % components.size();
        this.addComponent(components.get(activeComponentIndex));
        super.init();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
