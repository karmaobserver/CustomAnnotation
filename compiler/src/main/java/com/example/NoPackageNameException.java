package com.example;

import javax.lang.model.element.TypeElement;

/**
 * Created by Aleksej on 9/12/2017.
 */

class NoPackageNameException extends Exception {

    public NoPackageNameException(TypeElement typeElement) {
        super("The package of " + typeElement.getSimpleName() + " has no name");
    }
}
