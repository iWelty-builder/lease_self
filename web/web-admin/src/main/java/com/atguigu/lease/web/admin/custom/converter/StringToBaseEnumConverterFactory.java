package com.atguigu.lease.web.admin.custom.converter;

import com.atguigu.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumConverter<>(targetType);
    }

    private static class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            if (source == null || source.isEmpty()) {
                return null; // Or throw an IllegalArgumentException if null/empty is not allowed
            }
            for (T enumConstant : enumType.getEnumConstants()) {
                if (enumConstant.getCode().toString().equals(source)) {
                    return enumConstant;
                }
            }
            throw new IllegalArgumentException("No element with code " + source + " found in enum " + enumType.getName());
        }
    }
}
