'use strict';
$body?.addEventListener('click', click_f, true);

const $hashtag = document.querySelector('.hashtags');
const $inputtext = document.getElementById('beforeInputtext');


function backspace() { 
    if($inputtext.value == ""){  
      if (window.event.keyCode == 8) {
      $hashtag.lastChild.querySelector('.reset').click();  
      }
    }
  }

  function click_f(evt) {
  
    // 해시태그
    if (evt.target.matches('.bbb')) {
      newItem('.bbb', beforeSearchMakerRep8888, 'filter_list2', '.filter_list2', '에이수스', 'ccccc reset', 'prodbtn_del_small2', 'filterItem_searchMaker8888'
        );
      console.log('체크에이수스');
    }
    
    if (evt.target.matches('.ccccc')) {
      newItemBtn('.ccccc', '.prodbtn_del_small2', '.bbb',);
    }
    
    if (evt.target.matches('.aaa')) {
      newItem('.aaa', beforeSearchMakerRep6792, 'filter_list1', '.filter_list1', '레노버', 'aaaaa reset', 'prodbtn_del_small1', 'filterItem_searchMaker6792'
        );
      console.log('체크레노버');
    }
    
    if (evt.target.matches('.aaaaa')) {
      newItemBtn('.aaaaa', '.prodbtn_del_small1', '.aaa',);
    }
    
    
    function newItem(item1, item2, item3, item4, item5, item6, item7, item8) {
    
      const $item1 = document.querySelector(item1);
    
      if (item2.checked == false) {
    
        const $Ele = makeElement('ul', { 'class': item3, 'id': '' },
          makeElement('li', { 'class': 'fil_item', 'id': item8 },
            makeElement('span', { 'class': 'fil_name' }, item5),
            makeElement('button', { 'class': item7, 'type': 'button' },
              makeElement('span', { 'class': item6 }, "+")
            )));
        $hashtag.appendChild($Ele);
        $item1.style.color = "red";
        console.log('체크');
    
      } else if (item2.checked == true) {
        const $item4 = document.querySelector(item4);
        $hashtag.removeChild($item4);
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
    }