package com.makaji.aleksej.customanotation.Pojo;

import com.example.StaticStringUtil;
import com.makaji.aleksej.customanotation.Pojo.StringUtil;

/**
 * Created by Aleksej on 9/12/2017.
 */

/**
 * This is simple pojo class which is annotated with @StaticStringUtil annotation.
 * This annotation will generate code in {@link StringUtil} class.
 * Code which will be generated is createString method which is simulation of toString method.
 */
@StaticStringUtil
public class Animal {

    public String cat;

    public String dog;

    public Animal(String cat, String dog) {
        this.cat = cat;
        this.dog = dog;
    }

    @Override
    public String toString() {
        return StringUtil.createString(this);
    }

}
