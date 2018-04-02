package com.utils;

import java.util.Random;

public class CodeGenerator {
    private static final int DEFAULT_CODE_SIZE = 4;
    private static final int DEFAULT_MAX_VALUE = 10;

    public static String generateCode(){
        return generateCode(DEFAULT_CODE_SIZE);
    }

    public static String generateCode(int codeSize){
        Random rand = new Random();
        String code = "";
        for(int i = 0; i < codeSize; i++){
            code += rand.nextInt(DEFAULT_MAX_VALUE);
        }
        return code;
    }
}
