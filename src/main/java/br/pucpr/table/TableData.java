package br.pucpr.table;

public interface TableData {
  int getRowCount();

  int getColumnCount();

  String getHeader(int column);

  String getCell(int row, int column);
}
