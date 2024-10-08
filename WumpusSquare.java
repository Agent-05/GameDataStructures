public class WumpusSquare {
    private boolean gold;
    private boolean ladder;
    private boolean pit;
    private boolean breeze;
    private boolean wumpus;
    private boolean deadWumpus;
    private boolean stench;
    private boolean visited;
    private boolean empty = true;
    public WumpusSquare(){
        gold = false;
        ladder = false;
        pit = false;
        breeze = false;
        wumpus = false;
        deadWumpus = false;
        stench = false;
        visited =false;
        empty =true;
    }

    public boolean isGold() {
        return gold;
    }

    public boolean isLadder() {
        return ladder;
    }

    public boolean isPit() {
        return pit;
    }

    public boolean isBreeze() {
        return breeze;
    }

    public boolean isWumpus() {
        return wumpus;
    }

    public boolean isDeadWumpus() {
        return deadWumpus;
    }

    public boolean isStench() {
        return stench;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

    public void setLadder(boolean ladder) {
        this.ladder = ladder;
    }

    public void setPit(boolean pit) {
        this.pit = pit;
    }

    public void setBreeze(boolean breeze) {
        this.breeze = breeze;
    }

    public void setWumpus(boolean wumpus) {
        this.wumpus = wumpus;
    }

    public void setDeadWumpus(boolean deadWumpus) {
        this.deadWumpus = deadWumpus;
    }

    public void setStench(boolean stench) {
        this.stench = stench;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    @Override
    public String toString(){
        if(gold){return "G";}
        if(ladder){return "L";}
        if(pit){return "P";}
        if(breeze){return "B";}
        if(wumpus){return "W";}
        if(deadWumpus){return "D";}
        if(stench){return "S";}
        if(visited){return "V";}
        if(empty){return "*";}
        return "";
    }
}
