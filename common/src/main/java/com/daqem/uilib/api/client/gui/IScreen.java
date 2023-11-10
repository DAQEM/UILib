package com.daqem.uilib.api.client.gui;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.IComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.ContainerEventHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IScreen extends ContainerEventHandler {

    int getWidth();
    int getHeight();
    List<IComponent<?>> getComponents();
    void addComponent(IComponent<?> component);
    void addComponents(IComponent<?>... components);
    void removeComponent(IComponent<?> component);
    @Nullable IBackground<?> getBackground();
    void setBackground(@Nullable IBackground<?> background);

    void startScreen();
    void onResizeScreenRepositionComponents(int width, int height);
    void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks);
}
