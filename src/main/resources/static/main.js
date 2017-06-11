var app ={
  urls:{
    newUser: "/newuser",
    login: "/login",
    save: "/save-drawing"
  }
};
//===================================================//
//= ajax =//
//===================================================//
function newUser(user){
  $.ajax({
    url: app.urls.newUser,
    method:"POST",
    contentType: 'application/json; charset=utf-8',
    dataType: 'text',
    data: JSON.stringify(user),
    success: function(){
      console.log('new user added',user);
    },
    error: function(error){ console.log("uh oh, this does not look good for homestar", error.responseText);
    alert("you dun goofed");}
  });
}
//===================================================//
function loginFunction(user){
  $.ajax({
    url: app.urls.login,
    method:"POST",
    contentType: 'application/json; charset=utf-8',
    dataType: 'text',
    data: JSON.stringify(user),
    success: function(result){
      console.log("logged in",user,result);
      $("#login-view").css("display","none");
    },
    error: function(error){console.log("you dun goofed", error.responseText);}
  });
}
//===================================================//
function postCanvas(){
  var img = canvas.toDataURL(0, 0, context.canvas.width, context.canvas.height);
  var title =  $('input[class="canvas-title"]').val();
  var base64result = base64result = img.split(',')[1];
  var drawing = {title,base64result};
  $.ajax({
    url: app.urls.save,
    method: "POST",
    contentType: 'application/json; charset=utf-8',
    dataType: 'text',
    data:JSON.stringify(drawing),
    error: function(error){
      console.log(error.responseText);
    },
    // statusCode: {
    //   404: function(){
    //     console.log("internal server error, bitch");
    //   }
    // },
    success: function(){
      console.log("saved");
    }
  });
}
//===================================================//
//= click events =//
//===================================================//
$("#new-user-form").on('submit',function(e){
  e.preventDefault();
  var user ={};
  user.username = $('input[name="new-user"]').val();
  user.password = $('input[name="new-password"]').val();
  newUser(user);
  clearThis();
  console.log('new user submitted');
});
$("#login-form").on('submit',function(e){
  e.preventDefault();
  var user = {};
  user.username = $('input[name="cur-user"]').val();
  user.password = $('input[name="cur-password"]').val();
  loginFunction(user);
  clearThis();
});
function clearThis(){
  $('.formImput').val("");
}
//===================================================//
$("#save-link").on("click", function(){
  $(".save-modal").show();
});
$("#save").on("click", function(){
  postCanvas();
  $('input[class="canvas-title"]').val('');
  $(".save-modal").hide();
});
$(document).on('click', function(e){
  var sv = $("#save-link");
  var mdl = $(".save-modal").find("*");
  if ($(e.target).is(sv) || $(e.target).is(mdl)){
  //
  }else{$(".save-modal").hide();}
});
//===================================================//
