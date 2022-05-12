'use strict';
$body?.addEventListener('click', click_f, true);

const $tab1box = document.querySelector('.asd1');
const $tab2box = document.querySelector('.asd2');
const $tab3box = document.querySelector('.asd3');

let flagUlsan = false;
let flagBusan = false;
let flagSeoul = false;



const $areabox = document.querySelector('.areabox');


function click_f(evt) {

    const $toMove = evt.target.closest('a');
  
    if (evt.target.matches('.btn')) {
      $toMove.addEventListener('click', (evt) => {
  
      });
      if (evt.target.matches('.Ulsan') && !flagUlsan) {
       
        const $Ele = makeElement('ul', { 'class': 'com1 list3', 'id': '' },
          makeElement('li', {},
          makeElement('label', {'class': 'com3 btn3 Ulsan2 Ulsan100 area', 'id' : 'Ulsan100'},"울산중구", makeElement('input', { 'type': 'checkbox', 'id' : 'Ulsan10'}))),
          makeElement('li', {},
          makeElement('label', {'class': 'com3 btn3 Ulsan2 Ulsan110 area', 'id' : 'Ulsan110'},"울산북구", makeElement('input', { 'type': 'checkbox', 'id' : 'Ulsan11'}))),
          makeElement('li', {},
          makeElement('label', {'class': 'com3 btn3 Ulsan2 Ulsan120 area', 'id' : 'Ulsan120'},"울산동구", makeElement('input', { 'type': 'checkbox', 'id' : 'Ulsan12'})))
        );
        $tab3box.appendChild($Ele);
        flagUlsan = true;
      }
      if (evt.target.matches('.Busan') && !flagBusan) {
  
        const $Ele = makeElement('ul', { 'class': 'com1 list2', 'id': '' },
          makeElement('li', {},
            makeElement('a', { 'href': '#tab12', 'class': 'com3 btn2 Busan2' }, "부산중구")),
          makeElement('li', {},
            makeElement('a', { 'href': '#tab22', 'class': 'com3 btn2 Busan2' }, "부산북구")),
          makeElement('li', {},
            makeElement('a', { 'href': '#tab32', 'class': 'com3 btn2 Busan2' }, "부산동구"))
        );
        $tab2box.appendChild($Ele);
        flagBusan = true;
      }
      if (evt.target.matches('.Seoul') && !flagSeoul) {
  
        const $Ele = makeElement('ul', { 'class': 'list1', 'id': '' },
          
        );
        $tab1box.appendChild($Ele);
        flagSeoul = true;
      }
    }

      // 중구
      if (evt.target.matches('.Ulsan100')) {
        newItem('.Ulsan100', Ulsan10, 'filter_list10', '.filter_list10', '울산광역시중구', 'Ulsan1100 reset', 'prodbtn_del_small10', 'Ulsan101'
        );        
      }
      if (evt.target.matches('.Ulsan1100')) {
        newItemBtn('.Ulsan1100', '.prodbtn_del_small10', '.Ulsan100',);
      }

      // 북구
      if (evt.target.matches('.Ulsan110')) {
        newItem('.Ulsan110', Ulsan11, 'filter_list11', '.filter_list11', '울산광역시북구', 'Ulsan1110 reset', 'prodbtn_del_small11', 'Ulsan111'
        );        
      }
      if (evt.target.matches('.Ulsan1110')) {
        newItemBtn('.Ulsan1110', '.prodbtn_del_small11', '.Ulsan110',);
      }

      // 동구
      if (evt.target.matches('.Ulsan120')) {
        newItem('.Ulsan120', Ulsan12, 'filter_list12', '.filter_list12', '울산광역시동구', 'Ulsan1120 reset', 'prodbtn_del_small12', 'Ulsan121'
        );        
      }
      if (evt.target.matches('.Ulsan1120')) {
        newItemBtn('.Ulsan1120', '.prodbtn_del_small12', '.Ulsan120',);
      }

      


      function newItem(item1, item2, item3, item4, item5, item6, item7, item8) {
        
        const $item1 = document.querySelector(item1);
        
        console.log(Ulsan11.checked);
          if (item2.checked == false) {
            
            const $Ele = makeElement('ul', { 'class': item3, 'id': '' },
            makeElement('li', { 'class': 'fil_item', 'id': item8 },
            makeElement('span', { 'class': 'fil_name' }, item5),
            makeElement('button', { 'class': item7, 'type': 'button' },
            makeElement('span', { 'class': item6 }, "+")
            )));
            $areabox.appendChild($Ele);
            $item1.style.color = "red";
            console.log('체크');
            
          } else if (item2.checked == true) {
            const $item4 = document.querySelector(item4);
            $areabox.removeChild($item4);
            $item1.style.color = "black";
            console.log('체크해제');
            
          }
         
        }

        function newItemBtn(itemBtn1, itemBtn2, itemBtn3) {
          console.log('체크체크2222222222');
        
          if (evt.target.matches(itemBtn1)) {
            console.log('체크해제33333333333');
            document.querySelector(itemBtn3).click();
          }
          if (evt.target.matches(itemBtn2)) {
            console.log('체크해제44444444444');
            document.querySelector(itemBtn3).click();
          }
        }
     
    

    //  //지역 체크박스
    // if (evt.target.matches('.Ulsan100')) {
    //   combox('.Ulsan100', Ulsan10);
    //   console.log('울산중구');
    // }
    // if (evt.target.matches('.Ulsan110')) {
    //   combox('.Ulsan110', Ulsan11);
    //   console.log('울산북구');
    // }
    // if (evt.target.matches('.Ulsan120')) {
    //   combox('.Ulsan120', Ulsan12);
    //   console.log('울산동구');
    // }
    // function combox(item1,item2) {
    //   const $item1 = document.querySelector(item1);
      
    //   if (item2.checked == false) {    
    //     $item1.style.color = "red";
    //     console.log(item2.checked);
    //     item2.checked == true;
        
    //   } else if (item2.checked == true) {    
    //     $item1.style.color = "black";
    //     console.log(item2.checked);    
    //   }
    //   console.log(item2.checked);
    // }
       


     
    
 

   
    newFunction('.list li', '.cont', '.btn', 'com2');
   
    function newFunction(attr1, attr2, attr3, attr4) {
  
      const tabList = document.querySelectorAll(attr1);
      const contents = document.querySelectorAll(attr2);
      let activeCont3 = ''; // 현재 활성화 된 컨텐츠 (기본:#tab1 활성화)
  
      for (var i = 0; i < tabList.length; i++) {
        tabList[i].querySelector(attr3).addEventListener('click', function (e) {
          e.preventDefault();
          for (var j = 0; j < tabList.length; j++) {
            // 나머지 버튼 클래스 제거
            tabList[j].classList.remove(attr4);
  
            // 나머지 컨텐츠 display:none 처리
            contents[j].style.display = 'none';
          }
  
          // 버튼 관련 이벤트
          this.parentNode.classList.add(attr4);
  
          // 버튼 클릭시 컨텐츠 전환
          activeCont3 = this.getAttribute('href');
          document.querySelector(activeCont3).style.display = 'inline-block';
        });
      }
    }
  }
  
  /*-------------------------------------------------------------*
   * 노드생성 함수
   * 주어진 태그이름, 속성, 컨텐츠를 포함하는 요소를 만들어 주는 함수
   *-------------------------------------------------------------*/
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

