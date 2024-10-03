package com.btg.operaciones.enums;

public enum TipoTransaccionEnum {
    APERTURAR(1),
    CANCELAR(2);

    private final int value;

    TipoTransaccionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
