
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 120 || document.documentElement.scrollTop > 120) {
    document.getElementById("navbar-years").style.top = "0";
  } else {
    document.getElementById("navbar-years").style.top = "-56px";
  }
}