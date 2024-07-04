package com.daqem.uilib.client.gui.component.io;

import com.daqem.uilib.api.client.gui.component.io.IIOComponent;
import com.daqem.uilib.api.client.gui.component.io.IInputValidatable;
import com.daqem.uilib.client.UILibClient;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.MultilineTextField;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MultiLineTextBoxComponent extends AbstractScrollComponent<MultiLineTextBoxComponent> implements IIOComponent<MultiLineTextBoxComponent>, IInputValidatable {

    protected static final LinkedList<ResourceLocation> DEFAULT_SPRITES = new LinkedList<>(List.of(
            ResourceLocation.withDefaultNamespace("widget/text_field"),
            ResourceLocation.withDefaultNamespace("widget/text_field_highlighted"),
            ResourceLocation.fromNamespaceAndPath(UILibClient.MOD_ID, "widget/text_field_error"),
            ResourceLocation.withDefaultNamespace("widget/scroller")
    ));

    private static final int CURSOR_INSERT_COLOR = -3092272;
    private static final String CURSOR_APPEND_CHARACTER = "_";
    private static final int TEXT_COLOR = -2039584;
    private static final int CURSOR_BLINK_INTERVAL_MS = 300;
    private final Font font;
    private final MultilineTextField textField;
    private long focusedTime = Util.getMillis();
    private List<Component> validationErrors = new ArrayList<>();

    public MultiLineTextBoxComponent(int x, int y, int width, int height) {
        this(DEFAULT_SPRITES, x, y, width, height);
    }

    public MultiLineTextBoxComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height) {
        this(sprites, x, y, width, height, "");
    }

    public MultiLineTextBoxComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height, String value) {
        super(sprites, x, y, width, height);
        this.font = Minecraft.getInstance().font;
        this.textField = new MultilineTextField(font, width - this.totalInnerPadding());
        this.textField.setValue(value);
        this.textField.setCursorListener(this::scrollToCursor);

        setInputValidationErrors(this.validateInput(value));
    }

    public void setCharacterLimit(int i) {
        this.textField.setCharacterLimit(i);
    }

    public void setValueListener(Consumer<String> consumer) {
        this.textField.setValueListener(consumer);
    }

    public void setValue(String string) {
        this.textField.setValue(string);
    }

    public String getValue() {
        return this.textField.value();
    }

    @Override
    public boolean preformOnClickEvent(double mouseX, double mouseY, int button) {
        if (this.withinContentAreaPoint(mouseX, mouseY) && button == 0) {
            this.textField.setSelecting(Screen.hasShiftDown());
            setFocused(true);
            this.seekCursorScreen(mouseX, mouseY);
            return true;
        }
        return super.preformOnClickEvent(mouseX, mouseY, button);
    }

    @Override
    public boolean preformOnDragEvent(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (super.preformOnDragEvent(mouseX, mouseY, button, dragX, dragY)) {
            return true;
        }
        if (this.withinContentAreaPoint(mouseX, mouseY) && button == 0) {
            this.textField.setSelecting(true);
            this.seekCursorScreen(mouseX, mouseY);
            this.textField.setSelecting(Screen.hasShiftDown());
            return true;
        }
        return false;
    }

    @Override
    public boolean preformOnKeyPressedEvent(int keyCode, int scanCode, int modifiers) {
        if (!isVisible() || !isFocused()) {
            return false;
        }
        return this.textField.keyPressed(keyCode);
    }

    @Override
    public boolean preformOnCharTypedEvent(char typedChar, int modifiers) {
        if (!(this.isVisible() && this.isFocused() && StringUtil.isAllowedChatCharacter(typedChar))) {
            return false;
        }
        this.textField.insertText(Character.toString(typedChar));
        setInputValidationErrors(this.validateInput(getValue()));
        return true;
    }

    @Override
    protected void renderContents(GuiGraphics guiGraphics, int i, int j, float f) {
        String string = this.textField.value();
        if (string.isEmpty() && !this.isFocused()) {
            return;
        }
        int k = this.textField.cursor();
        boolean bl = this.isFocused() && (Util.getMillis() - this.focusedTime) / CURSOR_BLINK_INTERVAL_MS % 2L == 0L;
        boolean bl2 = k < string.length();
        int l = 0;
        int m = 0;
        int n = this.innerPadding();
        for (MultilineTextField.StringView stringView : this.textField.iterateLines()) {
            boolean bl3 = this.withinContentAreaTopBottom(n, n + this.font.lineHeight);
            if (bl && bl2 && k >= stringView.beginIndex() && k <= stringView.endIndex()) {
                if (bl3) {
                    l = guiGraphics.drawString(this.font, string.substring(stringView.beginIndex(), k), this.innerPadding(), n, TEXT_COLOR) - 1;
                    guiGraphics.fill(l, n - 1, l + 1, n + 1 + this.font.lineHeight, CURSOR_INSERT_COLOR);
                    guiGraphics.drawString(this.font, string.substring(k, stringView.endIndex()), l, n, TEXT_COLOR);
                }
            } else {
                if (bl3) {
                    l = guiGraphics.drawString(this.font, string.substring(stringView.beginIndex(), stringView.endIndex()), this.innerPadding(), n, TEXT_COLOR) - 1;
                }
                m = n;
            }
            n += this.font.lineHeight;
        }
        if (bl && !bl2 && this.withinContentAreaTopBottom(m, m + this.font.lineHeight)) {
            guiGraphics.drawString(this.font, CURSOR_APPEND_CHARACTER, l, m, CURSOR_INSERT_COLOR);
        }
        if (this.textField.hasSelection()) {
            MultilineTextField.StringView stringView2 = this.textField.getSelected();
            int o = this.innerPadding();
            n = this.innerPadding();
            for (MultilineTextField.StringView stringView3 : this.textField.iterateLines()) {
                if (stringView2.beginIndex() > stringView3.endIndex()) {
                    n += this.font.lineHeight;
                    continue;
                }
                if (stringView3.beginIndex() > stringView2.endIndex()) break;
                if (this.withinContentAreaTopBottom(n, n + this.font.lineHeight)) {
                    int p = this.font.width(string.substring(stringView3.beginIndex(), Math.max(stringView2.beginIndex(), stringView3.beginIndex())));
                    int q = stringView2.endIndex() > stringView3.endIndex() ? getWidth() - this.innerPadding() : this.font.width(string.substring(stringView3.beginIndex(), stringView2.endIndex()));
                    this.renderHighlight(guiGraphics, o + p, n, o + q, n + this.font.lineHeight);
                }
                n += this.font.lineHeight;
            }
        }
    }

    @Override
    public void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (isTotalHovered(mouseX, mouseY)) {
            this.renderInputValidationErrorsTooltip(guiGraphics, mouseX, mouseY);
        }
        super.renderTooltips(guiGraphics, mouseX, mouseY, delta);
    }

    @Override
    protected void renderDecorations(GuiGraphics guiGraphics) {
        super.renderDecorations(guiGraphics);
        if (this.textField.hasCharacterLimit()) {
            int i = this.textField.characterLimit();
            MutableComponent component = Component.translatable("gui.multiLineEditBox.character_limit", this.textField.value().length(), i);
            guiGraphics.drawString(this.font, component, getWidth() - this.font.width(component), getHeight() + innerPadding(), 0xA0A0A0);
        }
    }

    @Override
    protected ResourceLocation getBackgroundSprite() {
        return hasInputValidationErrors() ? getSprite(2) : isFocused() ? getSprite(1) : getSprite(0);
    }

    @Override
    protected ResourceLocation getScrollWheelSprite() {
        return getSprite(3);
    }

    @Override
    public int getInnerHeight() {
        return this.font.lineHeight * this.textField.getLineCount();
    }

    @Override
    protected boolean scrollbarVisible() {
        return (double) this.textField.getLineCount() > this.getDisplayableLineCount();
    }

    @Override
    protected double scrollRate() {
        return (double) this.font.lineHeight / 2.0;
    }

    private void renderHighlight(GuiGraphics guiGraphics, int i, int j, int k, int l) {
        guiGraphics.fill(RenderType.guiTextHighlight(), i, j, k, l, -16776961);
    }

    private void scrollToCursor() {
        double d = this.scrollAmount();
        MultilineTextField.StringView stringView = this.textField.getLineView((int) (d / (double) this.font.lineHeight));
        if (this.textField.cursor() <= stringView.beginIndex()) {
            d = this.textField.getLineAtCursor() * this.font.lineHeight;
        } else {
            MultilineTextField.StringView stringView2 = this.textField.getLineView((int) ((d + (double) getHeight()) / (double) this.font.lineHeight) - 1);
            if (this.textField.cursor() > stringView2.endIndex()) {
                d = this.textField.getLineAtCursor() * this.font.lineHeight - getHeight() + this.font.lineHeight + this.totalInnerPadding();
            }
        }
        this.setScrollAmount(d);
    }

    private double getDisplayableLineCount() {
        return (double) (getHeight() - this.totalInnerPadding()) / (double) this.font.lineHeight;
    }

    private void seekCursorScreen(double d, double e) {
        double f = d - (double) getTotalX() - (double) this.innerPadding();
        double g = e - (double) getTotalY() - (double) this.innerPadding() + this.scrollAmount();
        this.textField.seekCursorToPoint(f, g);
    }

    @Override
    public void setFocused(boolean bl) {
        super.setFocused(bl);
        if (bl) {
            this.focusedTime = Util.getMillis();
        }
    }

    @Override
    public String getStringValue() {
        return this.getValue();
    }

    @Override
    public List<Component> getInputValidationErrors() {
        return this.validationErrors;
    }

    @Override
    public void setInputValidationErrors(List<Component> errors) {
        this.validationErrors = errors;
    }
}
