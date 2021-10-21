<!--Pane in center--> 

<div class="bg_full white_bg tuple_pane_bg"></div>
<div class="pane center_window frm_tuple_pane"> 
    <div class="item pane_title">New Tuple

        <div data-fk_pane="" class="right_close_btn">
            <div class="close_bar close_bar1"></div>
            <div class="close_bar close_bar2"></div>
        </div>
    </div>
    <div class="item pane_contents"> 
        <form>
            <div class="page_section new_data_entry">
                <div class="new_data_tuple parts "> 
                    <div class="entry_label">          <label for ="txt_tuple_name"> Name </label> </div>  
                    <div class="entry_control">        <input type="text" autocomplete="off" class="textbox txt_escape" autofocus="" id="txt_tuple_name" data-unit="tuple"   name="txt_tuple_name" />   </div>
                </div>
                <div class="new_data_tuple parts "> 
                    <div class="entry_label">          <label for ="txt_data_type"> Data type </label> </div>  
                    <div class="entry_control">    

                        <div class="menu_pane_onsame_link draggable_item">
                            <a href="#">Choose data type </a>
                            <div class="pane same_link_pane"  draggable="true"> 
                                <div class="item pane_title">Foreign key
                                    <div data-fk_pane="fk_pane" class="right_close_btn">
                                        <div class="close_bar close_bar1"></div>
                                        <div class="close_bar close_bar2"></div>
                                    </div>
                                </div>
                                <div class="item pane_contents">
                                    <div onclick="fk_opt(this)" data-data_type="String"         class="opt_pk      data_type_opt_string opt"> String </div>
                                    <div onclick="fk_opt(this)" data-data_type="Integer"        class="opt_normal  data_type_opt_integer opt">Integer   </div>
                                    <div onclick="fk_opt(this)"             data-data_type="Date"           class="opt_foreign data_type_opt_date opt">Date      </div>
                                    <div class="off opt_reference_label">Referenced table </div>
                                </div>
                            </div>
                        </div>
                        <span id="data_type_box"></span>    
                        <input type="hidden" autocomplete="off" class="textbox" id="txt_data_type"   name="txt_data_type" />   </div>
                </div>
                <div class="new_data_tuple parts "> 
                    <div class="entry_label" >          <label for ="txt_category"> Category </label> </div>  
                    <div class="entry_control">        

                        <div class="menu_pane_onsame_link ">
                            <a href="#">Choose  </a>
                            <div class="pane same_link_pane opt_same_pane  "style="background-color: #fff" draggable="true"> 
                                <div class="item pane_title">Category
                                    <div data-fk_pane="fk_pane" class="right_close_btn">
                                        <div class="close_bar close_bar1"></div>
                                        <div class="close_bar close_bar2"></div>
                                    </div>
                                </div>
                                <div class="item pane_contents">
                                    <div onclick="fk_opt(this)" data-type="pk"         class="opt_pk      tuple_form_opt_pk opt"> Primary key </div>
                                    <div onclick="fk_opt(this)" data-type="normal"     class="opt_normal  tuple_form_opt_normal opt">Normal   </div>
                                    <div onclick="fk_opt(this)" data-type="foreign"    class="opt_foreign tuple_form_opt_fk opt">Foreign      </div>
                                    <div class="off opt_reference_label">Referenced table </div>
                                    <div class="cbo_fereign_field item">
                                        <table>

                                            <tr>
                                                <td> 

                                                    <select class="cbo_tuple_form"  data-cbo_units="tuple_form"><option></option>
                                                    <!--                                        <c:forEach items="${units_list}" var="ul">
                                               <option value="${ul.unit_id}">
                                                        ${ul.name}
                                                    </option>
                                                </c:forEach>-->
                                                    </select>
                                                </td>
                                                <td>
                                                    <table>
                                                        <tr>
                                                            <td> <input type="radio" name="cbo_fk" onchange="changed_cbo_tuplefk(this)" data-bind="rad_cbo"  id="rad_cbo"/> <label for="rad_cbo" >Combo </label>    </td>
                                                            <td> <input type="radio" name="cbo_fk" onchange="changed_cbo_tuplefk(this)" data-bind="rad_form" id="rad_form"/> <label for="rad_form" >Form</label></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>

                                        </table>

                                    </div>
                                    <div class=" save_fk btn_small_pane top_50" onclick="hide_fk_btn(this)">Confirm changes</div>

                                </div>
                            </div>
                        </div>
                        <span class="txt_category_choice">  </span>
                        <input type="hidden" autocomplete="off" class="textbox" id="txt_category"   name="txt_category" />   </div>
                </div>
                <div class="new_data_tuple parts "> 
                    <div class="entry_label">          <label for ="txt_display_caption"> Display caption </label> </div>  
                    <div class="entry_control">        <input type="text" autocomplete="off" class="textbox" id="txt_display_caption"   name="txt_display_caption" />   </div>
                </div>
                <div class="new_data_tuple parts "> 
                    <div class="entry_label">          <label for ="txt_today_date"> Today date </label> </div>  
                    <div class="entry_control">        <input type="text" autocomplete="off" class="textbox date_field" id="txt_today_date"   name="txt_today_date" />   </div>
                </div>
                <div class="new_data_tuple parts "> 
                    <div class="entry_label">          <label for ="txt_current_date"> Current date </label> </div>  
                    <div class="entry_control">    

                        <select class="textbox" id="txt_current_date"name="txt_current_date" >
                            <option>No</option>
                            <option>Yes</option>
                        </select>

                    </div>
                </div>

                <div class="save_button_row">    <div></div>  <div class="">   <input type="submit" class="btn_save" data-unit="tuple" value="Submit" />  </div>     
                </div> 
            </div>
        </form>
    </div>
</div>
