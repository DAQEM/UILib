package com.daqem.uilib.client.gui.text.multiline;

import net.minecraft.client.StringSplitter;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MultiLineUtils {

    public static List<FormattedText> findOptimalLines(Font font, Component component, int width) {
        StringSplitter stringSplitter = font.getSplitter();
        List<FormattedText> optimalLines = null;
        float minDifference = Float.MAX_VALUE;
        float tolerance = 10.0F;
        int offset = 5;

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


    public static float getMaxWidth(@NotNull StringSplitter stringSplitter, List<FormattedText> formattedTexts) {
        return (float) formattedTexts.stream().mapToDouble(stringSplitter::stringWidth).max().orElse(0.0F);
    }
}
