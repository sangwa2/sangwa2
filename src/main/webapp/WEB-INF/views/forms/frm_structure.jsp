 <!--Pane in center--> 

        <div class="bg_full structure_frm_pane_bg"></div>
        <div class="pane center_window structure_frm_pane"> 
            <div class="item pane_title">New Structure

                <div class="right_close_btn">
                    <div class="close_bar close_bar1"></div>
                    <div class="close_bar close_bar2"></div>
                </div>
            </div>
            <div class="item pane_contents"> 
                <form id="frm_new_structure" action="api/new_data/new_structure">

                    <div class="page_section new_data_entry">
                        <div class="new_data_tuple parts "> 
                            <div class="entry_label">          <label for ="txt_dbname"> db name </label> </div>  
                            <div class="entry_control">        <input type="text" autocomplete="off" class="textbox" id="txt_dbname"   name="txt_dbname" />   </div>
                        </div>
                        <div class="new_data_tuple parts "> 
                            <div class="entry_label">          <label for ="txt_db_user"> db user </label> </div>  
                            <div class="entry_control">        <input type="text" autocomplete="off" class="textbox" id="txt_db_user"   name="txt_db_user" value="sangwa" />   </div>
                        </div>
                        <div class="new_data_tuple parts "> 
                            <div class="entry_label">          <label for ="txt_db_password"> db password </label> </div>  
                            <div class="entry_control">        <input type="password" autocomplete="off" class="textbox" id="txt_db_password"   name="txt_db_password" value="123" />   </div>
                        </div>
                        <div class="new_data_tuple parts "> 
                            <div class="entry_label">          <label for ="txt_unit"> Total price </label> </div>  
                            <div class="entry_control">        <input type="text" autocomplete="off" class="textbox" id="txt_cash_total"   name="txt_cash_total"   value="300000"  />   </div>
                        </div>
                        <div class="new_data_tuple parts "> 
                            <div class="entry_label">          <label for ="txt_start_time"> start time </label> </div>  
                            <div class="entry_control">        <input type="text" autocomplete="off" class="textbox" id="txt_start_time"   name="txt_start_time"     />   </div>
                        </div>
                        <div class="new_data_tuple parts "> 
                            <div class="entry_label">          <label for ="txt_delivery_date"> delivery date </label> </div>  
                            <div class="entry_control">        <input type="text" autocomplete="off" class="textbox date_field" id="txt_delivery_date"   name="txt_delivery_date"     />   </div>
                        </div>
                        <div class="new_data_tuple parts "> 
                            <div class="entry_label">          <label for ="txt_pgm_language"> pgm language </label> </div>  
                            <div class="entry_control">        <input type="text" autocomplete="off" class="textbox" id="txt_pgm_language"   name="txt_pgm_language"  value="Java"    />   </div>
                        </div>
                        <div class="new_data_tuple parts "> 
                            <div class="entry_label">          <label for ="txt_platform"> platform </label> </div>  
                            <div class="entry_control">        <input type="text" autocomplete="off" class="textbox" id="txt_platform"   name="txt_platform"    value="Web"  />   </div>
                        </div>
                        <div class="new_data_tuple parts "> 
                            <div class="entry_label">          <label for ="txt_entry_date"> entry_ date </label> </div>  
                            <div class="entry_control">        <input type="text" autocomplete="off" class="textbox date_field" id="txt_entry_date"   name="txt_entry_date"     />   </div>
                        </div>

                        <div class="save_button_row">    <div></div>  <div class="">   <input type="submit" class="btn_save" data-unit="structure" value="Submit" />  </div>     
                        </div> 
                    </div>
                </form>
            </div>
        </div>
