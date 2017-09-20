package com.example;

/**
 * Created by Aleksej on 9/12/2017.
 */

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.*;
import javax.annotation.processing.Messager;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import static com.example.Utils.getPackageName;
import static com.squareup.javapoet.JavaFile.builder;
import static java.util.Collections.singleton;
import static javax.lang.model.SourceVersion.latestSupported;
import static javax.tools.Diagnostic.Kind.*;

/**
 * Annotated class cast instance of Pojo Class to string (for example like in Pojo class toString method).
 * Annotated class restrictions are that class must be public and not abstract.
 */
@AutoService(Processor.class)
public class StaticStringUtilProcessor extends AbstractProcessor {

    //Annotation name
    private static final String ANNOTATION = "@" + StaticStringUtil.class.getSimpleName();

    //Message which will be showed to developer when he make mistake with annotating
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return singleton(StaticStringUtil.class.getCanonicalName());
    }

    /**
     * Java version which processor handle.
     *
     * @return Java version
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return latestSupported();
    }

    /**
     * Method which needs to be override. Business logic of our processor.
     *
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        //List of our annotated classes
        ArrayList<AnnotatedClass> annotatedClasses = new ArrayList<>();

        //Check all elements which are annotated with out annotation (Element can be class, method, field, even operator...
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(StaticStringUtil.class)) {

            // Our annotation is defined with @Target(value=TYPE). Therefore, we can assume that
            // this annotatedElement is a TypeElement.
            TypeElement annotatedClass = (TypeElement) annotatedElement;

            //Check if class is valid
            if (!isValidClass(annotatedClass)) {
                return true;
            }

            try {

                //Add builded annotated classes to our list
                annotatedClasses.add(buildAnnotatedClass(annotatedClass));
            } catch (NoPackageNameException | IOException e) {
                String message = String.format("Couldn't process class %s: %s", annotatedClass, e.getMessage());
                messager.printMessage(ERROR, message, annotatedElement);
            }
        }

        try {

            //Generate code for our annotated class
            generate(annotatedClasses);
        } catch (NoPackageNameException | IOException e) {
            messager.printMessage(ERROR, "Couldn't generate class");
        }
        return true;
    }

    /**
     * Check if our annotated class is valid, check their restrictions.
     * In case developer made mistake and annotated not proper, show him message.
     *
     * @param annotatedClass Our annotated class
     * @return True if annotation is put on right place, else return false.
     */
    private boolean isValidClass(TypeElement annotatedClass) {

        //Check if class is Public
        if (!ClassValidator.isPublic(annotatedClass)) {
            String message = String.format("Classes annotated with %s must be public.", ANNOTATION);
            messager.printMessage(ERROR, message, annotatedClass);
            return false;
        }

        //Check if class if Abstract
        if (ClassValidator.isAbstract(annotatedClass)) {
            String message = String.format("Classes annotated with %s must not be abstract.", ANNOTATION);
            messager.printMessage(ERROR, message, annotatedClass);
            return false;
        }
        return true;
    }

    /**
     * Build annotated class with variable names.
     *
     * @param annotatedClass Annotated class
     * @return Annotated class with variables
     * @throws NoPackageNameException
     * @throws IOException
     */
    private AnnotatedClass buildAnnotatedClass(TypeElement annotatedClass) throws NoPackageNameException, IOException {

        //List of variable names
        ArrayList<String> variableNames = new ArrayList<>();

        //Iterate through elements and check if they are variables, if it is variable, add her name to variable list
        for (Element element : annotatedClass.getEnclosedElements()) {
            if (!(element instanceof VariableElement)) {
                continue;
            }
            VariableElement variableElement = (VariableElement) element;
            variableNames.add(variableElement.getSimpleName().toString());
        }
        return new AnnotatedClass(annotatedClass, variableNames);
    }

    /**
     * Generate code for our annotated class.
     *
     * @param annotatedClasses Annotated class
     * @throws NoPackageNameException
     * @throws IOException
     */
    private void generate(List<AnnotatedClass> annotatedClasses) throws NoPackageNameException, IOException {

        //In case we have no annotated classes, we don't have anything to generate for
        if (annotatedClasses.size() == 0) {
            return;
        }

        //Get package name
        String packageName = getPackageName(processingEnv.getElementUtils(), annotatedClasses.get(0).typeElement);

        //Generate code for our annotated class
        TypeSpec generatedClass = CodeGenerator.generateClass(annotatedClasses);

        //Store generated class as java file
        JavaFile javaFile = builder(packageName, generatedClass).build();

        javaFile.writeTo(processingEnv.getFiler());
    }
}