package Graphs;

import java.util.LinkedList;
import java.util.Stack;

public class BellmanFordSP
{
    private double[] distTo; // length of path to v
    private Edge[] edgeTo; // last edge on path to v
    private boolean[] onQ; // Is this vertex on the queue?
    private LinkedList<Integer> queue; // vertices being relaxed
    private int cost; // number of calls to relax()
    private Iterable<Edge> cycle; // negative cycle in edgeTo[]?
    public BellmanFordSP(EdgeWeightedGraph G, int s)
    {
        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];
        onQ = new boolean[G.V()];
        queue = new LinkedList<>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        queue.add(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !this.hasNegativeCycle())
        {
            int v = queue.poll();
            onQ[v] = false;
            for (Edge e : G.adj(v)){
                int w = e.to();
                if (distTo[w] > distTo[v] + e.weight())
                {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (!onQ[w])
                    {
                        queue.add(w);
                        onQ[w] = true;
                    }
                }
                if (cost++ % G.V() == 0) findNegativeCycle();
            }
        }
    }

    // See page 673.
    public double distTo(int v){ // standard client query methods
        return distTo[v];
    }
    public boolean hasPathTo(int v){ // for SPT implementatations
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    public Iterable<Edge> pathTo(int v){ // (See page 649.)
        if(!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<>();
        for(Edge e = edgeTo[v]; e!= null; e=edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }
    private void findNegativeCycle(){
        int V = edgeTo.length;
        EdgeWeightedGraph spt = new EdgeWeightedGraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);
        E cf;
        cf = new EdgeWeightedCycleFinder(spt);
        cycle = cf.cycle();
    }
    public boolean hasNegativeCycle(){
        return cycle != null;
    }
    public Iterable<Edge> negativeCycle(){
        return cycle;
    }
    // See page 677.
}