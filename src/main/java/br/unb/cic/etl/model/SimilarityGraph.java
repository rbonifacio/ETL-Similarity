package br.unb.cic.etl.model;

import org.jgrapht.alg.clustering.LabelPropagationClustering;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class SimilarityGraph {
    SimpleWeightedGraph<ScriptETL, DefaultWeightedEdge> graph;

    public SimilarityGraph() {
        graph = new SimpleWeightedGraph<ScriptETL, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    }

    public void addEdge(ScriptETL etl1, ScriptETL etl2, double similarity) {
        if(!graph.containsVertex(etl1)) {
            graph.addVertex(etl1);
        }
        if(!graph.containsVertex(etl2)) {
            graph.addVertex(etl2);
        }

        DefaultWeightedEdge e1 = graph.addEdge(etl1, etl2);

        graph.setEdgeWeight(e1, similarity);
    }

    public ClusteringAlgorithm.Clustering<ScriptETL> findClusters() {
        LabelPropagationClustering<ScriptETL, DefaultWeightedEdge> clustering = new LabelPropagationClustering<>(graph);
        return clustering.getClustering();
    }
}