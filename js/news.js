window.onscroll = function() {scrollFunction()};


function scrollFunction() 
{
  if (document.body.scrollTop > getHeight() || document.documentElement.scrollTop > getHeight()) 
  {
    document.getElementById("navbar-years").style.top = "0";
  } 
  else {
    document.getElementById("navbar-years").style.top = "-56px";
  }
}

function getHeight()
{
	  divElement = document.querySelector(".height-function"); 

      elemHeight = divElement.clientHeight + 125; 

      return elemHeight;
}
