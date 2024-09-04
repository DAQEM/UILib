package com.daqem.uilib.api.client.gui;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.IComponent;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.ContainerEventHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IScreen extends ContainerEventHandler {

    int getWidth();
    int getHeight();
    Font getFont();
    List<IComponent<?>> getComponents();
    @Nullable IComponent<?> getFocusedComponent();
    void setFocusedComponent(@Nullable IComponent<?> focusedComponent);
    void addComponent(IComponent<?> component);
    void addComponents(IComponent<?>... components);
    void removeComponent(IComponent<?> component);
    @Nullable IBackground<?> getBackground();
    void setBackground(@Nullable IBackground<?> background);

    void startScreen();
    void onResizeScreenRepositionComponents(int width, int height);
    void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta);
    void renderComponents(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta);
    void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta);
    boolean isPauseScreen();
    void setPauseScreen(boolean pauseScreen);

    IScreenAccessor getAccessor();
}
