import java.util.*;
/*Data set generator to craete a synthetic population and initialise the simulation.
Since real data is not available, this module:
Simulation modelling-> simulate real world population using random data.
Randomization-> used to craete variablity in: Age, Health, Immunity. */
public class DataGenerator {
    private static Random rand=new Random();    //Generates random values for attributes.
    //Population generation method.s
    public static List<Person> generatePopuation(int size) {
        List<Person> population=new ArrayList<>();  //Stores all population.
        for(int i=0; i<size; i++) {
            int age=rand.nextInt(60)+18;
            double healthscore=rand.nextDouble()*10;
            double immunitylevel=rand.nextDouble()*10;
            boolean hasAllergies=rand.nextBoolean();
            boolean hasInsurance=rand.nextBoolean();
            Person p=new Person(i, age, healthscore, immunitylevel, hasAllergies, hasInsurance);
            population.add(p);
        }
        return population;
    }
    //Randomly infects some part of the population.
    public static void infectinitial(List<Person> population, int count) {
        int size=population.size();
        if(count>size) {
            System.err.println("Infection count exceeds population size!");
            return;
        }
        Set<Integer> infectedSet=new HashSet<>();   //Ensures no duplicate infection.
        while(infectedSet.size()<count) {
            int index=rand.nextInt(size);
            infectedSet.add(index);
        }
        for(int i : infectedSet) {
            population.get(i).infected=true;
        }
    }
}