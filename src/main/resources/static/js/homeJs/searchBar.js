'use strict';

const $hashtag = document.getElementById('hashtags');
const tabList = document.querySelectorAll('.detailmenu .Area li');
const contents = document.querySelectorAll('.detailmenu .Areaboxs .cont')
let activeCont = ''; // 현재 활성화 된 컨텐츠 (기본:#tab1 활성화)

for (var i = 0; i < tabList.length; i++) {
  tabList[i].querySelector('.btn').addEventListener('click', function (e) {
    e.preventDefault();
    for (var j = 0; j < tabList.length; j++) {
      // 나머지 버튼 클래스 제거
      tabList[j].classList.remove('is_on');

      // 나머지 컨텐츠 display:none 처리
      contents[j].style.display = 'none';
    }

    // 버튼 관련 이벤트
    this.parentNode.classList.add('is_on');

    // 버튼 클릭시 컨텐츠 전환
    activeCont = this.getAttribute('href');
    document.querySelector(activeCont).style.display = 'block';
  });
}
//해시태그 검색 카운트 증가



//해시태그 검색 카운트 감소


//해시태그 탭 클릭 이벤트
//const $coffee = 'http://localhost:9080/search/hashtag/coffee';
//const $beverage = 'http://localhost:9080/search/hashtag/beverage';
//const $dessert = 'http://localhost:9080/search/hashtag/dessert';
//const $goods = 'http://localhost:9080/search/hashtag/goods';
//const $mood = 'http://localhost:9080/search/hashtag/mood';
//const $view = 'http://localhost:9080/search/hashtag/view';
//const $location = 'http://localhost:9080/search/hashtag/coffee';

newItems( 'http://localhost:9080/search/hashtag/coffee', '.btn-coffee');
newItems( 'http://localhost:9080/search/hashtag/beverage', '.btn-beverage');
newItems( 'http://localhost:9080/search/hashtag/dessert', '.btn-dessert');
newItems( 'http://localhost:9080/search/hashtag/goods', '.btn-goods');
newItems( 'http://localhost:9080/search/hashtag/mood', '.btn-mood');
newItems( 'http://localhost:9080/search/hashtag/view', '.btn-view');
newItems( 'http://localhost:9080/search/hashtag/loc', '.btn-location');

//해시태그 목록 출력
function newItems(items1, items2) {
  list_b();
  function list_b(evt) {

    console.log();
    fetch(items1 , {
      method: 'GET',
    })
        .then((res) => res.json())
        .then((res) => {
          console.log(res);
          displayItem2(res.data);
        })
        .catch((err) => {
          console.error('Err:', err);
        });
  }

//해시태그 목록 html
  function displayItem2(data) {
    let $hashtagList =``;
    data.forEach( ele => {
      $hashtagList +=
          `<label title="${ele.hashtagName}" class="${ele.hashtagName} hashtagList"
         data-hashtag-Numbering="${ele.hashtagNumbering}" data-hashtag-name="${ele.hashtagName}" data-hashtag-id="${ele.hashtagId}">
     <input type="checkbox" autocomplete="off" class="hashtag_name" id="${ele.hashtagId}">
     #${ele.hashtagName}
   </label>`;
    });

    const $hashtagBox = document.querySelector(items2);
    $hashtagBox.innerHTML = $hashtagList
//해시태그 목록 출력 끝
  }
}

document.querySelector('.Areaboxs').addEventListener('click', (evt) => {
//   console.log(evt.target);
  if(evt.target.tagName !== 'LABEL' ) return;

  newItem(evt.target.dataset.hashtagName, evt.target.dataset.hashtagId, evt.target.dataset.hashtagNumbering, evt.target);

//    console.log(evt.target);
  console.log(evt.target.dataset.hashtagName);
}, true);



