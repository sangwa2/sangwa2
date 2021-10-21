var timer_killer2;
var top_left = 0;
var top_right = 5;
var bottom_left = 15;
var bottom_right = 25;


var round = 1;
var card_n = 4;
var card_n2 = 4;
var card_n3 = 4;
var card_n4 = 4;
var margin = 12;
$(document).ready(function () {
    notficication_loop();
  
    // timer_killer2 = setInterval(notficication_loop, 800);
    //tab_left2();
});


function notficication_loop() {
    setTimeout(function () {


        top_left += 1;
        top_right += 1;
        bottom_left += 1;
        bottom_right += 1;
        var width = $('.slide_card_left > .card4').width();
//        $('.slide_card_left > .card4').append('left: ' + $('.slide_card_left > .card4').offset().left);

        if (top_left === 6) {
            round + 1;

            $('.slide_card_left > .card' + card_n)
                    .animate({left: '+=' + (width + 20)}, 300)
                    .animate({top: '-=36', left: '-=36', width: '-=100'}, 0)
                    .animate({zIndex: '1'})
                    .animate({width: '+=100', left: '-=+' + (width + 20)}, 300);


            var which_card1 = (card_n === 4) ? 1 : (card_n === 3) ? 4 : (card_n === 2) ? 3 : 2;
            var which_card2 = (card_n === 4) ? 2 : (card_n === 3) ? 1 : (card_n === 2) ? 4 : 3;
            var which_card3 = (card_n === 4) ? 3 : (card_n === 3) ? 2 : (card_n === 2) ? 1 : 4;
            $('.slide_card_left > .card' + which_card1).animate({zIndex: '2', top: '+=12', left: '+=12'}, 700);
            $('.slide_card_left > .card' + which_card2).animate({zIndex: '3', top: '+=12', left: '+=12'}, 700);
            $('.slide_card_left > .card' + which_card3).animate({zIndex: '4', top: '+=12', left: '+=12'}, 700);

            top_left = 0;
            card_n -= 1;

            card_n = (card_n === 0) ? 4 : card_n;

        }

        if (top_right === 17) {
            var width2 = $('.slide_card_right > .card4').width();
            $('.slide_card_right > .card' + card_n2)
                    .animate({right: '+=' + (width2 + 20)}, 300)
                    .animate({top: '-=36', right: '-=36', width: '-=100'}, 0)
                    .animate({zIndex: '1'})
                    .animate({width: '+=100', right: '-=' + (width2 + 20)}, 300);
            var which_card1 = (card_n2 === 4) ? 1 : (card_n2 === 3) ? 4 : (card_n2 === 2) ? 3 : 2;
            var which_card2 = (card_n2 === 4) ? 2 : (card_n2 === 3) ? 1 : (card_n2 === 2) ? 4 : 3;
            var which_card3 = (card_n2 === 4) ? 3 : (card_n2 === 3) ? 2 : (card_n2 === 2) ? 1 : 4;
            $('.slide_card_right > .card' + which_card1).animate({zIndex: '2', top: '+=12', right: '+=12'}, 700);
            $('.slide_card_right > .card' + which_card2).animate({zIndex: '3', top: '+=12', right: '+=12'}, 700);
            $('.slide_card_right > .card' + which_card3).animate({zIndex: '4', top: '+=12', right: '+=12'}, 700);
            card_n2 -= 1;
            card_n2 = (card_n2 === 0) ? 4 : card_n2;

            top_right = 8;
        }
        if (bottom_left === 22) {
            $('.slide_card_right_bottom > .card' + card_n3)
                    .animate({right: '+=' + (width + 20)}, 300)
                    .animate({bottom: '-=36', right: '-=36', width: '-=100'}, 0)
                    .animate({zIndex: '1'})
                    .animate({width: '+=100', right: '-=+' + (width + 20)}, 300);

            var which_card1 = (card_n3 === 4) ? 1 : (card_n3 === 3) ? 4 : (card_n3 === 2) ? 3 : 2;
            var which_card2 = (card_n3 === 4) ? 2 : (card_n3 === 3) ? 1 : (card_n3 === 2) ? 4 : 3;
            var which_card3 = (card_n3 === 4) ? 3 : (card_n3 === 3) ? 2 : (card_n3 === 2) ? 1 : 4;
            $('.slide_card_right_bottom > .card' + which_card1).animate({zIndex: '2', bottom: '+=12', right: '+=12'}, 700);
            $('.slide_card_right_bottom > .card' + which_card2).animate({zIndex: '3', bottom: '+=12', right: '+=12'}, 700);
            $('.slide_card_right_bottom > .card' + which_card3).animate({zIndex: '4', bottom: '+=12', right: '+=12'}, 700);
            card_n3 -= 1;
            card_n3 = (card_n3 === 0) ? 4 : card_n3;

            bottom_left = 14;
        }

        if (bottom_right === 37) {
            $('.slide_card_left_bottom > .card' + card_n4)
                    .animate({left: '+=' + (width + 20)}, 300)
                    .animate({bottom: '-=36', left: '-=36', width: '-=100'}, 0)
                    .animate({zIndex: '1'})
                    .animate({width: '+=100', left: '-=' + (width + 20)}, 300);

            var which_card1 = (card_n4 === 4) ? 1 : (card_n4 === 3) ? 4 : (card_n4 === 2) ? 3 : 2;
            var which_card2 = (card_n4 === 4) ? 2 : (card_n4 === 3) ? 1 : (card_n4 === 2) ? 4 : 3;
            var which_card3 = (card_n4 === 4) ? 3 : (card_n4 === 3) ? 2 : (card_n4 === 2) ? 1 : 4;
            $('.slide_card_left_bottom > .card' + which_card1).animate({zIndex: '2', bottom: '+=12', left: '+=12'}, 700);
            $('.slide_card_left_bottom > .card' + which_card2).animate({zIndex: '3', bottom: '+=12', left: '+=12'}, 700);
            $('.slide_card_left_bottom > .card' + which_card3).animate({zIndex: '4', bottom: '+=12', left: '+=12'}, 700);
            card_n4 -= 1;
            card_n4 = (card_n4 === 0) ? 4 : card_n4;


            bottom_right = 30;
        }

        notficication_loop();

    }, 1200);
}

function tab_left2() {//the user has left the tab or came back
    $(window).focus(function () {
        timer_killer2 = setInterval(notficication_loop, 800);
        console.log('2. The user has come back. so we have resumed the timer');
    });

    $(window).blur(function () {
        clearInterval(timer_killer2);
        console.log('2. We have temporarily stopped the timer');
    });
}


