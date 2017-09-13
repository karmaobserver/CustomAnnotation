package com.example;

import javax.lang.model.element.TypeElement;

import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * Created by Aleksej on 9/12/2017.
 */

final class ClassValidator {
    static boolean isPublic(TypeElement annotatedClass) {
        return annotatedClass.getModifiers().contains(PUBLIC);
    }

    static boolean isAbstract(TypeElement annotatedClass) {
        return annotatedClass.getModifiers().contains(ABSTRACT);
    }
}
