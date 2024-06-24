package com.daqem.uilib.client.util;

import com.mojang.math.Divisor;
import it.unimi.dsi.fastutil.ints.IntIterator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GuiGraphicsUtils {

    public static void blitNineSliced(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int m, int n, int o, int p, int q) {
        blitNineSliced(guiGraphics,resourceLocation, i, j, k, l, m, m, m, m, n, o, p, q);
    }

    public static void blitNineSliced(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int m, int n, int o, int p, int q, int r, int s, int t) {
        m = Math.min(m, k / 2);
        o = Math.min(o, k / 2);
        n = Math.min(n, l / 2);
        p = Math.min(p, l / 2);
        if (k == q && l == r) {
            guiGraphics.blit(resourceLocation, i, j, s, t, k, l);
        } else if (l == r) {
            guiGraphics.blit(resourceLocation, i, j, s, t, m, l);
            blitRepeating(guiGraphics, resourceLocation, i + m, j, k - o - m, l, s + m, t, q - o - m, r);
            guiGraphics.blit(resourceLocation, i + k - o, j, s + q - o, t, o, l);
        } else if (k == q) {
            guiGraphics.blit(resourceLocation, i, j, s, t, k, n);
            blitRepeating(guiGraphics, resourceLocation, i, j + n, k, l - p - n, s, t + n, q, r - p - n);
            guiGraphics.blit(resourceLocation, i, j + l - p, s, t + r - p, k, p);
        } else {
            guiGraphics.blit(resourceLocation, i, j, s, t, m, n);
            blitRepeating(guiGraphics, resourceLocation, i + m, j, k - o - m, n, s + m, t, q - o - m, n);
            guiGraphics.blit(resourceLocation, i + k - o, j, s + q - o, t, o, n);
            guiGraphics.blit(resourceLocation, i, j + l - p, s, t + r - p, m, p);
            blitRepeating(guiGraphics, resourceLocation, i + m, j + l - p, k - o - m, p, s + m, t + r - p, q - o - m, p);
            guiGraphics.blit(resourceLocation, i + k - o, j + l - p, s + q - o, t + r - p, o, p);
            blitRepeating(guiGraphics, resourceLocation, i, j + n, m, l - p - n, s, t + n, m, r - p - n);
            blitRepeating(guiGraphics, resourceLocation, i + m, j + n, k - o - m, l - p - n, s + m, t + n, q - o - m, r - p - n);
            blitRepeating(guiGraphics, resourceLocation, i + k - o, j + n, m, l - p - n, s + q - o, t + n, o, r - p - n);
        }
    }

    public static void blitRepeating(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int m, int n, int o, int p) {
        int q = i;

        int r;
        for(IntIterator intIterator = slices(k, o); intIterator.hasNext(); q += r) {
            r = intIterator.nextInt();
            int s = (o - r) / 2;
            int t = j;

            int u;
            for(IntIterator intIterator2 = slices(l, p); intIterator2.hasNext(); t += u) {
                u = intIterator2.nextInt();
                int v = (p - u) / 2;
                guiGraphics.blit(resourceLocation, q, t, m + s, n + v, r, u);
            }
        }
    }

    private static IntIterator slices(int i, int j) {
        int k = Mth.positiveCeilDiv(i, j);
        return new Divisor(i, k);
    }
}
