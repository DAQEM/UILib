package com.daqem.uilib.client.gui.component.selection;

import com.daqem.uilib.api.client.gui.component.scroll.ScrollOrientation;
import com.daqem.uilib.api.client.gui.component.selection.ISelectionItem;
import com.daqem.uilib.client.gui.component.AbstractComponent;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.SolidColorComponent;
import com.daqem.uilib.client.gui.component.advancement.AdvancementWindowComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollContentComponent;
import com.daqem.uilib.client.gui.component.scroll.ScrollPanelComponent;
import com.daqem.uilib.client.gui.text.Text;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.List;

public class SelectionListComponent extends AbstractComponent<SelectionListComponent> {

    private static final int BORDER_SIZE = 8;
    private static final int BORDER_SIZE_TOP = 17;
    private static final int BACKGROUND_COLOR = 0xFF1C1C1C;
    private static final int BACKGROUND_MARGIN = 2;

    private final Font font;
    private final List<ISelectionItem<ButtonComponent>> items;

    private SolidColorComponent backgroundComponent;
    private ScrollPanelComponent scrollPanelComponent;
    private AdvancementWindowComponent borderComponent;

    public SelectionListComponent(int x, int y, int width, int height, Font font, Component title, List<ISelectionItem<ButtonComponent>> items) {
        super(null, x, y, width, height);

        this.font = font;
        this.items = items;

        Text text = new Text(font, title, 8, 6);
        text.setTextColor(0x404040);
        this.setText(text);
    }

    @Override
    public void startRenderable() {
        this.backgroundComponent = new SolidColorComponent(
                BACKGROUND_MARGIN,
                BACKGROUND_MARGIN,
                this.getWidth() - BACKGROUND_MARGIN,
                this.getHeight() - BACKGROUND_MARGIN,
                BACKGROUND_COLOR);
        this.addChild(this.backgroundComponent);

        ScrollContentComponent scrollContentComponent = new ScrollContentComponent(0, 0, 0, ScrollOrientation.VERTICAL);
        this.items.forEach(iSelectionItem -> {
            ButtonComponent selectionItemComponent = new SelectionItemComponent(0, 0, this.getWidth() - BORDER_SIZE * 2, iSelectionItem.getHeight(), this.font, iSelectionItem);
            scrollContentComponent.addChild(selectionItemComponent);
            selectionItemComponent.setOnClickEvent(iSelectionItem.getOnClickEvent());
        });

        this.scrollPanelComponent = new ScrollPanelComponent(null, 8, 17, this.getWidth() - BORDER_SIZE * 2, this.getHeight() - BORDER_SIZE_TOP - BORDER_SIZE, ScrollOrientation.VERTICAL, scrollContentComponent);
        this.scrollPanelComponent.setZ(-1);
        this.addChild(this.scrollPanelComponent);

        this.borderComponent = new AdvancementWindowComponent(0, 0, this.getWidth(), this.getHeight());
        this.borderComponent.setZ(9);
        this.addChild(this.borderComponent);

        super.startRenderable();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
    }
}
