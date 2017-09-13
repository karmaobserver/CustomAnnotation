package com.makaji.aleksej.customanotation;

import com.example.StaticStringUtil;

/**
 * Created by Aleksej on 9/12/2017.
 */

@StaticStringUtil
public class Human {

    public String firstName;
    public String lastName;



    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    @Override
    public String toString() {
        return StringUtil.createString(this);
    }

}