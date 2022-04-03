package mondrian.olap.fun.FunUtil;
 import mondrian.calc;
import mondrian.calc.impl;
import mondrian.mdx;
import mondrian.olap;
import mondrian.olap.type;
import mondrian.resource.MondrianResource;
import mondrian.rolap;
import mondrian.util;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.log4j.Logger;
import java.util;
public class Quicksorter {

 public  int TOO_SMALL;

 private  Logger LOGGER;

 private  T[] vec;

 private  Comparator<T> comp;

 private  boolean traced;

 private  long partitions;

public Quicksorter(T[] vec, Comparator<T> comp) {
    this.vec = vec;
    this.comp = comp;
    partitions = comparisons = exchanges = 0;
    traced = LOGGER.isDebugEnabled();
}
public boolean equal(T x,T y){
    comparisons++;
    return comp.compare(x, y) == 0;
}


public int partition(int start,int end){
    partitions++;
    assert start <= end;
    // Find median of three (both ends and the middle).
    // TODO: use pseudo-median of nine when array segment is big enough.
    int mid = (start + end) / 2;
    order3(start, mid, end);
    if (end - start <= 2) {
        // sorted!
        return mid;
    }
    // Now the left and right ends are in place (ie in the correct
    // partition), and will serve as sentinels for scanning. Pick middle
    // as pivot and set it aside, in penultimate position.
    final T pivot = vec[mid];
    swap(mid, end - 1);
    // Scan inward from both ends, swapping misplaced items.
    // vec[start] is in place
    int left = start + 1;
    // vec[end - 1] is pivot
    int right = end - 2;
    while (left < right) {
        // scan past items in correct place, but stop at a pivot value
        // (Sedgewick's idea).
        while (less(vec[left], pivot)) {
            ++left;
        }
        while (less(pivot, vec[right])) {
            --right;
        }
        if (debug) {
            assert (left <= end) && (right >= start);
        }
        if (left < right) {
            // found a misplaced pair
            swap(left, right);
            ++left;
            --right;
        }
    }
    if ((left == right) && less(vec[left], pivot)) {
        ++left;
    }
    // All scanned. Restore pivot to its rightful place.
    swap(left, end - 1);
    if (debug) {
        for (int i = start; i < left; i++) {
            assert !more(vec[i], pivot);
        }
        assert equal(vec[left], pivot);
        for (int i = left + 1; i <= end; i++) {
            assert !less(vec[i], pivot);
        }
    }
    return left;
}


public void select(int limit){
    int n = vec.length - 1;
    select(limit, 0, n);
    if (traced) {
        traceStats("quickselect for " + limit + " from" + n + "items");
    }
}


public void swap(int i,int j){
    exchanges++;
    T temp = vec[i];
    vec[i] = vec[j];
    vec[j] = temp;
}


public void traceStats(String prefix){
    StringBuilder sb = new StringBuilder(prefix);
    sb.append(": ");
    sb.append(partitions).append(" partitions, ");
    sb.append(comparisons).append(" comparisons, ");
    sb.append(exchanges).append(" exchanges.");
    LOGGER.debug(sb.toString());
}


public boolean more(T x,T y){
    comparisons++;
    return comp.compare(x, y) > 0;
}


public void order3(int i,int j,int k){
    if (more(vec[i], vec[j])) {
        swap(i, j);
    }
    if (more(vec[i], vec[k])) {
        swap(i, k);
    }
    if (more(vec[j], vec[k])) {
        swap(j, k);
    }
}


public boolean less(T x,T y){
    comparisons++;
    return comp.compare(x, y) < 0;
}


public void sort(){
    int n = vec.length - 1;
    sort(0, n);
    if (traced) {
        traceStats("quicksort on " + n + "items");
    }
}


public void selectionSort(int start,int end){
    for (int i = start; i < end; ++i) {
        // pick the min of vec[i, end]
        int pmin = i;
        for (int j = i + 1; j <= end; ++j) {
            if (less(vec[j], vec[pmin])) {
                pmin = j;
            }
        }
        if (pmin != i) {
            swap(i, pmin);
        }
    }
}


public void partialSort(int limit){
    int n = vec.length - 1;
    select(limit, 0, n);
    if (traced) {
        traceStats("partial sort: quickselect phase for " + limit + "from " + n + "items");
    }
    sort(0, limit - 1);
    if (traced) {
        traceStats("partial sort: quicksort phase on " + n + "items");
    }
}


}