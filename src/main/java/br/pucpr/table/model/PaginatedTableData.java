package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.List;

public class PaginatedTableData implements TableData {
  private final TableData data;
  private final int pageSize;
  private final List<TableDataObserver> observers = new ArrayList<>();
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
      notifyObservers();
    }
  }

  public void previousPage() {
    if (currentPage > 0) {
      currentPage--;
      notifyObservers();
    }
  }

  public void addObserver(TableDataObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(TableDataObserver observer) {
    observers.remove(observer);
  }

  public int currentPage() {
    return currentPage;
  }

  private int offset() {
    return currentPage * pageSize;
  }

  private void notifyObservers() {
    for (var observer : observers) {
      observer.onDataChanged();
    }
  }
}
