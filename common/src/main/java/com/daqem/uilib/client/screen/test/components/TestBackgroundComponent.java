package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.TextureComponent;
import com.daqem.uilib.client.gui.texture.Texture;

import java.util.Arrays;

public class TestBackgroundComponent extends TextureComponent {

    private final TestTabs.TestLeftTabs leftTabs = new TestTabs.TestLeftTabs();
    private final TestTabs.TestRightTabs rightTabs = new TestTabs.TestRightTabs();

    public TestBackgroundComponent() {
        super(
                new Texture(UILibClient.getId("textures/jobs_screen.png"),
                        0, 0, 326, 166, 362),
                0, 0, 326, 166);
    }

    @Override
    public void start() {
        this.center();

        this.addChildren(Arrays.asList(
                leftTabs.ALL_JOBS,
                leftTabs.ACTIVE_JOBS,
                leftTabs.INACTIVE_JOBS,
                rightTabs.INFO,
                rightTabs.RESTRICTIONS,
                rightTabs.POWER_UPS,
                rightTabs.GET_EXP
        ));

        super.start();
    }


    public TestTabs.TestLeftTabs getLeftTabs() {
        return leftTabs;
    }

    public TestTabs.TestRightTabs getRightTabs() {
        return rightTabs;
    }
}
