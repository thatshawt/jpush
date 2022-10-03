package xyz.davidpineiro.jpush.vm.instruction.integer;

import java.util.Random;

//RAND: Pushes a newly generated random INTEGER that is greater
// than or equal to MIN-RANDOM-INTEGER and less than or equal
// to MAX-RANDOM-INTEGER
public class IntRandInstruction extends NullaryIntOperation {
    private static Random random = new Random();

    @Override
    int getResult() {
        return random.nextInt();
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.intStack.push(random.nextInt());
//    }
}
