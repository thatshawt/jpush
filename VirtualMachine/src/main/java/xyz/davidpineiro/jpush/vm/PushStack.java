package xyz.davidpineiro.jpush.vm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PushStack<T> {

    private Map<Integer, Stack<T>> stacks;
    private int stackIndex = 0;

    public PushStack(){
        this(10);
    }

    /**
     * the stacks will initialize in both directions from index zero
     * @param initStacks the number of stacks to initialize
     */
    public PushStack(int initStacks){
        stacks = new HashMap<>();
        boolean even = initStacks % 2 == 0;
        int half = initStacks/2;
        int start = -half;
        int end = even ? half : half+1;

        for(int i=start;i<end;i++){
            stacks.put(i, new Stack<T>());
        }

    }

    public int getStackIndex(){
        return this.stackIndex;
    }

    private Stack<T> getCurrentStack(){
        return stacks.get(stackIndex);
    }

    public void changeIndex(int index){
        if(!stacks.containsKey(index))stacks.put(index, new Stack<>());
        stackIndex = index;
    }

    public void incrementIndex(){
        changeIndex(stackIndex+1);
    }

    public void decrementIndex(){
        changeIndex(stackIndex-1);
    }

    public T pop(){
        return getCurrentStack().pop();
    }

    public T remove(int index){
        return getCurrentStack().remove(index);
    }

    public void push(T item){
        getCurrentStack().push(item);
    }

    public int size(){
        return getCurrentStack().size();
    }

    public T peek(){
        return getCurrentStack().peek();
    }

    public T get(int index){
        return getCurrentStack().get(index);
    }

    public void insertElementAt(T element, int index){
        getCurrentStack().insertElementAt(element, index);
    }

    public boolean isEmpty(){
        return getCurrentStack().isEmpty();
    }

    public void clear(){
        getCurrentStack().clear();
    }

    @Override
    public String toString() {
        return "PushStack{" +
                "getCurrentStack()=" + getCurrentStack() +
                ", stackIndex=" + stackIndex +
                '}';
    }
}
