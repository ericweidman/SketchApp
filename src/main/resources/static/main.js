var app ={
  urls:{
    newUser: "/newuser",
    login: "/login"
    // logout: "/logout"
  }
};
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
    error: function(error){
      console.log("uh oh, this does not look good for homestar", error.responseText);
      alert("you dun goofed");
    }
  });
}
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
    error: function(error){
      console.log("you dun goofed", error.responseText);
    }
  });
}
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
// $("#login-user").on('click',function(){
//   login();
// });
