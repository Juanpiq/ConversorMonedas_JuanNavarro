package org.challenge.Modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Moneda {
    private String baseCode;
    private String targetCode;
    private double conversionRate;


}
