'use strict';

const $memberInfo = document.querySelector('.memberInfo')
$memberInfo.addEventListener('click', (evt) => {
  const [...classValues] = evt.target.classList;

  const memberEmail = evt.target.dataset.memberEmail;

   if (classValues.includes('memDelBtn')) {
    memDelBtn_f(memberEmail);
   }
  });

  function memDelBtn_f(memberEmail) {
  if (!confirm('삭제하시겠습니까?')) {
     console.log('삭제 취소함!');
     return;
  }
  console.log('삭제 처리 시작');
  const data = {
      memberEmail: memberEmail
    };
  const url = `http://localhost:9080/members/${memberEmail}/memberDel`;
  fetch(url, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(data),
  })
    .then((res) => res.json())
    .then((res) => {
      if (res.rtcd == '00') {
        list_f();
      } else {
        console.log(res.rtmsg);
      }
    })
    .catch((err) => console.log(err));
}