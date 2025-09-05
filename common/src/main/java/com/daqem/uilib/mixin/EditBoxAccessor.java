package com.daqem.uilib.mixin;

import com.daqem.uilib.api.widget.IEditBoxWidget;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EditBox.class)
public abstract class EditBoxAccessor extends AbstractWidget implements IEditBoxWidget {

    @Shadow protected abstract void updateTextPosition();

    public EditBoxAccessor(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
    }

    @Override
    public void uilib$updateTextPosition() {
        updateTextPosition();
    }
}
