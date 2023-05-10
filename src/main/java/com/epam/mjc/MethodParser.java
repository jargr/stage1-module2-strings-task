package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] parts = signatureString.split("\\(");
        var declaration = parts[0].split("\\s+");
        var modifier = declaration.length == 3 ? declaration[0] : null;
        var returnType = declaration.length == 3 ? declaration[1] : declaration[0];
        var methodName = declaration.length == 3 ? declaration[2] : declaration[1];


        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (parts[1].trim().length() > 1) {
            var args = parts[1].substring(0, parts[1].length() - 1).split(",");

            for (String arg : args) {
                String[] typeAndName = arg.trim().split("\\s+");

                arguments.add(new MethodSignature.Argument(typeAndName[0], typeAndName[1]));
            }
        }
        var signature = new MethodSignature(methodName, arguments);
        signature.setAccessModifier(modifier);
        signature.setReturnType(returnType);

        return signature;

    }
}
