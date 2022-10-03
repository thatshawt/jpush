package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatDupInstruction extends UnaryFloatOperation {
    FloatDupInstruction(){
        this.keepInputs = true;
    }

    @Override
    float getResult(float a) {
        return a;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push(vm.floatStack.peek());
//    }
}
