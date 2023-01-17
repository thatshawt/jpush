package xyz.davidpineiro.jpush.vm;

import xyz.davidpineiro.jpush.vm.instruction.Instruction;

/*
-add "struct-like" stacks that can be user defined and has their own instructions and stuff.
    -   this can be made with a scripting language or something and composing other
        more fundamental stacks like bool and int or something.
        this will most likely be at the language level, idk yet.
-i am just making a scripting language at this point...
    so what i thought of is to make each instruction generic and it pops one
    type from the type stack to see which stack to apply the instruction to.
    so there is a FloatAdd and an Add instruction. FloatAdd adds two floats,
    Add will consume one type from the type stack and then use that to see which
    stack to operate in. so "FloatType, Add" is equivalent to "FloatAdd"

-you have seen this input before and you apply the output.
humans are powerful because they can generate many inputs/outputs for one experience.
a measure of iq could be how well you are able to use past information to
solve a new problem.
like, iq and information required to solve a problem are linear opposites.
iq decreases when you need more information to solve a problem.
 */

public class PushVM {

    public PushStack<Integer> intStack = new PushStack<Integer>();
    public PushStack<Float> floatStack = new PushStack<Float>();
    public PushStack<Boolean> boolStack = new PushStack<Boolean>();
    public PushStack<String> stringStack = new PushStack<String>();
    public PushStack<Instruction> execStack = new PushStack<Instruction>();
    public PushStack<Instruction> codeStack = new PushStack<Instruction>();

    //pops and executes one instruction from the exec stack
    public void step(boolean backwards){
        if(!backwards)
            execStack.pop().exec(this);
        else
            execStack.remove(0).exec(this);
    }

    public void clearDataStacks(){
//        this.execStack.clear();
        this.intStack.clear();
        this.floatStack.clear();
        this.boolStack.clear();
        this.stringStack.clear();
        this.codeStack.clear();
    }

    public void addInstructions(Instruction instruction, Instruction... instructions){
        execStack.push(instruction);
        for(Instruction op : instructions)
            execStack.push(op);
    }

    public void addInstructions(Iterable<Instruction> instructions){
        for(Instruction op : instructions)
            execStack.push(op);
    }

    public void runUntilHalt(){
        while(!execStack.isEmpty()){
            step(true);
        }
    }

}
