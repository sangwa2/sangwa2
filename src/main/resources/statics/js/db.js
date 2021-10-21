$.getScript('globals.js', function () {});
//<editor-fold defaultstate="collapsed" desc="----------------OnLoad-----------------">
var fk_dispType = '';
function get_all_structures() {
    $.ajax({//Get all the structures on the page load
        type: 'GET',
        url: 'api/ajaxrest/all_structures',
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            var s = '';
            var selectable_structure = '';
            for (var i = 0; i < result.length; i++) {
                s += '<a onclick=\"clicked(this)\" data-structure_id=\"' + result[i].structure_id + '"\"    href=\"#\" class=\"data_tables\">' + result[i].db_name + '</a><br/><br/>';
                selectable_structure += '<a onclick=\"select_structure_clicked(this)\" data-structure_id=\"' + result[i].structure_id + '"\"    href=\"#\" class=\"data_tables structure_select_link\">' + result[i].db_name + '</a><br/><br/>';
                $('.structuer_box').html(s);
                $('.structure_box_select').html(selectable_structure);
            }
            $('.structuer_box').append('<div onclick=\"new_structure(this)\" class=\"new_structure_link cursor_hand\"  >New structure</div>');
        }

    });
}
function get_all_layout_by_structure() {
    $.ajax({
        type: 'GET',
        url: 'api/ajaxrest/demo4',
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            for (var i = 0; i < result.length; i++) {

            }
        }

    });
}
get_all_structures();
$('.clear_onload').val('');
get_all_layout_by_structure();
save_on_last_txtbox(); //To save the data when the user reaches the last textbox



//</editor-fold>

function clicked(item) {//Getting the units by the structure clicked
    $('.data_tables_box').html('');
    structure = $(item).data('structure_id');
    get_unit_tuples_by_structure(structure);
    get_total_units_by_structure(structure);
    $('.extra_pane_contents').html('');
    $('.extra_pane_c .title > .title_first').html('');
}
function get_unit_tuples_by_structure(structureid) {
//This gets the  unit and tuples by a structure
    structure = structureid;
    //Clear the tuple 
    //The tuples- units by structure
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
                var s = '';
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
                            s += tuple_data + result[i].tuple_name + '</div></div>';
                            close_once = true;
                            close_index === 0;
                        } else if (current !== first_unit) {
                            tuple_counter = 0;
                            first_unit = current;
                            s += '</div><div class=\"unit_table\"  title=\"Double click to view details\" data-unit_name=\"' + result[i].name + '\" data-unit_id=\"' + result[i].unit_id + '\" onclick=\"tuple_by_unit(this)\"><div class=\"unit_title\" ondblclick=\"unit_title_clicked(this)\" ><input type=\"text\" onmouseleave=\"mouse_leave_txt(this)\" onkeyup=\"txt_unit_enter(this)\" class=\"small_textbox\"  disabled   data-field=\"name\"    data-unit_type=\"unit\" data-unit=\"' + result[i].name + '\" value=\"' + result[i].name + '\"/> </div>' + tuple_data + result[i].tuple_name + '</div></div>';
                            close_once = false;
                            close_index += 1;
                        }
                        tuple_counter += 1;
                    } else if (unit_counter === 0) {
                        first_unit = current;
                        s += '<div class=\"unit_table\"  title=\"Double click to view details\" data-unit_name=\"' + result[i].name + '\" data-unit_id=\"' + result[i].unit_id + '\" onclick=\"tuple_by_unit(this)\"><div class=\"unit_title\" ondblclick=\"unit_title_clicked(this)\" > <input type=\"text\" onmouseleave=\"mouse_leave_txt(this)\" onkeyup=\"txt_unit_enter(this)\" class=\"small_textbox\"           disabled   data-field=\"name\"    data-unit_type=\"unit\" data-unit=\"' + result[i].name + '\" value=\"' + result[i].name + '\"/></div>';
                    }
                    unit_counter += 1;
                    tuple_counter += 1;
                }

                $('.data_tables_box').html(s);
//                $('.query_table_holder').html(s);

            }
        }

    });
    units_by_structure_not_tuples();
}

//units by structure in the combobox
function units_by_structure_not_tuples() {
    $('.cbo_tuple_form').empty();
    $.ajax({
        type: 'GET',
        url: 'api/ajaxrest/units_by_structure_no_tuples/' + structure,
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                //Fill combo box
                $('.cbo_tuple_form').append('<option value=\"' + result[i].unit_id + '\">' + result[i].name + '</option>');
            }

        }, error: function (r) {
            alert('Error retreiving units: ' + r.toString());
        }

    });
}


$('.frm_caller').click(function () {
    ref_unit = 0; //because it is used both on new tuple and on update category of the tuple

    frm_bg_name = $(this).data('call'); // the "frm_bg_name" is declared in script.js

    if (frm_bg_name === 'unit_pane') {//if it is a unit
        if (structure < 1) {
            alert('You have to select a structure!!..');
        } else {
            get_position(this, 'form');
            $('#txt_name').focus();
        }
    } else if (frm_bg_name === 'tuple_pane') {
        if (unit > 1) {
            get_position(this, 'form');
            $('#txt_tuple_name').focus();
        } else {
            alert('You have to select a unit!!..');
        }
    } else {//this is the structure
        get_position(this, 'form');
        $('#frm_new_structure > .page_section').scrollTop(0);
        $('#txt_structure_name').focus();
    }
});
function show_delete_dialog() {
    $('.delete_bg').fadeIn(50);
    $('.delete_dialog').fadeIn(50);
}
function hide_delete_dialog() {
    $('.delete_bg').fadeOut(50);
    $('.delete_dialog').fadeOut(50);
}

