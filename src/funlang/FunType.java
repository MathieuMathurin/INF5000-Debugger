
package funlang;

import funlang.syntax.node.*;

public enum FunType {

    BOOL,
    INT,
    STRING,
    VOID;

    public static FunType getType(
            PType type) {

        if (type instanceof ABoolType) {
            return BOOL;
        }

        if (type instanceof AIntType) {
            return INT;
        }

        if (type instanceof AStringType) {
            return FunType.STRING;
        }

        throw new InternalException("unhandled type");
    }

    public static FunType getType(
            PReturnType returnType) {

        if (returnType == null) {
            return VOID;
        }

        return getType(((AReturnType) returnType).getType());
    }
}
