package com.miaoshaproject.validator;
import com.miaoshaproject.service.impl.ItemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;
    private Logger logger = LoggerFactory.getLogger(ValidatorImpl.class);


    public ValidationResult validate(Object bean){
        logger.info("进入validate");
        final ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);

        logger.info("validate");
        if(constraintViolationSet.size() >0){
            result.setHasErrors(true);

            constraintViolationSet.forEach(constraintViolation->{
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();

                result.getErrorMsgMap().put(propertyName, errMsg);
            });
        }

        logger.info("进入validate");
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        // 将 hibernate validator 通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
