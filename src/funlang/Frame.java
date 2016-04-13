
package funlang;

import java.util.*;

public class Frame {

    private int callingLine;

    private Frame previousFrame;

    private FunctionInfo functionInfo;

    private Map<String, Value> variables = new HashMap<String, Value>();
    public Map<String, Value> getVariables() { return variables; }

    private Value returnValue;

    public Frame(
            Frame previousFrame,
            FunctionInfo functionInfo) {
        this.previousFrame = previousFrame;
        this.functionInfo = functionInfo;
    }

    public int getCallingLine(){
        return this.callingLine;
    }

    public void setCallingLine(int line){
        this.callingLine = line;
    }

    public Frame getPreviousFrame() {

        return this.previousFrame;
    }

    public void setVariable(
            String name,
            Value arg) {

        this.variables.put(name, arg);
    }

    public Value getReturnValue() {

        return this.returnValue;
    }

    public void setReturnValue(
            Value returnValue) {

        this.returnValue = returnValue;
    }

    public Value getVariable(
            String name) {

        return this.variables.get(name);
    }

    public FunctionInfo getFunctionInfo() {

        return this.functionInfo;
    }
}
