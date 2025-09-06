package com.daqem.uilib.util;

import com.daqem.uilib.UILib;
import net.minecraft.network.chat.MutableComponent;

public class ValidationErrors {

    public static MutableComponent minLength(int length) {
        return UILib.translatable("widget.validation_error.min_length", length);
    }

    public static MutableComponent maxLength(int length) {
        return UILib.translatable("widget.validation_error.max_length", length);
    }

    public static MutableComponent pattern(String pattern) {
        return UILib.translatable("widget.validation_error.pattern", pattern);
    }

    public static MutableComponent validValues(Object values) {
        return UILib.translatable("widget.validation_error.valid_values", values);
    }

    public static MutableComponent minValue(Number value) {
        return UILib.translatable("widget.validation_error.min_value", value);
    }

    public static MutableComponent maxValue(Number value) {
        return UILib.translatable("widget.validation_error.max_value", value);
    }

    public static MutableComponent invalidNumber() {
        return UILib.translatable("widget.validation_error.invalid_number");
    }

    public static MutableComponent invalidDateTime(String format) {
        return UILib.translatable("widget.validation_error.invalid_date_time", format);
    }

    public static MutableComponent invalidResourceLocation() {
        return UILib.translatable("widget.validation_error.invalid_resource_location");
    }

    public static MutableComponent invalidRegistryValue() {
        return UILib.translatable("widget.validation_error.invalid_registry_value");
    }

    public static MutableComponent duplicateKey() {
        return UILib.translatable("widget.validation_error.duplicate_key");
    }

    public static MutableComponent emptyKey() {
        return UILib.translatable("widget.validation_error.empty_key");
    }
}
