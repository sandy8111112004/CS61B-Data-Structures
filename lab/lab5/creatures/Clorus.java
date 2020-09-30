package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;


public class Clorus extends Creature{
    private int r;
    private int g;
    private int b;

    public Clorus(double e){
        super("clorus");
        r=0;
        g=0;
        b=0;
        energy=e;
    }

    public Clorus(){this(1);}

    public Color color() {
        r=34;
        g=0;
        b=231;
        return color(r,g,b);
    }

    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    public void move() {
        energy = energy-0.03;
    }

    public void stay() {
        energy = energy-0.01;
    }

    public Clorus replicate() {
        energy = energy*0.5;
        Clorus c = new Clorus(energy);
        return c;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for(Direction i: neighbors.keySet()){
            if (neighbors.get(i).name().equals("empty")){
                emptyNeighbors.add(i);
            }
            else if(neighbors.get(i).name().equals("plip")){
                plipNeighbors.add(i);
            }
        }

        if(emptyNeighbors.size()==0){
            return new Action(Action.ActionType.STAY);
        }
        else if(plipNeighbors.size()!=0){
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        }
        else if(energy>=1){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }
}
