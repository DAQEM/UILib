package com.daqem.uilib.api.screen;

import com.daqem.uilib.api.IParent;
import com.daqem.uilib.api.background.IBackground;
import net.minecraft.client.gui.components.events.GuiEventListener;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IScreen extends IParent {

    @Nullable IBackground getBackground();
    void setBackground(@Nullable IBackground background);
    void clearBackground();
    List<? extends GuiEventListener> children();
}
