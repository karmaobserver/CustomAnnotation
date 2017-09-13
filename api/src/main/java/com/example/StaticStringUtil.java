package com.example;

/**
 * Created by Aleksej on 9/12/2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Create a function in {@link StringUtil} for creating strings.
 */
@Retention(value = CLASS)   // Stored into class file and not retained in runtime
@Target(value = TYPE)   //Class type annotation
public @interface StaticStringUtil {}


/*@Retention: annotation indicates how the custom annotation is stored. There are 3 types of retention.
        SOURCE — analyses by compiler and never stored
        CLASS — stored into class file and not retained in runtime
        RUNTIME — store into class file and usable in runtime(by reflection)*/
