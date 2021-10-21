$(document).ready(function () {
    var variant1 = [200, 255];
    var variant2 = [170, 220];
    var color_variations = 0;
    var rand_images = 0;
    var rand_part_type = 0;
    var rand_number_parts = 0;
    var h = '<div class=\"templ_header\">Header</div>';
    var f = '<div class=\"templ_footer\">Footer</div>';
    var open_cont = '<div class="templ_part_content">';
    var close_cont = '</div>';
    var contents = '';

    //<editor-fold defaultstate="collapsed" desc="----------------Parts contents --------------------------">
    var p1_data = ' <div class=\"parts part1\">'
            + ' <div>'
            + '     <h4>Your title</h4>'
            + '     <p>   Your contents<br/> and more, ..   </p>' +
            +' </div>'
            + ' </div>';
    var p2_data = '  <div class="parts part2">' +
            ' <div>' +
            '<h4>Your title</h4>' +
            '<p>   Your contents<br/> and more, ..ces  </p>' +
            ' </div>' +
            ' <div> <h4>Your title</h4>' +
            ' <p>   Your contents<br/> and more, ..' +
            '</p> </div>' +
            ' </div>';
    var p3_data = '<div class="parts part3">' +
            ' <div><h4>Your title</h4>' +
            '     <p>    Your contents<br/> and more, ..</p></div>' +
            ' <div> <h4>Your title</h4>' +
            '     <p>    Your contents<br/> and more, ...</p> </div>' +
            ' <div> <h4>Your title</h4>' +
            '     <p>    Your contents<br/> and more, ..</p> </div>' +
            ' </div>';
    var p4_data = '<div class="parts part4">' +
            '<div><h4>Your title</h4>' +
            '    <p>    Your contents<br/> and more, ..</p></div>' +
            '<div> <h4>Your title</h4>' +
            '    <p>   Your contents<br/> and more, .. .</p> </div>' +
            '</div>';
    var p5_data = '<div class="parts part5">' +
            '<div><h4>Your title</h4>'
    '<p>    Your contents<br/> and more, ..</p></div>' +
            '<div> <h4>Your title</h4>' +
            '    <p>    Your contents<br/> and more, ..</p> </div>' +
            ' </div>';
    var p6_data = '<div class="parts part6">' +
            '<div><h4>Ttl</h4>' +
            '    <p>   Con ..</p></div>' +
            '<div> <h4>Your title</h4>' +
            '    <p>    Con..</p> </div>' +
            '<div> <h4>Your title</h4>' +
            '    <p>    Con..</p> </div>' +
            '<div> <h4>Your title</h4>' +
            '    <p>    Con..</p> </div>' +
            '</div>';
    var p7_data = '<div class="parts part7">' +
            '           <div><h4>Your title</h4>' +
            '           <p>    Your contents<br/> and more, ..</p>' +
            '       </div>' +
            '<div> <h4>Your title</h4>' +
            '    <p>    Your contents<br/> and more, ..</p> </div>' +
            '<div> <h4>Your title</h4>' +
            '    <p>    Your contents<br/> and more, ..</p> </div> ' +
            '</div>';
    var p8_data = '<div class="parts part8">' +
            ' <div><h4>Your title</h4>' +
            '     <p>    Your contents<br/> and more, ..</p></div>' +
            ' <div> <h4>Your title</h4>' +
            '     <p>    Your contents<br/> and more, ..</p> </div>' +
            ' <div> <h4>Your title</h4>' +
            '     <p>    Your contents<br/> and more, ..</p> </div> ' +
            '</div>';
    //</editor-fold>

    var pics = ['', ''];
    var rgb = '';

    var copy;


    set_for(200, 255, 200, 100); //Average
    $('.colors_board').append('<p><h1>Dark colors </h1></p>');
    set_for(0, 200, 200, 0); //Dark
    $('.colors_board').append('<p><h1>Light colors </h1></p>');
    Light_colors(); //Light


    function set_for(start_i, end_i, start_j, end_j) {
//       Green and blue

        for (var i = start_i; i < end_i; i += 5) {
            rgb_color('gb', i, rgb, start_j, end_j);
            rgb_color('bg', i, rgb, start_j, end_j);
        }
//        Red and blue
        for (var i = start_i; i < end_i; i += 5) {
            rgb_color('rb', i, rgb, start_j, end_j);
            rgb_color('br', i, rgb, start_j, end_j);
        }
//        green and red
        for (var i = start_i; i < end_i; i += 5) {
            rgb_color('rg', i, rgb, start_j, end_j);
            rgb_color('gr', i, rgb, start_j, end_j);
        }

    }

    function rgb_color(color_type, i, rgb, start_j, end_j) {

        for (var j = start_j; j > end_j; j -= 15) {//Blue 

            if (color_type === 'gb') {
                rgb = '0,' + i + ',' + j;
            } else if (color_type === 'bg') {
                rgb = '0,' + j + ',' + i;
            } else if (color_type === 'rb') {
                rgb = i + ',0,' + j;
            } else if (color_type === 'br') {
                rgb = j + ',0,' + i;
            } else if (color_type === 'rg') {
                rgb = i + ',' + j + ',0';
            } else if (color_type === 'gr') {
                rgb = j + ',' + i + ',0';
            }
            color_variations += 1;
            get_random(); //randomize how many number of part and the type of part

            $('.colors_board').append('<div class="colored"  ondblclick=\"templ_click(this)\" style=\"background-color:rgb(' + rgb + ')\">' + open_cont + h + contents + f + close_cont + '</div>');
            contents = '';

        }
    }

    function Light_colors() {
        var rgb = '';
        for (var i = 205; i <= 255; i++) {
            color_variations += 1;
            rgb = i + ',' + i + ',' + i;
            get_random();
            $('.colors_board').append('<div class="colored" style=\"background-color:rgb(' + rgb + ')\">' + contents + '</div>');
            contents = '';
        }

    }

    $('#color_number').html('We have got: ' + color_variations + ' Templates');
    function get_random() {

        rand_number_parts = Math.round(Math.random() * 4);
        rand_part_type = Math.round(Math.random() * 8);
        $('.testtimer').html('');
        if (rand_number_parts < 2) {
            while (rand_number_parts < 2) {
                rand_number_parts = Math.round(Math.random() * 4);
            }
        }
        // $('.testtimer').html('<br/>The number of parts: <b>' + rand_number_parts + '</b>');

        for (var gi = 1; gi <= rand_number_parts; gi++) {
            rand_images = Math.round(Math.random() * 4);
            //Type of part
            rand_part_type = Math.round(Math.random() * 8);
            if (rand_part_type < 1) {
                while (rand_part_type === 0) {
                    rand_part_type = Math.round(Math.random() * 8);
                }
            }
            contents += part_content_switch();
            if (rand_images < 1) {
                while (rand_images === 0) {
                    rand_images = Math.round(Math.random() * 4);
                }
            }
            // $('.testtimer').append('<br/><br/>--<br/><b>On part: </b>' + gi + ' ' + text_or_image(rand_images) + '----><b> WITH TYPE:</b> ' + rand_part_type);
        }

    }


    function text_or_image(n) {
        return   (n === 1) ? 'image and text ' : (n === 2) ? 'text' : 'image';
    }

    function part_content_switch() {

        var b = '';
        if (rand_part_type === 1) {
            return     p1_data;
        } else if (rand_part_type === 2) {
            return     p2_data;
        } else if (rand_part_type === 3) {
            return     p3_data;
        } else if (rand_part_type === 4) {
            return     p4_data;
        } else if (rand_part_type === 5) {
            return     p5_data;
        } else if (rand_part_type === 6) {
            return     p6_data;
        } else if (rand_part_type === 7) {
            return     p7_data;
        } else if (rand_part_type === 8) {
            return     p8_data;
        }
    }


    function bigger_header() {
        return (rand_number_parts === 2) ? 'few contents' : 'Bigger contents';
    }


    $('.bg_full > .only_close > .cl_org > .lone_close').click(function () {
        $('.bg_full').hide();
        $(copy).hide(300);
    });

});



//<editor-fold defaultstate="collapsed" desc="------------Visualizing a template -------------------">
function  templ_click(item) {
    var close_btn = ' <div class=\"only_close\">' +
            ' <div class=\"cl_org\">' +
            ' <div class=\"right_close_btn lone_close\">' +
            '  <div class=\"close_bar close_bar1\"></div>' +
            '    <div class=\"close_bar close_bar2\"></div>' +
            ' </div> ' +
            ' </div>' +
            ' </div>';

    copy = $(item).clone();

    $('.bg_full').show(0);
    $('.template_view').fadeIn(100);

    alert('part is: ' + $(item).children('.p').val());


    console.log('Visualizing the template');



}


//</editor-fold>