package com.daqem.uilib.client.util;

import com.daqem.uilib.api.client.gui.IGuiGraphics;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Divisor;
import it.unimi.dsi.fastutil.ints.IntIterator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;

public class GuiGraphicsUtils {

    public static void blitNineSliced(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int m, int n, int o, int p, int q, int color) {
        blitNineSliced(guiGraphics, resourceLocation, i, j, k, l, m, m, m, m, n, o, p, q, color);
    }

    public static void blitNineSliced(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int m, int n, int o, int p, int q, int r, int s, int t, int color) {
        m = Math.min(m, k / 2);
        o = Math.min(o, k / 2);
        n = Math.min(n, l / 2);
        p = Math.min(p, l / 2);
        if (k == q && l == r) {
            blit(guiGraphics, resourceLocation, i, j, s, t, k, l, color);
        } else if (l == r) {
            blit(guiGraphics, resourceLocation, i, j, s, t, m, l, color);
            blitRepeating(guiGraphics, resourceLocation, i + m, j, k - o - m, l, s + m, t, q - o - m, r, color);
            blit(guiGraphics, resourceLocation, i + k - o, j, s + q - o, t, o, l, color);
        } else if (k == q) {
            blit(guiGraphics, resourceLocation, i, j, s, t, k, n, color);
            blitRepeating(guiGraphics, resourceLocation, i, j + n, k, l - p - n, s, t + n, q, r - p - n, color);
            blit(guiGraphics, resourceLocation, i, j + l - p, s, t + r - p, k, p, color);
        } else {
            blit(guiGraphics, resourceLocation, i, j, s, t, m, n, color);
            blitRepeating(guiGraphics, resourceLocation, i + m, j, k - o - m, n, s + m, t, q - o - m, n, color);
            blit(guiGraphics, resourceLocation, i + k - o, j, s + q - o, t, o, n, color);
            blit(guiGraphics, resourceLocation, i, j + l - p, s, t + r - p, m, p, color);
            blitRepeating(guiGraphics, resourceLocation, i + m, j + l - p, k - o - m, p, s + m, t + r - p, q - o - m, p, color);
            blit(guiGraphics, resourceLocation, i + k - o, j + l - p, s + q - o, t + r - p, o, p, color);
            blitRepeating(guiGraphics, resourceLocation, i, j + n, m, l - p - n, s, t + n, m, r - p - n, color);
            blitRepeating(guiGraphics, resourceLocation, i + m, j + n, k - o - m, l - p - n, s + m, t + n, q - o - m, r - p - n, color);
            blitRepeating(guiGraphics, resourceLocation, i + k - o, j + n, m, l - p - n, s + q - o, t + n, o, r - p - n, color);
        }
    }

    public static void blitRepeating(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int m, int n, int o, int p, int color) {
        int q = i;

        int r;
        for (IntIterator intIterator = slices(k, o); intIterator.hasNext(); q += r) {
            r = intIterator.nextInt();
            int s = (o - r) / 2;
            int t = j;

            int u;
            for (IntIterator intIterator2 = slices(l, p); intIterator2.hasNext(); t += u) {
                u = intIterator2.nextInt();
                int v = (p - u) / 2;
                blit(guiGraphics, resourceLocation, q, t, m + s, n + v, r, u, color);
            }
        }
    }

    private static IntIterator slices(int i, int j) {
        int k = Mth.positiveCeilDiv(i, j);
        return new Divisor(i, k);
    }

    public static void blit(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int m, int n, int color) {
        blit(guiGraphics, resourceLocation, i, j, (float) k, l, m, n, 256, 256, color);
    }

    public static void blit(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, float f, float g, int l, int m, int n, int o, int color) {
        blit(guiGraphics, resourceLocation, i, i + l, j, j + m, l, m, f, g, n, o, color);
    }

    public static void blit(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, float f, float g, int m, int n, int o, int p, int color) {
        blit(guiGraphics, resourceLocation, i, i + k, j, j + l, m, n, f, g, o, p, color);
    }

//    public static void blit(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, float f, float g, int k, int l, int m, int n, int color) {
//        blit(guiGraphics, resourceLocation, i, j, k, l, f, g, k, l, m, n, color);
//    }

    public static void blit(GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, int n, int o, float f, float g, int p, int q, int color) {
        innerBlit(guiGraphics, resourceLocation, i, j, k, l, (f + 0.0f) / (float) p, (f + (float) n) / (float) p, (g + 0.0f) / (float) q, (g + (float) o) / (float) q, color);
    }

    private static void innerBlit(
            GuiGraphics guiGraphics, ResourceLocation resourceLocation, int i, int j, int k, int l, float f, float g, float h, float n, int color
    ) {
        RenderType renderType = RenderType.guiTextured(resourceLocation);
        Matrix4f matrix4f = guiGraphics.pose().last().pose();
        VertexConsumer vertexConsumer = ((IGuiGraphics) guiGraphics).uilib$getBufferSource().getBuffer(renderType);
        vertexConsumer.addVertex(matrix4f, (float) i, (float) k, 0.0F).setUv(f, h).setColor(color);
        vertexConsumer.addVertex(matrix4f, (float) i, (float) l, 0.0F).setUv(f, n).setColor(color);
        vertexConsumer.addVertex(matrix4f, (float) j, (float) l, 0.0F).setUv(g, n).setColor(color);
        vertexConsumer.addVertex(matrix4f, (float) j, (float) k, 0.0F).setUv(g, h).setColor(color);
    }
}
