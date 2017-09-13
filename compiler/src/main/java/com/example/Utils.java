package com.example;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by Aleksej on 9/12/2017.
 */

final class Utils {

    private Utils() {
        // no instances
    }

    static String getPackageName(Elements elementUtils, TypeElement type)
            throws NoPackageNameException {
        PackageElement packageElement = elementUtils.getPackageOf(type);
        if (packageElement.isUnnamed()) {
            throw new NoPackageNameException(type);
        }
        return packageElement.getQualifiedName().toString();
    }
}
