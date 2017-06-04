var app ={
  urls:{
    newUser: "/newuser",
    // login: "/login",
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
      console.log('new user added',data);
    },
    error: function(error){
      console.log("uh oh, this does not look good for homestar", error);
    }
  });
}
// function login(){
//
// }
// function logout(){
//
// }
$("#new-user-form").on('submit',function(e){
  e.preventDefault();
  var user ={};
  user.username = $('input[name="new-user"]').val();
  user.password = $('input[name="new-password"]').val();
  newUser(user);
  console.log('new user submitted');
});
// $("#login-user").on('click',function(){
//   login();
// });
