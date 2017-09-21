package com.example.util;

import com.example.util.NoPackageNameException;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by Aleksej on 9/12/2017.
 */

/**
 * Util class.
 */
public final class Utils {

    private Utils() {
        // no instances
    }

    /**
     * Get package name.
     *
     * @param elementUtils Element
     * @param type         Type of our annotated class
     * @return String of package name
     * @throws NoPackageNameException
     */
    public static String getPackageName(Elements elementUtils, TypeElement type) throws NoPackageNameException {

        PackageElement packageElement = elementUtils.getPackageOf(type);

        //In case our package element has no name, trow exception
        if (packageElement.isUnnamed()) {
            throw new NoPackageNameException(type);
        }
        return packageElement.getQualifiedName().toString();
    }
}
