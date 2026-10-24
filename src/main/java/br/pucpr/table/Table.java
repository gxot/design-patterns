package br.pucpr.table;

import br.pucpr.user.Theme;

public class Table {
  public void print(TableData data, boolean alignRight, Theme theme) {
    if (data == null || data.getRowCount() == 0) {
      System.out.println("ERRO: Tabela vazia ou nula.");
      return;
    }

    var header = formatHeader(data);
    var border = theme.getBorderChar().repeat(header.length());
    var indentation = alignRight ? " ".repeat(20) : "";
    System.out.println(indentation + border);
    System.out.println(indentation + header);
    System.out.println(indentation + border);
    for (int row = 0; row < data.getRowCount(); row++) {
      System.out.println(indentation + formatRow(data, row));
    }
    System.out.println(indentation + border);
  }

  private static String formatHeader(TableData data) {
    var header = new StringBuilder("|");
    for (int column = 0; column < data.getColumnCount(); column++) {
      header.append(" ").append(data.getHeader(column)).append(" |");
    }
    return header.toString();
  }

  private static String formatRow(TableData data, int row) {
    var line = new StringBuilder("|");
    for (int column = 0; column < data.getColumnCount(); column++) {
      int width = data.getHeader(column).length();
      var cell = String.format("%-" + width + "." + width + "s", data.getCell(row, column));
      line.append(" ").append(cell).append(" |");
    }
    return line.toString();
  }
}
