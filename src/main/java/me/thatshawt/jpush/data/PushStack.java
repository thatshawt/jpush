package me.thatshawt.jpush.data;

import java.util.*;
import java.util.function.Consumer;

public class PushStack<T> implements Iterable<T>{
    private final Map<Integer, Stack<T>> stacks = new HashMap<>();

    /**
     * this is the "bank index". each index corresponds to a new stack.
     */
    public int index = 0;

    private void initStack(){
        if(!stacks.containsKey(index))stacks.put(index, new Stack<>());
    }

    public T pop(){
        initStack();
        return stacks.get(index).pop();
    }

    public void push(T val){
        initStack();
        stacks.get(index).push(val);
    }

    public int size(){
        initStack();
        return stacks.get(index).size();
    }

    @Override
    public Iterator<T> iterator() {
        initStack();
        return stacks.get(index).iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        initStack();
        stacks.get(index).forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        initStack();
        return stacks.get(index).spliterator();
    }

    public T lastElement() {
        initStack();
        return stacks.get(index).lastElement();
    }

    public void clear() {
        initStack();
        stacks.get(index).clear();
    }
}
