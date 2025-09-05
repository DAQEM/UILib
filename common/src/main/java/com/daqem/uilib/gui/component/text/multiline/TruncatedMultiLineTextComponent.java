package com.daqem.uilib.gui.component.text.multiline;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;

public class TruncatedMultiLineTextComponent extends AbstractMultiLineTextComponent{

    private int maxLines;
    private String ellipsis = "...";

    public TruncatedMultiLineTextComponent(int x, int y, int maxWidth, int maxLines, Component text) {
        super(x, y, maxWidth, text, false);
        this.maxLines = maxLines;
        updateSize();
    }

    public TruncatedMultiLineTextComponent(int x, int y, int maxWidth, int maxLines, Component text, int color) {
        super(x, y, maxWidth, text, color, false);
        this.maxLines = maxLines;
        updateSize();
    }

    public int getMaxLines() {
        return maxLines;
    }

    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    public String getEllipsis() {
        return ellipsis;
    }

    public void setEllipsis(String ellipsis) {
        this.ellipsis = ellipsis;
    }

    @Override
    protected void updateSize() {
        setLines(Language.getInstance().getVisualOrder(findOptimalLines(getFont(), getText(), getMaxWidth())));
        if (getLines().size() > maxLines) {
            List<FormattedCharSequence> lines = new ArrayList<>(getLines());
            lines.subList(maxLines, lines.size()).clear();
            setLines(lines);
            int lastIndex = getLines().size() - 1;
            if (lastIndex < 0) return;

            // Extract string from the last FormattedCharSequence
            FormattedCharSequence lastLine = getLines().get(lastIndex);
            StringBuilder builder = new StringBuilder();
            lastLine.accept((index, style, codePoint) -> {
                builder.appendCodePoint(codePoint);
                return true;
            });
            String truncatedLine = builder.toString();

            // Truncate the line to fit within maxWidth, accounting for ellipsis
            while (getFont().width(truncatedLine + ellipsis) > getMaxWidth() && !truncatedLine.isEmpty()) {
                truncatedLine = truncatedLine.substring(0, truncatedLine.length() - 1);
            }

            // Replace the last line with the truncated line plus ellipsis
            getLines().set(lastIndex, Component.literal(truncatedLine + ellipsis).withStyle(Style.EMPTY).getVisualOrderText());
        }

        // Update width and height
        setWidth(getLines().stream().mapToInt(getFont()::width).max().orElse(0));
        setHeight(getFont().lineHeight * getLines().size());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, int parentWidth, int parentHeight) {
        for (int i = 0; i < getLines().size(); i++) {
            guiGraphics.drawString(
                    getFont(),
                    getLines().get(i),
                    getTotalX(),
                    getTotalY() + i * getFont().lineHeight,
                    getColor(),
                    isDrawShadow()
            );
        }

        if (isRenderDebugBorder()) {
            guiGraphics.hLine(getTotalX() + getUnusedSpaceX(), getTotalX() + getMaxWidth() + getUnusedSpaceX() - 1, getTotalY(), 0xFF0000FF);
            guiGraphics.vLine(getTotalX() + getMaxWidth() + getUnusedSpaceX() - 1, getTotalY(), getTotalY() + getHeight() - 1, 0xFF0000FF);
            guiGraphics.hLine(getTotalX() + getUnusedSpaceX(), getTotalX() + getMaxWidth() + getUnusedSpaceX() - 1, getTotalY() + getHeight() - 1, 0xFF0000FF);
            guiGraphics.vLine(getTotalX() + getUnusedSpaceX(), getTotalY(), getTotalY() + getHeight() - 1, 0xFF0000FF);
        }
    }
}