document.getElementById('hashtags').addEventListener('click', (evt) => {
  console.log("확인용");
//    if(evt.target.tagName !== 'LABEL' ) return;

//const $li = evt.target.closest(`span`);
//console.log($li.innerText);
  console.log(evt.target.closest(`span`).innerText);
  console.log(evt.target.closest(`button`));

  newItemBtn(evt.target.closest(`span`).innerText, evt.target.closest(`button`), evt.target, evt.target.closest(`li`));

//    newItemBtn(evt.target.closest(`li`));

}, true);



function newItem(item1, item2, item3, item4) {
  const $item1 = document.querySelector(item1);
  const $item2 = document.getElementById(item2);

  if ($item2.checked == false) {
    const $Ele = makeElement('ul', { 'class': '', 'id': item3 },
        makeElement('li', { 'class': '', 'id': '' },
            makeElement('span', { 'class': 'tag_name' }, item1),

        ));
    $hashtag.appendChild($Ele);

    //해시태그가 선택되면 색깔을 빨간색으로 변경
    if($hashtag.childElementCount <= 4) {
      item4.style.color = "red";
      console.log('체크');
    }


    //해시태그가 선택되면 키워드 검색 비활성화
    if ($hashtag.childElementCount >= 1) {
      searchType.options[1].selected = true; //select option value 해시태그로 바뀜
      keyword.value = '';
      keyword.style.display = 'none';
    }

    //해시태그 검색 시 태그 개수 제한
    if ($hashtag.childElementCount > 4) {
      alert('해시태그를 이용한 검색은 최대 4개까지 허용됩니다.');
      const $lastChild = $hashtag.lastChild;
      $hashtag.removeChild($lastChild);
    }

  } else if ($item2.checked == true) {
    const $item3 = document.getElementById(item3);
    $hashtag.removeChild($item3);


    item4.style.color = "black";
    console.log('체크해제');

    //해시태그 선택이 0일때 키워드 검색 활성화
    if ($hashtag.childElementCount == 0) {
      searchType.options[0].selected = true; //select option value 키워드로 바뀜
      keyword.value = '';
      keyword.style.display = 'inline-block';
    }
  }
  console.log($hashtag.getElementsByClassName('tag_name'));

}

function newItemBtn(itemBtn1, itemBtn2, itemBtn4) {
  console.log('체크체크2222222222');
  console.log(itemBtn1);
  console.log(itemBtn2);

  console.log(itemBtn4);
//띄어쓰기를 .으로 변경

  console.log(itemBtn1.replaceAll(" ","."));
  const $itemBtn = itemBtn1.replaceAll(" ",".");

  const $itemBtn1 = document.querySelector(""+'.'+$itemBtn+"");
  console.log($itemBtn1);

  $itemBtn1.click();

}





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
  //해시태그 검색
  if ($hashtag.childElementCount >= 1) {
    let $selectedTags = $hashtag.getElementsByClassName('tag_name');
    $keyword.value = ``;
    for (let i = 0; i < $selectedTags.length; i++) {
      $keyword.value += $selectedTags[i].textContent;
      if (i != $selectedTags.length-1) {
        $keyword.value += ', ';
      }
    }
    const url = `/coffeeShop/list/1/${$searchType.value}/${$keyword.value}`;
    location.href= url;
  }

  //키워드 검색
  //입력값 유무 체크
  if($keyword.value.trim().length === 0 ){
    alert('검색어를 입력하세요');
    $keyword.focus(); $keyword.select(); //커서이동
    return false;
  }

  const url = `/coffeeShop/list/1/${$searchType.value}/${$keyword.value}`;
  location.href= url;
}

function makeElement(tagName, attributes, ...contents){
  //태그생성
  const elementNode = document.createElement(tagName);

  //어트리뷰트 추가
  if(attributes){
    for(let attr in attributes){
      if(attributes.hasOwnProperty(attr)) {
        elementNode.setAttribute(attr,attributes[attr] );
      }
    }
  }

  //컨텐츠 생성
  for(let i=0; i < contents.length; i++){
    let child = contents[i];
    //3번째 매개값이 문자열이면 텍스트 노드생성
    if(typeof child === 'string' ) {
      child = document.createTextNode(child);
    }
    elementNode.appendChild(child);
  }

  return elementNode;
}