package me.thatshawt.jpush.instruction;

import me.thatshawt.jpush.PushVM;

public abstract class Instruction {

    public abstract void execute(PushVM vm);

    public abstract String toString();

}