$('.delete_unit_link').click(function () {
    if (unit !== '') {
        show_delete_dialog();
        item_type = type = $(this).data('item_type');
    } else {
        alert('You have to choose a unit');
    }

});
$('.del_yes').click(function () {
    if (item_type === 'unit') {//Deleting a unit 
        var unit_id = unit;
        $.ajax({
            type: 'POST',
            url: 'api/deletions/delete_unit/' + unit_id,
            success: function (result) {
                //Refresh units and tuples
                get_unit_tuples_by_structure(structure);
                get_tuples_by_unit(unit); //Reload tuples
                get_total_units_by_structure(structure);
            }

        });
    } else if (item_type === 'tuple') {
        $.ajax({
            type: 'POST',
            url: 'api/deletions/delete_tuple/' + tuple,
            success: function (result) {
                get_tuples_by_unit(unit);
                get_unit_tuples_by_structure(structure);
            }
        });
    } else if (item_type === 'structure') {
        $.ajax({
            type: 'POST',
            url: 'api/deletions/delete_structure/' + structure,
            success: function (result) {

            }

        });
        get_all_structures();
        get_unit_tuples_by_structure(structure);
        get_tuples_by_unit();
    }
    hide_delete_dialog();
});
$('.del_no').click(function () {
    hide_delete_dialog();
});
$('.delete_structure_link').click(function () {
    if (structure !== 0) {
        item_type = 'structure';
        show_delete_dialog();
    } else {
        alert('You have to select a structure');
    }
});
function get_position(frm, type) {
    var pref = (type === 'form') ? 'frm_' : '';
    var sufix = (type === 'form') ? '_bg' : '';
    if (type === 'form') {
        $('.' + frm_bg_name + sufix).show();
        $('.' + pref + frm_bg_name).show();
    }

    //Set the pane's top position at the right pos
    $('.' + pref + frm_bg_name).css('top', $(frm).offset().top + 12);
    $('.' + pref + frm_bg_name).css('left', parseInt($(frm).offset().left + 24));
    var pane_left = remove_px($('.' + pref + frm_bg_name).css('left'));
    var pane_width = $('.' + pref + frm_bg_name).width();
    var win_width = $(window).innerWidth();
    var exceed = ((pane_left + pane_width)) > win_width;
    if (exceed) {//the pane has overflowed
        var excess = (pane_left + pane_width) - win_width;
        excess += 20;
        $('.' + pref + frm_bg_name).css('left', (pane_left - excess));
    }

}

function new_structure(item) {//To show the structure form
    $('.structure_frm_pane_bg').show();
    $('.structure_frm_pane').show();
    //Set the pane's top position at the 
    $('.structure_frm_pane').css('top', 100);
    $('.structure_frm_pane').css('left', parseInt($(item).offset().left + 84));
    $('#txt_dbname').focus();
}

function get_total_units_by_structure(structureid) {
    $.ajax({
        type: 'GET',
        url: 'api/ajaxrest/total_units/' + structureid,
        success: function (result) {
            var s = '';
            $('.title_first').html(result + ' units');
        }

    });
}

