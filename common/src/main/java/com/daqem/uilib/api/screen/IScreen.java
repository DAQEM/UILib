package com.daqem.uilib.api.screen;

import com.daqem.uilib.api.IParent;
import com.daqem.uilib.api.background.IBackground;
import org.jetbrains.annotations.Nullable;

public interface IScreen extends IParent {

    @Nullable IBackground getBackground();
    void setBackground(@Nullable IBackground background);
    void clearBackground();
}
