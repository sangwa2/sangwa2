
var unit_name = '';
var fields = '';
var counter = 0;
var items_box = [], arr = [];
var item = '';
var unit_field = '';
var obj_box = [];
var business = [];
var unit_id = 0, tuple_id = 0, unit_tupelid = 0, structure = 0;
var qry = '';
var unit_field_found = false;
//var my_timer;

var unitdata_count = 0;


var prev = '';
 function postagain() {//this is to post the data again after adding/removing the unit & fields from thr query table from frontend
        $.ajax({//get the query again
            type: 'POST', dataType: 'text', contentType: 'application/json',
            url: 'api/query_filter/' + unit_id + '/' + tuple_id + '/' + structure,
            data: JSON.stringify({mdl_unit_nameses: obj_box}),
            success: function (result) {
                qry = result;
                $('.query_result').html('SELECT <br/>&nbsp;' + fields + '<br/>' + qry);
            }, error: function (xhr, ajaxOptions, thrownError) {
                console.log(`Error occured: `);
                console.log(thrownError);
                console.log(ajaxOptions);
                console.log(xhr);
            }
        });
    }
function query_chk_change(this_item) {
    unit_name = $(this_item).data('unit');
    item = $(this_item).data('tuple_name');
    tuple_id = $(this_item).data('tuple_id');
    unit_id = $(this_item).data('unit_id');
    unit_id = $(this_item).data('unit_id');
    structure = $(this_item).data('structure');
    unit_field = unit_name + '.' + item;


    if ($(this_item).is(':checked')) {

        if (checkValue(unit_name + '.' + item, items_box)) {//exists
        } else {
//            fields += comma + $(this_item).data('tuple_name');
//            fields += comma + unit_field;
        }

        items_box.push(unit_field);
        loop_fields();
        if (arr.indexOf(unit_name) === -1 || arr.length === 0) {
            arr.push(unit_name);
            var unit_name_obj = {
                name: unit_name
            };
            obj_box.push(unit_name_obj);
            console.log(`added item`);
            postagain();
        } else {
            $('.query_result').html('SELECT <br/>&nbsp;' + fields + '<br/>' + qry);
            console.log('Item not deleted');
        }
    } else {
        var index = items_box.indexOf(unit_field);


        console.log(`The items before removing: ${items_box} `);
        items_box.splice(index, 1);//removing the item from the array
        console.log(`The items after removing: ${items_box} `);
        loop_fields();


        // if the unit has no tuple in the array, 
        //remove the unit in the "arr"


        if (items_box.length > 0) {//Before removing the unit in the array we have to check all the fields of tue units have been removed
            unit_field_found = false;
            $(items_box).each(function (key, val) {
                if (~val.indexOf(unit_name)) {//To check the subtring of an item in the array
//                    arr.splice(arr.indexOf(unit_name), 1);//removing the item by its name
                    unit_field_found = true;
                }
            });
            if (!unit_field_found) {
                arr.splice(arr.indexOf(unit_name), 1);
            }
        }


        obj_box = [];
        for (var i = 0; i < arr.length; i++) {
            unit_name_obj = {
                name: arr[i]
            };
            obj_box.push(unit_name_obj);
        }

        //remove from db
        refresh_unit_data();

    }
}
function loop_fields() {
    fields = '';
    var comma = (counter === 0) ? '' : ', ';
    for (var i = 0; i < items_box.length; i++) {

        if (items_box[i] !== 'undefined') {
            fields += comma + items_box[i];
            console.log(items_box[i]);
        } else {
            alert('The item is: ' + items_box[i]);
        }
        counter += 1;
    }
}
function refresh_unit_data() {
    console.log(obj_box);
    unitdata_count += 1;
   
    if (!unit_field_found) {//delete unit from db to help the new query
        $.ajax({
            type: 'POST',
            url: 'api/deletions/delete_query_by_unit_tuple/' + unit_id + '/' + tuple_id,
            dataType: 'text',
            contentType: 'application/json',
            success: function (result) {
                if (result !== null) {
                    for (var i = 0; i < result.length; i++) {
                    }
                }
            }, error: function (a, b, c) {
                alert(`Error removing the unit: ${c}`);
            }
        }).done(postagain());
    } else {
        postagain();
    }










}



function query_tuple_click(this_unit) {
    //Pass the item and return a list of tuples and joins
    $(items_box).each(function (key, val) {

    });
}
function checkValue(value, arr) {
    var status = false;
    for (var i = 0; i < arr.length; i++) {
        var name = arr[i];
        if (name === value) {
            status = true;
            break;
        }
    }
    return status;
}
function item_found(item, arr) {
    return ($.inArray(item, arr) > 0) ? true : false;
}
$('.query_result').mouseover(function () {
    $('.qry_options').slideDown(100);
});
$('.query_result').mouseleave(function () {
    $('.qry_options').slideUp(100);
});

$('.layout_merger_menus > .tab_menu').click(() => {
    fields = '';
    $('.query_result').hide("slide", {direction: 'down'}, 300);
});


$('.qry_menu').click(() => {
//        $('#slide_p_1_2').delay(400).show("slide", {direction: 'down'}, 300);
    $('.query_result').html('');
    $('.query_result').show("slide", {direction: 'down'}, 300);

});
$(document).ready(function () {
    $('#btn_data_call').click(function () {
        var cont = '';
    });
});