function tuple_by_unit(item) {
//Calculate right postion and width
    $('.item_right_2wi_size').css('right', -win_width + 200);
    $('.item_right_2wi_size').css('width', fmer_w);
    $('.extra_pane').show();
    $('.dynamic_title').html($(item).data('unit_name')).css('font-weight', 'bolder');
    $('.item_right_2wi_size_org').removeClass('normal_item_right_2wi_size_org');
    unit = $(item).data('unit_id');
    unit_name = $(item).data('unit_name');
    $('.extra_pane_c > .title_holder > .title').html(unit_name); //To display the title in the tuples area
    if (tuples_retrieve) {//If the user double clicks, the system will only bring the data just once
        current_unit = unit;
        get_tuples_by_unit(unit);
    }
}
function get_tuples_by_unit(unit) {
    reload_again = (display_count < 1) ? true : false;
    $.ajax({
        type: 'GET',
        url: 'api/ajaxrest/tuple_by_unit/' + unit + '/' + structure,
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            var s = '';
            var units_combo = '<select onchange=\"unit_selected(this)\"><option></option>';
            for (var i = 0; i < result.units.length; i++) {
                units_combo += '<option data-bind=\"' + result.units[i].name + '\" value=\"' + result.units[i].unit_id + '\">' + result.units[i].name + '</option>';
            }
            units_combo += '</select>';
            var pane_contents = '';
            function open_pane(id) {//This id is the tuple id
                return '<div class=\"menu_pane_onsame_link\">'
                        + '<a href="#" data-tupleid=\"' + id + '\" onclick=showFK(this)>';
            }

            var pane_mid = '</a>'
                    + '<div class=\"pane same_link_pane fk_small_pane\"> '
                    + '    <div class=\"item pane_title\">Pane 1'
                    + '        <div class=\"right_close_btn\" onclick=close_pane(this)>'
                    + '            <div class=\"close_bar close_bar1\"></div>'
                    + '            <div class=\"close_bar close_bar2\"></div>'
                    + '        </div>'
                    + '    </div>'
                    + '    <div class=\"item pane_contents fk_pane_contents\">'
                    + '        <div>'
                    + '            '
                    + '            <div onclick=\"fk_opt(this)\" data-type=\"pk\"                                  class=\"opt_pk  opt\"> Primary key     </div>'
                    + '            <div onclick=\"fk_opt(this)\" data-type=\"normal\" data-upd=\"only_update\"      class=\"opt_normal  opt \">Normal           </div>'
                    + '            <div onclick=\"fk_opt(this)\" data-type=\"foreign\"                             class=\"opt_foreign  opt\">Foreign          </div>'
                    + '            <div class=\"off opt_reference_label">Referenced table </div>'
                    + '                <div>'
                    + '                   <div class=\"off cbo_fereign_field\"> Units:' + units_combo + ' </div>'
                    + '                    <div>FK tuple:<select class=\"cbo_ref_col\" id=\"cbo_ref_col\"  onchange=\"cbo_ref_tuple_changed(this)\">  </select></div>'
                    + '                    <div class=\" save_fk btn_small_pane\" onclick="save_fk(this)">Confirm changes</div>';
            var category_pane_close = '</div>'
                    + '            </div>'
                    + '       </div>'
                    + '   </div>'
                    + '</div>';
            var tuple_table = ' <div class="tuple_table">';
            var tab_header = '<div class="tuple_table tab_header">' +
                    '<div class=\"tuple_td\"> Tuple id </div>' +
                    '<div class=\"tuple_td\"> Unit </div>' +
                    '<div class=\"tuple_td\"> Tuple name </div>' +
                    '<div class=\"tuple_td\"> Data type </div>' +
                    '<div class=\"tuple_td\"> Category </div>' +
                    '<div class=\"tuple_td\"> Display_caption </div>' +
                    '<div class=\"tuple_td\"> Today date </div>' +
                    '<div class=\"tuple_td\"> Current date </div>' +
                    '</div> ';
            s += tab_header + tuple_table;
            for (var i = 0; i < result.tuples.length; i++) {
                s += '<div class="tuple_td"   class=\"tuple_id_delete\"   data-tuple_id=\"' + result.tuples[i].tuple_id + '\"         ondblclick=delete_tuple(this)>' + result.tuples[i].tuple_id + '</div>';
                s += '<div class="tuple_td">' + result.tuples[i].unit + '</div>';
                s += '<div class="tuple_td" ondblclick=tuple_double_click(this)><input type=\"text\" onmouseleave=\"tuple_mouse_left(this)\" onkeyup=\"txt_unit_enter(this)\"   class=\"small_textbox black_txt\" disabled data-id=\"' + result.tuples[i].tuple_id + '\"  data-unit_type=\"tuple\" data-field=\"name\" data-unit=\"' + result.tuples[i].name + '\"  value=\"' + result.tuples[i].name + '\" /></div>';
                s += '<div class="tuple_td" ondblclick=tuple_double_click(this)><input type=\"text\" onmouseleave=\"tuple_mouse_left(this)\" onkeyup=\"txt_unit_enter(this)\"   class=\"small_textbox black_txt\" disabled data-id=\"' + result.tuples[i].tuple_id + '\"  data-unit_type=\"tuple\" data-field=\"data_type\" data-unit=\"' + result.tuples[i].data_type + '\"  value=\"' + result.tuples[i].data_type + '\" /></div>';
                s += '<div class="tuple_td"> ' + open_pane(result.tuples[i].tuple_id) + result.tuples[i].category + pane_mid + category_pane_close + '</div>';
                s += '<div class="tuple_td" ondblclick=tuple_double_click(this)><input type=\"text\" onmouseleave=\"tuple_mouse_left(this)\" onkeyup=\"txt_unit_enter(this)\"   class=\"small_textbox black_txt\" disabled data-id=\"' + result.tuples[i].tuple_id + '\"  data-unit_type=\"tuple\" data-field=\"display_caption\" data-unit=\"' + result.tuples[i].data_type + '\"  value=\"' + result.tuples[i].display_caption + '\" /></div>';
                s += '<div class="tuple_td">' + result.tuples[i].today_date + '</div>';
                s += '<div class="tuple_td">' + result.tuples[i].curr_date + '</div>';
            }
            tuple_table += '</div>';
            s += tuple_table;
            $('.extra_pane_contents').html(s);
            stop_refresh = false;
            reload_again = true;
//            when the user finishes saving the tuple the system should refresh using a timer of 3sec, for the system stop
//            refreshing (for just 3 times), we make a flag to false (reload_again);
        }
    });
}
function showFK(item) {
    ref_tuple = $(item).data('tupleid');
    $(item).siblings('.same_link_pane').show(0).addClass('fk_small_pane');
    var this_left = $(item).offset().left;
    var win_height = $(window).innerHeight();
    var pane_height = $(item).siblings('.same_link_pane').height();
    var pane_top = $(item).offset().top;
    var remaining_height = (pane_top + pane_height) - win_height;
//    alert('win height: ' + win_height + ' \npane top: ' + pane_top + ' \npane height: ' + pane_height + '\n the exceeding size: ' + remaining_height);
    var exceed = (remaining_height <= 1) ? true : false;
    if (exceed) {//if has not exceeded, it means that the window's height is bigger than the pane's height
        if ((remaining_height * -1) < 60) {//if the height is bigger than 
//            alert('Not too bad remaining: ' + remaining_height + '  however we should shift towards top about 100, top is: ' + $(item).siblings('.same_link_pane').css('top'));
            $(item).siblings('.same_link_pane').css('top', -(remaining_height + 60));
        } else {
//            alert('Not too bad remaining: ' + remaining_height + '   top is: ' + $(item).siblings('.same_link_pane').css('top'));
        }
    } else {
        $(item).siblings('.same_link_pane').css('top', -(remaining_height + 60));
//        alert('Not too bad remaining: ' + remaining_height + '   link top: ' + pane_top + '  \npanetop is: ' + $(item).siblings('.same_link_pane').css('top'));
    }



}
function close_pane(item) {
    $(item).parent().parent('.pane').hide();
    $('.bg_full').hide();
}
function unit_selected(item) {
    just_tuples = true;
    var unit_id = $(item).val();
    ref_unit = unit_id;
    var unit_name = $(item).data('bind');
    $('.cbo_ref_col').empty().append('<option></option>');
    $.ajax({
        type: 'GET',
        url: 'api/ajaxrest/tuple_by_unit/' + unit_id + '/' + structure,
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            for (var i = 0; i < result.tuples.length; i++) {
                $('.cbo_ref_col').append(' <option value=\"' + result.tuples[i].tuple_id + '\">' + result.tuples[i].name + '</option>');
            }
            console.log('data: ' + result.tuples[i]);
        }
    });
}
function cbo_ref_tuple_changed(item) {
    var unit = 0;
    var ref_unit_id = 0;
}
function fk_opt(item) {
    $('.opt').removeClass('opt_hovered');
    $(item).addClass('opt_hovered');
    var this_item = $(item);
    if (this_item.data('type') === 'pk') {
        $('#txt_category').val('PK');
        $('.txt_category_choice').html('PK');
        $('.same_link_pane').hide(0);
        $('.cbo_ref_col').hide(0);
        tuple_type = 'PK';
    } else if (this_item.data('type') === 'normal') {
        if (this_item.data('upd') !== null) {
            tuple_type = 'NORMAL';
            update_tuple_fk_pk();
            get_tuples_by_unit(unit);
            $('.cbo_ref_col').hide(0);
            $('.same_link_pane').hide(0);

            console.log('finished updating');
        }
        $('#txt_category').val('NORMAL');
        $('.txt_category_choice').html('NORMAL');
        $('.cbo_ref_col').hide(0);
        $('.same_link_pane').hide(0);
        tuple_type = 'NORMAL';

    } else if (this_item.data('type') === 'foreign') {
        units_by_structure_not_tuples(); // this is to select all the unit in the combobox
        tuple_type = 'FK';
        $('#txt_category').val('FK');
        $('.txt_category_choice').html('FK');
        $('.cbo_fereign_field').show(0);
        $('.cbo_tuple_form').show(0);
        $('.cbo_ref_col').show(0);
        $('.save_fk').show(0);
    } else if (this_item.data('data_type') === 'String') {
        $('#data_type_box').html(this_item.data('data_type'));
        $('.same_link_pane').hide(0);
    } else if (this_item.data('data_type') === 'Integer') {
        $('#data_type_box').html(this_item.data('data_type'));
        $('.same_link_pane').hide(0);
    } else if (this_item.data('data_type') === 'Date') {
        $('#data_type_box').html(this_item.data('data_type'));
        $('.same_link_pane').hide(0);
    }
    console.log('we have got the type: ' + this_item.data('type'));
}
function save_fk(item) {
    if (tuple_type === 'FK') {
        //save the relationship count
        $.ajax({type: 'POST', url: 'api/new_data/new_relationship_update/' + unit + '/' + ref_unit + '/' + fk_dispType,
            dataType: 'json', contentType: 'application/json',
            success: function (result) {
                $('.fk_small_pane').hide(0);
                console.log(result);
            }

        }).done(update_tuple_fk_pk());
    }
}
function update_tuple_fk_pk() {
    $.ajax({type: 'POST', url: 'api/update_data/upd_tuple_category/' + tuple_type + '/' + ref_tuple, seccess: function (result) {
            alert(result);
        }}).done(function () {
        get_tuples_by_unit(unit);
    });
}

