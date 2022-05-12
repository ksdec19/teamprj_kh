'use strict';
// Event in Search-Bar
// 검색창 안 이벤트 (상세검색 / 검색 버튼)

/*  
상세검색 아이콘 클릭 시 

  아이콘 변경(삼각형) + 상세내용 출력
  다시 클릭하면 아이콘 변경(역삼각형) + 상세내용 숨기기

*/



const beverageBtn = document.querySelector('.btn_beverage');
const dessertBtn = document.querySelector('.btn_dessert');
const goodsBtn = document.querySelector('.btn_goods');
const reviewBtn = document.querySelector('.btn_review');
const beverageMenu = document.querySelector('.menu_beverage');
const dessertMenu = document.querySelector('.menu_dessert');
const goodsMenu = document.querySelector('.menu_goods');
const reviewMenu = document.querySelector('.menu_review');
let flagBeverage = true;
let flagDessert = true;
let flagGoods = true;
let flagReview = true;

function beverageShowHide() {
  if(flagBeverage==true){
    beverageMenu.style.display="none";
    beverageBtn.style.backgroundImage = "url('/img/icon/icon-triangle-inverted-white.png')";
    flagBeverage = false;
  }
  else if(flagBeverage==false){
    beverageMenu.style.display="block";
    beverageBtn.style.backgroundImage = "url('/img/icon/icon-triangle-white.png')";
    flagBeverage = true;
  };
}
function dessertShowHide() {
  if(flagDessert==true){
    dessertMenu.style.display="none";
    dessertBtn.style.backgroundImage = "url('/img/icon/icon-triangle-inverted-white.png')";
    flagDessert = false;
  }
  else if(flagDessert==false){
    dessertMenu.style.display="block";
    dessertBtn.style.backgroundImage = "url('/img/icon/icon-triangle-white.png')";
    flagDessert = true;
  } 
};
function goodsShowHide() {
  if(flagGoods==true){
    goodsMenu.style.display="none";
    goodsBtn.style.backgroundImage = "url('/img/icon/icon-triangle-inverted-white.png')";
    flagGoods = false;
  }
  else if(flagGoods==false){
    goodsMenu.style.display="block";
    goodsBtn.style.backgroundImage = "url('/img/icon/icon-triangle-white.png')";
    flagGoods = true;
  } 
};
function reviewShowHide() {
  if(flagReview==true){
    reviewMenu.style.display="none";
    reviewBtn.style.backgroundImage = "url('/img/icon/icon-triangle-inverted-white.png')";
    flagReview = false;
  }
  else if(flagReview==false){
    reviewMenu.style.display="block";
    reviewBtn.style.backgroundImage = "url('/img/icon/icon-triangle-white.png')";
    flagReview = true;
  } 
};

beverageBtn.addEventListener("click", beverageShowHide);
dessertBtn.addEventListener("click", dessertShowHide);
goodsBtn.addEventListener("click", goodsShowHide);
reviewBtn.addEventListener("click", reviewShowHide);


