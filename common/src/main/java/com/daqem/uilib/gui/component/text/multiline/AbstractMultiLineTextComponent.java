package com.daqem.uilib.gui.component.text.multiline;

import com.daqem.uilib.gui.component.text.AbstractTextComponent;
import net.minecraft.client.StringSplitter;
import net.minecraft.client.gui.Font;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractMultiLineTextComponent extends AbstractTextComponent {

    private int maxWidth;
    private List<FormattedCharSequence> lines;

    public AbstractMultiLineTextComponent(int x, int y, int maxWidth, Component text) {
        super(x, y, 0, 0, text);
        this.maxWidth = maxWidth;
        updateSize();
    }

    public AbstractMultiLineTextComponent(int x, int y, int maxWidth, Component text, int color) {
        super(x, y, 0, 0, text, color);
        this.maxWidth = maxWidth;
        updateSize();
    }

    public AbstractMultiLineTextComponent(int x, int y, int maxWidth, Component text, boolean preformSizeUpdate) {
        super(x, y, 0, 0, text);
        this.maxWidth = maxWidth;
        if (preformSizeUpdate) updateSize();
    }

    public AbstractMultiLineTextComponent(int x, int y, int maxWidth, Component text, int color, boolean preformSizeUpdate) {
        super(x, y, 0, 0, text, color);
        this.maxWidth = maxWidth;
        if (preformSizeUpdate) updateSize();
    }

    public List<FormattedCharSequence> getLines() {
        return lines;
    }

    public void setLines(List<FormattedCharSequence> lines) {
        this.lines = lines;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        updateSize();
    }

    protected void updateSize() {
        this.lines = Language.getInstance().getVisualOrder(findOptimalLines(getFont(), getText(), maxWidth));
        setWidth(lines.stream().mapToInt(getFont()::width).max().orElse(0));
        setHeight(getFont().lineHeight * getLines().size());
    }

    protected int getUnusedSpaceX() {
        switch (getTextAlign()) {
            case CENTER -> {
                return (getWidth() - getMaxWidth()) / 2;
            }
            case RIGHT -> {
                return getWidth() - getMaxWidth();
            }
            default -> {
                return 0;
            }
        }
    }

    protected static List<FormattedText> findOptimalLines(Font font, Component component, int width) {
        StringSplitter stringSplitter = font.getSplitter();
        List<FormattedText> optimalLines = null;
        float minDifference = Float.MAX_VALUE;
        float tolerance = 10.0F;
        int offset = 0;

        List<FormattedText> lines = stringSplitter.splitLines(component, width - offset, Style.EMPTY);
        float difference = Math.abs(getMaxWidth(stringSplitter, lines) - (float) width);
        if (difference <= tolerance) {
            return lines;
        }

        if (difference < minDifference) {
            optimalLines = lines;
        }

        return optimalLines;
    }


    private static float getMaxWidth(@NotNull StringSplitter stringSplitter, List<FormattedText> formattedTexts) {
        return (float) formattedTexts.stream().mapToDouble(stringSplitter::stringWidth).max().orElse(0.0F);
    }
}
