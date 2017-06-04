var app ={
  urls:{
    newUser: "/newuser",
    login: "/login",
    logout: "/logout"
  }
};
function newUser(createUser){
  $.ajax({
    url: app.urls.newUser,
    method:"POST",
    contentType: 'application/json; charset=utf-8',
    dataType: 'text',
    data: JSON.stringify(createUser),
    success: function(){
      console.log('new user added',data);
    },
    error: function(){
      console.log("uh oh, this does not look good for homestar", error);
    }
  });
}
function login(){

}
function logout(){

}
$("#new-user-form").on('submit',function(e){
  e.preventDefault();
  var createUser ={};
  var newName = $('input[name="new-user"]').val();
  var newPassword = $('input[name="new-password"]').val();
  newUser(createUser);
  conosle.log('new user submitted');
});
$("#login-user").on('click',function(){
  login();
});
