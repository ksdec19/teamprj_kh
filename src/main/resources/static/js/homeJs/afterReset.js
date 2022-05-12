'use strict';

// 초기화 버튼  데이터 선택시 생성 / 초기화버튼 클릭시 삭제
$body?.addEventListener('click', click_f, true);

let flagReset = false;


function click_f(evt) {
        // .area .hashtagbox
        const $condition_reset_wrap = document.querySelector('.condition-reset-wrap');
        const $areaReset = document.querySelector('.condition-reset-wrap');
    
        
        if ((evt.target.matches('.area') || evt.target.matches('.hash_com')) && !flagReset) {
          const $Ele = makeElement('div', { 'class': 'reset-icon-box', 'id': '' },
            
            makeElement('a', {'class': 'reset-icon', 'href' : '#' },"초기화"));
    
          $condition_reset_wrap.appendChild($Ele);
           flagReset = true;
          console.log('생성');
          console.log(flagReset);
        }
         if(evt.target.matches('.reset-icon')){       
           
           for(let i=0; i<=10; i++){

             document.querySelector('.reset').click();
             document.querySelector('.All').click();

             console.log('삭제');

            }
             let $item4 = document.querySelector('.reset-icon-box');
             $areaReset.removeChild($item4);
             flagReset = false;
        }  
    }
           // if (evt.target.matches('.reset-icon')) {
      
    // // comboxBtn('.Ulsan100',Ulsan10);
    // // comboxBtn('.Ulsan110',Ulsan11);
    // // comboxBtn('.Ulsan120',Ulsan12);
    // // function comboxBtn(itemBtn,itemBtn2) {
    // //     if (itemBtn2.checked == true) {  
    // //         document.querySelector(itemBtn).click();           
    // //     }  
    // // }  
    // for(i=0; i<10; i++){
    //     document.querySelector('.reset').click();  
    //     document.querySelector('.All').click();
    // }
    // }