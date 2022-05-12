package com.kh.cafesurfer.web.form.coffeeShop;
public enum allday {
  allday("예"), noAllDay("아니오");

  private final String description;

  allday(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
