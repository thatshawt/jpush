package xyz.davidpineiro.jpush.vm.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import xyz.davidpineiro.jpush.vm.DebugPushVM;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.bool.*;

import java.util.Arrays;

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
        vm.clearAllStacks();
        vm.addInstructions(new BoolConstantInstruction(bool));
        loadValues(bools);
    }

    static void loadValues(boolean[] bools){
        vm.clearAllStacks();
        for(boolean booleanlol : bools)
            vm.addInstructions(new BoolConstantInstruction(booleanlol));
    }

    void performTest(Instruction instruction, boolean expected){
        vm.clearAllStacks();
        vm.addInstructions(instruction);
        vm.runUntilHalt(true);
        Assertions.assertEquals(expected, vm.boolStack.pop());
    }

    void performStackEqualityTest(boolean[] stackBefore,
                                  Instruction[] instructions,
                                  boolean [] stackAfter){
        loadValues(stackBefore);
        vm.addInstructions(Arrays.asList(instructions));
        vm.runUntilHalt(true);
        Assertions.assertEquals(stackAfter.length, vm.boolStack.size());
        for(boolean bool : stackAfter)
            Assertions.assertEquals(bool, vm.boolStack.pop());
    }

    void performStackInequalityTest(boolean[] stackBefore,
                                  Instruction[] instructions,
                                  boolean [] stackAfter){
        loadValues(stackBefore);
        vm.runUntilHalt(true);
        Assertions.assertEquals(stackAfter.length, vm.boolStack.size());
        for(boolean bool : stackAfter)
            Assertions.assertNotEquals(bool, vm.boolStack.pop());
    }

    @Test
    void and(){
        performStackEqualityTest(new boolean[]{true, true},
                new Instruction[]{
                        new BoolAndInstruction(),
                },
                new boolean[]{true}
        );

        performStackEqualityTest(new boolean[]{true, false},
                new Instruction[]{
                        new BoolAndInstruction(),
                },
                new boolean[]{false}
        );

        performStackEqualityTest(new boolean[]{false, true},
                new Instruction[]{
                        new BoolAndInstruction(),
                },
                new boolean[]{false}
        );

        performStackEqualityTest(new boolean[]{false, false},
                new Instruction[]{
                        new BoolAndInstruction(),
                },
                new boolean[]{false}
        );

    }

    @Test
    void or(){
        performStackEqualityTest(new boolean[]{true, true},
                new Instruction[]{
                        new BoolOrInstruction(),
                },
                new boolean[]{true}
        );

        performStackEqualityTest(new boolean[]{true, false},
                new Instruction[]{
                        new BoolOrInstruction(),
                },
                new boolean[]{true}
        );

        performStackEqualityTest(new boolean[]{false, true},
                new Instruction[]{
                        new BoolOrInstruction(),
                },
                new boolean[]{true}
        );

        performStackEqualityTest(new boolean[]{false, false},
                new Instruction[]{
                        new BoolOrInstruction(),
                },
                new boolean[]{false}
        );

    }

    @Test
    void not(){
        performStackEqualityTest(new boolean[]{true},
                new Instruction[]{
                        new BoolNotInstruction(),
                },
                new boolean[]{false}
        );

        performStackEqualityTest(new boolean[]{false},
                new Instruction[]{
                        new BoolNotInstruction(),
                },
                new boolean[]{true}
        );
    }

    @Test
    void xor(){
        performStackEqualityTest(new boolean[]{true, true},
                new Instruction[]{
                        new BoolXorInstruction(),
                },
                new boolean[]{false}
        );
        performStackEqualityTest(new boolean[]{true, false},
                new Instruction[]{
                        new BoolXorInstruction(),
                },
                new boolean[]{true}
        );
        performStackEqualityTest(new boolean[]{false, true},
                new Instruction[]{
                        new BoolXorInstruction(),
                },
                new boolean[]{true}
        );
        performStackEqualityTest(new boolean[]{false, false},
                new Instruction[]{
                        new BoolXorInstruction(),
                },
                new boolean[]{false}
        );

    }

            /*
        BoolEqual
        BoolFlush
        BoolFromFloat
        BoolFromInt
        BoolPop
        BoolRand
        BoolRot
        BoolShove
        BoolStackDepth
        BoolSwap
        BoolYank
         */

    @Test
    void dup(){
        performStackEqualityTest(new boolean[]{true},
                new Instruction[]{
                        new BoolDupInstruction()
                },
                new boolean[]{true, true}
        );

        performStackEqualityTest(new boolean[]{false},
                new Instruction[]{
                        new BoolDupInstruction()
                },
                new boolean[]{false, false});

        performStackEqualityTest(new boolean[]{true,true},
                new Instruction[]{
                        new BoolDupInstruction()
                },
                new boolean[]{true, true, true});

        performStackEqualityTest(new boolean[]{false,false},
                new Instruction[]{
                        new BoolDupInstruction()
                },
                new boolean[]{false,false,false});

        performStackEqualityTest(new boolean[]{true},
                new Instruction[]{
                        new BoolDupInstruction()
                },
                new boolean[]{true, true});
    }

    @Test
    void equal(){
        performStackEqualityTest(new boolean[]{true, true},
                new Instruction[]{
                        new BoolEqualInstruction()
                },
                new boolean[]{true}
        );
        performStackEqualityTest(new boolean[]{true, false},
                new Instruction[]{
                        new BoolEqualInstruction()
                },
                new boolean[]{false}
        );
        performStackEqualityTest(new boolean[]{false, true},
                new Instruction[]{
                        new BoolEqualInstruction()
                },
                new boolean[]{false}
        );
        performStackEqualityTest(new boolean[]{false, false},
                new Instruction[]{
                        new BoolEqualInstruction()
                },
                new boolean[]{true}
        );
    }

    @Test
    void flush(){
        performStackEqualityTest(new boolean[]{true},
                new Instruction[]{
                        new BoolFlushInstruction()
                },
                new boolean[]{}
        );

        performStackEqualityTest(new boolean[]{false},
                new Instruction[]{
                        new BoolFlushInstruction()
                },
                new boolean[]{}
        );

        performStackEqualityTest(new boolean[]{true, true},
                new Instruction[]{
                        new BoolFlushInstruction()
                },
                new boolean[]{}
        );

        performStackEqualityTest(new boolean[]{true, false},
                new Instruction[]{
                        new BoolFlushInstruction()
                },
                new boolean[]{}
        );

        performStackEqualityTest(new boolean[]{false, false},
                new Instruction[]{
                        new BoolFlushInstruction()
                },
                new boolean[]{}
        );

    }

//    @Test
//    void duplicate(){
//        loadValues();
//    }

}
