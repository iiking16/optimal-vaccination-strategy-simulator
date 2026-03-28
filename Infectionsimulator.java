import java.util.*;
public class Infectionsimulator {
    private static Random rand=new Random();

    public static void spread(List<Person> population, double infectprobab, int days) {
        Queue<Integer> queue=new LinkedList<>();
        for(Person p : population) {
            if(p.infected) {
                queue.add(p.id);
            }
        }
        System.out.println("Initial infected count: " + queue.size());
        for(int day=1; day<=days; day++) {
            int size=queue.size();
            for(int i=0; i<size; i++) {
                int currentid=queue.poll();
                Person curr=population.get(currentid);
                for(int neighborid : curr.neighbors) {
                    Person neighbor=population.get(neighborid);
                    if(!neighbor.infected && !neighbor.vaccinated) {
                        double chance=rand.nextDouble();
                        if(chance<infectprobab) {
                            neighbor.infected=true;
                            queue.add(neighborid);
                        }
                    }
                }
            }
            int infectedcnt=countinfect(population);
            System.out.println("Day " + day + "-> Infected: " + infectedcnt);
        }
    }
    public static int countinfect(List<Person> population) {
        int count=0;
        for(Person p : population) {
            if(p.infected) count++;
        }
        return count;
    }
}