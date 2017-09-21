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
public class Car {

    public String name;

    public Car(String name) {
        this.name = name;
    }

    public String toStringCustom() {
        return StringUtil.createString(this);
    }

}
