'use strict';

//검색
const $searchType = document.getElementById('searchType');
const $keyword = document.getElementById('keyword');
const $searchBtn = document.getElementById('searchBtn');


//검색 버튼 클릭시
$searchBtn?.addEventListener('click', search_f);

//키워드 입력창 엔터키 눌렀을때 검색
$keyword?.addEventListener('keydown', e => {
       if(e.key === 'Enter') {
       search_f(e);
       }
    });

function search_f(e){
            //검색어 입력 유무체크
            if($keyword.value.trim().length === 0 ){
            alert('검색어를 입력하세요');
            $keyword.focus(); $keyword.select(); //커서이동
            return false;
            }
            const url = `/coffeeShop/list/1/${$searchType.value}/${$keyword.value}`;
            location.href= url;
}

// 대표 해시태그 정보 텍스트 앞에 # 붙이기
const $mainHashtag = document.querySelectorAll('.mainHashtag');

$mainHashtag.forEach(ele => {
  if(ele.textContent.trim().length > 1) {
    const $hashtagView = "#" + ele.textContent;
    ele.textContent = $hashtagView;
  }
});