'use strict';
const $body = document.querySelector('.header_top_wrap');
$body?.addEventListener('click', click_f, true);

const $detail_icon = document.querySelector('.detail-icon');
const $detailCHK = document.querySelector('.detailCHK');

function click_f(evt) {
//상세버튼 출력
if (evt.target.matches('.detail-icon')) {

 if ($detailCHK.checked == true) {
   $detail_icon.addEventListener('click', (evt) => {
    $detailCHK.checked = false;
   });
 } else if ($detailCHK.checked == false) {
   $detail_icon.addEventListener('click', (evt) => {
    $detailCHK.checked = true;
   });
 }
} 
}