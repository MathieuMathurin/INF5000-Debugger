
package funlang;

import java.util.*;

import funlang.syntax.node.*;

public class Semantics {

    private FunctionTable functionTable;

    private Map<ANumTerm, Integer> numbers;

    public Semantics(
            FunctionTable functionTable,
            Map<ANumTerm, Integer> numbers) {

        this.functionTable = functionTable;
        this.numbers = numbers;
    }

    public FunctionTable getFunctionTable() {

        return this.functionTable;
    }

    public int getNumber(
            ANumTerm node) {

        return this.numbers.get(node);
    }

}
