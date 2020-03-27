package Handlers;

public class IDAssigner {
    
    private int BaseId;

    public IDAssigner(int BaseId) {
        this.BaseId = BaseId;
    }
    
    public int next(){
        return BaseId++;
    }
    
    public int getCurrentId(){
        return BaseId;
    }
}
