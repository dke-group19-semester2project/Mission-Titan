import java.util.ArrayList;
import java.util.Random;

/**
 * Run
 */
public class Run {

    static Planet TARGET;
    static final double MUTATIONPROB = 0.2;
    static final int ELITENUM = 20;
    static final boolean GENERATIONBOUND = false;
    static final int GENERATIONLIMIT = 50;
    static final int POPSIZE = 100;
    static final int TESTS = 20;
    static final Random generator = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        ArrayList<Probe> monte = new ArrayList<>();

        for (int i = 0; i < TESTS; i++) {
            monte.add(GA(TARGET));
            System.out.println("Probe with inititial velocity "+ monte.get(i).getStartVX() + " " +monte.get(i).getStartVy() + " " +monte.get(i).getStartVZ());
        }

        System.out.println("---------------------------");
        
        System.out.println("out of : " + TESTS + " tests");
        System.out.println("Population size : " + POPSIZE);
        System.out.println("---------------------------");

    }

    public static Probe GA(Planet o) {
        Probe[] population = new Probe[POPSIZE];
        
        for (int i=0; i < POPSIZE;i++){
            double v = (generator.nextDouble()-0.5) * 60;
            population[i] = new Probe(300,o.getxPosition(),o.getyPosition(),o.getzPosition(),v,v,v);
        }
        HeapSort.sort(population);

        //actual algorithm
        int generation = 0;
        while (population[0].getFitness() < 1.0){
            if (GENERATIONBOUND && generation > GENERATIONLIMIT)
            break;

            Probe[] parents = elitestSelection(population);

            for (int i = 0; i < population.length; i++) {
                population[i] = crossover(parents[generator.nextInt(parents.length)], parents[generator.nextInt(parents.length)]);
                mutation(population[i]);
                population[i].setFitness(computeFitness(population[i]));
            }

            generation++;

            HeapSort.sort(population);
        }
        return population[i];
    }

    public static double computeFitness(Probe o){
        double c = o.distance(TARGET);
        if (o.getMinDist() > c){
            o.setMinDist(c);
        } 
        double r = 100000-c;
        return Math.max(r/100000D,0);
    }

    //selection method
    public static Probe[] elitestSelection(Probe[] population){
        HeapSort.sort(population);
        Probe[] parents = new Probe[ELITENUM];
        for (int i = 0; i < ELITENUM; i++){
            parents[i] = population[i].clone();
        }
        return parents;
    }

    //crossover
    public static Probe crossover(Probe p1, Probe p2){
        return new Probe(mass,p1.getStartX(),p1.getStartY(),p1.getStartZ(),(p1.getxVelocity()+p2.getxVelocity())/2,(p1.getyVelocity()+p2.getyVelocity())/2,(p1.getzVelocity()+p2.getzVelocity())/2);
    }

    //mutation
    public static void mutation(Probe p){
        double r = generator.nextDouble() * 2;
        p.setxVelocity(p.getxVelocity()*r);
        p.setyVelocity(p.getyVelocity()*r);
        p.setzVelocity(p.getzVelocity()*r);
    }
}