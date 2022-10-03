package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public abstract class BinaryIntOperation implements Instruction {

    //make this true to keep the inputs of the thing
    private boolean keepInputs = false;
    abstract int getResult(int a, int b);

    public void exec(PushVM vm){
        if(!keepInputs)//dont keep inputs (default behavior)
            vm.intStack.push(getResult(vm.intStack.pop(), vm.intStack.pop()));
        else//keep inputs
            vm.intStack.push(getResult(vm.intStack.get(vm.intStack.size()-1),
                                        vm.intStack.get(vm.intStack.size()-2)));
    }

}
