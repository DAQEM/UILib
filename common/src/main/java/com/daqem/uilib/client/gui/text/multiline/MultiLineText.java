package com.daqem.uilib.client.gui.text.multiline;

import com.daqem.uilib.client.gui.text.AbstractText;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public class MultiLineText extends AbstractText<MultiLineText> {

    private int maxWidth;
    private boolean hasChange = false;
    private List<FormattedCharSequence> lines;

    public MultiLineText(Font font, Component text, int x, int y, int maxWidth) {
        super(font, text, x, y);
        this.maxWidth = maxWidth;
        this.lines = getVisualOrder(findOptimalLines(font, text, maxWidth));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        if (hasChange) {
            List<FormattedText> optimalLines = findOptimalLines(getFont(), getText(), getMaxWidth());
            setLines(getVisualOrder(optimalLines));
            hasChange = false;
        }

        for (int i1 = 0; i1 < getLines().size(); i1++) {
            FormattedCharSequence line = getLines().get(i1);
            graphics.drawString(getFont(),line, 0, i1 * 9, 0x000000, false);
        }
    }

    private List<FormattedText> findOptimalLines(Font font, Component text, int maxWidth) {
        return MultiLineUtils.findOptimalLines(font, text, maxWidth);
    }

    private List<FormattedCharSequence> getVisualOrder(List<FormattedText> lines) {
        return Language.getInstance().getVisualOrder(lines);
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        hasChange = true;
    }

    @Override
    public void setText(Component text) {
        super.setText(text);
        hasChange = true;
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        hasChange = true;
    }

    public List<FormattedCharSequence> getLines() {
        return lines;
    }

    public void setLines(List<FormattedCharSequence> lines) {
        this.lines = lines;
    }
}
