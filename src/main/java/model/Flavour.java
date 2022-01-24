package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Flavour {
    private final String name;
    private final BigDecimal price;
    private final Promotion promotion;

    public Flavour(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.promotion = null;
    }
}
