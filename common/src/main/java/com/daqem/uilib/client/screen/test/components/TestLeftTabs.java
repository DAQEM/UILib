package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.client.gui.component.IconComponent;
import com.daqem.uilib.client.gui.texture.icon.Icons;

import java.util.List;

public class TestLeftTabs extends TestTabs {
    private static final IconComponent ICON_BOOK = new IconComponent(Icons.BOOK);
    private static final IconComponent ICON_CHECK_MARK = new IconComponent(Icons.CHECK_MARK);
    private static final IconComponent ICON_CROSS = new IconComponent(Icons.CROSS);

    public TestTabComponent allJobs = new TestTabComponent(true, Position.ALL_JOBS_X, Position.TAB_Y, ICON_BOOK);
    public TestTabComponent activeJobs = new TestTabComponent(false, Position.ACTIVE_JOBS_X, Position.TAB_Y, ICON_CHECK_MARK);
    public TestTabComponent inactiveJobs = new TestTabComponent(false, Position.INACTIVE_JOBS_X, Position.TAB_Y, ICON_CROSS);

    @Override
    public List<TestTabComponent> getTabComponents() {
        return List.of(allJobs, activeJobs, inactiveJobs);
    }
}
