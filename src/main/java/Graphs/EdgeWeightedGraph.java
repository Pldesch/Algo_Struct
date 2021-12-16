package Graphs;

import java.util.LinkedList;

public class EdgeWeightedGraph{
        private final int V; // number of vertices
        private int E; // number of edges
        private LinkedList<Edge>[] adj; // adjacency lists
        public EdgeWeightedGraph(int V)
        {
            this.V = V;
            this.E = 0;
            adj = (LinkedList<Edge>[]) new LinkedList[V];
            for (int v = 0; v < V; v++)
                adj[v] = new LinkedList<>();
        }

        public int V() { return V; }
        public int E() { return E; }
        public void addEdge(Edge e)
        {
            adj[e.from()].add(e);
            E++;
        }
        public Iterable<Edge> adj(int v)
        { return adj[v]; }
        public Iterable<Edge> edges()
        {
            LinkedList<Edge> bag = new LinkedList<>();
            for (int v = 0; v < V; v++)
                for (Edge e : adj[v])
                    bag.add(e);
            return bag;
        }
}
