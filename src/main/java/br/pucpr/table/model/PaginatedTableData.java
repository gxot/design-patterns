package br.pucpr.table.model;

public class PaginatedTableData implements TableData {
  private final TableData data;
  private final int pageSize;
  private int currentPage;

  public PaginatedTableData(TableData data, int pageSize) {
    if (pageSize <= 0) {
      throw new IllegalArgumentException("Page size must be greater than zero");
    }
    this.data = data;
    this.pageSize = pageSize;
  }

  @Override
  public int rowCount() {
    return Math.min(pageSize, data.rowCount() - offset());
  }

  @Override
  public int colCount() {
    return data.colCount();
  }

  @Override
  public String header(int col) {
    return data.header(col);
  }

  @Override
  public String get(int row, int col) {
    return data.get(offset() + row, col);
  }

  public void nextPage() {
    if (offset() + pageSize < data.rowCount()) {
      currentPage++;
    }
  }

  public void previousPage() {
    if (currentPage > 0) {
      currentPage--;
    }
  }

  public int currentPage() {
    return currentPage;
  }

  private int offset() {
    return currentPage * pageSize;
  }
}
