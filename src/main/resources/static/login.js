var app ={
  urls:{
    newUser: "/newuser",
    login: "/login",
    logout: "/logout"
  }
};
function newUser(user){
  $.ajax({
    url: app.urls.newUser,
    method:"POST",
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    data: JSON.stringify(user),
    success: function(){
      console.log('user added',data);
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
$("#create-user").on('click',function(){
  newUser();
});
$("#login-user").on('click',function(){
  login();
});