function hide_fk_btn(item) {
    $('.opt_same_pane').hide();
}

function unit_title_clicked(item) {//When the unit title is double clicked
//    $('.unit_title').dblclick(function () {
    $(item).children('.small_textbox').prop('disabled', false);
    unit_txt_enter_press = true;
//    });
}

function tuple_double_click(item) {
    unit_txt_enter_press = true;
    $(item).children('.small_textbox').prop('disabled', false);
}
function tuple_mouse_left(item) {
    $(item).prop('disabled', true);
}

function mouse_leave_txt(item) {
    if ($(item).val !== '') {
        $(item).prop('disabled', true);
        unit_txt_enter_press = true;
    }
}

function txt_unit_enter(item) {
    $(item).keyup(function (e) {

        var unit_type = $(item).data('unit_type');
        var field_name = $(item).data('field').trim();
        if (e.keyCode === 13) {//Enter key
            if (unit_txt_enter_press) {
                unit_name = $(item).val().trim();
                if (unit_name !== '') {//if the input is empty
                    //alert('data found: ' + unit_name + '  \nfor the unit: ' + unit_type + ' \nField name: ' + field_name);
                    unit_txt_enter_press = false; //To disable the double saving/editing, in other words if the user presses the key the event wont't fire twice!
                    if (only_one_word(unit_name)) {//if the user has not entered the two-word name
                        if (unit_type === 'unit') {//if it is the unit
                            $(item).parent().parent().data('unit_name', unit_name); //set the data so that on the next click the data attr will have been changed
                            unit_name = $(item).parent().parent().data('unit_name');
                            $('.extra_pane_c > .title_holder > .title').html($(item).val());
                            //Update the database
                            update_unit(field_name);
                        } else if (unit_type === 'tuple') {//this is the tuple name
                            tuple_display_caption = $(item).val();
                            upadate_tuple(item, field_name);
                        }
                        $(item).prop('disabled', true);
                    } else {

                        alert('The input must be one-word input ');
                    }
                } else {
                    alert('Empty space not allowed');
                }
            }
        } else {
            unit_txt_enter_press = true;
        }

    });
}

