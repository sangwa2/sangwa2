var global_var = 'to be seen by all the files, i mean JSPs';

var all_structures = 'yes';
var all_units = 'yes';
var result = [];
var structure = 0;
var unit = 0;


var current_unit = 0; // This is the unit that help to check of the the user has not double clicked, 
//if the user has clicked twice the system should not retreive the tuples over and over again

var win_height = $(window).innerHeight();
var win_width = $(window).innerWidth();
var switch_pos = false;

var unit_name = '';


var fmer_w = 1166;
var unit_txt_enter_press = true;


var just_tuples=false;//This is used to only get tuples in the combobox, db.js
var ref_unit=0;//used to getfk table to update fk
var ref_tuple=0;//used to keep the tuple which is changing the category (PK, FK, NORMAL) 
var tuple_type='';// this is either pk,fk or normal

var item_type='';//This is the item to delete by, because there will be many unit to delete, wether unit. structure, etc


var tuple=0;//first used for deleting
var tuple_display_caption='';
var tuples_retrieve=false;


var display_count = 0;//this is a flag in db.js to allow refreshing of data after saving a tuple
var stop_refresh=true;
var reload_again=false;//used in db.js to allow the retrieval of data one process after another
var load_data=true; 


var working_db = '';//This is use while importing the existing database in Gigaflex it is use on script.js
function remove_px(word) {
    var lenght = word.length;
    var remain = lenght - 2;

    var to_int = parseInt(word.substring(0, remain));
    return to_int;
} 