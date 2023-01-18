package xyz.davidpineiro.jpush.vm;

public class DebugPushVM extends PushVM {

    public boolean verbose = false;
    public DebugPushVM(){}

    @Override
    public void step(boolean backwards) {
        if(verbose){
            System.out.println("executed " + super.execStack.get(0).getClass().getSimpleName());
        }
        super.step(backwards);
    }

    public void printAllStacks(){
        System.out.println("Exec stack: " + this.execStack.toString());
        System.out.println("Int stack: " + this.intStack.toString());
        System.out.println("Code stack: " + this.codeStack.toString());
        System.out.println("Float stack: " + this.floatStack.toString());
        System.out.println("Boolean stack: " + this.boolStack.toString());
        System.out.println("String stack: " + this.stringStack.toString());
    }

}
