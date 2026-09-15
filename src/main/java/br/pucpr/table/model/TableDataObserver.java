package br.pucpr.table.model;

@FunctionalInterface
public interface TableDataObserver {
  void onDataChanged();
}
