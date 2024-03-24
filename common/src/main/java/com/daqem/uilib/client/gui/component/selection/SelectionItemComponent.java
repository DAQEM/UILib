package com.daqem.uilib.client.gui.component.selection;

import com.daqem.uilib.api.client.gui.component.selection.ISelectionItem;
import com.daqem.uilib.client.gui.component.ButtonComponent;
import com.daqem.uilib.client.gui.component.TextComponent;
import com.daqem.uilib.client.gui.text.TruncatedText;
import com.daqem.uilib.client.gui.texture.Textures;
import net.minecraft.client.gui.Font;

public class SelectionItemComponent extends ButtonComponent {

    private final Font font;
    private final ISelectionItem<ButtonComponent> selectionItem;

    private TextComponent name;
    private TextComponent description;

    public SelectionItemComponent(int x, int y, int width, int height, Font font, ISelectionItem<ButtonComponent> selectionItem) {
        super(Textures.SCROLL_BAR_BACKGROUND, x, y, width, height);
        this.font = font;
        this.selectionItem = selectionItem;
    }

    @Override
    public void startRenderable() {
        TruncatedText nameText = new TruncatedText(font, selectionItem.getName(), 0, 0, this.getWidth(), font.lineHeight);
        TruncatedText descriptionText = new TruncatedText(font, selectionItem.getDescription(), 0, 0, this.getWidth(), font.lineHeight);

        nameText.setTextColor(0x4444FF);

        this.name = new TextComponent(4, 4, nameText);
        this.description = new TextComponent(4, 4 + font.lineHeight, descriptionText);

        this.addChildren(this.name, this.description);

        super.startRenderable();
    }
}
