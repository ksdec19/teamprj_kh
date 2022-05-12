'use strict';

// 등록
saveBtn?.addEventListener('click', (e) => {
  const $attachFile = document.querySelector('#attachFiles5 div');
  if ($attachFile != null) {
    editForm.submit();
  } else {
    var $files5 = document.getElementById('files5').value;
    if (!$files5) {
      alert('썸네일 파일을 첨부해 주세요');
      return false;
    } else {
      editForm.submit();
    }
  }
});

// 취소
cancelBtn?.addEventListener('click', (e) => {
  const url = `/admin/coffeeShop/list`;
  location.href = url;
});

// 목록
listBtn?.addEventListener('click', (e) => {
  const url = `/coffeeShop/list`;
  location.href = url;
});

//첨부파일삭제
document.querySelector('.files').addEventListener('click', (evt) => {
  const [...classValues] = evt.target.classList;
  if (!classValues.includes('fa-trash-can')){
    return;
  }

  if (classValues.includes('fa-trash-can')) {
    if (evt.target.tagName != 'I') return;
    if (!confirm('삭제하시겠습니까?')) return;

    const $i = evt.target;
    const url = `/attach/${$i.dataset.fid}`;
    fetch(url, {
      method: 'DELETE',
    })
      .then((res) => res.json())
      .then((res) => {
        if (res.rtcd == '00') {
          //첨부파일 정보 화면에서 제거
          removeAttachFileFromView(evt);
        } else {
          console.log(res.rtmsg);
        }
      })
      .catch((err) => console.log(err));
  }
  location.reload();
});

//삭제
const $delBtn = document.getElementById('delBtn');
$delBtn?.addEventListener('click', (e) => {
  if (confirm('삭제하시겠습니까?')) {
    const url = `/coffeeShop/${shopId.value}/del`;
    location.href = url;
  }
});

function removeAttachFileFromView(evt) {
  const $parent = document.getElementById('attachFiles');
  const $child = evt.target.closest('.attachFile');
  $parent.removeChild($child);
}
