import java.util.*;
/*Every person is treated as a node.
This class stores all the attributes required for decision-making and simulation.
Each person stores:
Healthinfo-> used for vaccination priority.
Infection state-> used for simulation.
Neighbors-> used for graph traversal. */
//Person class defines structure.
public class Person {
        int id;
        int age;
        double healthscore;
        double immunitylevel;
        boolean hasAllergies;
        boolean hasInsurance;
        boolean infected;
        boolean vaccinated;
        List<Integer>neighbors;
        public Person(int id, int age, double healthscore, double immunitylevel, boolean hasAllergies, boolean hasInsurance) {
            this.id=id;
            this.age=age;
            this.healthscore=healthscore;
            this.immunitylevel=immunitylevel;
            this.hasAllergies=hasAllergies;
            this.hasInsurance=hasInsurance;
            this.infected=false;
            this.vaccinated=false;
            this.neighbors=new ArrayList<>();   //Used to store neighbors, allows flexible graph building.
        }
        //addneighbor()- adds graph connectins.
        public void addneighbor(int personId) {
            neighbors.add(personId);
        }
        //getDegree()- returns number of connections.
        public int getDegree() {
            return neighbors.size();
        }
        //Display method.
        public void display() {
            System.out.println("ID: " + id +
            "Age: " + age +
            "Healthscore: " + healthscore +
            "Immunity: " + immunitylevel +
            "Allergies: " + hasAllergies +
            "Insurance: " + hasInsurance +
            "Infected: " + infected +
            "Vaccinated: " + vaccinated +
            "Connections: " + neighbors.size());  
        }
}