package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.client.gui.component.IconComponent;
import com.daqem.uilib.client.gui.texture.icon.Icons;

import java.util.List;

public class TestRightTabs extends TestTabs {
    private static final IconComponent ICON_INFO = new IconComponent(Icons.INFO);
    private static final IconComponent ICON_CRAFTING_TABLE = new IconComponent(Icons.CRAFTING_TABLE);
    private static final IconComponent ICON_ARROW_UP = new IconComponent(Icons.ARROW_UP);
    private static final IconComponent ICON_EXP_ORB = new IconComponent(Icons.EXP_ORB);

    public TestTabComponent info = new TestTabComponent(true, Position.INFO_X, Position.TAB_Y, ICON_INFO);
    public TestTabComponent restrictions = new TestTabComponent(false, Position.RESTRICTIONS_X, Position.TAB_Y, ICON_CRAFTING_TABLE);
    public TestTabComponent powerUps = new TestTabComponent(false, Position.POWER_UPS_X, Position.TAB_Y, ICON_ARROW_UP);
    public TestTabComponent getExp = new TestTabComponent(false, Position.GET_EXP_X, Position.TAB_Y, ICON_EXP_ORB);

    @Override
    public List<TestTabComponent> getTabComponents() {
        return List.of(info, restrictions, powerUps, getExp);
    }
}
