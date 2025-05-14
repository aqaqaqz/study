
import Accordion from 'react-bootstrap/Accordion';
import {useEffect} from 'react'
import $ from 'jquery';

var characterObj = (p)=>{
	var obj;
	var MAX_X = p.MAX_X;
	var MAX_Y = p.MAX_Y;
	var key = {l:false, r:false, u:false, d:false}; 
	var pos = {y:MAX_Y/2+200, x:MAX_X/2, r:8};
	var life = 3;
	var power = 1;
	var speed = 3;

	var keyDown = (e)=>{
		if(e.keyCode == 37) key.l = true;
		if(e.keyCode == 39) key.r = true;
		if(e.keyCode == 38) key.u = true;
		if(e.keyCode == 40) key.d = true;
	}

	var keyUp = (e)=>{
		if(e.keyCode == 37) key.l = false;
		if(e.keyCode == 39) key.r = false;
		if(e.keyCode == 38) key.u = false;
		if(e.keyCode == 40) key.d = false;
	}

	var updatePos = ()=>{
		if(key.l && (pos.x-speed>=0)) pos.x-=speed;
		if(key.r && (pos.x+13+speed<=MAX_X)) pos.x+=speed;
		if(key.u && (pos.y-speed>=0)) pos.y-=speed;
		if(key.d && (pos.y+13+speed<=MAX_Y)) pos.y+=speed;
	}

	var uptPosAndDrawChar = (ship, ctx)=>{
		updatePos();
		ctx.beginPath();
		ctx.drawImage(ship, pos.x, pos.y);
		ctx.fill();
	}

	var init = ()=>{
		key.l = false;
		key.r = false;
		key.u = false;
		key.d = false;
		pos.y = MAX_Y/2+200;
		pos.x = MAX_X/2;
	}

	obj = {keyDown:keyDown, keyUp:keyUp, uptPosAndDrawChar:uptPosAndDrawChar, pos:pos, init:init};

	return obj;
}

var bulletObj = (p)=>{
	var obj;
	var MAX_Y = p.MAX_Y;
	var MAX_X= p.MAX_X;
	var bullet = {
		pos : {x:0, y:0},	//좌표
		vec : {x:0, y:0},	//벡터
		cir : {x:0, y:0},	//회전
		r : 0,				//반경
		vr : 0,				//변화반경
		angle : 0,			//각도
		angleRate : 0,		//변화각도
		type : 0,			//총알타입
		speed : 4,			//속도
		size : 2,			//총알크기
		color : "white"
	}
	var level = 1;
	var maxLevel = 4;

	var shotAngle = 0;
	var shotAngleRate = 0.4;
	
	var bulletList = [];

	var updatePos = ()=>{
		for(var i=0;i<bulletList.length;i++){
			var b = bulletList[i];
			if(!checkValid(b)){
				bulletList.splice(i, 1);
				i--;
			}else{
				if(b.type == 1){
					b.pos.x += b.vec.x;
					b.pos.y += b.vec.y;
				}else if(b.type == 2){
					b.angle += b.angleRate;
					b.r += b.vr*(parseInt(Math.random()*2)==0?1:-1);

					b.pos.x += b.cir.x + b.r*Math.cos(b.angle);
					b.pos.y += b.cir.y + b.r*Math.sin(b.angle);

					b.vec.x = -b.r*b.angle*Math.sin(b.angle);
					b.vec.y = -b.r*b.angle*Math.cos(b.angle);
				}
			}
		};
	}

	var createBullet = (p, c)=>{
		/*
			1 : circle
			2 : direct to ship
			3 : random
		*/

		if(level < 3){
			for(var i=0;i<3;i++){
				var b = deepClone(bullet);
				b.pos.x = p.x; 	b.pos.y = p.y;
				b.type = 1;
				b.vec.x = Math.cos(shotAngle+120*i)*Math.sqrt(b.speed);
				b.vec.y = Math.cos(shotAngle)*Math.sqrt(b.speed);
				b.color = "red";
				bulletList.push(b);

				shotAngle += shotAngleRate;
			}
		}else{
			for(var i=0;i<2;i++){
				var b = deepClone(bullet);
				b.pos.x = p.x; 	b.pos.y = p.y;
				b.type = 1;
				b.vec.x = Math.sin(shotAngle)*Math.sqrt(b.speed)*(i==0?1:-1);
				b.vec.y = Math.cos(shotAngle)*Math.sqrt(b.speed)*(i==0?1:-1);
				b.color="pink";
				bulletList.push(b);

				shotAngle += shotAngleRate;
			}
		}
		
		if(level == 2){
			var b = deepClone(bullet);
			b.pos.x = p.x; 	b.pos.y = p.y;
			b.type = 1;
			b.vec.x = c.pos.x-p.x;	
			b.vec.y = c.pos.y-p.y;

			var temp = Math.sqrt(b.vec.x*b.vec.x + b.vec.y*b.vec.y);
			b.vec.x /= temp;
			b.vec.y /= temp;
			b.vec.x *= Math.sqrt(b.speed);
			b.vec.y *= Math.sqrt(b.speed);
			b.color="green";
		
			bulletList.push(b);
		}else if(level == 3){
			for(var i=0;i<4;i++){
				var b = deepClone(bullet);
				b.pos.x = p.x; 	b.pos.y = p.y;
				b.type = 1;
				b.vec.x = Math.sin(shotAngle)*Math.sqrt(b.speed);
				b.vec.y = Math.cos(shotAngle+90*i)*Math.sqrt(b.speed);
				b.color="gold";
				bulletList.push(b);

				shotAngle += shotAngleRate;
			}
		}else if(level == 4){
			for(var i=0;i<2;i++){
				var b = deepClone(bullet);
				b.pos.x = p.x; 	b.pos.y = p.y;
				b.type = 2;

				b.vec.x = 0;	b.vec.y = 0;
				b.cir.x = Math.random()*(parseInt(Math.random()*2)!=0?1:-1);
				b.cir.y = Math.random()*(parseInt(Math.random()*2)!=0?1:-1);
				b.r = 1;		b.vr = 0.005;
				b.angle = 0;	b.angleRate = 0.05;
				b.color="white";
				
				bulletList.push(b);
			}
		}
	}

	var uptPosAndDrawBullet = (ctx)=>{
		updatePos();
		
		$.each(bulletList, function(idx, b){
			ctx.beginPath();
			ctx.fillStyle = b.color;
			ctx.arc(b.pos.x, b.pos.y, b.size, 0, 2*Math.PI);
			ctx.fill();
		});
	}

	var checkValid = (b)=>{
		if(b.type == 1 || b.type == 2){
			if(b.pos.x>0 && b.pos.x<MAX_X && b.pos.y>0 && b.pos.y<MAX_Y) return true;
			return false;
		}
	}

	var crashCheck = (c)=>{
		var result = false;
		var cr = c.pos.r;
		var cx = c.pos.x + cr/2+1;
		var cy = c.pos.y + cr/2+4;

		$.each(bulletList, function(idx, b){
			var tx = b.pos.x-cx;
			var ty = b.pos.y-cy;
			var tr = cr+b.size;
			if( tx*tx + ty*ty  < tr*tr ){
				result = true;
				return false;
			}
		});
		return result;
	}

	var deepClone = (obj)=>{ 
		var objectClone = new obj.constructor(); 
		for (var p in obj) {
			if (typeof obj[p] == 'object') 
				objectClone[p] = deepClone(obj[p]); 
			else 
				objectClone[p] = obj[p]; 
		}
		return objectClone; 
	};

	var levelUp = ()=>{
		level = Math.min(level+1, maxLevel);
	}

	var init = ()=>{
		bulletList = [];
		level = 1;
	}

	obj = {uptPosAndDrawBullet : uptPosAndDrawBullet, createBullet:createBullet, crashCheck:crashCheck, levelUp:levelUp, init:init};

	return obj;
}

