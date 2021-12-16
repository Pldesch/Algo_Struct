package Graphs;

import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra {

    private Edge[] edgeTo;
    private int[] distTo;
    private PriorityQueue<Integer> pq;
    int src;
    public Dijkstra(EdgeWeightedGraph G, int s) {
        src = s;
        edgeTo = new Edge[G.V()];
        distTo = new int[G.V()];
        pq = new PriorityQueue<>((a,b)->distTo[a]-distTo[b]);
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Integer.MAX_VALUE;
        distTo[s] = 0;
        pq.add(s);
        while (!pq.isEmpty()){
            int v = pq.poll();
            for(Edge e : G.adj(v))
            {
                int w = e.to();
                if (distTo[w] > distTo[v] + e.weight())
                {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    pq.remove(w);
                    pq.add(w);
                }
            }
        }
    }

    public int distTo(int v){return distTo[v];}
    public boolean hasPathTo(int v){return edgeTo[v] != null;}
    public Iterable<Edge> pathTo(int v){
        Stack<Edge> path = new Stack<>();
        Edge p = edgeTo[v];
        while(p.from() != src){
            path.push(p);
            p = edgeTo[p.from()];
        }
        path.push(p);
        return path;

    }
}
