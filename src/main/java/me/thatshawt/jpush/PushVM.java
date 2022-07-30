package me.thatshawt.jpush;

import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.Instruction;
import me.thatshawt.jpush.instruction.bool.BooleanConstantInstruction;
import me.thatshawt.jpush.instruction.bool.BooleanNotInstruction;
import me.thatshawt.jpush.instruction.bool.BooleanXorInstruction;

import java.util.ArrayList;
import java.util.List;

public class PushVM {

    //special stacks
    public PushStack<Exception> exceptionStack;//all exceptions are stored here
    public PushStack<PushStack<?>> stackStack;//used to save other stacks contents

    //standard data stacks
    public PushStack<Boolean> booleanStack;
    public PushStack<Integer> integerStack;

    public PushVM() {
        resetStacks();
    }

    public void resetStacks(){
        this.stackStack = new PushStack<>();
        this.booleanStack = new PushStack<>();
        this.exceptionStack = new PushStack<>();
        this.integerStack = new PushStack<>();
    }

    public void printStacks(){
        System.out.print("bool stack: [");
        for(boolean b : booleanStack){
            System.out.printf(" %b,", b);
        }
        System.out.print("]\n");

        System.out.print("int stack: [");
        for(int i : integerStack){
            System.out.printf(" %d,", i);
        }
        System.out.print("]\n");
    }

    public void execute(Iterable<Instruction> instructions){
        for(Instruction instruction : instructions){
            try {
                instruction.execute(this);
            }catch(Exception e){
                exceptionStack.push(e);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PushVM vm = new PushVM();

        System.out.println("before");
        vm.printStacks();

        List<Instruction> instructions = new ArrayList<>();

        instructions.add(new BooleanConstantInstruction(true));
        instructions.add(new BooleanConstantInstruction(false));
        instructions.add(new BooleanNotInstruction());
        instructions.add(new BooleanXorInstruction());

        vm.execute(instructions);

        System.out.println("after");
        vm.printStacks();

    }

}
