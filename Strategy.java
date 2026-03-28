import java.util.*;
public class Strategy {
    public static void greedyvaccination(List<Person> population, int vaccines) {
        population.sort(Comparator.comparingDouble(p->p.healthscore));
        int count=0;
        for(Person p : population) {
            if(!p.vaccinated && count<vaccines) {
                count++;
            }
        }
        System.out.println("Greedy vaccination applied.");
    }
    public static void randomvaccination(List<Person> population, int vaccines) {
        Random rand=new Random();
        int size=population.size();
        Set<Integer> selected=new HashSet<>();
        while(selected.size()<vaccines) {
            int idx=rand.nextInt(size);
            selected.add(idx);
        }
        for(int i : selected) {
            population.get(i).vaccinated=true;
        }
        System.out.println("Random vaccination applied.");
    }
    public static void degreevaccination(List<Person> population, int vaccines) {
        population.sort((a,b)->b.getDegree()-a.getDegree());
        int count=0;
        for(Person p : population) {
            if(!p.vaccinated && count<vaccines) {
                p.vaccinated=true;
                count++;
            }
        }
        System.out.println("Degree-based vaccination applied.");
    }
}