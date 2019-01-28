package io.wanyxkhalil.abstinence.wubmybatis.daoscan;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.Objects;

public class WubMybatisScanRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {


    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
        scanner.registerFilters();

        String[] basePackages = retrieveBasePackages();
        scanner.doScan(basePackages);
    }

    private String[] retrieveBasePackages() {
        String property = environment.getProperty("wub.mybatis.scan");

        if (Objects.isNull(property)) {
            return new String[]{"io.wanyxkhalil"};
        }

        String[] split = StringUtils.split(property, ",");
        if (Objects.isNull(split)) {
            throw new IllegalArgumentException("wub mybatis scan config is invalid");
        }

        return Arrays.stream(split)
                .map(StringUtils::trim)
                .toArray(String[]::new);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}