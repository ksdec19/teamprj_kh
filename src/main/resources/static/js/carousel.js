'use strict';
var slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) {
  showSlides((slideIndex += n));
}

// Thumbnail image controls
function currentSlide(n) {
  showSlides((slideIndex = n));
}

function showSlides(n) {
  let i;
  const $shopImg = document.getElementsByClassName("shop-img");

  if (n > $shopImg.length) {
    slideIndex = 1;
  }
  if (n < 1) {
    slideIndex = $shopImg.length;
  }
  for (i = 0; i < $shopImg.length; i++) {
    $shopImg[i].style.display = "none";
  }

  $shopImg[slideIndex - 1].style.display = "block";
}