//<editor-fold defaultstate="collapsed" desc="-----------------New Data--------------">

function save_structure() {

    var db_name = $('#txt_dbname').val();
    var db_user = $('#txt_db_user').val();
    var db_password = $('#txt_db_password').val();
    var cash_total = $('#txt_cash_total').val();
    var start_time = $('#txt_start_time').val();
    var delivery_date = $('#txt_delivery_date').val();
    var pgm_language = $('#txt_pgm_language').val();
    var platform = $('#txt_platform').val();
    var entry_date = $('#txt_entry_date').val();
    var User = 1;
    if (db_name !== '' && db_user !== '' && db_password !== '' && cash_total !== '' && start_time !== '' && delivery_date !== '') {
        var db_exists = false;
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_structure/' + db_name + '/' + db_user + '/' + db_password + '/' + cash_total + '/' + start_time + '/' + delivery_date + '/' + pgm_language + '/' + platform + '/' + entry_date + '/' + User,
            success: function (result) {
                $('#txt_dbname').val('');
                $('#txt_db_user').val('sangwa');
                $('#txt_db_password').val('123');
                $('#txt_cash_total').val('300000');
                $('#txt_start_time').val('now');
                $('#txt_delivery_date').val('');
                $('#txt_pgm_language').val('Java');
                $('#txt_platform').val('Web');
                $('#txt_entry_date').val('');
                $('.structuer_box').html('');
                get_all_structures();
                //Scroll up to show the save unit

            }
        });
    } else {
        alert('You have to fill all the required fields');
    }

}
function save_unit() {
    if (structure !== 0) {
        var name = $('#txt_name').val();
        var number_children = 0;
        var number_parent = 0;
        if (only_one_word(name)) {
            if (name !== '' && number_children !== '' && number_parent !== '') {
                $.ajax({
                    type: 'POST',
                    url: 'api/new_data/new_unit/' + structure + '/' + name + '/' + number_children + '/' + number_parent,
                    success: function (result) {
                        $('#txt_name').val('');
                        get_unit_tuples_by_structure(structure); //Display the data again, Kind of refresh
                        units_by_structure_not_tuples();
                        var this_height = $('.two_window_p1').height();
                        $('.two_window_p1').animate({scrollTop: this_height}, 'slow');
                    }
                });
            }
        } else {
            alert('You should make one word name');
        }
    } else {
        alert('There is no structure selected!!');
    }
}
$('.cbo_tuple_form').change(function () {
    ref_unit = $(this).val();
});
function changed_cbo_tuplefk(item) {
    var type = $(item).data('bind');
    if ($(item).is(':checked')) {
        if (type === 'rad_cbo') {
            fk_dispType = 'combo';
        } else {
            // rad_form
            fk_dispType = 'form';
        }
    }

}
function make_db() {
    $.ajax({//this makes the db scripts
        type: 'GET',
        url: 'api/files/make_db/' + structure,
        success: function (result) {

        }, error: function (result) {
            alert(result);
        }
    });
}
function save_tuple() {
    if (unit !== 0) {
        if (ref_unit === 0 && $('#txt_category').val() === 'FK') {
            alert('Select the Referenced (FK) unit first');
        } else if (ref_unit !== 0 && ref_unit === '') {
            alert('You have to supply the display type ');
        } else {

            var name = $('#txt_tuple_name').val();
            var data_type = $('#data_type_box').html();
            var category = $('#txt_category').val();
            var display_caption = $('#txt_display_caption').val();
            var today_date = $('#txt_today_date').val();
            var curr_date = $('#txt_current_date').val();
            var ref_unit_var = (ref_unit !== 0) ? ref_unit : 0;
            var number_children = 0;
            var number_parent = 0;
            fk_dispType = (fk_dispType === '') ? 'null' : fk_dispType;
            make_db();

            if (name !== '' && data_type !== '' && category !== '' && display_caption !== '' && today_date !== '' && curr_date !== '') {
                $.ajax({
                    type: 'POST', //906      staff%20salary Integer            NORMAL           staff%20salary         2021-07-31          No                0/                                    142]
                    url: 'api/new_data/new_tuple/' + unit + '/' + name + '/' + data_type + '/' + category + '/' + display_caption + '/' + today_date + '/' + curr_date + '/' + ref_unit_var + '/' + fk_dispType + '/' + structure,
                    success: function (result) {}
                });
                $('#txt_tuple_name').val('');
                $('#txt_data_type').val('');
                $('#txt_category').val('');
                $('#txt_display_caption').val('');
                $('#txt_today_date').val('');
                $('#txt_current_date').val('');
//                save_fk();

                //Refresh units and tuples
                display_count = 0;
                refresh_data();
            } else {
                alert('You have to enter all the fields!!  \nname: ' + name + ' \ndata_type:' + data_type + ' \ncategory: ' + category + ' \ndisplay_caption: ' + display_caption + '  \ntoday_date: ' + today_date);
            }
            $('#data_type_box').html('');
            $('.txt_category_choice').html('');
            $('#txt_tuple_name').focus();
            fk_dispType = '';
        }
    } else {
        alert('You have to select the unit first');
    }

}

