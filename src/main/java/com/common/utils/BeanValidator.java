package com.common.utils;

import com.common.exception.ParmException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;

import javax.swing.text.html.HTMLDocument;
import javax.validation.*;
import java.util.*;

/**
 * Created by Administrator on 2018/8/28.
 */

public class BeanValidator {


    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap erros = new LinkedHashMap();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                erros.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return erros;
        }
    }


    public static Map<String, String> validateList(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map erros;

        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            erros = validate(object, new Class[0]);
        } while (erros.isEmpty());

        return erros;
    }


    public static Map<String, String> validateObject(Object first, Object... objects) {

        if (objects != null && objects.length > 0) {
           return validateList(Lists.asList(first, objects));
        } else {

            return validate(first, new Class[0]);
        }
    }

    public static void check(Object param) throws ParmException{
        Map<String ,String> map = BeanValidator.validateObject(param);
        if (MapUtils.isNotEmpty(map)){
            throw new ParmException(map.toString());
        }
    }







}