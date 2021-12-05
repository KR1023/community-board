const emailBox = document.querySelector(".input_box .input_el:first-child");
const pwBox = document.querySelector(".input_box .input_el:nth-child(4)");
const spanEmail = document.getElementById("email");
const spanPw = document.getElementById("pw");
const eSt = spanEmail.style;
const pSt = spanPw.style;

emailBox.addEventListener("focusin",function(){
  eSt.fontSize="14px";
  eSt.top = "-130px";
});

emailBox.addEventListener("focusout",function(){
  if(emailBox.value == ''){
    eSt.fontSize="18px";
    eSt.top = "-100px";
  }
});

pwBox.addEventListener("focus",function(){
  pSt.fontSize = "14px";
  pSt.bottom = "40px";
});

pwBox.addEventListener("focusout", function(){
  if(pwBox.value == ''){
    pSt.fontSize = "18px";
    pSt.bottom = "0px";
  }
});





