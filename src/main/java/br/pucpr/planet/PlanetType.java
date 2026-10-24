package br.pucpr.planet;

public enum PlanetType {
  ROCK("Rochoso"),
  GAS("Gasoso"),
  ICE("Gelado"),
  DWARF("Anão");

  private final String description;

  PlanetType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
