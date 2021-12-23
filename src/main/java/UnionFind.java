public class UnionFind {
    private int[] id; // access to component id (site indexed)
    private int count; // number of components
    public UnionFind(int N) { // Initialize component id array.
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    public int count(){ return count; }
    public boolean connected(int p, int q) { return find(p) == find(q); }
    public int find(int p){
        return id[p];
    }
    public void union(int p, int q){
        int pId = find(p);
        int qId = find(q);
        if(pId==qId) return;
        for(int i = 0; i<id.length; i++){
            if(id[i]==pId) id[i]=qId;
        }
        count--;
    }
    // See page 222 (quick-find),page 224 (quick-union) andpage 228 (weighted).

}
