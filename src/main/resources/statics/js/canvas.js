var center = 0;
var angle = 0;
var x = 0;
var y = 0;

//here is html5
var canvas;
var cvs;


var cx = 0;
var cy = 0;
var context;
var ctx2;
var ctx3;
var counter = 0;
var radius = 0;
var min_speed = 0.01;
var speed = 0.1;

// var radius=
var pos = 0;
//canvas = document.getElementById('DemoCanvas');
cvs = document.getElementById('DemoCanvas');

var timer_killer;

var centerh = 0;

$(document).ready(function () {
    ctx2 = cvs.getContext('2d');

    center = $('.overlay').width() / 2;
    centerh = $('.overlay').height() / 2;
    radius = $('.overlay').width() / 2;

    x = center + radius * Math.cos(angle);
    y = centerh - 50 + (radius / 9) * Math.sin(angle);

    timer_killer = setInterval(rotate, 20);



    hover_slow();

    tab_left();


    $('#quick_notification').click(function () {
        clearInterval(timer_killer);
        timer_killer = setInterval(rotate, 20);
        $('.quick_notify').show("drop", {direction: "up"}, "slow");
        $('.page2').slideUp();
        speed = 0.1;
        
        $('.card1, .card2, .card3, .card4').addClass('card_reset_width');
    });

    $('#start_app').click(function () {
        clearInterval(timer_killer);
        $('.quick_notify').hide("drop", {direction: "up"}, "slow");
        $('.page2').slideDown();
    });





});

function tab_left() {//the user has left the tab or came back
    $(window).focus(function () {
        speed = 0.1;
        if ($('.quick_notify').is(':visible')) {
            clearInterval(timer_killer);
            timer_killer = setInterval(rotate, 20);
//            console.log('The user has come back. so we have resumed the timer');
          
        }

    });

    $(window).blur(function () {
        clearInterval(timer_killer);
//        console.log('We have temporarily stopped the timer');
    });
}

function rotate() {
    angle += 0.01;
    var num_items = $('.points').length;


    $('.points').each(function () {
        var nangle = counter * (Math.PI / 180);
        var point_px = center + Math.cos(nangle) * radius;
        var point_py = centerh - 50 + Math.sin(nangle) * (radius / 9);
        $(this).css('left', point_px + 'px').css('top', point_py + 'px');
        counter += 360 / num_items + speed;
    });

    draw_line_above();


}


function hover_slow() {
    $('.merge_3').hover(function () {
        speed = min_speed;
    });
    $('.points').mouseover(function () {
        $(this).addClass('shade_on_hover');
    });
    $('.points').mouseleave(function () {
        $(this).removeClass('shade_on_hover');
    });

    $('.merge_3').mouseleave(function () {
        speed = 0.1;
    });
}



function draw_line_above() {
    ctx2.beginPath();
    ctx2.moveTo(center, center);
    ctx2.lineTo(x, y);
    ctx2.stroke();
}

