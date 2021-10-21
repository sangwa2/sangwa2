<!--Two sized pane, expandable-->
<div  class="flex_bg off">

</div>


<div  class="page_container flex_two_sized_pane">
    <div class="only_close" id="tw_pane">
        <div class="cl_org">
            <div class="right_close_btn lone_close">
                <div class="close_bar close_bar1"></div>
                <div class="close_bar close_bar2"></div>
            </div> 
        </div>
    </div>
    <div class="part2 p1">
        <div class="part2 page_container">
            <div class="part1 page_container">
                <div class="structuer_box" style="min-width: 100px;"><!--Structure list js work--></div> 
                <div></div>
            </div>
            <div class="part1 two_window_p1">
                <div></div>
                <div class="page_container">
                    <div class="part5">
                        <div class="title_first"><a href="#" class="title_first">No</a></div>
                        <div>
                            <!--Pane in the center-->
                            <a href="#" data-call="unit_pane"  class="center_diff frm_caller">new </a>
                        </div>
                        <div> <a href="#" class="delete_structure_link" data-item_type="structure">Delete</a></div>
                        <div> 
                            <!--<a href="#" class="diff">Structure options</a>-->
                            <a href="#" data-call="strSettings_pane" class="frm_caller">Structure options</a>
                        </div>
                        <div> 
                             
                            <!--Pane in the center-->
                            <%@include file="Existing_db.jsp" %>
                            </div>
                    </div>
                </div>
                <div class="unit_box data_tables_box">
                    <!--contents,....-->  
                </div>
            </div>
        </div>
        <div class="part2 page_container"> 
            <div class="tuple_header page_container">
                <div class="part4">
                    <div class="title dynamic_title">   <!--Dynamic title-->   </div>
                    <div><a class="new_unit_link frm_caller" data-call="tuple_pane"  href="#">New</a></div>
                    <div><a class="delete_unit_link" data-item_type="unit" href="#">Delete</a>  </div>
                    <div><a  href="#"> </a></div>
                </div>
            </div>
            <div class="tuple_contents extra_pane_contents"> <!--content--></div>
        </div>
    </div>

</div>


































