$.getScript('globals.js', function () {});
$.getScript('query.js', function () {});
////These are the scripts that will help make the layout of the website. It means that 
//it will collect manyt database tables where more than one table can be in one layout


var dropped_item = undefined;
var item_receiver = undefined;
var append_c = 0;
var layout_count = 1;
var layout_count_holder = 0;


var dragged_unit_id = 0;

$('.show_on_load').show();

$('.pane_caller').click(function () {
    var item = $(this).data('call');
    $('.tab_content1').show();
    $('.' + item).show();
    $('.bg_full').show();
    //Empty the pane and 
    
});

function get_last_layout_id() {
    var id = 0;
    $.ajax({
        type: 'GET', url: 'api/new_data/get_last_layout/' + structure, success: function (result) {
            layout_count_holder = result;
        }});
    
}
function drop_units() {
    var name = '';
    var layout_type = 0;
    var unit = 0;
    var order = 0;
    var supplied_index = 0;
    
    
    $('.separator').droppable({
        accept: '.layou_merge_content',
        tolerance: 'fit',
        over: function (ev, ui) {
            $(this).addClass('dashed_border');
//            $(ui.draggable).css('height', '110px');
            $(ui.draggable).css('color', 'black');
            dropped_item = item.parent().parent().data('layout_id');
            
        },
        drop: function (ev, ui) {
            var target = $(this);
            $(ui.draggable).addClass('layou_merge_content_cp').removeClass('layou_merge_content');
            unit = $(ui.draggable).data('id');
            var layout = $(this).parent().parent().data('layout_id');
            
            var this_item = $(this);
            //GEt the last layout fom db
            var id = 0;
            $.ajax({
                type: 'GET',
                url: 'api/new_data/get_last_layout/' + structure,
                success: function (result) {
                    if (result !== null) {
                        layout_count = result.toString();
                        layout_count = parseInt(layout_count) + 1;
                        this_item.parent('.layout_box').parent('.layout_group').data('layout_id', layout_count);
                        var data_items = this_item.parent('.layout_box').parent('.layout_group').data('layout_id');
                        alert('It must be: ' + data_items);
                    } else {
                        layout_count = 1;
                    }
                }, error: function (res) {
                    console.log('Error: ' + res.toString());
                }
            });
            
//            save_f_layout_unit_template(layout, unit);
            
            layout_count_holder += ' ' + layout_count;
            
            var drag_data_item = $(ui.draggable).data('id');
            
            $(this).parent().parent().attr('data-' + layout_count_holder, drag_data_item);
            console.log('You have added: ' + $(this).data('added_data'));
            
            $('.same_link_pane_unit_content').remove($(ui.draggable));
            $(ui.draggable).appendTo(target);
            
            //Add draggable data_item to droppable and show all the data_items
            
        },
        out: function () {
            $(this).removeClass('dashed_border');
        }
        
        
    });
}
function drag_units() {
    $('.layou_merge_content').mousedown(function () {
        console.log('mouse down');
        $(this).css('position', 'absolute').css('z-index', '19');
    });
    
    $('.layou_merge_content').draggable({
        cursor: 'move',
        revert: function () {
            
        },
        start: function (ev, ui) {
            append_c = 0;
            item_receiver = $(ui.draggable);
            $(this).css('height', '20');
        },
        stop: function (ev, ui) {
            cursor: 'pointer'
        }
    });
}

function item_down(item) {
//    $(item).css('height', '20');
}



