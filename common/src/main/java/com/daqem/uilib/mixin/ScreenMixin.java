package com.daqem.uilib.mixin;

import com.daqem.uilib.UILib;
import com.daqem.uilib.api.component.IComponent;
import com.daqem.uilib.api.widget.IWidget;
import com.daqem.uilib.gui.AbstractScreen;
import com.daqem.uilib.gui.widget.ButtonWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mixin(Screen.class)
public abstract class ScreenMixin {

    @Shadow
    @Final
    private List<NarratableEntry> narratables;

    @Shadow
    @Final
    private List<Renderable> renderables;

    @Redirect(
            method = "*",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/gui/screens/Screen;narratables:Ljava/util/List;",
                    opcode = Opcodes.GETFIELD
            )
    )
    private List<NarratableEntry> redirectNarratables(Screen instance) {
        if ((Screen) (Object) this instanceof AbstractScreen screen) {
            Set<NarratableEntry> widgets = new HashSet<>(narratables);
            for (GuiEventListener widget : screen.children()) {
                if (widget instanceof NarratableEntry narratableEntry) {
                    widgets.add(narratableEntry);
                }
            }
            UILib.LOGGER.info("Narratables: {}", widgets);
            UILib.LOGGER.info("Narratables2: {}", widgets.stream()
                    .filter(widget -> widget instanceof ButtonWidget)
                    .map(widget -> (ButtonWidget) widget)
                    .map(ButtonWidget::getMessage)
                    .map(Component::getString)
                    .toList()
            );
            return new ArrayList<>(widgets);
        }
        return narratables;
    }
}
