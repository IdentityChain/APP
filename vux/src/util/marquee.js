function Ruby ()
{
}

if (!Array.prototype.push) {
  Array.prototype.push = function() {
    var startLength = this.length;
    for (var i = 0; i < arguments.length; i++)
      this[startLength + i] = arguments[i];
    return this.length;
  }
}

$ = function ()
{
  var elements = new Array();

  for (var i = 0; i < arguments.length; i++) {
    var element = arguments[i];
    if (typeof element == ''string'')
    element = document.getElementById(element);

    if (arguments.length == 1)
      return element;

    elements.push(element);
  }

  return elements;
}

$F = function ()
{
  if(arguments.length == 1)
    return document.getElementById(arguments[0]).value;
  else
  {
    var elements = new Array();
    for(var i = 0;i< arguments.length; i++)
    {
      elements.push(document.getElementById(arguments[i]).value);
    }
    return elements;
  }
}


function Marquee (demo, demo1, demo2, direction, delay, step)
{
  direction = direction.toLowerCase();

  if(((direction == "up" || direction == "down") && ($(demo1).offsetHeight > $(demo).offsetHeight)) || ((direction == "left" || direction == "right") && ($(demo1).offsetWidth > $(demo).offsetWidth)))
  {
    $(demo2).innerHTML = $(demo1).innerHTML;
    if(direction == "down")
      $(demo).scrollTop = 2 * $(demo1).offsetHeight - $(demo).offsetHeight;
    if(direction == "right")
      $(demo).scrollLeft = 2 * $(demo1).offsetWidth - $(demo).offsetWidth;
  }
  else
    return;

  var flag = true;
  var speed = delay == null? 1 : parseInt(delay);
  var amount = step == null? 1 : parseInt(step);

  var Marquee = function ()
  {
    switch(direction)
    {
      case "up":
        if($(demo2).offsetTop - $(demo).scrollTop <= 0)
          $(demo).scrollTop -= $(demo1).offsetHeight;
        else
          $(demo).scrollTop += amount;
        break;
      case "down":
        if($(demo1).offsetTop - $(demo).scrollTop >= 0)
          $(demo).scrollTop += $(demo2).offsetHeight;
        else
          $(demo).scrollTop -= amount;
        break;
      case "left":
        if($(demo2).offsetWidth - $(demo).scrollLeft <= 0)
          $(demo).scrollLeft -= $(demo1).offsetWidth;
        else
          $(demo).scrollLeft += amount;
        break;
      case "right":
        if($(demo).scrollLeft <= 0)
          $(demo).scrollLeft += $(demo2).offsetWidth;
        else
          $(demo).scrollLeft -= amount;
        break;
      default:break;
    }
  }

  var timer = setInterval(Marquee,speed);

  var play = function ()
  {
    if(flag)
    {
      clearInterval(timer);
      timer = setInterval(Marquee, speed);
    }
  }

  $(demo).onmouseover = function ()
  {
    if(flag)
      clearInterval(timer);
  }

  $(demo).onmouseout = function ()
  {
    if(flag)
      timer = setInterval(Marquee, speed);
  }

  this.delay = function (s)
  {
    speed = s == null? 50 : parseInt(s);
    play();
  }

  this.step = function (s)
  {
    amount = s == null? 1 : parseInt(s);
    play();
  }

  this.start = function ()
  {
    if(!flag)
    {
      flag = true;
      play();
    }
  }

  this.stop = function ()
  {
    if(flag)
    {
      flag = false;
      clearInterval(timer);
    }
  }

  this.direction = function (s)
  {
    s = s.toLowerCase();
    if( s == direction )
      return;
    if(s == "down" && direction == "up" )
      direction = s;
    if(s == "up" && direction == "down")
      direction = s;
    if(s == "right" && direction == "left")
      direction = s;
    if(s == "left" && direction == "right")
      direction = s;
    if (s == direction)
      play();
  }
}
