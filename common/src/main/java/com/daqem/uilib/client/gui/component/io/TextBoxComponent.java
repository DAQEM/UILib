package com.daqem.uilib.client.gui.component.io;

import com.daqem.uilib.api.client.gui.component.io.IIOComponent;
import com.daqem.uilib.api.client.gui.component.io.IInputValidatable;
import com.daqem.uilib.client.UILibClient;
import com.daqem.uilib.client.gui.component.AbstractSpriteComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TextBoxComponent extends AbstractSpriteComponent<TextBoxComponent> implements IIOComponent<TextBoxComponent>, IInputValidatable {

    private static final LinkedList<ResourceLocation> DEFAULT_SPRITES = new LinkedList<>(List.of(
            ResourceLocation.withDefaultNamespace("widget/text_field"),
            ResourceLocation.withDefaultNamespace("widget/text_field_highlighted"),
            ResourceLocation.fromNamespaceAndPath(UILibClient.MOD_ID, "widget/text_field_error")
    ));

    private static final int CURSOR_INSERT_COLOR = -3092272;
    private static final String CURSOR_APPEND_CHARACTER = "_";
    public static final int DEFAULT_TEXT_COLOR = 0xE0E0E0;
    private static final int CURSOR_BLINK_INTERVAL_MS = 300;

    private final Font font;
    private String value;
    private int maxLength = 32;
    private boolean bordered = true;
    private boolean canLoseFocus = true;
    private boolean isEditable = true;
    private int displayPos;
    private int cursorPos;
    private int highlightPos;
    private int textColor = DEFAULT_TEXT_COLOR;
    private int textColorUneditable = 0x707070;
    @Nullable
    private String suggestion;
    @Nullable
    private Consumer<String> responder;
    private Predicate<String> filter = Objects::nonNull;
    private List<Component> validationErrors = new ArrayList<>();
    private BiFunction<String, Integer, FormattedCharSequence> formatter = (string, integer) -> FormattedCharSequence.forward(string, Style.EMPTY);
    @Nullable
    private Component hint;
    private long focusedTime = Util.getMillis();

    public TextBoxComponent(int x, int y, int width, int height, String value) {
        this(DEFAULT_SPRITES, x, y, width, height, value);
    }

    public TextBoxComponent(LinkedList<ResourceLocation> sprites, int x, int y, int width, int height, String value) {
        super(sprites, x, y, width, height);
        this.value = value;
        this.font = Minecraft.getInstance().font;

        setInputValidationErrors(this.validateInput(value));
    }

    public void setResponder(@Nullable Consumer<String> consumer) {
        this.responder = consumer;
    }

    public void setFormatter(BiFunction<String, Integer, FormattedCharSequence> biFunction) {
        this.formatter = biFunction;
    }

    public void setValue(String string) {
        if (!this.filter.test(string)) {
            return;
        }
        this.value = string.length() > this.maxLength ? string.substring(0, this.maxLength) : string;
        this.moveCursorToEnd(false);
        this.setHighlightPos(this.cursorPos);
        this.onValueChange(string);
    }

    public String getValue() {
        return this.value;
    }

    public String getHighlighted() {
        int i = Math.min(this.cursorPos, this.highlightPos);
        int j = Math.max(this.cursorPos, this.highlightPos);
        return this.value.substring(i, j);
    }

    public void setFilter(Predicate<String> predicate) {
        this.filter = predicate;
    }

    public void insertText(String string) {
        String string3;
        int i = Math.min(this.cursorPos, this.highlightPos);
        int j = Math.max(this.cursorPos, this.highlightPos);
        int k = this.maxLength - this.value.length() - (i - j);
        if (k <= 0) {
            return;
        }
        String string2 = StringUtil.filterText(string);
        int l = string2.length();
        if (k < l) {
            if (Character.isHighSurrogate(string2.charAt(k - 1))) {
                --k;
            }
            string2 = string2.substring(0, k);
            l = k;
        }
        if (!this.filter.test(string3 = new StringBuilder(this.value).replace(i, j, string2).toString())) {
            return;
        }
        this.value = string3;
        this.setCursorPosition(i + l);
        this.setHighlightPos(this.cursorPos);
        this.onValueChange(this.value);
    }

    private void onValueChange(String string) {
        if (this.responder != null) {
            this.responder.accept(string);
        }
        setInputValidationErrors(this.validateInput(string));
    }

    private void deleteText(int i) {
        if (Screen.hasControlDown()) {
            this.deleteWords(i);
        } else {
            this.deleteChars(i);
        }
    }

    public void deleteWords(int i) {
        if (this.value.isEmpty()) {
            return;
        }
        if (this.highlightPos != this.cursorPos) {
            this.insertText("");
            return;
        }
        this.deleteCharsToPos(this.getWordPosition(i));
    }

    public void deleteChars(int i) {
        this.deleteCharsToPos(this.getCursorPos(i));
    }

    public void deleteCharsToPos(int i) {
        int k;
        if (this.value.isEmpty()) {
            return;
        }
        if (this.highlightPos != this.cursorPos) {
            this.insertText("");
            return;
        }
        int j = Math.min(i, this.cursorPos);
        if (j == (k = Math.max(i, this.cursorPos))) {
            return;
        }
        String string = new StringBuilder(this.value).delete(j, k).toString();
        if (!this.filter.test(string)) {
            return;
        }
        this.value = string;
        this.moveCursorTo(j, false);
    }

    public int getWordPosition(int i) {
        return this.getWordPosition(i, this.getCursorPosition());
    }

    private int getWordPosition(int i, int j) {
        return this.getWordPosition(i, j, true);
    }

    private int getWordPosition(int i, int j, boolean bl) {
        int k = j;
        boolean bl2 = i < 0;
        int l = Math.abs(i);
        for (int m = 0; m < l; ++m) {
            if (bl2) {
                while (bl && k > 0 && this.value.charAt(k - 1) == ' ') {
                    --k;
                }
                while (k > 0 && this.value.charAt(k - 1) != ' ') {
                    --k;
                }
                continue;
            }
            int n = this.value.length();
            if ((k = this.value.indexOf(32, k)) == -1) {
                k = n;
                continue;
            }
            while (bl && k < n && this.value.charAt(k) == ' ') {
                ++k;
            }
        }
        return k;
    }

    public void moveCursor(int i, boolean bl) {
        this.moveCursorTo(this.getCursorPos(i), bl);
    }

    private int getCursorPos(int i) {
        return Util.offsetByCodepoints(this.value, this.cursorPos, i);
    }

    public void moveCursorTo(int i, boolean bl) {
        this.setCursorPosition(i);
        if (!bl) {
            this.setHighlightPos(this.cursorPos);
        }
        this.onValueChange(this.value);
    }

    public void setCursorPosition(int i) {
        this.cursorPos = Mth.clamp(i, 0, this.value.length());
        this.scrollTo(this.cursorPos);
    }

    public void moveCursorToStart(boolean bl) {
        this.moveCursorTo(0, bl);
    }

    public void moveCursorToEnd(boolean bl) {
        this.moveCursorTo(this.value.length(), bl);
    }

    @Override
    public boolean preformOnKeyPressedEvent(int keyCode, int scanCode, int modifiers) {
        if (!this.isVisible() || !this.isFocused()) {
            return false;
        }
        switch (keyCode) {
            case GLFW.GLFW_KEY_LEFT: {
                if (Screen.hasControlDown()) {
                    this.moveCursorTo(this.getWordPosition(-1), Screen.hasShiftDown());
                } else {
                    this.moveCursor(-1, Screen.hasShiftDown());
                }
                return true;
            }
            case GLFW.GLFW_KEY_RIGHT: {
                if (Screen.hasControlDown()) {
                    this.moveCursorTo(this.getWordPosition(1), Screen.hasShiftDown());
                } else {
                    this.moveCursor(1, Screen.hasShiftDown());
                }
                return true;
            }
            case GLFW.GLFW_KEY_BACKSPACE: {
                if (this.isEditable) {
                    this.deleteText(-1);
                }
                return true;
            }
            case GLFW.GLFW_KEY_DELETE: {
                if (this.isEditable) {
                    this.deleteText(1);
                }
                return true;
            }
            case GLFW.GLFW_KEY_HOME: {
                this.moveCursorToStart(Screen.hasShiftDown());
                return true;
            }
            case GLFW.GLFW_KEY_END: {
                this.moveCursorToEnd(Screen.hasShiftDown());
                return true;
            }
        }
        if (Screen.isSelectAll(keyCode)) {
            this.moveCursorToEnd(false);
            this.setHighlightPos(0);
            return true;
        }
        if (Screen.isCopy(keyCode)) {
            Minecraft.getInstance().keyboardHandler.setClipboard(this.getHighlighted());
            return true;
        }
        if (Screen.isPaste(keyCode)) {
            if (this.isEditable()) {
                this.insertText(Minecraft.getInstance().keyboardHandler.getClipboard());
            }
            return true;
        }
        if (Screen.isCut(keyCode)) {
            Minecraft.getInstance().keyboardHandler.setClipboard(this.getHighlighted());
            if (this.isEditable()) {
                this.insertText("");
            }
            return true;
        }
        return false;
    }

    public boolean canConsumeInput() {
        return this.isVisible() && this.isFocused() && this.isEditable();
    }


    @Override
    public boolean preformOnCharTypedEvent(char typedChar, int modifiers) {
        if (!this.canConsumeInput()) {
            return false;
        }
        if (StringUtil.isAllowedChatCharacter(typedChar)) {
            if (this.isEditable) {
                this.insertText(Character.toString(typedChar));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean preformOnClickEvent(double mouseX, double mouseY, int button) {
        if (!this.isVisible()) {
            return false;
        }
        if (isTotalHovered(mouseX, mouseY)) {
            setFocused(true);
            int i = Mth.floor(mouseX) - this.getTotalX();
            if (this.bordered) {
                i -= 4;
            }
            String string = this.font.plainSubstrByWidth(this.value.substring(this.displayPos), this.getInnerWidth());
            this.moveCursorTo(this.font.plainSubstrByWidth(string, i).length() + this.displayPos, Screen.hasShiftDown());
        }
        return super.preformOnClickEvent(mouseX, mouseY, button);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (this.isBordered()) {
            ResourceLocation resourceLocation = hasInputValidationErrors() ? getSprite(2) : isFocused() ? getSprite(1) : getSprite(0);
            guiGraphics.blitSprite(resourceLocation, 0, 0, this.getWidth(), this.getHeight());
        }
        int k = this.isEditable ? this.textColor : this.textColorUneditable;
        int l = this.cursorPos - this.displayPos;
        String string = this.font.plainSubstrByWidth(this.value.substring(this.displayPos), this.getInnerWidth());
        boolean bl = l >= 0 && l <= string.length();
        boolean bl2 = this.isFocused() && (Util.getMillis() - this.focusedTime) / CURSOR_BLINK_INTERVAL_MS % 2L == 0L && bl;
        int m = this.bordered ? 4 : 0;
        int n = this.bordered ? (getHeight() - 8) / 2 : 0;
        int o = m;
        int p = Mth.clamp(this.highlightPos - this.displayPos, 0, string.length());
        if (!string.isEmpty()) {
            String string2 = bl ? string.substring(0, l) : string;
            o = guiGraphics.drawString(this.font, this.formatter.apply(string2, this.displayPos), o, n, k);
        }
        boolean bl3 = this.cursorPos < this.value.length() || this.value.length() >= this.getMaxLength();
        int q = o;
        if (!bl) {
            q = l > 0 ? m + getWidth() : m;
        } else if (bl3) {
            --q;
            --o;
        }
        if (!string.isEmpty() && bl && l < string.length()) {
            guiGraphics.drawString(this.font, this.formatter.apply(string.substring(l), this.cursorPos), o, n, k);
        }
        if (this.hint != null && string.isEmpty() && !this.isFocused()) {
            guiGraphics.drawString(this.font, this.hint, o, n, k);
        }
        if (!bl3 && this.suggestion != null) {
            guiGraphics.drawString(this.font, this.suggestion, q - 1, n, -8355712);
        }
        if (bl2) {
            if (bl3) {
                guiGraphics.fill(RenderType.guiOverlay(), q, n - 1, q + 1, n + 1 + this.font.lineHeight, CURSOR_INSERT_COLOR);
            } else {
                guiGraphics.drawString(this.font, CURSOR_APPEND_CHARACTER, q, n, k);
            }
        }
        if (p != l) {
            int r = m + this.font.width(string.substring(0, p));
            this.renderHighlight(guiGraphics, q, n - 1, r - 1, n + 1 + this.font.lineHeight);
        }
    }

    @Override
    public void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (isTotalHovered(mouseX, mouseY)) {
            this.renderInputValidationErrorsTooltip(guiGraphics, mouseX, mouseY);
        }
        super.renderTooltips(guiGraphics, mouseX, mouseY, delta);
    }

    private void renderHighlight(GuiGraphics guiGraphics, int i, int j, int k, int l) {
        int m;
        if (i < k) {
            m = i;
            i = k;
            k = m;
        }
        if (j < l) {
            m = j;
            j = l;
            l = m;
        }
        if (k > getWidth()) {
            k = getWidth();
        }
        if (i > getWidth()) {
            i = getWidth();
        }
        guiGraphics.fill(RenderType.guiTextHighlight(), i, j, k, l, -16776961);
    }

    public void setMaxLength(int i) {
        this.maxLength = i;
        if (this.value.length() > i) {
            this.value = this.value.substring(0, i);
            this.onValueChange(this.value);
        }
    }

    private int getMaxLength() {
        return this.maxLength;
    }

    public int getCursorPosition() {
        return this.cursorPos;
    }

    public boolean isBordered() {
        return this.bordered;
    }

    public void setBordered(boolean bl) {
        this.bordered = bl;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public void setTextColorUneditable(int i) {
        this.textColorUneditable = i;
    }

    @Override
    public void setFocused(boolean bl) {
        if (!this.canLoseFocus && !bl) {
            return;
        }
        super.setFocused(bl);
        if (bl) {
            this.focusedTime = Util.getMillis();
        }
    }

    private boolean isEditable() {
        return this.isEditable;
    }

    public void setEditable(boolean bl) {
        this.isEditable = bl;
    }

    public int getInnerWidth() {
        return this.isBordered() ? getWidth() - 8 : getWidth();
    }

    public void setHighlightPos(int i) {
        this.highlightPos = Mth.clamp(i, 0, this.value.length());
        this.scrollTo(this.highlightPos);
    }

    private void scrollTo(int i) {
        if (this.font == null) {
            return;
        }
        this.displayPos = Math.min(this.displayPos, this.value.length());
        int j = this.getInnerWidth();
        String string = this.font.plainSubstrByWidth(this.value.substring(this.displayPos), j);
        int k = string.length() + this.displayPos;
        if (i == this.displayPos) {
            this.displayPos -= this.font.plainSubstrByWidth(this.value, j, true).length();
        }
        if (i > k) {
            this.displayPos += i - k;
        } else if (i <= this.displayPos) {
            this.displayPos -= this.displayPos - i;
        }
        this.displayPos = Mth.clamp(this.displayPos, 0, this.value.length());
    }

    public void setCanLoseFocus(boolean bl) {
        this.canLoseFocus = bl;
    }

    public void setSuggestion(@Nullable String string) {
        this.suggestion = string;
    }

    public int getScreenX(int i) {
        if (i > this.value.length()) {
            return this.getTotalX();
        }
        return this.getTotalX() + this.font.width(this.value.substring(0, i));
    }

    public void setHint(@Nullable Component component) {
        this.hint = component;
    }

    @Override
    public String getStringValue() {
        return this.value;
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
