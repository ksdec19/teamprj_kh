'use strict';

//현재 페이지 url 링크 복사
const $copyLink = document.querySelector('.link-btn');
$copyLink.addEventListener('click', evt => {
  shopUrl.style.display='block';
  shopUrl.select();
  document.execCommand('copy');
  shopUrl.style.display='none';
  alert('현재 링크가 복사 되었습니다.');
});