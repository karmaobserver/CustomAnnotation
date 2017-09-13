package com.makaji.aleksej.customanotation;

import com.example.StaticStringUtil;

/**
 * Created by Aleksej on 9/12/2017.
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

    public String getName() {
        return StringUtil.getname(this);
    }
}
