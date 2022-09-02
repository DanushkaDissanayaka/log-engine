package com.logger.base.enums;

public enum RoleEnum {
    ADMIN(1), END_USER(2);

    private final int value;

    RoleEnum(int value) {this.value = value; }

    public int getValue() {
        return value;
    }

    public static RoleEnum parse(int id) {
        RoleEnum right = null; // Default
        for (RoleEnum item : RoleEnum.values()) {
            if (item.getValue()==id) {
                right = item;
                break;
            }
        }
        return right;
    }
}
