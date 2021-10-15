package br.com.money.credito.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FaixaSalarial {

    public static boolean isEntre1000And2000(BigDecimal salario){
        return salario.compareTo(BigDecimal.valueOf(1000)) >= 0 && salario.compareTo(BigDecimal.valueOf(2000)) <= 0;
    }

    public static boolean isEntre2001And3000(BigDecimal salario){
        return salario.compareTo(BigDecimal.valueOf(2001)) >= 0 && salario.compareTo(BigDecimal.valueOf(3000)) <= 0;
    }

    public static boolean isEntre3001And4000(BigDecimal salario){
        return salario.compareTo(BigDecimal.valueOf(3001)) >= 0 && salario.compareTo(BigDecimal.valueOf(4000)) <= 0;
    }

    public static boolean isEntre4001And5000(BigDecimal salario){
        return salario.compareTo(BigDecimal.valueOf(4001)) >= 0 && salario.compareTo(BigDecimal.valueOf(5000)) <= 0;
    }

    public static boolean isEntre5001And6000(BigDecimal salario){
        return salario.compareTo(BigDecimal.valueOf(5001)) >= 0 && salario.compareTo(BigDecimal.valueOf(6000)) <= 0;
    }

    public static boolean isEntre6001And7000(BigDecimal salario){
        return salario.compareTo(BigDecimal.valueOf(6001)) >= 0 && salario.compareTo(BigDecimal.valueOf(7000)) <= 0;
    }

    public static boolean isEntre7001And8000(BigDecimal salario){
        return salario.compareTo(BigDecimal.valueOf(7001)) >= 0 && salario.compareTo(BigDecimal.valueOf(8000)) <= 0;
    }

    public static boolean isEntre8001And9000(BigDecimal salario){
        return salario.compareTo(BigDecimal.valueOf(7001)) >= 0 && salario.compareTo(BigDecimal.valueOf(9000)) <= 0;
    }

    public static boolean isMaiorIgual9001(BigDecimal salario){
        return salario.compareTo(BigDecimal.valueOf(9001)) >= 0;
    }
}
