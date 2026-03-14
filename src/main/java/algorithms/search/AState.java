package algorithms.search;

import java.io.Serializable;

public abstract class AState implements Serializable {

    private double cost;
    private AState camefrom; // save his parent

    public AState() {
        camefrom = null;
        cost=0;
    }

    public double getCost() {
        return cost;
    }

    public AState getCamefrom() {
        return camefrom;
    }


    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCamefrom(AState camefrom) {
        this.camefrom = camefrom;
    }

    public int getNumofcolumn() {
        return 0;
    }// implement in the Mazestate

    public int getNumofrow() {
        return 0;
    }// implement in the Mazestate

    public void printmazestate(){};// implement in the Mazestate

    @Override
    public String toString() {
        return super.toString();// implement in the Mazestate
    }
}
