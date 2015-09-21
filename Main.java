import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by god on 4/21/15.
 */
public class Main {

    private static Graph graph;
    private static int num_tasks;
    private static int num_prec;
    private static int treshold;

    public static void main(String[] args) throws IOException {
        int max_tasks = 0;
        int num_hard = 0;
        //(new InputStreamReader(System.in, "UTF-8")))
        //(new FileReader("h1.txt")))
        try (BufferedReader in = new BufferedReader(new FileReader("h1.txt"))) {

            String Line1 = in.readLine();
            StringTokenizer st = new StringTokenizer(Line1, " ");
            num_tasks = Integer.parseInt(st.nextToken());
            num_prec = Integer.parseInt(st.nextToken());
            treshold = Integer.parseInt(st.nextToken());
            graph = new Graph(num_tasks);
            String sCurrentLine;
            while ((sCurrentLine = in.readLine()) != null) {
                StringTokenizer st2 = new StringTokenizer(sCurrentLine, " ");
                int antecessor = Integer.parseInt(st2.nextToken());
                int sucessor = Integer.parseInt(st2.nextToken());
                graph.addEdge(antecessor, sucessor);

            }

        }

        Queue<Integer> ready = new ArrayDeque<Integer>();
        Queue<Integer> next_week = new ArrayDeque<Integer>();

        int[] inCounter = new int[num_tasks];


        for (int i = 0; i < num_tasks; i++) {
            inCounter[i] = graph.degree_antecess(i); // indegree
            if (inCounter[i] == 0)
                ready.add(i);
        }

        while (!ready.isEmpty()) {
            if (ready.size() > treshold)
                num_hard++;
            if (ready.size() > max_tasks)
                max_tasks = ready.size();

            next_week= new ArrayDeque<>();
            while (!ready.isEmpty()) {
                int v = ready.remove();

                for (int i : graph.getOutVertexes(v)) {
                    inCounter[i]--;
                    if (inCounter[i] == 0)
                        next_week.add(i);

                }


            }

            ready = next_week;
        }

        System.out.println(max_tasks + " " + num_hard);

    }
}