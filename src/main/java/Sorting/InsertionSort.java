package Sorting;

public class InsertionSort {
    public InsertionSort(Comparable[] a){
        int n = a.length;
        for(int i = 1; i<n; i++){
            for(int j = i; j>0 && less(a[j], a[j-1]); j--){
                exch(a,j,j-1);
            }
        }
    }
    private void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
