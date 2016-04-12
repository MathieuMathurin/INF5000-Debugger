
package funlang;

import java.util.*;

import funlang.syntax.node.*;

public class FunctionTable {

    Map<String, FunctionInfo> functionMap = new LinkedHashMap<String, FunctionInfo>();

    public void add(
            FunctionInfo functionInfo,
            Token location) {

        if (this.functionMap.containsKey(functionInfo.getName())) {

            throw new SemanticException("Function already exists.", location);
        }

        this.functionMap.put(functionInfo.getName(), functionInfo);
    }

    public FunctionInfo get(
            String name) {

        return this.functionMap.get(name);
    }

}