export default function Dotji(){
  var MAX_Y = 500;
  var MAX_X = 500;

  var c = characterObj({
    y : MAX_Y/2+200,
    x : MAX_X/2,
    MAX_Y : MAX_Y,
    MAX_X : MAX_X
  });

  var b = bulletObj({
    MAX_Y : MAX_Y,
    MAX_X : MAX_X
  });

  
  var ship;
  var canvas;
  var ctx;
  var timer;
  var game;
  var state = false;

	function drawBackground(){
		ctx.beginPath();
		ctx.fillStyle = "black";
		ctx.fillRect(0, 0, MAX_X, MAX_Y);
		ctx.fill();
	}
	
	function drawScore(){
		ctx.beginPath();
		ctx.fillStyle = "white";
		ctx.translate(50, 50);
		ctx.font = 20 + "px arial";
		ctx.textBaseline="middle";
		ctx.textAlign="left";
		ctx.fillText("Score : "+timer.toString(), 0, 0);
		ctx.fill();
		ctx.translate(-50, -50);
	}
	
	function update() {
		timer++;
		
		drawBackground(ctx);
		drawScore(ctx);
		c.uptPosAndDrawChar(ship, ctx);
		b.uptPosAndDrawBullet(ctx);

		if(timer%5 == 0) 
			b.createBullet({y:MAX_Y/2, x:MAX_X/2}, c);
		if(timer%500==0) 
			b.levelUp();

		if(b.crashCheck(c)){
			clearInterval(game);
			state = false;
			timer = 0;
		}
	}

	var fnInit = ()=>{
		$("body").keydown(function(e){ c.keyDown(e); });
		$("body").keyup(function(e){ c.keyUp(e) });
		$("#dotji").attr("height", MAX_Y);
		$("#dotji").attr("width", MAX_X);
	};

  var run = () => {
    state = true;
    b.init();
    c.init();
    game = setInterval(update, 15);
  }

  useEffect(() => {
    ship = document.getElementById("ship");
    canvas = $("#dotji")[0];
    ctx = canvas.getContext("2d");
    timer = 0;
    state = false;

    fnInit();

    $("body").keydown(function(e){ 
      if(e.keyCode == '32' && !state){
        run();
      }
    });
    
    
  });

  return (
    <div>
      <Accordion defaultActiveKey="0">
        <Accordion.Item eventKey="0">
          <Accordion.Body>
            Spacebar를 누르면 시작합니다.
          </Accordion.Body>
        </Accordion.Item>
      </Accordion>
      <img id='ship' width='0' heigth='0' src={`${process.env.PUBLIC_URL}/img/ship.png`} />
      <canvas id="dotji"></canvas>
    </div>
  );
}


