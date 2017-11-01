package com.example.springboot.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Desc ：
 * Created by JHAO on 2017/10/26.
 */
public class ValidationUtlis {

    private static Validator validator;

    static {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    public static <T> String  validate(T t){
        Set<ConstraintViolation<T>> set = validator.validate(t);
        StringBuilder validateError = new StringBuilder();
        if(set.size()>0){
            for(ConstraintViolation<T> val : set){

                validateError.append(val.getMessage());
                break ;
            }
            return validateError.toString();
        }else{
            return null;
        }
    }

}
