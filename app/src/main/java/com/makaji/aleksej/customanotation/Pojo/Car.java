package com.makaji.aleksej.customanotation.Pojo;

import com.example.StaticStringUtil;
import com.makaji.aleksej.customanotation.Pojo.StringUtil;

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

}
