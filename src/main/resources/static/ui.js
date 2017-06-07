$(".nav-btn").on("click touch",function() {
  $(".settings").slideToggle("fast");
})
$(".first-li").on("click touch", function(){
  $(this).find("i").toggleClass("rotate");
  $(this).find(".nth-settings").toggle();
})

$(document).on("click", function(e){
  var x = $(".settings").find("*");
  var y = $(".nav-btn").find("*");

  if ($(e.target).is(x)||$(e.target).is(y)){
    console.log("nav-related")
  }else{
    console.log("not nav-related");
    $(".settings").hide();
  }
})
