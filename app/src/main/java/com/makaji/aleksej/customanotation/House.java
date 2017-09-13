package com.makaji.aleksej.customanotation;

import com.example.StaticStringUtil;

/**
 * Created by Aleksej on 9/12/2017.
 */


@StaticStringUtil
public class House {
    public String address;

    public House(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return StringUtil.createString(this);
    }
}
