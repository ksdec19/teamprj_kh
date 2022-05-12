package com.kh.cafesurfer.web.form.coffeeShop;
public enum parking {
  Parking("예"), noParking("아니오");

  private final String description;

  parking(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
