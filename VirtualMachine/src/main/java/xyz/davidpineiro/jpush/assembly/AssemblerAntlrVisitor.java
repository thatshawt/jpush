package xyz.davidpineiro.jpush.assembly;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssemblerAntlrVisitor extends xyz.davidpineiro.jpush.assembly.PushAssemblerBaseVisitor<List<Object>> {

    @Override
    public List<Object> visitStart(xyz.davidpineiro.jpush.assembly.PushAssemblerParser.StartContext ctx) {
        List<Object> instructions = new ArrayList<>();
        ctx.instruction().forEach((instructionContext) -> {
            instructions.add(visit(instructionContext).get(0));
        });
        return instructions;
    }

    @Override
    public List<Object> visitString(xyz.davidpineiro.jpush.assembly.PushAssemblerParser.StringContext ctx) {
        final String rawText = ctx.STRING().getText();
        //substring to remove quotes
        return List.of(rawText.substring(1,rawText.length()-1));
    }

    @Override
    public List<Object> visitInteger(xyz.davidpineiro.jpush.assembly.PushAssemblerParser.IntegerContext ctx) {
        return List.of(Integer.parseInt(ctx.INTEGER().getText()));
    }

    @Override
    public List<Object> visitDecimal(xyz.davidpineiro.jpush.assembly.PushAssemblerParser.DecimalContext ctx) {
        return List.of(Float.parseFloat(ctx.DECIMAL().getText()));
    }

    @Override
    public List<Object> visitBooleanz(xyz.davidpineiro.jpush.assembly.PushAssemblerParser.BooleanzContext ctx) {
        return List.of(Boolean.parseBoolean(ctx.BOOLEANZ().getText()));
    }

    @Override
    public List<Object> visitArgList(xyz.davidpineiro.jpush.assembly.PushAssemblerParser.ArgListContext ctx) {
        List<Object> args = new ArrayList<>();
        for(ParseTree child : ctx.children){
            Object val = visit(child).get(0);
            args.add(val);
        }
        return args;
    }

    @Override
    public List<Object> visitInstructionWithArgs(xyz.davidpineiro.jpush.assembly.PushAssemblerParser.InstructionWithArgsContext ctx) {
        final List<Object> args = visit(ctx.argList());
        final String iden = ctx.ID().getText();

        try {
            return List.of(parseInstruction(iden, args));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object> visitInstructionWithoutArgs(xyz.davidpineiro.jpush.assembly.PushAssemblerParser.InstructionWithoutArgsContext ctx) {
        try {
            return List.of(parseInstruction(ctx.ID().getText()));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Instruction parseInstruction(String iden) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = PushAssembler.instructionMap.get(iden.toLowerCase());

        return (Instruction) clazz.getConstructors()[0].newInstance();
    }

    private static Instruction parseInstruction(String iden, List<Object> args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = PushAssembler.instructionMap.get(iden.toLowerCase());

        return (Instruction) clazz.getConstructors()[0].newInstance(args.toArray());
    }

    public static void main(String[] args) {
        System.out.println("test");

        final String program =
                "IntConstant 1;IntConstant 2;IntAdd;"
                + "StringConstant \"sussy baka\";";

        xyz.davidpineiro.jpush.assembly.PushAssemblerLexer lexer = new xyz.davidpineiro.jpush.assembly.PushAssemblerLexer(CharStreams.fromString(program));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        xyz.davidpineiro.jpush.assembly.PushAssemblerParser parser = new xyz.davidpineiro.jpush.assembly.PushAssemblerParser(tokenStream);
        AssemblerAntlrVisitor visitor = new AssemblerAntlrVisitor();
        Object[] objects = visitor.visitStart(parser.start()).toArray();
        System.out.println(Arrays.toString(objects));
    }

}
