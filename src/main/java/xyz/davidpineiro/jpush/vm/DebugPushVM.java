package xyz.davidpineiro.jpush.vm;

public class DebugPushVM extends PushVM {

    public boolean verbose = false;
    public DebugPushVM(){

    }

    @Override
    public void step() {
        if(verbose){
            System.out.println("executed " + super.execStack.peek().getClass().getSimpleName());
        }
        super.step();
    }

    public void printAllStacks(){
        System.out.println("Int stack: " + this.intStack.toString());
//        System.out.println("String stack: " + this.stringStack.toString());
        System.out.println("Float stack: " + this.floatStack.toString());
        System.out.println("Boolean stack: " + this.boolStack.toString());
        System.out.println("Exec stack: " + this.execStack.toString());
    }

}
