package xyz.davidpineiro.jpush.vm.instruction;

import xyz.davidpineiro.jpush.vm.PushVM;

public interface Instruction {

    void exec(PushVM vm);

}
