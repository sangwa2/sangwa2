/* 
 * To change this license header, choose License Headers in Project Properties.
 * author SANGWA Emmanuel 
 * and open the template in the editor.
 */
$.getScript('globals.js', function () {});


var structure_data = '';
$(document).ready(() => {

    dict_structures();
    hide_prev();

});


var hide_prev = () => {
    $('.hide_prev').click(() => {
        $('.posed').hide();
        $('.menu_pane_onsame_link').show();
        $('.dict_same_link_pane').hide(0);
        $('.dict_structure').show(0);
        structure_data = '';
    });
};

var dict_structures = () => {
    $('.dict_structure').click(() => {//call the structures
        structure_data = '';
        $('.dict_pane_content').html('');
        
        $.ajax({//Get all the structures on the page load
            type: 'GET',
            url: 'api/ajaxrest/all_structures',
            dataType: 'json',
            contentType: 'application/json',
            success: function (result) {
                var selectable_structure = '';
                for (var i = 0; i < result.length; i++) {
                    structure_data += '<a onclick=\"get_dictionary(this)\" data-structure_id=\"' + result[i].structure_id + '"\"    href=\"#\" class=\"data_tables\">' + result[i].db_name + '</a><br/><br/>';
                }
                $('.dict_pane_content').html(structure_data);

            }
        });

    });
};
function get_dictionary(item) {
    structure = $(item).data('structure_id');
    $.ajax({
        type: 'GET',
        url: 'api/files/make_dictionary_diag/' + structure,
        success: function (result) {
            var s = '';

            $('.frm_dictionary .menu_pane_onsame_link').hide(function () {
                $('.posed').show();
                $('.posed').html(result);
            });

//            var copyText = $('.posed')  ;
//            
//
//
//            /* Select the text field */
//            copyText.select();
//            copyText.setSelectionRange(0, 99999); /*For mobile devices*/
//
//            /* Copy the text inside the text field */
//            document.execCommand("copy");
//
//            alert(copyText.value);



        }, error: function (err) {
                    console.log(err);
            alert(err);
        }

    });
}