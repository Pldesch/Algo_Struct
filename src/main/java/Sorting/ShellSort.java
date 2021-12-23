package Sorting;

public class ShellSort {
    public ShellSort(Comparable[] a){
        int n = a.length;
        int h = 1;
        while(h<n/3) h = 3*h+1;
        while(h>=1){
            for(int i = h; i<n; i++){
                for(int j = i; j>=h && less(a[j], a[j-h]); j -= h){
                    exch(a,j,j-h);
                }
                h=h/3;
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
