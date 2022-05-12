'use strict';

heartBtn.addEventListener('click', (evt) => {
  if (heartBtn.textContent === '등록') {
    console.log("북마크 등록!")
    create_f();
    heartBtn.style.backgroundImage = "url('/img/icon/icon-heart-red.png')";
    heartBtn.textContent = '등록취소';
  } else if (heartBtn.textContent === '등록취소') {
    console.log("북마크 해제!")
    del_f();
    heartBtn.style.backgroundImage = "url('/img/icon/icon-heart-empty-red.png')";
    heartBtn.textContent = '등록';
  }
});

//북마크 등록
function create_f(e) {
  const data = {
    shopId: shopId.value,
    memberId: memberId.value
  };
  fetch(`/bookmark`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  })
    .then((res) => res.json()) // json포맷 문자열 => js객체 변환
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.error('Err:', err);
    });
  console.log('after fetch');
}

//북마크 삭제
function del_f(e) {
  const data = {
    shopId: shopId.value,
    memberId: memberId.value
  };
  fetch(`/bookmark`, {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  })
    .then((res) => res.json())
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.error('Err:', err);
    });
  }


