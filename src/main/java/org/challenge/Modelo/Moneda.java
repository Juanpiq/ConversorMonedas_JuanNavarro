package org.challenge.Modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Moneda {
    private String baseCode;
    private String targetCode;
    private double conversionRate;

    public Moneda(MonedaDTO m){
        this.baseCode = m.base_code();
        this.targetCode = m.target_code();
        this.conversionRate = m.conversion_rate();
    }

}
