public class Hillclimb {

    SpaceObject goal;
    SpaceObject probe;
    Double[] goalPolar = new Double[2];
    Double[] probePolar = new Double[2];
    Vector initialVelocity = new Vector();
    SolarSystem solarSystem;

    public Hillclimb(SolarSystem solarSystem, SpaceObject probe, SpaceObject goal){
        this.goal = goal;
        this.probe = probe;
        initialVelocity = probe.getVelocity();
        this.solarSystem = solarSystem;
    }

    public void computePolar(SpaceObject probe, SpaceObject goal) {
        goalPolar[0] = goal.getPosition().getDistance();
        double angle = Math.atan(goal.getPosition().getX()/goal.getPosition().getY());
        goalPolar[1] = (goal.getPosition().getX() > 0) ? angle : angle + Math.PI; 

        probePolar[0] = probe.getPosition().getDistance();
        angle = Math.atan(probe.getPosition().getX()/probe.getPosition().getY());
        probePolar[1] = (probe.getPosition().getX() > 0) ? angle : angle + Math.PI; 
    }

    public void updateProbeAndGoal(SpaceObject probe, SpaceObject goal) {
        this.goal = goal;
        this.probe = probe;
    }

    public void hillclimb(SpaceObject probe) {
        final double YEAR_TO_SECONDS = 365*86400;
        computePolar(probe, goal);
        int ctr = 0;
        System.out.println(goalPolar[0]+ " " +goalPolar[0]+ " " +probePolar[1]+" "+goalPolar[1]);
        while ((Math.abs(goalPolar[1]-probePolar[1])>0.1)&&((Math.abs(goalPolar[0]-probePolar[0])>0.1))){
            if (ctr > 3*YEAR_TO_SECONDS){
                break;
            }
            else{
                solarSystem.updateSolarSystem(1);
                updateProbeAndGoal(probe, solarSystem.getSpaceObjectList().get(8));
                computePolar(probe, solarSystem.getSpaceObjectList().get(8));
            }
        }
        if (Math.abs(goalPolar[1]-probePolar[1])<=0.1){
            System.out.println("fix speed");
        }
        else if (Math.abs(goalPolar[0]-probePolar[0])<=0.1){
            System.out.println("fix angle");
        }
        else System.out.println("break happened");
    }

    /**
     * @return the goal
     */
    public SpaceObject getGoal() {
        return goal;
    }

    /**
     * @param goal the goal to set
     */
    public void setGoal(SpaceObject goal) {
        this.goal = goal;
    }

    /**
     * @return the probe
     */
    public SpaceObject getProbe() {
        return probe;
    }

    /**
     * @param probe the probe to set
     */
    public void setProbe(SpaceObject probe) {
        this.probe = probe;
    }

    /**
     * @return the goalPolar
     */
    public Double[] getGoalPolar() {
        return goalPolar;
    }

    /**
     * @param goalPolar the goalPolar to set
     */
    public void setGoalPolar(Double[] goalPolar) {
        this.goalPolar = goalPolar;
    }

    /**
     * @return the probePolar
     */
    public Double[] getProbePolar() {
        return probePolar;
    }

    /**
     * @param probePolar the probePolar to set
     */
    public void setProbePolar(Double[] probePolar) {
        this.probePolar = probePolar;
    }

    /**
     * @return the initialVelocity
     */
    public Vector getInitialVelocity() {
        return initialVelocity;
    }

    /**
     * @param initialVelocity the initialVelocity to set
     */
    public void setInitialVelocity(Vector initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    /**
     * @return the solarSystem
     */
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    /**
     * @param solarSystem the solarSystem to set
     */
    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

}