package com.daqem.uilib.client.screen.test.components;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class TestTabs {

    abstract public List<TestTabButtonComponent> getComponents();

    public @Nullable TestTabButtonComponent getSelected() {
        return this.getComponents().stream()
                .filter(TestTabButtonComponent::isSelected)
                .findFirst()
                .orElse(null);
    }

    public void setSelected(TestTabButtonComponent selected) {
        this.getComponents().forEach(component -> component.setSelected(component == selected));
    }

    public void deselectAll() {
        this.getComponents().forEach(component -> component.setSelected(false));
    }

    public static class TestLeftTabs extends TestTabs {
        public TestTabButtonComponent ALL_JOBS = new TestTabButtonComponent(true, 4, -19);
        public TestTabButtonComponent ACTIVE_JOBS = new TestTabButtonComponent(false, 34, -19);
        public TestTabButtonComponent INACTIVE_JOBS = new TestTabButtonComponent(false, 64, -19);

        @Override
        public List<TestTabButtonComponent> getComponents() {
            return List.of(ALL_JOBS, ACTIVE_JOBS, INACTIVE_JOBS);
        }
    }

    public static class TestRightTabs extends TestTabs {
        public TestTabButtonComponent INFO = new TestTabButtonComponent(true, 154, -19);
        public TestTabButtonComponent RESTRICTIONS = new TestTabButtonComponent(false, 184, -19);
        public TestTabButtonComponent POWER_UPS = new TestTabButtonComponent(false, 214, -19);
        public TestTabButtonComponent GET_EXP = new TestTabButtonComponent(false, 244, -19);

        @Override
        public List<TestTabButtonComponent> getComponents() {
            return List.of(INFO, RESTRICTIONS, POWER_UPS, GET_EXP);
        }
    }
}