function select_structure_clicked(item) {//This in db.js file it comes from a function called "get_all_structures"
    $('.tab_content').hide();
    $('.tab_content2').show();
    $('.tab_menu').removeClass('selected_tab');
    $('.tab_menu2').addClass('selected_tab');
    
    var name = $(item).html();
    structure = $(item).data('structure_id');
    
    //GEt units by structure_id
    
    $('.same_link_pane_unit').html(name);
    var unit_name = '';
    var my_div = '<div class=\"unit_draggable\">';
    $('.same_link_pane_unit_content').html('');
    
    function dropme(event) {
        var item = event.target;
        $('.layou_merge_content').append(item);
    }
    
    $.ajax({type: 'GET', url: 'api/ajaxrest/units_by_structure_no_tuples/' + structure, dataType: 'json', contentType: 'application/json',
        success: function (result) {
            if (result === null) {
                $('.same_link_pane_unit').html('No content');
            } else {
                var unit_clone = $('.layou_merge_content').clone(true);
                var dragged;
                var c = 0;
                for (var i = 0; i < result.length; i++) {
                    if (c > 0) {
                        $('.same_link_pane_unit_content').append(' <div title="' + result[i].name + '" data-id=\"' + result[i].unit_id + '\"   id=\"item_' + i + '\" onmousedown=\"item_down(this)\" ondragstart=\"dragStart(event)\" draggable=\"true\" class="layou_merge_content">' + result[i].name + '</div>');
                    } else {
                        $('.same_link_pane_unit_content').append(' <div title="' + result[i].name + '" data-id=\"' + result[i].unit_id + '\"   id=\"item_' + i + '\" onmousedown=\"item_down(this)\" ondragstart=\"dragStart(event)\" draggable=\"true\" class="layou_merge_content">' + result[i].name + '</div>');
                    }
                    
                    c += 1;
                }
                
            }
        }
        
    });
    
    //This gets the  unit and tuples by a structure
    
    //Clear the tuple 
    //The tuples- units by structure
    $.ajax({type: 'GET', url: 'api/ajaxrest/units_by_structure/' + structure, dataType: 'json',
        contentType: 'application/json', success: function (result) {
            tuples_retrieve = true;
            function tuple_check_box(id) {
                return '  <input type="checkbox" onchange=\"query_chk_change(this);\"   data-structure=\"' + structure + '\"       data-tuple_id=\"' + result[i].tuple_id + '\" data-unit_id=\"' + result[i].unit_id + '\" data-unit=\"' + result[i].name + '\" data-tuple_name=\"' + result[i].tuple_name + '\" data-id=\"' + id + '\" id=\"chk_' + id + '\"/> ';
            }
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
                var tuple_data = '<div class=\"unit_body\"> ';
                
                for (var i = 0; i < result.length; i++) {
                    current = result[i].name;
                    if (unit_counter > 0) {//if we at the benginning of unit
                        if (current === first_unit) {//not changed. we are on the same unit
                            s += tuple_data + '<div class=\"tuple_body\" \n\
                              data-unit_name=\"' + result[i].name + '\"  \n\
                              data-name=\"' + result[i].tuple_name + '\"  \n\
                              data-unit_id=\"' + result[i].unit_id + '\"  \n\
                              data-tuple_id=\"' + result[i].tuple_id + '\"  onclick=\"query_tuple_click(this)\"  ><table><tr><td>' + tuple_check_box(i) + '</td><td><label for=\"chk_' + i + '\">' + result[i].tuple_name + '</td></tr></table></label></div></div>';
                            close_once = true;
                            close_index === 0;
                            
                        } else if (current !== first_unit) {
                            tuple_counter = 0;
                            first_unit = current;
                            s += '</div><div class=\"unit_table\"  title=\"Double click to update unit name\"     ' + result[i].name + '\"\n\
                             data-unit_id=\"' + result[i].unit_id + '\"><div class=\"unit_title\"><input type=\"text\" onmouseleave=\"mouse_leave_txt(this)\" onkeyup=\"txt_unit_enter(this)\" class=\"small_textbox\"  disabled   data-field=\"name\"    data-unit_type=\"unit\" data-unit=\"' + result[i].name + '\" value=\"' + result[i].name + '\"/> \n\
                                \n\
                            </div>' + tuple_data + '<div class=\"tuple_body\"  \n\
                            data-unit_name=\"' + result[i].name + '\"  \n\
                            data-name=\"' + result[i].tuple_name + '\"  \n\
                            data-unit_id=\"' + result[i].unit_id + '\"   \n\
                            data-tuple_id=\"' + result[i].tuple_id + '\" onclick=\"query_tuple_click(this)\"  ><table><tr><td> ' + tuple_check_box(i) + '</td><td><label for=\"chk_' + i + '\">' + result[i].tuple_name + '</td></tr></table></label></div></div>';
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
                $('.query_table_holder').html(s);
            }
        }
        
    });
    
    // get_unit_tuples_by_structure($(item).data('structure_id'));
}

//$('.unit_holder').draggable();
$('.layou_merge_content').draggable();
$('.draggable_item').draggable({
    cursor: 'move',
    revert: 'invalid',
    start: function (ev, ui) {
        $(this).addClass('dragged');
        $(ui.draggable).addClass('drop_behavior');
        $(this).css('height', '10px');
        $(this).removeClass('drop_behavior');
    }
});
function dragme(item) {
    var event;
    // (2) prepare to moving: make absolute and on top by z-index
    item.style.position = 'absolute';
    item.style.zIndex = 1000;
    // move it out of any current parents directly into body
    // to make it positioned relative to the body
    document.body.append(item);
    // ...and put that absolutely positioned ball under the pointer
    
    moveAt(event.pageX, event.pageY);
    // centers the ball at (pageX, pageY) coordinates
    function moveAt(pageX, pageY) {
        item.style.left = pageX - item.offsetWidth / 2 + 'px';
        item.style.top = pageY - item.offsetHeight / 2 + 'px';
    }
    
    function onMouseMove(event) {
        moveAt(event.pageX, event.pageY);
    }
    
    // (3) move the ball on mousemove
    document.addEventListener('mousemove', onMouseMove);
    // (4) drop the ball, remove unneeded handlers
    item.onmouseup = function () {
        document.removeEventListener('mousemove', onMouseMove);
        item.onmouseup = null;
        console.log('Finished dropping');
    };
}


$('.new_item, .delete_item, .update_item').draggable({
    containment: 'document',
    start: function () {
        $('.drag_box').hide(function () {
            $('.box').addClass('get_oneitem_onbox');
        });
    },
    stop: function () {
        $('.drag_box').show(function () {
            $('.box').removeClass('get_oneitem_onbox');
        });
    }
});
$('.details').droppable({
    accept: '.crud_items',
    over: function (ev, ui) {
        $('.repack').addClass('repack_border_change');
        $(this).switchClass("margin_free", "drop", 500, "easeInOutQuad");
    },
    drop: function (ev, ui) {
        var item = ui.draggable.clone();
        $('.repack').append(item);
        $('.repack').removeClass('repack_border_change');
    },
    out: function () {
        $('.repack').removeClass('repack_border_change');
    }
});


//Addon another layout
var layout_item = '<div class=\"layout_item\">New layout</div>';
var n = 0;
//drop_units();
$('.plus_btn').click(function () {
    $(this).siblings('.layout_box').append('<div class="separator"  data-layout_id=\"' + layout_count + '\"  ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\"><a onclick=\"remove_separator(this)\" href="#">remove</a></div>');
    alert('This is from jquery');
    //Add the event on the created item
    var separator = document.getElementsByClassName('separator');
    
    for (var i = 0; i < separator.length; i++) {
        separator[i].addEventListener('drop', drop);
        separator[i].addEventListener('dragover', allowDrop);
    }
    
    $('.layout_group').each(function () {
        console.log('All the data: ' + $(this).data());
    });
    
    layout_order();
});
function add_unit_holder() {
    $(this).siblings('.layout_box').append('  <div class="separator" ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\"></div>');
}

function save_layout_txt_enter(e) {
    if (e.keyCode === 13) {
        var name = $(this).val();
        var layout_type = 0;
        var unit = dragged_unit_id;
        var order = 0;
        var supplied_index = 0;
        if (name !== '') {
            if (unit_txt_enter_press) {
                unit_txt_enter_press = false;
                console.log('The unit id before posting the request: ' + unit);
                var layout_id = $(this).parent().attr('data-layout_id');
                //Get the children elements with a class separator
                
                var html_item = '';
                var nodeNames = [];
                
                var this_1 = $(this);
                var new_unit_name = $(this).val();
                
                var name = $(this).val(), layout_type = 0, order = 0, supplied_index = 0;
                var curr = $(this);
                
                //<editor-fold defaultstate="collapsed" desc="---some not checked">
                
                var some_not_checked = false;
                $(this).parent().children().children('.separator').each(function (index, item) {
                    var chk = $(item).find('.chk_layout_type');
                    var chk2 = $(item).find('.chk2');
                    some_not_checked = (!chk.is(':checked')) && (!chk2.is(':checked'));
                });
                //</editor-fold>
                if (some_not_checked) {
                    alert('You have to set FK type: FORM or COMBO');
                } else {
                    var units_box = new Array();
                    $(this).parent().children().children('.separator').each(function (index, item) {//saving each units in a layout
                        var chk = $(item).find('.chk_layout_type');
                        var chk2 = $(item).find('.chk2');
                        var cbo_form = (chk2.is(':checked')) ? 'form' : 'combo';
                        var unit_id = $(item).data('id');
                        
                        var one_unit = {
                            name: new_unit_name,
                            layout_type: 0,
                            unit: $(item).data('id'),
                            order: 0,
                            supplied_index: 0,
                            structure: structure
                        };
                        units_box.push(one_unit);
                        
                    });
                    $.ajax({
                        type: 'POST',
                        dataType: 'json', contentType: 'application/json',
                        url: 'api/new_data/new_f_layout_unit_template',
                        data: JSON.stringify(
                                {
                                    mdl_layouts_and_types: units_box
                                }
                        ),
                        success: function (result) {
                            console.log('saved the unit template: ');
                        }, error: function (err) {
                            console.log('Unit template not saved');
                            console.log(err);
                        }
                    });
                }
                this_1.parent().children('.layout_box').children('.layout_item').children('.registration').val('');
            }
        } else {
            if (unit_txt_enter_press) {
                alert('you have to provide the name of the layout');
            }
        }
    }
}

function remove_layout_btn_click(e) {
    
    var layout_id = e.target.getAttribute('data-layout_id');
    e.target.parentElement.remove();
    console.log(e);
    $.ajax({
        type: 'POST',
        url: 'api/deletions/delete_f_end_layout/' + layout_id,
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            alert(result);
            console.log(result);
        }
        
    });
}
function dragStart(event) {
    event.dataTransfer.setData("Text", event.target.id);
    event.target.style.height = '20px';
    event.target.style.margin = '0px';
    dragged_unit_id = event.target.getAttribute('data-id');
    
//    var data = event.dataTransfer.getData("Text");
//    var my_b = document.getElementById(data);     
    
}
function allowDrop(event) {
    event.preventDefault();
}

function el(class_name) {
    return $(document.createElement('div')).addClass(class_name);
}


var dropped_items = 0;
function drop(event) {
    event.preventDefault();
    var data = event.dataTransfer.getData("Text");
    event.target.appendChild(document.getElementById(data));
    event.target.setAttribute('data-id', dragged_unit_id);
    dropped_items += 1;
    var c = dropped_item;
    var idtwin = event.target.getAttribute('data-id');
    
    var form_type = el('chk_layout_type_box abs row')
            .append($(document.createElement('div')).addClass('col_lg_6 col_md_6 col_sm_12  col_c2')
                    .append($(document.createElement('label')).attr('for', 'chk_cbo_' + idtwin).html('combo'))
                    .append($(document.createElement('input')).addClass('chk_layout_type').attr('title', 'combo').attr('data-chk_cbo', idtwin).attr('type', 'checkbox').attr('id', 'chk_cbo_' + idtwin)
                            .on('click', function () {
                                if ($(this).prop('checked')) {
                                    $(this).parent().siblings(".layou_merge_content").attr('combo_form', 'combo');
                                } else {
                                    
                                }
                            })
                            )
                    
                    )
            .append($(document.createElement('div')).addClass('col_lg_6 col_md_6 col_sm_12 col_c2 ')
                    .append($(document.createElement('label')).attr('for', 'chk_form_' + (parseInt(idtwin) * 3)).html('Form'))
                    .append($(document.createElement('input')).addClass('chk_layout_type chk2').attr('title', 'form').attr('data-chk_frm', (parseInt(idtwin) * 3)).attr('type', 'checkbox').attr('id', 'chk_form_' + (parseInt(idtwin) * 3))
                            .on('click', function () {
                                if ($(this).prop('checked')) {
                                    $(this).parent().siblings(".layou_merge_content").attr('combo_form', 'form');
                                }
                            }))
//                    .append($(document.createElement('div')).addClass('top_right').css('width', '35px').css('height', '35px').css('color', 'red').html('x'))
                    );
    
    $(event.target).append(form_type);
    $('.separator').css('position', 'relative');
    $('.chk_layout_type_box').css('width', '200px').css('position', 'absolute').css('right', '0px').css('top', '0px');
    
}
function get_all_separators() {
    var parent_note = this.getAttribute('');
    var all_sepatators = document.getElementsByClassName('');
}

$('.layout_adder').click(function () {
    var g_30 = document.getElementsByClassName('gap_30');
    var sub_1 = document.createElement('div');
    var remove_layout_btn_lock = document.createElement('div');
    
    var unit = 0, name = 0, layout_type = 0, unit = 0, order = 0, supplied_index = 0;
    
    
    //<editor-fold defaultstate="collapsed" desc="---------------------Create new layout box and layout ---------------">
    
    
    sub_1.classList.add('part1', 'page_container', 'layout_group');
    remove_layout_btn_lock.className = 'remove_layout_btn_lock';
    remove_layout_btn_lock.addEventListener('click', remove_layout_btn_click);
    remove_layout_btn_lock.innerHTML = 'Close';
    
    
    var sub_2_text = document.createElement('input');
    sub_2_text.setAttribute('type', 'text');
    sub_2_text.classList.add('small_textbox', 'wid_full', 'clear_onload', 'enabled', 'txt_layout');
    sub_2_text.setAttribute('disabled', 'false');
    sub_2_text.setAttribute('required', 'true');
    sub_2_text.setAttribute('placeholder', 'layout name');
    sub_2_text.setAttribute('name', 'name');
    sub_2_text.addEventListener('keyup', save_layout_txt_enter);
    
    var sub_3_div = document.createElement('div');
    sub_3_div.classList.add('part1', 'layout_box');
    
    var sub_4_div = document.createElement('div');
    sub_4_div.className = 'layout_item';
    
    var sub_5_input_1 = document.createElement('input');
    sub_5_input_1.setAttribute('placeholder', 'Menu');
    sub_5_input_1.classList.add('textbox', 'menu');
    
    var sub_6_input_2 = document.createElement('input');
    sub_6_input_2.classList.add('textbox', 'registration');
    sub_6_input_2.setAttribute('placeholder', 'Registration');
    
    var sub_7_input_3 = document.createElement('input');
    sub_7_input_3.setAttribute('placeholder', 'Report');
    sub_7_input_3.className = 'textbox';
    
    
    var sub_8_div = document.createElement('div');
    sub_8_div.classList.add('separator', 'relate');
    
    
    var sub_9_link = document.createElement('a');
    sub_9_link.setAttribute('href', '#');
    sub_9_link.addEventListener('click', remove_separator);
    
    var sub_10_div = document.createElement('div');
    sub_10_div.classList.add('item', 'plus_btn');
    sub_10_div.addEventListener('click', add_unit_holder);
    sub_10_div.innerHTML = '+';
    sub_8_div.style.backgroundColor = '#ed6015';
    
    
    sub_4_div.appendChild(sub_5_input_1);
    sub_4_div.appendChild(sub_6_input_2);
    sub_4_div.appendChild(sub_7_input_3);
    
    
    sub_8_div.addEventListener('drop', drop);
    sub_8_div.addEventListener('dragover', allowDrop);
    
    
    sub_8_div.appendChild(sub_9_link);
    sub_3_div.appendChild(sub_4_div);
    sub_3_div.appendChild(sub_8_div);
    
    sub_1.appendChild(remove_layout_btn_lock);
    sub_1.appendChild(sub_2_text);
    sub_1.appendChild(sub_3_div);
    sub_1.appendChild(sub_10_div);
    
    sub_1.addEventListener('click', get_all_separators);
    
    for (var i = 0; i < g_30.length; i++) {
        g_30[i].appendChild(sub_1);
    }
    //</editor-fold>
    
    $('.enabled').prop('disabled', false);
    
    var req = 'yes';
    $.post('api/new_data/clear_and_prepareNew', function (data) {
        console.log('Data: ' + data);
    }).complete(function () { });
    
    
//    drop_units();
});

function remove_separator(item) {
    $(item).children().remove();
}
$('.separator > a').click(function () {
    $(this).parent().remove();
    
});


//$('.enabled').prop('disabled', false);

function rad_fk_changed(item) {
    if ($(item).is(':checked')) {
        
        var layout_id = $(item).data('layoutid');
        var unit = $(item).data('unitid');
        var tuple = $(item).data('tuple_id');
        var disp_type = $(item).data('disp');
        var structureid = $(item).data('structureid');
        $.ajax({
            type: 'POST',
            url: 'api/new_data/new_fk_order/' + layout_id + '/' + unit + '/' + tuple + '/' + disp_type,
            dataType: 'json',
            contentType: 'application/json',
            beforeSend: function (xhr, opt) {
                
            },
            success: function (result) {
                var s = '';
                for (var i = 0; i < result.length; i++) {
                    s += '<br/> Unit id:  ' + result[i].unit_id;
                }
            }, error: function (xhr, ajaxOptions, thrownError) {
                console.log('status sending request: ' + xhr.status);
                console.log('Error sending request: ' + thrownError);
            }
            
        }).done(function () {
            
        });
    } else {
        alert('not selected');
    }
}



function get_only_tuples_by_unit(item) {
    var unit_id = $(item).data('unit_id');
    
    var layout_type = $(item).data('typeid');
    var layoutid = $(item).data('layoutid');
    var this_item = $(item);
    
    $.ajax({
        type: 'GET', url: 'api/ajaxrest/get_only_tuples_by_unit/' + unit_id,
        dataType: 'json',
        beforeSend: function (xhr, opt) {
            $('.tuples_box').remove();
        },
        contentType: 'application/json',
        
        success: function (result) {
            var s = '';
            console.log(result);
            
            for (var i = 0; i < result.length; i++) {
                var form_checked = (result[i].fkdisp_type === 'form') ? 'checked' : '';
                var cbo_checked = (result[i].fkdisp_type === 'combo') ? 'checked' : '';
                s += '<div style=\"float: left; width:100%;padding: 3px; border-top: 1px solid #7a9cd1;  border-bottom: 1px solid #7a9cd1;\"  >' + result[i].name;
                if (result[i].category === 'FK') {
                    s += '        <div class=\"\" style=\"float: right;\">'
                            + '             <div style=\"float: left;\"><input type=\"radio\" id=\"r1' + i + '\"   data-structureid=\"' +
                            structure + '\"   data-layoutid=\"' +
                            layoutid + '\"    data-unitid=\"' +
                            result[i].unit + '\"  onchange=\"rad_fk_changed(this)\" data-tuple_id=\"' +
                            result[i].tuple_id + '\"  data-disp="combo" ' + cbo_checked + '    name=\"r1_' + i + '\"/><label for=\"r1' + i + '\" class=\"hand_cursor\"> Combo </label></div>'
                            + '\
                            \n\
                            \n\
             <div style=\"float: left;\"><input type=\"radio\" id=\"r1_' + i + '\"         data-structureid=\"' +
                            structure + '\"   data-layoutid=\"' +
                            layoutid + '\"    data-unitid=\"' +
                            result[i].unit + '\" onchange=\"rad_fk_changed(this)\" data-tuple_id=\"' +
                            result[i].tuple_id + '\" data-disp="form"  ' + form_checked + '    name=\"r1_' + i + '\"/><label for=\"r1_' + i + '\" class=\"hand_cursor\"> Form  </label></div>'
                            + '        </div>';
                }
                s += '</div>';
                
            }
            var tp = $(document.createElement('div')).addClass('tuples_box').css('padding', '3px 10px;');
            $(this_item).parent().append(tp);
            $('.tuples_box').slideUp(0, function () {
                
                this_item.parent().children('.tuples_box').slideDown(100, function () {
                    $('.tuples_box').html('').html(s);
                });
            });
            
        }, error: function (xhr, ajaxOptions, thrownError) {
            console.log('status sending request: ' + xhr.status);
            console.log('Error sending request: ' + thrownError);
        }
    }).done(function () {
        
    });
}


function   get_units_by_layouts(item) {
    var layout_type = $(item).data('typeid');//this is the name even if the variable is calle layout_id
    $.ajax({
        type: 'GET',
        url: 'api/ajaxrest/units_by_layout/' + layout_type + '/' + structure,
        dataType: 'json', contentType: 'application/json',
        success: function (result) {
            var s = '';
            s += '<p><h3>Units by Layout</h3></p>';
            for (var i = 0; i < result.length; i++) {
                s += '<br/><div style=\"float: left; width:100%; margin-top: 3px;\" class=\"brd  \">'
                        + '       <div class=\"pad\ hand_cursor" style=\"float: left;min-width: 80px; color: #fff; background-color: #007fb5;\" onclick=\"get_only_tuples_by_unit(this)\" data-layoutid=\"' + result[i].layoutid + '\" data-typeid=\"' + result[i].f_end_layout_type_id + '\"  data-unit_id=\"' + result[i].unit_id + '\" > ' + result[i].name + '</div>'
                        + '      <div style=\"padding: 3px 10px;\" class=\"tuples_box\"> </div>'
                        + '</div>';
            }
            $('.units_by_layout_databox').html(s);
        }, error: function (xhr, ajaxOptions, thrownError) {
            console.log('status sending request: ' + xhr.status);
            console.log('Error sending request: ' + thrownError);
        }
        
    }).done(function () {
        
    });
    
    
    
}
$('.layout_fk_order').click(function () {
    
    $.ajax({
        type: 'GET',
        
        url: 'api/ajaxrest/f_end_layout_by_structure/' + structure,
        
        dataType: 'json',
        contentType: 'application/json',
        beforeSend: function (xhr, opt) {
            //if(condition){
            //xhr.abort();//Terminate if condition =true
            //}
        },
        success: function (result) {
            var s = '';
            s += '<p><h3>Layouts</h3></p>';
            for (var i = 0; i < result.length; i++) {
                s += '<br/> <div class=\"pad brd hand_cursor\" data-typeid=\"' + result[i].f_end_layout_type_id + '\" onclick=\"get_units_by_layouts(this)\"> ' + result[i].name + '</div>';
                
            }
            $('.layout_data_box').html(s);
        }, error: function (xhr, ajaxOptions, thrownError) {
            console.log('status sending request: ' + xhr.status);
            console.log('Error sending request: ' + thrownError);
        }
        
    }).done(function () {
        
    });
});
