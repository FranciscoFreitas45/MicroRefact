package com.gbcom.common.im.ds;
 import java.util.List;
public class IDNameStructUtil {

 public  IIDNameStruct[] MO_ARRAY_TYPE;


public IIDNameStruct[] add(IIDNameStruct[] a,IIDNameStruct data){
    IIDNameStruct[] result;
    if (a == null || a.length == 0) {
        result = a == null ? (IIDNameStruct[]) java.lang.reflect.Array.newInstance(data.getClass(), 1) : (IIDNameStruct[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), 1);
        result[0] = data;
        return result;
    }
    result = (IIDNameStruct[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), a.length + 1);
    int index = indexOf(a, data);
    System.arraycopy(a, 0, result, 0, index);
    result[index] = data;
    System.arraycopy(a, index, result, index + 1, a.length - index);
    // key not found.
    return result;
}


public int med3(IIDNameStruct[] x,int a,int b,int c){
    return (x[a].getID() < x[b].getID() ? (x[b].getID() < x[c].getID() ? b : x[a].getID() < x[c].getID() ? c : a) : (x[b].getID() > x[c].getID() ? b : x[a].getID() > x[c].getID() ? c : a));
}


public void swap(IIDNameStruct[] x,int a,int b){
    IIDNameStruct t = x[a];
    x[a] = x[b];
    x[b] = t;
}


public void vecswap(IIDNameStruct[] x,int p1,int p2,int n){
    int a = p1;
    int b = p2;
    for (int i = 0; i < n; i++, a++, b++) {
        swap(x, a, b);
    }
}


public int binarySearch(IIDNameStruct[] a,int key,int offset,int length){
    if (length == 0) {
        return -1;
    }
    int off = offset;
    int high = off + length - 1;
    if (key < a[off].getID() || key > a[high].getID()) {
        return -1;
    }
    while (off <= high) {
        int mid = (off + high) >> 1;
        // cant be equal
        int cmp = -1;
        if (a[mid].getID() == key) {
            return mid;
        } else if (a[mid].getID() > key) {
            // Neither val is NaN, thisVal is larger
            cmp = 1;
        }
        if (cmp < 0) {
            off = mid + 1;
        } else if (cmp > 0) {
            high = mid - 1;
        }
    }
    // key not found.
    return -1;
}


public IIDNameStruct[] sort(List<IIDNameStruct> a){
    if (a == null || a.size() == 0) {
        return null;
    }
    IIDNameStruct[] result = (IIDNameStruct[]) a.toArray(MO_ARRAY_TYPE);
    sort(result, 0, result.length);
    return result;
}


public int bestIndexOf(IIDNameStruct[] a,int id,int offset,int length){
    // int highest = off + length - 1;
    int off = offset;
    // highest;
    int high = off + length - 1;
    int mid = 0;
    while (off <= high) {
        mid = (off + high) >> 1;
        // cant be equal
        int cmp = -1;
        if (a[mid].getID() > id) {
            // Neither val is NaN, thisVal is larger
            cmp = 1;
        } else if (a[mid].getID() == id) {
            break;
        }
        if (cmp < 0) {
            off = mid + 1;
        } else if (cmp > 0) {
            high = mid - 1;
        }
    }
    // if (mid > highest || a[mid].id > id)
    if (mid > high) {
        return mid;
    }
    return mid + 1;
}


public String[] toNameArray(IIDNameStruct[] a){
    if (a != null) {
        String[] result = new String[a.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = a[i].getName();
        }
        return result;
    }
    return null;
}


public int indexOf(IIDNameStruct[] a,IIDNameStruct data,int offset,int length){
    // int highest = off + length - 1;
    int off = offset;
    // highest;
    int high = off + length - 1;
    int mid = 0;
    while (off <= high) {
        mid = (off + high) >> 1;
        // cant be equal
        int cmp = -1;
        if (a[mid].getID() > data.getID()) {
            // Neither val is NaN, thisVal is larger
            cmp = 1;
        }
        if (cmp < 0) {
            off = mid + 1;
        } else if (cmp > 0) {
            high = mid - 1;
        }
    }
    // if (mid > highest || a[mid].id > data.id)
    if (mid > high) {
        return mid;
    }
    return mid + 1;
}


public int[] toIDArray(IIDNameStruct[] a){
    if (a != null) {
        int[] result = new int[a.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = a[i].getID();
        }
        return result;
    }
    return null;
}


public IIDNameStruct[] remove(IIDNameStruct[] a,int index){
    if (index == -1) {
        return a;
    }
    if (a.length == 1) {
        return null;
    }
    IIDNameStruct[] result = (IIDNameStruct[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), a.length - 1);
    System.arraycopy(a, 0, result, 0, index);
    System.arraycopy(a, index + 1, result, index, a.length - index - 1);
    // key not found.
    return result;
}


}