function refresh_data() {

    setTimeout(function () {
        if (display_count < 3) {
            console.log('REfreshing: ' + display_count);
            reload_again = true;
            display_count += 1;
            get_tuples_by_unit(unit); //Reload tuples
            get_unit_tuples_by_structure(structure);
            units_by_structure_not_tuples();
            console.log('Data called ');
        }
        if (display_count === 3) {//will stop reloading data on third time
            stop_refresh === false;
        }
        refresh_data();
    }, 3000);
}
function alert_save() {
    alert('Alert that you have saved');
}
function save_f_end_layout_type() {

    var name = $('#name').val();
    if (name !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_layout_type/' + name,
            success: function (result) {

            }
        });
        $('#txt_f_end_layout_type_id').val('');
        $('#txt_name').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_profile() {

    var name = $('#name').val();
    var surname = $('#surname').val();
    var date_birth = $('#date_birth').val();
    var gender = $('#gender').val();
    var tel = $('#tel').val();
    var email = $('#email').val();
    var residence = $('#residence').val();
    var image = $('#image').val();
    if (name !== '' && surname !== '' && date_birth !== '' && gender !== '' && tel !== '' && email !== '' && residence !== '' && image !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_profile/' + name + '/' + surname + '/' + date_birth + '/' + gender + '/' + tel + '/' + email + '/' + residence + '/' + image,
            success: function (result) {

            }
        });
        $('#txt_profile_id').val('');
        $('#txt_name').val('');
        $('#txt_surname').val('');
        $('#txt_date_birth').val('');
        $('#txt_gender').val('');
        $('#txt_tel').val('');
        $('#txt_email').val('');
        $('#txt_residence').val('');
        $('#txt_image').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_account() {

    var name = $('#name').val();
    var account_category = $('#account_category').val();
    var profile = $('#profile').val();
    if (name !== '' && account_category !== '' && profile !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_account/' + name + '/' + account_category + '/' + profile,
            success: function (result) {

            }
        });
        $('#txt_account_id').val('');
        $('#txt_name').val('');
        $('#txt_account_category').val('');
        $('#txt_profile').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_account_category() {

    var name = $('#name').val();
    if (name !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_account_category/' + name,
            success: function (result) {

            }
        });
        $('#txt_account_category_id').val('');
        $('#txt_name').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_code_place() {

    var code_number = $('#code_number').val();
    var html_code_line = $('#html_code_line').val();
    var query = $('#query').val();
    if (code_number !== '' && html_code_line !== '' && query !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_code_place/' + code_number + '/' + html_code_line + '/' + query,
            success: function (result) {

            }
        });
        $('#txt_code_place_id').val('');
        $('#txt_code_number').val('');
        $('#txt_html_code_line').val('');
        $('#txt_query').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_class_attrib() {

    var attribute = $('#attribute').val();
    var value = $('#value').val();
    if (attribute !== '' && value !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_class_attrib/' + attribute + '/' + value,
            success: function (result) {

            }
        });
        $('#txt_f_end_class_attrib_id').val('');
        $('#txt_attribute').val('');
        $('#txt_value').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_css() {

    var name = $('#name').val();
    var html_code_line = $('#html_code_line').val();
    var css_category = $('#css_category').val();
    if (name !== '' && html_code_line !== '' && css_category !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_css/' + name + '/' + html_code_line + '/' + css_category,
            success: function (result) {

            }
        });
        $('#txt_f_end_class_id').val('');
        $('#txt_name').val('');
        $('#txt_html_code_line').val('');
        $('#txt_css_category').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_class_category() {

    var name = $('#name').val();
    if (name !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_class_category/' + name,
            success: function (result) {

            }
        });
        $('#txt_f_end_class_category_id').val('');
        $('#txt_name').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_data_list() {

    var query = $('#query').val();
    if (query !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_data_list/' + query,
            success: function (result) {

            }
        });
        $('#txt_f_end_data_list_id').val('');
        $('#txt_query').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_data_list_template() {

    var name = $('#name').val();
    var description = $('#description').val();
    if (name !== '' && description !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_data_list_template/' + name + '/' + description,
            success: function (result) {

            }
        });
        $('#txt_f_end_data_list_template_id').val('');
        $('#txt_name').val('');
        $('#txt_description').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_form() {

    var layout = $('#layout').val();
    var unit = $('#unit').val();
    var form_template = $('#form_template').val();
    if (layout !== '' && unit !== '' && form_template !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_form/' + layout + '/' + unit + '/' + form_template,
            success: function (result) {

            }
        });
        $('#txt_f_end_form_id').val('');
        $('#txt_layout').val('');
        $('#txt_unit').val('');
        $('#txt_form_template').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_form_template() {

    var name = $('#name').val();
    var html_code = $('#html_code').val();
    if (name !== '' && html_code !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_form_template/' + name + '/' + html_code,
            success: function (result) {

            }
        });
        $('#txt_f_end_form_template_id').val('');
        $('#txt_name').val('');
        $('#txt_html_code').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_html_code() {

    var name = $('#name').val();
    if (name !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_html_code/' + name,
            success: function (result) {

            }
        });
        $('#txt_f_end_html_code_id').val('');
        $('#txt_name').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_code_line() {

    var name = $('#name').val();
    var content = $('#content').val();
    var content_type = $('#content_type').val();
    var html_code = $('#html_code').val();
    if (name !== '' && content !== '' && content_type !== '' && html_code !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_code_line/' + name + '/' + content + '/' + content_type + '/' + html_code,
            success: function (result) {

            }
        });
        $('#txt_f_end_code_line_id').val('');
        $('#txt_name').val('');
        $('#txt_content').val('');
        $('#txt_content_type').val('');
        $('#txt_html_code').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_js_category() {

    var name = $('#name').val();
    if (name !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_js_category/' + name,
            success: function (result) {

            }
        });
        $('#txt_f_end_js_category_id').val('');
        $('#txt_name').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_js_code() {

    var code_line = $('#code_line').val();
    var css = $('#css').val();
    var js_code_category = $('#js_code_category').val();
    if (code_line !== '' && css !== '' && js_code_category !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_js_code/' + code_line + '/' + css + '/' + js_code_category,
            success: function (result) {

            }
        });
        $('#txt_f_end_js_code_id').val('');
        $('#txt_code_line').val('');
        $('#txt_css').val('');
        $('#txt_js_code_category').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}
