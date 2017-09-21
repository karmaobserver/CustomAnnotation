package com.example.util;

import com.example.util.AnnotatedClass;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import static com.squareup.javapoet.ClassName.get;
import static com.squareup.javapoet.MethodSpec.methodBuilder;
import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * Class which generates code for our annotated class.
 */
public final class CodeGenerator {

    private static final String CLASS_NAME = "StringUtil";

    /**
     * Generate class code, add modifiers and methods.
     *
     * @param classes Annotated classes
     * @return TypeSpec which can be generated class, interface, or enum declaration. In our case, class.
     */
    public static TypeSpec generateClass(List<AnnotatedClass> classes) {

        //build class name and class modifiers
        TypeSpec.Builder builder = classBuilder(CLASS_NAME)
                .addModifiers(PUBLIC, FINAL);
        for (AnnotatedClass annotatedClass : classes) {

            //Add class methods
            builder.addMethod(makeCreateStringMethod(annotatedClass));
        }
        return builder.build();
    }

    /**
     * Building specific method, which code will be generated.
     *
     * @param annotatedClass Annotated class
     * @return a createString() method that takes annotatedClass's type as an input
     */
    private static MethodSpec makeCreateStringMethod(AnnotatedClass annotatedClass) {
        StringBuilder builder = new StringBuilder();

        //Building string which will be generated as code
        builder.append(String.format("return \"%s{\" + ", annotatedClass.annotatedClassName));
        for (String variableName : annotatedClass.variableNames) {
            builder.append(String.format(" \"%s='\" + String.valueOf(instance.%s) + \"',\" + ",
                    variableName, variableName));
        }
        builder.append("\"}\"");

        //Building method which will be generated as code
        return methodBuilder("createString")
                .addJavadoc("@return string suitable for {@param instance}'s toString()")
                .addModifiers(PUBLIC, STATIC)
                .addParameter(get(annotatedClass.getType()), "instance")
                .addStatement(builder.toString())
                .returns(String.class)
                .build();
    }

}
