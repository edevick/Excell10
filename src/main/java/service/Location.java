package service;

public class Location {
    int rowIndex;
    int columnIndex;

    public Location(int rowIndex, int columnIndex) {

        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public String toString() {
        return "Location{" +
                "rowIndex=" + rowIndex +
                ", columnIndex=" + columnIndex +
                '}';
    }
}