function save_f_end_layout(name, layout_type, unit, order, supplied_index) {
//    if (name !== '' && layout_type !== '' && unit !== 0 && order !== '' && supplied_index !== '') {
    var name = 'unit_name';
    var layout_type = 0;
    var layout_type = 0;
    var unit = unit;
    var order = 0;
    var supplied_index = 0;

    $.ajax({
        type: 'POST',
        url: 'api/new_data/new_f_end_layout/' + name + '/' + layout_type + '/' + unit + '/' + order + '/' + supplied_index,
        succeess: function (res) {

        },
        error: function (err) {

        }
    });
    //Refresh data
//    } else {
//        alert('Fill all the fields');
//    }
}
function save_layout_txt_enter(item) {
    $('.txt_layout').keyup(function (e) {
        if (e.keyCode === 13) {
            var name = $(item).val();
            var layout_type = 0;
            var layout_type = 0;
            var unit = unit;
            var order = 0;
            var supplied_index = 0;
            alert('We have presse to save a layout');
            if (name !== '') {
                if (unit_txt_enter_press) {
                    //  save_f_end_layout(name, layout_type, structure, order, supplied_index);
                    $(item).prop('disabled', true);
                    unit_txt_enter_press = false;
                }
            } else {
                if (unit_txt_enter_press) {
                    alert('you have to provide the name of the layout');
                }
            }
        }
    });
}

function save_f_end_datalist() {

    var query = $('#query').val();
    var data_list_template = $('#data_list_template').val();
    var layout = $('#layout').val();
    if (query !== '' && data_list_template !== '' && layout !== '') {
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_end_datalist/' + query + '/' + data_list_template + '/' + layout,
            success: function (result) {

            }
        });
        $('#txt_f_end_datalist_id').val('');
        $('#txt_query').val('');
        $('#txt_data_list_template').val('');
        $('#txt_layout').val('');
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}

function save_f_layout_unit_template(layout, unit, unit_name, cbo_form) {
    var form_template = 0; // we will have to define the templates later. for now we set 0. still in dev
    if (unit !== 0) {
        console.log(
                +' unit: ' + unit
                + ' unitname: ' + unit_name
                + ' form template: ' + form_template
                + ' structure: ' + structure);
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_f_layout_unit_template/' + unit + '/' + unit_name + '/' + form_template + '/' + cbo_form + '/' + structure,
            success: function (result) {
                console.log('saved the unit template: ');
            }, error: function (err) {
                console.log('Unit template not saved');
            }
        });
        //Refresh data
    } else {
        alert('You have to enter all the fields!! ');
    }
}

$('.txt_layout').keyup(function (e) {//This s the event that works with the items that come on load
    if (e.keyCode === 13) {
        var name = $(this).val();
        var layout_type = 0;
        var layout_type = 0;
        var unit = unit;
        var order = 0;
        var supplied_index = 0;
        if (name !== '') {
            if (unit_txt_enter_press) {
                // save_f_end_layout(name, layout_type, structure, order, supplied_index);
                $(this).prop('disabled', true);
                unit_txt_enter_press = false;
            }
        } else {
            if (unit_txt_enter_press) {
                alert('you have to provide the name of the layout');
            }
        }
    }
});
$('.txt_unit_name').keyup(function (e) {
    if (e.keyCode === 13) {//Enter key

        $.ajax({
            type: 'GET',
            url: 'api/ajaxrest/unit_by_unit_structure/' + unit + '/' + structure,
            success: function (result) {
                if (result > 0) {
                    alert('The unit in the structure already exists');
                } else {
                    save_unit();
                }

            }
        });
    }
});
$('.btn_save').click(function () {//Any save button
    var unit_ = $(this).data('unit');
    if (unit_ === 'unit') {
        var name = $('#txt_name').val();
        $.ajax({
            type: 'GET',
            url: 'api/ajaxrest/unit_by_unit_structure/' + name + '/' + structure,
            success: function (result) {
                if (result > 0) {
                    alert('The unit in the structure already exists');
                } else {
                    save_unit();
                }

            }
        });
    } else if (unit_ === 'structure') {
        var db_exists = $('#txt_dbname').val();
        $.ajax({
            type: 'GET',
            url: 'api/ajaxrest/db_exists/' + db_exists,
            success: function (result) {
                if (result !== '') {
                    alert('The structure already exists, please give another name: ' + result);
                } else {

                    save_structure();
                }
            }, error: function (err) {
                alert('Error: ' + err);
            }
        });
    } else if (unit_ === 'tuple') {
        save_tuple();
    } else if (unit_ === 'str_settings') {
        if (structure !== 0) {
            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: 'api/files/getpath_files',
                beforeSend: function (xhr, opt) {
                    make_db();
                },
                contentType: 'application/json',
                success: function (result) {
                    var ls = '';
                    for (var i = 0; i < result.length; i++) {
                        var namebeforedot = result[i].split('.')[0] ;
                        var nameafterdot = result[i].split('.')[1] + '.sql';
                        var new_name = namebeforedot + nameafterdot;

                        ls += '<br/><a href=\"' + new_name + '\">' + result[i].split('.')[1];
                    }
                    $('.str_db_list').html(ls);
                }, error: function (xhr, ajaxOptions, thrownError) {
                    console.log('status sending request: ' + xhr.status);
                    console.log('Error sending request: ' + thrownError);
                }

            }).done(function () {

            });

        } else {
            alert(`You have to select the structure`);
        }
    }

    return false;
});
//</editor-fold>
function delete_tuple(item) {
    item_type = 'tuple';
    tuple = $(item).data('tuple_id');
    show_delete_dialog();
}

