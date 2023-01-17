grammar PushAssembler;

@header {
import xyz.davidpineiro.jpush.assembly.PushAssemblerParser;
import xyz.davidpineiro.jpush.assembly.PushAssemblerVisitor;
}

start
    : ( instruction )* ;

instruction
    : ID argList EOL   #InstructionWithArgs
    | ID EOL           #InstructionWithoutArgs
    ;

argList
    : (string | integer | decimal | booleanz) + ;

string: STRING;
integer: INTEGER;
decimal: DECIMAL;
booleanz: BOOLEANZ;

DECIMAL: '-'? DIGIT+ '.' DIGIT+;
INTEGER: '-'? DIGIT+;
BOOLEANZ: 'true' | 'false';

EOL
   : ';'
   | [\r\n] +
   ;

STRING:     '"' (~["\\\r\n])* '"';
ID : [a-zA-Z_] [a-zA-Z]*;
WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
LINE_COMMENT:       '#' ~[\r\n]*    -> channel(HIDDEN);



fragment DIGIT: [0-9];

