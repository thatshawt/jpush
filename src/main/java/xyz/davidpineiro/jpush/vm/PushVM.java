package xyz.davidpineiro.jpush.vm;

import xyz.davidpineiro.jpush.vm.instruction.Instruction;

import java.util.List;

public class PushVM {

    protected boolean debug = false;

    public PushStack<Integer> intStack = new PushStack<Integer>();
    public PushStack<Float> floatStack = new PushStack<Float>();
    public PushStack<Boolean> boolStack = new PushStack<Boolean>();
//    public PushStack<String> stringStack = new PushStack<String>();
    public PushStack<Instruction> execStack = new PushStack<Instruction>();
//    public PushStack<Instruction> codeStack = new PushStack<Instruction>();

    public void step(){
        step(false);
    }

    //pops and executes one instruction from the exec stack
    public void step(boolean backwards){
        if(!backwards)
            execStack.pop().exec(this);
        else
            execStack.remove(0).exec(this);
    }

    public void clearAllStacks(){
        this.execStack.clear();
        this.intStack.clear();
        this.floatStack.clear();
        this.boolStack.clear();
    }

    public void addInstructions(List<Instruction> instructions){
        for(Instruction op : instructions)
            execStack.push(op);
    }

    public void addInstructions(Instruction instruction, Instruction... instructions){
        execStack.push(instruction);
        for(Instruction op : instructions)
            execStack.push(op);
    }

    //halting problem lol, i dont care
    public void runUntilHalt(){
        runUntilHalt(true);
    }

    public void runUntilHalt(boolean backwards){
        while(!execStack.isEmpty()){
            step(backwards);
        }
    }

}
