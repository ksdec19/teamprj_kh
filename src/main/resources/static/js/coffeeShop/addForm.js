'use strict';

////textArea => ck_editor 대체
//ClassicEditor
//      .create( document.querySelector( '#bcontent' ), {
//       plugin:['ListStyle','Markdown','MediaEmbed','MediaEmbedToolbar'],
//         toolbar: {
//            items: [
//               'heading',
//               '|',
//               'underline',
//               'bold',
//               'italic',
//               'link',
//               'bulletedList',
//               'numberedList',
//               'todoList',
//               '|',
//               'outdent',
//               'indent',
//               '|',
//               'imageInsert',
//               'imageUpload',
//               'blockQuote',
//               'insertTable',
//               'mediaEmbed',
//               'markdown',
//               'undo',
//               'redo',
//               '|',
//               'highlight',
//               'fontFamily',
//               'fontColor',
//               'fontBackgroundColor',
//               'fontSize',
//               '|',
//               'htmlEmbed',
//               'specialCharacters'
//            ]
//         },
//         language: 'en',
//         image: {
//            toolbar: [
//               'imageTextAlternative',
//               'imageStyle:full',
//               'imageStyle:side'
//            ]
//         },
//         table: {
//            contentToolbar: [
//               'tableColumn',
//               'tableRow',
//               'mergeTableCells',
//               'tableCellProperties',
//               'tableProperties'
//            ]
//         },
//      })
//      .then( editor => {
//
//         window.editor = editor;
//      } )
//      .catch( error => {
//         console.error( error );
//      } );


const $coffeeShop1 = document.querySelector('.coffeeShop-wrap1');
const category1 = ($coffeeShop1?.dataset.code)? $coffeeShop1.dataset.code : '';

const $coffeeShop2 = document.querySelector('.coffeeShop-wrap2');
const category2 = ($coffeeShop2?.dataset.code)? $coffeeShop2.dataset.code : '';

const $coffeeShop3 = document.querySelector('.coffeeShop-wrap3');
const category3 = ($coffeeShop3?.dataset.code)? $coffeeShop3.dataset.code : '';

const $coffeeShop4 = document.querySelector('.coffeeShop-wrap4');
const category4 = ($coffeeShop4?.dataset.code)? $coffeeShop4.dataset.code : '';

const $coffeeShop5 = document.querySelector('.coffeeShop-wrap5');
const category5 = ($coffeeShop5?.dataset.code)? $coffeeShop5.dataset.code : '';

// function onFileUpload(event) {
//    event.preventDefault();
//    let file = event.target.files[0];
//    console.log(file);
//  }
let $files5 = document.getElementById("files5").value;
//console.log($files5);
// 등록
writeBtn?.addEventListener('click', e=> {
 var $files5 = document.getElementById("files5").value;
    if(!$files5){
        alert("썸네일 파일을 첨부해 주세요");
        return false;
    }else{
     writeForm.submit();
    }

});

// 취소
cancelBtn?.addEventListener("click", e => {
 const url = `/admin/coffeeShop/list`;
  location.href = url;
});

// 목록
listBtn?.addEventListener("click", e => {
 const url = `/coffeeShop/list`;
  location.href = url;
});
//분류자동 선택
const $options1 = document.querySelectorAll('#bcategoryB0101 option');
[...$options1].find(option=>option.value===category1).setAttribute('selected','selected');

const $options2 = document.querySelectorAll('#bcategoryB0102 option');
[...$options2].find(option=>option.value===category2).setAttribute('selected','selected');

const $options3 = document.querySelectorAll('#bcategoryB0103 option');
[...$options3].find(option=>option.value===category3).setAttribute('selected','selected');

const $options4 = document.querySelectorAll('#bcategoryB0104 option');
[...$options4].find(option=>option.value===category4).setAttribute('selected','selected');

const $options5 = document.querySelectorAll('#bcategoryB0105 option');
[...$options5].find(option=>option.value===category5).setAttribute('selected','selected');