//<editor-fold defaultstate="collapsed" desc="-----------------Udpate field by field---------------">
function Tuple(id, name, data_type, category, display_caption, today_date) {
    this.id = id;
    this.name = name;
    this.data_type = data_type;
    this.category = category;
    this.display_caption = display_caption;
    this.today_date = today_date;
    this.changeName = function (name, id) {
        this.name = name;
        this.id = id;
        $.ajax({type: 'POST', url: 'api/update_data/upd_tuple_name/' + name + '/' + id,
            success: function () {
                get_tuples_by_unit(unit);
                get_unit_tuples_by_structure(structure);
            }
        });
    };
    this.Change_data_type = function (data_type, id) {
        this.data_type = data_type;
        this.id = id;
        $.ajax({type: 'POST', url: 'api/update_data/upd_tuple_data_type/' + data_type + '/' + id,
            success: function () {
                get_tuples_by_unit(unit);
                get_unit_tuples_by_structure(structure);
            }
        });
    };
    this.change_display_caption = function (display_caption, id) {
        this.display_caption = display_caption;
        this.id = id;
        $.ajax({type: 'POST', url: 'api/update_data/upd_display_caption/' + display_caption + '/' + id,
            success: function () {
                get_tuples_by_unit(unit);
                get_unit_tuples_by_structure(structure);
            }
        });
    };
}
function Unit(id, name) {
    this.id = id;
    this.name = name;
    this.changeName = function (name, id) {
        this.name = name;
        this.id = id;
        $.ajax({type: 'POST', url: 'api/update_data/update_unit_name/' + name + '/' + id,
            success: function (res) {
                get_tuples_by_unit(unit);
                get_unit_tuples_by_structure(structure);
            }
        });
    };
}
function upadate_tuple(item, field_name) {
    var id = $(item).data('id');
    if (field_name === 'name') {//field 1
        var tuple = new Tuple(id, unit_name, '', '', '', '');
        tuple.changeName(unit_name, id);
    } else if (field_name === 'data_type') {// field 2
        var tuple = new Tuple(id, unit_name, '', '', '');
        tuple.Change_data_type(unit_name, id);
    } else if (field_name === 'display_caption') {
        var tuple = new Tuple(id, '', '', '', tuple_display_caption, '', '');
        tuple.change_display_caption(tuple_display_caption, id);
    }
}
function update_unit(field_name) {
    var id = unit;
    if (field_name === 'name') {//field 1
        var unito = new Unit(id, unit_name);
        unito.changeName(unit_name, id);
    }
}


//</editor-fold>

function only_one_word(name) {
    var words = [];
    words = name.split(' ');
    var number = 0;
    for (var i = 0; i < words.length; i++) {
        number += 1;
    }
    return (number > 1) ? false : true;
}

function save_on_last_txtbox() {

    $('#txt_tuple_name').keyup(function () {
        $('#txt_display_caption').val($(this).val()).addClass('capitalized_text');
    });
    $('.save_on_last_txtbox').keyup(function (e) {
        var unit = $(this).data('last_textbox_unit');
        if (e.keyCode === 13) {//Enter key
            if (unit === 'tuple') {

                save_tuple();
                $('#txt_tuple_name').focus();
            }
        }
    });
}



$(function () {
    $('.btn_login').click(function (e) {
        e.preventDefault();
        var username = $('#txt_username').val();
        var password = $('#txt_password').val();
        $.ajax({
            type: 'POST',
            headers: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            url: $('.root').val() + '/api/ajaxrest/login/' + username + '/' + password,
            dataType: 'json',
            contentType: 'application/json',
            beforeSend: function (xhr, opt) {
                //if(condition){
                //xhr.abort();//Terminate if condition =true
                //}
            },
            success: function (result) {
                var s = '';
                if (result.account_category === 1 || result.account_category === 2) {
                    window.location.replace($('.root').val() + '/web');
                }
//                console.log(result.account_category);
            }, error: function (xhr, ajaxOptions, thrownError) {
//                console.log(xhr.status);
                if (xhr.status === 400) {
                    alert('Login failed');
                }
                //console.log(thrownError);
            }

        }).done(function () {

        });
    });
});

