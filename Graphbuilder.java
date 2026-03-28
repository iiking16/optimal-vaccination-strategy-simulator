import java.util.*;
/*Graph builder to create a contact network between individuals.
Graph based system:
Graph theory-> peaople=nodes, connections=edges.
Adjacency list representation-> each person stores neighbors in a list.
Undirected graph-> models real world interactions. */
public class Graphbuilder {
    private static Random rand=new Random();    //Randomly creates connections.
    //Graph builder method.
    public static void buildGraph(List<Person> population, int maxconnections) {
        int size=population.size();
        for(Person p : population) {
            //Random connections for each person.
            int connections=rand.nextInt(maxconnections)+1;
            while(p.neighbors.size()<connections) {
                int randomIndex=rand.nextInt(size);
                //Avoid self-loop and duplicate connections.
                if(randomIndex!=p.id && !p.neighbors.contains(randomIndex)) {
                    p.addneighbor(randomIndex);
                    population.get(randomIndex).addneighbor(p.id);  //Undirected graph.
                }
            }
        }
    }
    //Display graph.
    public static void display(List<Person> population) {
        for(Person p : population) {
            System.out.println("Person " + p.id + " -> ");
            for(int neighbor : p.neighbors) {
                System.out.println(neighbor + " ");
            }
            System.out.println();
        }
    }
}