package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.api.client.gui.component.IComponent;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.TextureComponent;
import com.daqem.uilib.client.gui.texture.Texture;

import java.util.ArrayList;
import java.util.List;

public class TestBackgroundComponent extends TextureComponent {

    private final TestLeftTabs leftTabs = new TestLeftTabs();
    private final TestRightTabs rightTabs = new TestRightTabs();

    public TestBackgroundComponent() {
        super(
                new Texture(UILibClient.getId("textures/jobs_screen.png"),
                        0, 0, 326, 166, 362),
                0, 0, 326, 166);
    }

    @Override
    public void startRenderable() {
        this.center();
        this.addChildren(getAllTabs());
        super.startRenderable();
    }

    public TestLeftTabs getLeftTabs() {
        return leftTabs;
    }

    public TestRightTabs getRightTabs() {
        return rightTabs;
    }

    private List<IComponent<?>> getAllTabs() {
        List<IComponent<?>> allTabs = new ArrayList<>();
        allTabs.addAll(this.getLeftTabs().getTabComponents());
        allTabs.addAll(this.getRightTabs().getTabComponents());
        return allTabs;
    }
}
