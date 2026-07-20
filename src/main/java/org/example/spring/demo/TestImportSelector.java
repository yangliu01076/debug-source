package org.example.spring.demo;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author duoyian
 * @date 2026/5/10
 */
public class TestImportSelector implements org.springframework.context.annotation.ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"org.other.A"};
    }
}
