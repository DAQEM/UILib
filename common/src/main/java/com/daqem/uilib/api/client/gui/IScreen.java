package com.daqem.uilib.api.client.gui;

import com.daqem.uilib.api.client.gui.background.IBackground;
import com.daqem.uilib.api.client.gui.component.IComponent;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IScreen {

    int getWidth();
    int getHeight();

    /**
     * Called when the screen is initialized.
     * NOTE: Add components and set the background here.
     */
    void onStartScreen();

    /**
     * Called every tick to render the screen.
     * NOTE: Do not add components here, use {@link IScreen#onStartScreen()} instead.
     * @param guiGraphics the gui graphics
     * @param mouseX the mouse x position
     * @param mouseY the mouse y position
     * @param partialTicks the partial ticks
     */
    void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks);

    List<IComponent<?>> getComponents();

    void addComponent(IComponent<?> component);
    void addComponents(IComponent<?>... components);

    void removeComponent(IComponent<?> component);

    @Nullable IBackground<?> getBackground();

    void setBackground(@Nullable IBackground<?> background);
}
