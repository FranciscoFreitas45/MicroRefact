package com.algorithm.sort;
 public class HeapSort {


public void createMaxHeap(int[] data,int lastIndex){
    for (int i = (lastIndex - 1) >> 1; i >= 0; i--) {
        // 保存当前正在判断的节点
        int k = i;
        // 若当前节点的子节点存在
        while ((k << 1) + 1 <= lastIndex) {
            // biggerIndex总是记录较大节点的值,先赋值为当前判断节点的左子节点
            int biggerIndex = (k << 1) + 1;
            // 若右子节点存在，否则此时biggerIndex应该等于 lastIndex 
            if (biggerIndex < lastIndex) {
                if (data[biggerIndex] < data[biggerIndex + 1]) {
                    biggerIndex++;
                }
            }
            if (data[k] < data[biggerIndex]) {
                SortUtils.swap(data, k, biggerIndex);
                k = biggerIndex;
            } else {
                break;
            }
        }
    }
}


public void main(String[] args){
    int[] data5 = new int[] { 5, 3, 6, 2, 1, 9, 4, 8, 7 };
    SortUtils.printData(data5);
    heapSort(data5);
    SortUtils.printData(data5);
}


public void heapSort(int[] data){
    for (int i = 0; i < data.length; i++) {
        createMaxHeap(data, data.length - 1 - i);
        SortUtils.swap(data, 0, data.length - 1 - i);
        SortUtils.printData(data);
    }
}


}