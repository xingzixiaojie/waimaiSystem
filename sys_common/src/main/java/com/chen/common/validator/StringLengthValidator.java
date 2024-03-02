package com.chen.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * 字符串长度校验器
 */
public class StringLengthValidator extends ValidatorHandler<String> implements Validator<String> {

    /** 校验字段名 */
    private final String fieldName;

    /** 最小长度 */
    private final Integer minLength;

    /** 最大长度 */
    private final Integer maxLength;

    /**
     *  是否允许为空
     *  如果为空 则不做长度校验
     *  true 允许为空  false 不允许
     */
    private final boolean isAllowNull;

    public StringLengthValidator(String fieldName) {
        this(fieldName, false, 1, Integer.MAX_VALUE);
    }
    public StringLengthValidator(String fieldName, int minLength, int maxLength) {
        this(fieldName, false, minLength, maxLength);
    }
    public StringLengthValidator(String fieldName, boolean isAllowNull, int minLength, int maxLength) {
        this.fieldName = fieldName;
        this.isAllowNull = isAllowNull;
        if (minLength < 0) {
            minLength = 0;
        }
        if (maxLength <= 0) {
            maxLength = Integer.MAX_VALUE;
        }
        if (minLength > maxLength) {
            maxLength = Integer.MAX_VALUE;
        }
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(ValidatorContext context, String str) {
        boolean flag;
        if (StringUtils.isBlank(str)) {
            if(isAllowNull){
                return true;
            }

            flag = false;
            context.addError(ValidationError.create(String.format("%s", fieldName))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(str));
        } else {
            str = str.trim();
            flag = str.length() >= minLength && str.length() <= maxLength;
            if (!flag) {
                context.addError(ValidationError.create(String.format("%s", fieldName))
                        .setErrorCode(-1)
                        .setField(fieldName)
                        .setInvalidValue(str));
            }
        }
        return flag;
    }
}
