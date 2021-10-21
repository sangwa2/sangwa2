$.getScript('globals.js', function () {});
var frm_bg_name = '';

var units = [];//Thsi is the array that is used to store unit to be sent to database


$(document).ready(function () {
    //<editor-fold defaultstate="collapsed" desc="-------------Navigation----------------">
    
    $('.android_link').click(function () {
        var path = window.location.pathname;
        window.location.replace(path + 'databy');
    });
    $('.desktop_link').click(function () {
        var path = window.location.pathname;
        window.location.replace(path + '/datalist');
    });
    
    $('.diff').click(function () {
        $('.menu_pane_with_menu').show();
        $('.tab_content1').show();
        $('.pane_with_other_menu').show();
        $('.bg_full').show();
    });
    
    //</editor-fold>
    
    $('#call_data_by').click(function () {
        var structure = 63;
        var path = window.location.pathname;
        var page = [];
        page = path.split('/').slice();
        
        
        var new_path = 'jquery';
        $('#data2').html('');
        var res = '';
        $.post(new_path, {structure: structure}, function (data) {
            res = data;
            alert(data);
        }).complete(function () {
            $('#data2').html(res);
        });
    });
    
    $('.item_bottom').mouseleave(function () {
        $('.sub_menu').hide(0);
        
    });
    $('.item_bottom a').click(function () {
        $(this).parent('.item_bottom').addClass('.item_bottom_after').addClass('.item_bottom_after_hov');
        $(this).parent('.item_bottom').children('.sub_menu').slideToggle(80);
        
    });
    
    
    $('#p2_link_show').click(function () {
        $('.sub_organizer_right').toggleClass('get_one_part');
        $('.p2_content').toggle();
//        $('.sub_menu_right').toggleClass('sub_menu_right_big');
    });
    
    //Show menu on right
    $('.item_right').click(function () {
        $('.sub_menu_right').hide();
        $('.item_right').removeClass('item_right_hovered');
        $(this).children('.sub_menu_right').show();
        $(this).addClass('item_right_hovered');
    });
    
    
    $('.tab_menu1').addClass('selected_tab');
    //Cange tabs
    $('.tab_menu').click(function () {
        var counter = $(this).data('nmbr');
        $('.tab_menu').removeClass('selected_tab');
        $(this).addClass('selected_tab');
        
        $('.tab_content').hide();
        $('.tab_content' + counter).show();
    });
    //Hide the pane by default
    $('.bg_full').hide();
    $('.center_window').hide();
    
    
    //Show the pane in the center
    $('.menu_pane_onsame_link a').click(function () {
        $(this).siblings('.same_link_pane').show(0);
    });
    
    function position_any_pane(pane) {//These are the non-bg demanding ones (same link panes)
        
    }
    
    //show center pane
    $('.menu_pane_center a').click(function () {
        $('.center_window').show();
        $(this).siblings('.bg_full').show();
        
    });
    
    $('.center_windows').click(function () {
        var wind = $(this).data('window');
        $('.' + wind).show();
        
        $(this).siblings('.bg_full').show();
        $.ajax({
            type: 'GET',
            url: 'api/new_data/all_existing_structures',
            dataType: 'json',
            contentType: 'application/json',
            success: function (result) {
                var s = '';
                s += '<p><b>COLUMNS  STRUCTURES  </b></p>';
                for (var i = 0; i < result.length; i++) {
                    s += '<p><input type=\"checkbox\" data-key=\"' + i + '\" value=\"' + result[i] + '\"     onchange=\"get_tables_existingdb(this)\"  id=\"chk_' + i + '\"> <label value=\"' + result[i] + '\" for=\"chk_' + i + '\"> ' + result[i] + ' </label></p>';
                }
                $('#existing_db_content1').html(s);
            }
            
        });
        var bgfull = $(this).data('bind');
        if (bgfull === 'layout_view') {
            $('.bg_full_' + bgfull).show(0);
            $.ajax({
                type: 'GET',
                url: 'api/ajaxrest/demo4',
                dataType: 'json',
                contentType: 'application/json',
                success: function (result) {
                    var s = '';
                    for (var i = 0; i < result.length; i++) {
                        s += '<br/> Unit id:  ' + result[i].unit_id;
                        s += '<br/> Structure:  ' + result[i].structure;
                        s += '<br/> Name:  ' + result[i].name;
                        s += '<br/> =========================';
                        $('#result4').html(s);
                    }
                }
                
            });
        }
        
        
    });
    
    
    $('.import_btn').click(function () {
        if (structure > 0) {
            
            
            //We have tyo check if the user has checked any checkbox
            var n = 0;
            check_if_checked_any(n);
            
            if (n >= 0) {
                
                $('.table_chkbox').each(function (index, value) {
                    if ($(this).is(':checked')) {//Only get the cheched ones
                        if (!item_found($(this).val()))
                            console.log('Item alredy exists');
                    }
                    
                });
                
                $.ajax({
                    type: 'POST',
                    url: 'api/new_data/new_tuples_existing_db/' + working_db + '/' + structure,
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(units),
                    success: function (result) {
                        var s = '';
                        for (var i = 0; i < result.length; i++) {
                            s += '<br/> Unit id:  ' + result[i];
                        }
                    }, error: function (err) {
                        console.log(err);
                    }
                    
                }).done(function () {
                    get_unit_tuples(structure);//refreshing the units and tuples of the structure
                    units = [];
                });
                
                
                
                
            } else {
                alert(`You have to select at least one unit`);
            }
        } else {
            alert('You have to select the structure into which you want to import the structure(existing)');
        }
    });
    
    
    $('.menu_pane_with_menu a').click(function () {
//        $('.tab_menu').removeClass('selected_tab');
//        $(this).siblings('.pane_with_other_menu').children('.full_pane_contents').children('.full_pane_tabs').children('.tab_menu1').addClass('selected_tab');
//        $('.tab_content1').show();
//        $('.pane_with_other_menu').show();
//        $('.bg_full').show();
        
    });
    
//    hide center pane
    $('.menu_pane_with_menu > .pane_with_other_menu > .pane_title > .right_close_btn, .menu_pane_center > .center_window > .pane_title .right_close_btn').click(function () {
        var type = $(this).data('fk_pane');
        if (type === 'fk_pane' || type === 'structure') {
            $(this).parent().parent('.pane').hide();
        } else if (type === '') {
            $('.bg_full').hide();
            $(this).parent().parent('.pane').hide();
        }
//        $('.center_window').hide();
//        $('.pane_with_other_menu').hide();
//        $(this).parent().parent('.pane').hide();
    });
    $('.right_close_btn').click(function () {
        
        var type = $(this).data('fk_pane');
        if (type === 'fk_pane' || type === 'structure') {
            $(this).parent().parent('.pane').hide();
        } else if (type !== 'undefined') {
            $('.bg_full, .bg_full_layout_view').hide();
            
            $(this).parent().parent('.pane').hide();
        }
    });
    
    $('.lone_close').click(function () {
        $('.item_right').removeClass('item_right_hovered');
        $('.sub_menu_right').slideUp(1);
        
        //Also hide the delete (It also has lone close button)
        
        $('.delete_dialog').slideUp(0, function () {
            $('.delete_bg').fadeOut(10);
        });
    });
    
//put the pane on top of any other pane on show
    $('.menu_pane_with_menu a').click(function () {
        $(this).siblings('.pane').css('z-index', '103');
        $(this).siblings('.pane_with_other_menu').children('.pane_contents').children('.tab_contents').height(400);
        
    });
    
    
    //Rotate continuosly
    
    var c = 0;
    function get_rotation() {
        setTimeout(function () {
            c += 1;
            $('.close_bar1').css('transform', 'rotate(' + c + 'deg)');
            
//             '-webkit-transform' : 'scale(' + ui.value + ')',
//  '-moz-transform'    : 'scale(' + ui.value + ')',
//  '-ms-transform'     : 'scale(' + ui.value + ')',
//  '-o-transform'      : 'scale(' + ui.value + ')',
//  'transform'         : 'scale(' + ui.value + ')'
            
            
            get_rotation();
        }, 5);
    }
//    get_rotation();
    
    
    var c2 = 0;
    function get_rotation2() {
        setTimeout(function () {
            c2 -= 1;
            $('.close_bar2').css('transform', 'rotate(' + c2 + 'deg)');
            
            //             '-webkit-transform' : 'scale(' + ui.value + ')',
            //  '-moz-transform'    : 'scale(' + ui.value + ')',
            //  '-ms-transform'     : 'scale(' + ui.value + ')',
            //  '-o-transform'      : 'scale(' + ui.value + ')',
            //  'transform'         : 'scale(' + ui.value + ')'
            
            
            get_rotation2();
        }, 5);
    }
//    get_rotation2();
    
    
//    var rotated = document.querySelector('.rotated');
//    rotated.style.transition = "transform 3s ease-in-out";
//    $('.rotated').css('transform', 'translateX(100px)');
    
    
    
    //Make the layout height of the right menu
    
    $('.sub_menu_right').height(win_height);
//    var menu_offset = $('.item_right').offset();
//    var pos_y = parseInt(menu_offset.top);
    
    $('.item_right').click(function () {
        pos_y = parseInt($(this).offset().top);
        win_height = $(window).innerHeight();
        win_height = $(window).innerHeight();
        //Show the sub menu
        
        sub_menu_height = win_height - 70;
        $(this).children('.sub_menu_right').css('top', -(pos_y - 50));
        $(this).children('.sub_menu_right').css('height', sub_menu_height);
        $(this).children('.sub_menu_right').children('.sub_organizer_right').height(sub_menu_height - 20);
        $(this).children('.tab_contents').height(sub_menu_height - 50);
        $(this).addClass('item_right_hover_bck');
        
        //Get the content to upper index
        $('.sub_menu_right').css('z-index', '10');
        
        
        
        //Show the first tab
//        $(this).children('.sub_menu_right').children('.sub_organizer_right').children('.sub_content').children('.tabs').children('.tab_menu1').addClass('selected_tab');
        $(this).children('.sub_menu_right > .sub_organizer_right > .sub_content > .tabs > .tab_menu1').css('background-color', 'red');
//        $(this).children('.sub_menu_right').children('.sub_organizer_right').children('.sub_content').children('.tab_contents').children('.tab_content1').show();
        
        //Change the back ground color base on the color(data attribute)
        
    });
    
    //Put the height on resizing
    
//    $('.sub_menu_right').css('height', sub_menu_height);
    var win_height = $(window).innerHeight();
    var win_width = $(window).innerWidth();
    
    $(window).resize(function () {
        win_height = $(window).innerHeight();
        win_width = $(window).innerWidth();
        
//        $('.item_right_2wi_size').css('right', -win_width);
        $('.item_right_2wi_size').css('width', win_width - 200);
        
        $('.item_right_2wi_size').height(win_height - 100);
        
        $('.item_right_2wi_size, .sub_pane_2w').height(win_height - 100);
        
        
    });
    
    
    //Small right menu
    
//    $('.data_tables_box').height($('.second_sub_menu_content').height()-50);
    
    var input;
    $('.txt_structure_search').keyup(function () {//Searching the unit by its name
        input = $('.txt_structure_search').val();
        var nodes = $('.small_textbox');
        
        for (i = 0; i < nodes.length; i++) {
            if (nodes[i].innerText.includes(input)) {
                nodes[i].parentNode.style.display = "block";
                nodes[i].style.display = "block";
            } else {
                nodes[i].parentNode.style.display = "none";
                nodes[i].style.display = "none";
            }
        }
    });
    $('.tuple_form_opt_fk').click(function () {
        
    });
    $('.tuple_form_opt_normal').click(function () {
//        $('.txt_category_choice').html('Normal');
    });
    $('.tuple_form_opt_pk').click(function () {
        
    });
    
    
    
//Get rando colors
    
    
    $('.start_link').click(function () {
        $('.page_container > .part3').toggleClass('part9');
        $('.slide_card_left, .slide_card_right, .slide_card_left_bottom, .slide_card_right_bottom').toggleClass('card_short_top');
        $('.web_layout').css('top', '1%');
        $('.card1, .card2, .card3, .card4').toggleClass('card_short_top').css('width', '70%');
        $('.web_layout, .web_container').hide();
    });
    
    var sw = true;
    
    $('.app_switcher').click(function () {
        
        if (sw) {
            if ($('.hder').is(':visible')) {
                $('.hder').slideUp();
                $('.page_layout').animate({top: '-=35'}, 300);
                $('.right_menu, .left_menu').hide();
                sw = false;
            }
        } else {
            if (!$('.hder').is(':visible')) {
                $('.page_layout').animate({top: '+=35'}, 300);
                $('.hder').slideDown();
                $('.right_menu, .left_menu').show();
                sw = true;
            }
        }
        
        if ($('.quick_notify').is(':visible')) {
            $('.quick_notify').hide("drop", {direction: "up"}, "slow");
            $('.page2').slideDown();
            $('.right_menu, .left_menu').show();
            
        }
        
    });
    
    $('.txt_escape').keyup(function (e) {
        if (e.which === 27) {
            var unit = $(this).data('unit');
            $('.' + unit + '_pane_bg').hide(0);
            $('.frm_' + unit + '_pane').hide(0);
        }
    });
    
    //Temporary
    show_hide();
    
    
    $('.unselect_btn').click(() => {
        units = [];
        
        $('#existing_db_content3').html('');
        $('.table_chkbox').each(function (index, value) {
            $(this).prop('checked', true);
            if ($(this).is(':checked')) {//Only get the cheched ones
                $(this).prop('checked', false);
            }
            
        });
    });
    $('.select_all_btn').click(() => {//remove in the da
        units=[];
        $('.table_chkbox').each(function (index, value) {
            units.push($(this).val());
            $(this).prop('checked', true);
        });
    });
    
});
function get_unit_tuples(structure) {//Refreshing the unit tuples
    console.log('We should refresh the with this structure: ' + structure);
    $.ajax({
        type: 'GET',
        url: 'api/ajaxrest/units_by_structure/' + structure,
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            tuples_retrieve = true;
            if (result === null) {
                $('.data_tables_box').html('No content');
            } else {
                var s2 = '';
                var unit_changed = false;
                var first_unit = '';
                var current = '';
                var close_once = false;
                var close_index = 0;
                var unit_counter = 0;
                var tuple_counter = 0;
                var tuple_data = '<div class=\"unit_body\"> <div class=\"tuple_body\">';
                
                //$('.cbo_tuple _form').html('');//This is found on the tuple form
                for (var i = 0; i < result.length; i++) {
                    current = result[i].name;
                    
                    if (unit_counter > 0) {//if we at the benginning of unit
                        if (current === first_unit) {//not changed. we are on the same unit
                            s2 += tuple_data + result[i].tuple_name + '</div></div>';
                            close_once = true;
                            close_index === 0;
                        } else if (current !== first_unit) {
                            tuple_counter = 0;
                            first_unit = current;
                            s2 += '</div><div class=\"unit_table\"  title=\"Double click to view details\" data-unit_name=\"' + result[i].name + '\" data-unit_id=\"' + result[i].unit_id + '\" onclick=\"tuple_by_unit(this)\"><div class=\"unit_title\" ondblclick=\"unit_title_clicked(this)\" ><input type=\"text\" onmouseleave=\"mouse_leave_txt(this)\" onkeyup=\"txt_unit_enter(this)\" class=\"small_textbox\"  disabled   data-field=\"name\"    data-unit_type=\"unit\" data-unit=\"' + result[i].name + '\" value=\"' + result[i].name + '\"/> </div>' + tuple_data + result[i].tuple_name + '</div></div>';
                            close_once = false;
                            close_index += 1;
                        }
                        tuple_counter += 1;
                    } else if (unit_counter === 0) {
                        first_unit = current;
                        s2 += '<div class=\"unit_table\"  title=\"Double click to view details\" data-unit_name=\"' + result[i].name + '\" data-unit_id=\"' + result[i].unit_id + '\" onclick=\"tuple_by_unit(this)\"><div class=\"unit_title\" ondblclick=\"unit_title_clicked(this)\" > <input type=\"text\" onmouseleave=\"mouse_leave_txt(this)\" onkeyup=\"txt_unit_enter(this)\" class=\"small_textbox\"           disabled   data-field=\"name\"    data-unit_type=\"unit\" data-unit=\"' + result[i].name + '\" value=\"' + result[i].name + '\"/></div>';
                    }
                    unit_counter += 1;
                    tuple_counter += 1;
                }
                
                $('.data_tables_box').html(s2);
                //                $('.query_table_holder').html(s);
                
            }
        }
        
    });
}
function check_if_checked_any(n) {//Check of there is a checkbox selected
    $('.table_chkbox').each(function (index, value) {
        if ($(this).is(':checked')) {//checks if the checckbos is checked
            n += 1;
        }
    });
}
var cz = 0;
function show_hide() {
    
    setTimeout(function () {
        if (cz % 3 === 0) {
            cz = 0;
            $('#show_hide').show(400).delay(2500).hide(400);
        }
        cz += 1;
        show_hide();
    }, 3000);
    
}

