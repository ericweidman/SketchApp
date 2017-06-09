//=======================================  create canvas //
//
var canvasID = document.getElementById('canvas-wrapper');
canvas = document.createElement('canvas');
var cW = canvasID.offsetWidth;
var cH = canvasID.offsetHeight;
console.log(cW,cH);
canvas.setAttribute('width',cW);
canvas.setAttribute('height',cH);
canvas.setAttribute('id','canvas');
canvasID.appendChild(canvas);
if(typeof G_vmlCanvasManager != 'undefined'){
  canvas = G_vmlCanvasManager.initElement(canvas);
}
context = canvas.getContext('2d');
//
//=======================================  config & tools //
//
//==defaults==//
context.lineWidth = "2";
context.lineJoin = "round";
context.linecap = "round";
context.strokestyle = "#000000";
//
//=======================================  events //
var mouse = {
  x:0,
  y:0
};
var draw = function(){
  context.lineTo(mouse.x,mouse.y);
  context.stroke();
};
canvas.addEventListener('mousemove', function(e){
  mouse.x = e.pageX - this.offsetLeft;
  mouse.y = e.pageY - this.offsetTop;
},false);
//
canvas.addEventListener('mousedown',function (e){
  context.beginPath();
  context.moveTo(mouse.x,mouse.y);
  canvas.addEventListener('mousemove',draw,false);
},false);
//
canvas.addEventListener('mouseup',function (e){
  canvas.removeEventListener('mousemove',draw,false);
},false);
//
//==================================================== //
