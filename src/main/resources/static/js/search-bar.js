'use strict';
// Event in Search-Bar
// 검색창 안 이벤트 (상세검색 / 검색 버튼)

/*  
상세검색 아이콘 클릭 시 

  아이콘 변경(삼각형) + 상세내용 출력
  다시 클릭하면 아이콘 변경(역삼각형) + 상세내용 숨기기

*/

const $detail = document.querySelector('.detail-icon');
const locSelected = document.querySelector('.location-selected-wrap');
const sDtWrap = document.querySelector('.search-detail-wrap');
const sContentWrap = document.querySelector('.search-detail-content-wrap');
let flag = false;

locSelected.style.display="none";
sDtWrap.style.display="none";
sContentWrap.style.display="none";

function hiddenStage() {
  if(flag==true){
    locSelected.style.display="none";
    sDtWrap.style.display="none";
    sContentWrap.style.display="none";
    $detail.style.backgroundImage = "url('../icon/icon-triangle_inverted.png')";
    flag = false;
  }
  else if(flag==false){
    locSelected.style.display="block";
    sDtWrap.style.display="block";
    sContentWrap.style.display="block";
    $detail.style.backgroundImage = "url('../icon/icon-triangle.png')";
    flag = true;
  }
};

$detail.addEventListener("click", hiddenStage);


