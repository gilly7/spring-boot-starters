package org.shield.service.converter;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;

import org.shield.service.annotation.EmptyShouldBeNull;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 自动解码 header 中的中文
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public class HeaderDecoderConverter implements GenericConverter {

    private static final String START_WITH = "%";

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, String.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (ObjectUtils.isEmpty(source)) {
            return emptyShouldBeNull(source, targetType) ? null : source;
        }

        if (!shouldDecode(source, targetType)) {
            return source;
        }
        return convert(source.toString());
    }

    /**
     * 空字符串转为 null
     *
     * @param source
     * @param targetType
     * @return
     */
    private boolean emptyShouldBeNull(Object source, TypeDescriptor targetType) {
        EmptyShouldBeNull annotation = targetType.getAnnotation(EmptyShouldBeNull.class);
        if (annotation == null) {
            return false;
        }
        return true;
    }

    /**
     * 是否需要解码
     *
     * @param source
     * @param targetType
     * @return
     */
    private boolean shouldDecode(Object source, TypeDescriptor targetType) {
        RequestHeader requestHeader = targetType.getAnnotation(RequestHeader.class);
        Class<?> type = targetType.getType();
        if (requestHeader == null || type != String.class) {
            return false;
        }
        if (source.toString().indexOf(START_WITH) < 0) {
            return false;
        }
        if (StringUtils.isEmpty(requestHeader.name()) && StringUtils.isEmpty(requestHeader.value())) {
            return false;
        }
        return true;
    }

    /**
     * 解码
     *
     * @param source
     * @return
     */
    private String convert(final String source) {
        try {
            return URLDecoder.decode(source, Charset.forName("UTF-8").name());
        } catch (Exception e) {
            return source;
        }
    }
}