function item_found(item, arr) {
    return ($.inArray(item, arr) > 0) ? true : false;
}

function get_tables_existingdb(item) {
    if ($(item).is(':checked')) {
        var db = $(item).val();
        working_db = db;
        console.log('the selected db is: ' + db);
        //Get tables of this dabase
        $.ajax({
            type: 'GET',
            url: 'api/ajaxrest/get_tables_existing_db/' + db,
            dataType: 'json',
            contentType: 'application/json',
            success: function (result) {
                var s = '';
                
                s += '<p><b>TABLES (' + db + ')</b></p>';
                for (var i = 0; i < result.length; i++) {
                    
                    s += '<p><input type=\"checkbox\" data-key=\"' + i + '\" value=\"' + result[i].table + '\" class=\"table_chkbox\"     onchange=\"get_columns_existingdb(this)\"  id=\"chkc_' + i + '\"> <label value=\"' + result[i].table + '\" for=\"chkc_' + i + '\"> ' + result[i].table + ' </label></p>';
                }
                $('#existing_db_content2').html('');
                $('#existing_db_content2').html(s);
            }
            
        });
        
    }
    
}
function get_columns_existingdb(item) {//This is when  clicked the unit to see the columns
    var table = $(item).val();
    var key = $(item).data('key');
    
    if ($(item).is(':checked')) {
        if (!item_found($(item).val(), units)) {
            units.push($(item).val());
            $.ajax({
                type: 'GET',
                url: 'api/ajaxrest/get_tuples_of_existingdb/' + working_db + '/' + table,
                dataType: 'json',
                contentType: 'application/json',
                success: function (result) {
                    var s = '';
                    s += '<p><b>COLUMNS(' + working_db + ' =>' + table + ')</b></p>';
                    for (var i = 0; i < result.length; i++) {
                        s += '<p><a href=\"#\">' + result[i] + '</a></p>';
                    }
                    $('#existing_db_content3').html('');
                    $('#existing_db_content3').html(s);
                }
                
            });
            console.log(units);
        } else {
            console.log('Not found in the array');
        }
    } else {
        units.splice(key, 1);
        console.log('removed: ' + key);
        console.log(units);
    }
    
}
