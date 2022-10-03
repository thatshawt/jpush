package xyz.davidpineiro.jpush.vm.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import xyz.davidpineiro.jpush.vm.DebugPushVM;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.bool.*;

public class BooleanOpsTest {

    static DebugPushVM vm = new DebugPushVM();

    @BeforeEach
    void idkBruh(){
        vm.clearAllStacks();
    }

    @AfterEach
    void idkBruh2(){
        vm.clearAllStacks();
    }

    static void loadValues(boolean bool, boolean... bools){
        vm.addInstructions(new BoolConstantInstruction(bool));
        for(boolean booleanlol : bools)
            vm.addInstructions(new BoolConstantInstruction(booleanlol));
    }

    void performTest(Instruction instruction, boolean expected){
        vm.addInstructions(instruction);
        vm.runUntilHalt(true);
        Assertions.assertEquals(expected, vm.boolStack.pop());
    }

    @Test
    void and(){
        loadValues(true, true);
        performTest(new BoolAndInstruction(), true);

        loadValues(true, false);
        performTest(new BoolAndInstruction(), false);

        loadValues(false, true);
        performTest(new BoolAndInstruction(), false);

        loadValues(false, false);
        performTest(new BoolAndInstruction(), false);
    }

    @Test
    void or(){
        loadValues(true, true);
        performTest(new BoolOrInstruction(), true);

        loadValues(true, false);
        performTest(new BoolOrInstruction(), true);

        loadValues(false, true);
        performTest(new BoolOrInstruction(), true);

        loadValues(false, false);
        performTest(new BoolOrInstruction(), false);
    }

    @Test
    void not(){
        loadValues(false);
        performTest(new BoolNotInstruction(), true);

        loadValues(true);
        performTest(new BoolNotInstruction(), false);
    }

    @Test
    void xor(){
        loadValues(true, true);
        performTest(new BoolXorInstruction(), false);

        loadValues(true, false);
        performTest(new BoolXorInstruction(), true);

        loadValues(false, true);
        performTest(new BoolXorInstruction(), true);

        loadValues(false, false);
        performTest(new BoolXorInstruction(), false);

        //TODO: make a test for no inputs
//        vm.clearAllStacks();
//        vm.addInstructions(new BoolXorInstruction());
//        vm.runUntilHalt(true);
//        assertEquals();
    }

//    @Test
//    void duplicate(){
//        loadValues();
//    }

}
