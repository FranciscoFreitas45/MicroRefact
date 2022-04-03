package com.gbcom.common.im;
 import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
public class HashCodeBuilder {

 private  int iConstant;

 private  int iTotal;

/**
 * <p>
 * Constructor.
 * </p>
 *
 * <p>
 * This constructor uses two hard coded choices for the constants needed to
 * build a <code>hashCode</code>.
 * </p>
 */
public HashCodeBuilder() {
    super();
    iConstant = 37;
    iTotal = 17;
}/**
 * <p>
 * Constructor.
 * </p>
 *
 * <p>
 * Two randomly chosen, non-zero, odd numbers must be passed in. Ideally
 * these should be different for each class, however this is not vital.
 * </p>
 *
 * <p>
 * Prime numbers are preferred, especially for the multiplier.
 * </p>
 *
 * @param initialNonZeroOddNumber
 *            a non-zero, odd number used as the initial value
 * @param multiplierNonZeroOddNumber
 *            a non-zero, odd number used as the multiplier
 * @throws IllegalArgumentException
 *             if the number is zero or even
 */
public HashCodeBuilder(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber) {
    super();
    if (initialNonZeroOddNumber == 0) {
        throw new IllegalArgumentException("HashCodeBuilder requires a non zero initial value");
    }
    if (initialNonZeroOddNumber % 2 == 0) {
        throw new IllegalArgumentException("HashCodeBuilder requires an odd initial value");
    }
    if (multiplierNonZeroOddNumber == 0) {
        throw new IllegalArgumentException("HashCodeBuilder requires a non zero multiplier");
    }
    if (multiplierNonZeroOddNumber % 2 == 0) {
        throw new IllegalArgumentException("HashCodeBuilder requires an odd multiplier");
    }
    iConstant = multiplierNonZeroOddNumber;
    iTotal = initialNonZeroOddNumber;
}
public void reflectionAppend(Object object,Class clazz,HashCodeBuilder builder,boolean useTransients){
    Field[] fields = clazz.getDeclaredFields();
    AccessibleObject.setAccessible(fields, true);
    for (int i = 0; i < fields.length; i++) {
        Field f = fields[i];
        if ((f.getName().indexOf('$') == -1) && (useTransients || !Modifier.isTransient(f.getModifiers())) && (!Modifier.isStatic(f.getModifiers()))) {
            try {
                builder.append(f.get(object));
            } catch (IllegalAccessException e) {
                // this can't happen. Would get a Security exception instead
                // throw a runtime exception in case the impossible happens.
                throw new InternalError("Unexpected IllegalAccessException");
            }
        }
    }
}


public HashCodeBuilder appendSuper(int superHashCode){
    iTotal = iTotal * iConstant + superHashCode;
    return this;
}


public int reflectionHashCode(int initialNonZeroOddNumber,int multiplierNonZeroOddNumber,Object object,boolean testTransients,Class reflectUpToClass){
    if (object == null) {
        throw new IllegalArgumentException("The object to build a hash code for must not be null");
    }
    HashCodeBuilder builder = new HashCodeBuilder(initialNonZeroOddNumber, multiplierNonZeroOddNumber);
    Class clazz = object.getClass();
    reflectionAppend(object, clazz, builder, testTransients);
    while (clazz.getSuperclass() != null && clazz != reflectUpToClass) {
        clazz = clazz.getSuperclass();
        reflectionAppend(object, clazz, builder, testTransients);
    }
    return builder.toHashCode();
}


public int toHashCode(){
    return iTotal;
}


public HashCodeBuilder append(boolean[] array){
    if (array == null) {
        iTotal = iTotal * iConstant;
    } else {
        for (int i = 0; i < array.length; i++) {
            append(array[i]);
        }
    }
    return this;
}


}