package br.pucpr.planet;

import br.pucpr.table.TableData;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class PlanetsTableData implements TableData {
  private static final Locale LOCALE = Locale.forLanguageTag("pt-BR");
  private static final String[] HEADERS = {
    "%-20s".formatted("Nome"),
    "%-10s".formatted("Diâmetro"),
    "%-15s".formatted("Dist. sol (km)"),
    "%-15s".formatted("Dist. sol (ua)"),
    "%-10s".formatted("Tipo")
  };

  private final List<Planet> planets;

  public PlanetsTableData(List<Planet> planets) {
    this.planets = planets == null ? new ArrayList<>() : new ArrayList<>(planets);
    this.planets.removeIf(Objects::isNull);
  }

  @Override
  public int getRowCount() {
    return planets.size();
  }

  @Override
  public int getColumnCount() {
    return HEADERS.length;
  }

  @Override
  public String getHeader(int column) {
    return HEADERS[column];
  }

  @Override
  public String getCell(int row, int column) {
    var planet = planets.get(row);
    return switch (column) {
      case 0 -> formatName(planet.name());
      case 1 -> String.format(LOCALE, "%,.1f", planet.diameterKm());
      case 2 -> String.format(LOCALE, "%,d", planet.sunDistanceKm());
      case 3 -> String.format(LOCALE, "%,.2f", Planet.kmToAu(planet.sunDistanceKm()));
      case 4 -> formatType(planet.type());
      default -> throw new IndexOutOfBoundsException(column);
    };
  }

  private static String formatName(String name) {
    return name == null || name.isEmpty() ? "NÃO INFORMADO" : name;
  }

  private static String formatType(PlanetType type) {
    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gasoso";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }
}
