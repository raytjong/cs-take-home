package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderItem {
    private final Flavour flavour;
    private final int quantity;
}
