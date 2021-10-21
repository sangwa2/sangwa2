
///*
//var win_height = $(window).innerHeight();
//var win_width = $(window).innerWidth();
//
//
////Calculate right postion and width
//$('.item_right_2wi_size').css('right', -win_width + 199);
//$('.item_right_2wi_size').css('width', win_width - 220);
//
//
////Calculate the height
//var pos_y2w = parseInt($('.item_right_2wi_size').offset().top);
//
//$('.item_right_2wi_size').css('top', 0);
////$('.item_right_2wi_size').css('top', -100);
//
//$('.item_right_2wi_size, .sub_pane_2w').height(win_height - 100);
//
////var fmer_w = $('.item_right_2wi_size').width();
//
//var switch_pos = false;
//
//
//
//
//
////Calculate the height
//var pos_y2w = parseInt($('.item_right_2wi_size').offset().top);
//$('.item_right_2wi_size').css('top', -(pos_y2w - 50));
//$('.item_right_2wi_size_org').removeClass('normal_item_right_2wi_size_org');
//
//var left = $('.item_right_2wi_size').css('left');
//$('.item_right_2wi_size').css('width', (parseInt(1166 / 2)));
//$('.item_right_2wi_size_org').addClass('normal_item_right_2wi_size_org');
//
//$('.item_right_2wi_size').css('left', left);
//
//$('.basic_pane_data').css('height', (win_height - 200));
//$('.extra_pane_contents, .extra_pane_c, .extra_pane').height(win_height - 220);
//$('.extra_pane').hide();
//
//
//$('.win_size_pane_link .link').click(function () {
//    $('.item_right_2wi_size').css('top', -250);
//    $('.item_right_2wi_size').show(0, function () {
//        $('.item_right_2wi_size_org').removeClass('normal_item_right_2wi_size_org');
//
//        var left = $('.item_right_2wi_size').css('left');
//        $('.item_right_2wi_size').css('width', (parseInt(1166 / 2)));
//        $('.item_right_2wi_size_org').addClass('normal_item_right_2wi_size_org');
//
//        $('.item_right_2wi_size').css('left', left);
//        $('.extra_pane').hide();
//        
//    });
//
//});
//*/

$('.lone_close > .close_bar').click(function () {
    $('.flex_two_sized_pane').fadeOut(100);
    $('.flex_bg').fadeOut(100);
});
$('.new_project').click(function () {
    $('.flex_two_sized_pane').fadeIn(100);
    $('.flex_bg').fadeIn(100);
    
    $('#txt_db_user').val('').val('sangwa');
    $('#txt_db_password').val('').val('123');
    $('#txt_cash_total').val('').val('300000');
    $('#txt_start_time').val('').val('now');
    $('#txt_pgm_language').val('').val('Java');
    $('#txt_platform').val('').val('Web');
    
//    console.log('new project started');
//    $.ajax({//Get all the structures on the page load
//        type: 'GET',
//        url: 'api/ajaxrest/all_structures',
//        dataType: 'json',
//        contentType: 'application/json',
//        success: function (result) {
//            var s = '';
//            var selectable_structure = '';
//            for (var i = 0; i < result.length; i++) {
//                s += '<a onclick=\"clicked(this)\" data-structure_id=\"' + result[i].structure_id + '"\"    href=\"#\" class=\"data_tables\">' + result[i].db_name + '</a><br/><br/>';
//                selectable_structure += '<a onclick=\"select_structure_clicked(this)\" data-structure_id=\"' + result[i].structure_id + '"\"    href=\"#\" class=\"data_tables structure_select_link\">' + result[i].db_name + '</a><br/><br/>';
//                $('.structuer_box').html(s);
//                $('.structure_box_select').html(selectable_structure);
//            }
////            $('.structuer_box').append('<div onclick=\"new_structure(this)\" class=\"new_structure_link cursor_hand\"  >New structure</div>');
//
//        }

//    });


});
//$('.item_right_2wi_size').show().hide(0);




