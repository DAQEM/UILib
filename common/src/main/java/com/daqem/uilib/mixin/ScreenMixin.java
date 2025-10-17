package com.daqem.uilib.mixin;

import com.daqem.uilib.api.screen.IScreen;
import com.daqem.uilib.gui.AbstractScreen;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
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

    @Redirect(
            method = "*",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/gui/screens/Screen;narratables:Ljava/util/List;",
                    opcode = Opcodes.GETFIELD
            )
    )
    private List<NarratableEntry> redirectNarratables(Screen instance) {
        if ((Screen) (Object) this instanceof IScreen screen) {
            Set<NarratableEntry> widgets = new HashSet<>(narratables);
            for (GuiEventListener widget : screen.children()) {
                if (widget instanceof NarratableEntry narratableEntry) {
                    widgets.add(narratableEntry);
                }
            }
            return new ArrayList<>(widgets);
        }
        return narratables;
    }
}
