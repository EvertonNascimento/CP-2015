import java.util.*;

/**
 * Created by god on 4/21/15.
 */
public class Graph {
    private int nrVertexes;
    private List<Integer>[] inVertexes;
    private int[] in_degree;
    private List<Integer>[] outVertexes;// vetor dos antecessores de cada vertice

    @SuppressWarnings("unchecked")
    public Graph(int nVertex) {

        this.nrVertexes = nVertex;
        this.in_degree = new int[nVertex];
        this.inVertexes = (List<Integer>[]) new List[nrVertexes];
        this.outVertexes = (List<Integer>[]) new List[nrVertexes];

        for (int i = 0; i < nrVertexes; i++) {
            this.inVertexes[i] = new LinkedList<Integer>();
            this.in_degree[i] = 0;
            this.outVertexes[i] = new LinkedList<Integer>();
        }


    }
    public void addEdge(int origin, int destination) {
        inVertexes[destination].add(origin);
        in_degree[destination]++;
        outVertexes[origin].add(destination);
    }
    public List<Integer> getOutVertexes(int vertex) {

        return outVertexes[vertex];
    }
    public int degree_antecess(int vertex) {

        return in_degree[vertex];
    }
}