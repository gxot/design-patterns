package br.pucpr.planet;

import br.pucpr.user.Theme;
import java.util.ArrayList;
import java.util.Locale;

public class PlanetasPrinter {
  private static final double ASTRONOMICAL_UNIT_KM = 149_600_000.0;
  private static final Locale LOCALE = Locale.forLanguageTag("pt-BR");
  private static final String HEADER_FORMAT = "| %-10s | %-12s | %-17s | %-14s | %-7s |%n";
  private static final String ROW_FORMAT = "| %-10s | %,12.1f | %,17d | %,14.2f | %-7s |%n";

  public void print(ArrayList<Planet> planets, boolean alignRight, Theme theme) {
    if (planets == null || planets.isEmpty()) {
      System.out.println("ERRO: Lista de planetas vazia ou nula.");
      return;
    }

    var header =
        String.format(
            HEADER_FORMAT, "Nome", "Diâmetro", "Dist. sol (km)", "Dist. sol (ua)", "Tipo");
    var border =
        theme.getBorderChar().repeat(header.stripTrailing().length()) + System.lineSeparator();
    var table = new StringBuilder(border).append(header).append(border);
    for (var planet : planets) {
      if (planet == null) {
        continue;
      }
      table.append(formatPlanet(planet));
    }
    table.append(border);
    printTable(table, alignRight);
  }

  private static String formatPlanet(Planet planet) {
    return String.format(
        LOCALE,
        ROW_FORMAT,
        planet.name(),
        planet.diameterKm(),
        planet.sunDistanceKm(),
        planet.sunDistanceKm() / ASTRONOMICAL_UNIT_KM,
        planet.type().getDescription());
  }

  private static void printTable(StringBuilder table, boolean alignRight) {
    var indentation = alignRight ? " ".repeat(20) : "";
    for (var line : table.toString().split("\\R")) {
      System.out.println(indentation + line);
    }
  }
}
