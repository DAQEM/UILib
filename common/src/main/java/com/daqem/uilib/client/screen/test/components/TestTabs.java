package com.daqem.uilib.client.screen.test.components;

import com.daqem.uilib.client.gui.component.IconComponent;
import com.daqem.uilib.client.gui.texture.icon.Icons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class TestTabs {
    abstract public List<TestTabComponent> getTabComponents();

    public void deselectAll() {
        this.getTabComponents().forEach(component -> component.setSelected(false));
    }

    public @Nullable TestTabComponent getSelectedTab() {
        return this.getTabComponents().stream().filter(TestTabComponent::isSelected).findFirst().orElse(null);
    }

    public void setSelectedTab(@NotNull TestTabComponent selectedLeftTab) {
        deselectAll();
        selectedLeftTab.setSelected(true);
    }

    protected static class Position {
        protected static final int TAB_POS = 4;
        protected static final int TAB_SPACING = 30;
        protected static final int TAB_Y = -20;
        protected static final int TAB_RIGHT_X = 150;

        protected static final int ALL_JOBS_X = TAB_POS;
        protected static final int ACTIVE_JOBS_X = TAB_POS + TAB_SPACING;
        protected static final int INACTIVE_JOBS_X = TAB_POS + TAB_SPACING * 2;
        protected static final int INFO_X = TAB_RIGHT_X + TAB_POS;
        protected static final int RESTRICTIONS_X = TAB_RIGHT_X + TAB_POS + TAB_SPACING;
        protected static final int POWER_UPS_X = TAB_RIGHT_X + TAB_POS + TAB_SPACING * 2;
        protected static final int GET_EXP_X = TAB_RIGHT_X + TAB_POS + TAB_SPACING * 3;
    }
}
