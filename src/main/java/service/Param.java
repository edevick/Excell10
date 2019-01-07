package service;

public enum Param {
    ADD_COLUMN("addColumn"),
    ADD_ROW("addRow"),
    DELETE_COLUMN("deleteColumn"),
    DELETE_ROW("deleteRow");

    private String typeOfParam;

    Param(String typeOfParam) {
        this.typeOfParam = typeOfParam;
    }
}
