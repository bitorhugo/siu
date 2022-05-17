package main.edu.ufp.inf.en.models.lp2;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.TransitiveClosure;

public class MainLP {


    public void ex9 () {
        Digraph d = new Digraph(10);
        d.addEdge(3, 7);
        d.addEdge(1, 4);
        d.addEdge(7, 8);
        d.addEdge(0, 5);
        d.addEdge(5, 2);
        d.addEdge(3, 8);
        d.addEdge(2, 9);
        d.addEdge(0, 6);
        d.addEdge(4, 9);
        d.addEdge(2, 6);
        d.addEdge(6, 4);

        TransitiveClosure tc = new TransitiveClosure(d);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tc.reachable(i, j);
            }
        }
    }

    public double vertex_weight(int v, EdgeWeightedDigraph d) {
        double sum = 0.0;
        int count = 0;
        for (var value : d.adj(v)) {
            sum += value.weight();
            count ++;
        }
        return sum / count;
    }

    public static void main(String[] args) {
    
        EdgeWeightedDigraph d = new EdgeWeightedDigraph(10);

        for (int i = 0; i < d.V(); i++) {
            d.addEdge(new DirectedEdge(StdRandom.uniform(d.V()), StdRandom.uniform(d.V()), StdRandom.uniform(500)));
        }

        System.out.println(d);

        double sum = 0.0;
        for (var v : d.edges()) {
            sum += v.weight();
        }
        System.out.println(sum/d.V());
    }

    
}