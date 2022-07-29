package me.thatshawt.jpush;

import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.Instruction;
import me.thatshawt.jpush.instruction.bool.BooleanConstantInstruction;
import me.thatshawt.jpush.instruction.bool.BooleanXorInstruction;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PushVM {

    public PushStack<Boolean> booleanStack;

    public PushVM() {
        resetStacks();
    }

    public void resetStacks(){
        this.booleanStack = new PushStack<>();
    }

    public void printStacks(){
        System.out.print("bool stack: [");
        for(boolean b : booleanStack){
            System.out.printf(" %b,", b);
        }
        System.out.print("]\n");
    }

    public void execute(Instruction instruction){
        instruction.execute(this);
    }

    public void execute(Iterable<Instruction> instructions){
        for(Instruction instruction : instructions){
            instruction.execute(this);
        }
    }


    public static void main(String[] args) {
        PushVM vm = new PushVM();

        System.out.println("before");
        vm.printStacks();

        List<Instruction> instructions = new ArrayList<>();

        instructions.add(new BooleanConstantInstruction(false));
        instructions.add(new BooleanConstantInstruction(true));
        instructions.add(new BooleanXorInstruction());

        vm.execute(instructions);

        System.out.println("after");
        vm.printStacks();

    }

}
