<%@include file="main_page_parts/layouts_view.jsp" %>
<div class="menu_pane_with_menu layout_form">

    <div class="pane pane_with_other_menu layout_form"> 
        <div class="item pane_title">Layouts
            <div class="right_close_btn">
                <div class="close_bar close_bar1"></div>
                <div class="close_bar close_bar2"></div>
            </div>
        </div>
        <div class="item pane_contents full_pane_contents">
            <div class="tabs full_pane_tabs layout_merger_menus">
                <div class="tab_menu1 tab_menu" data-nmbr="1">Select Structure</div>
                <div class="tab_menu2 tab_menu" data-nmbr="2">Arrange</div>
                <div class="tab_menu3 tab_menu qry_menu" data-nmbr="3">Query</div>
                <div class="tab_menu4 tab_menu" data-nmbr="4">New Layout </div>
                <div class="tab_menu6 tab_menu layout_fk_order" data-nmbr="6">Layout Order</div>
                <div class="tab_menu5 tab_menu" data-nmbr="5">Edit fields</div>
                <div class="tab_menu7 tab_menu" data-nmbr="7">menu 7</div>
                <div class="tab_menu8 tab_menu" data-nmbr="8">menu 8</div>
            </div>
            <div class="tab_contents"> 
                <div class="tab_content tab_content1 structure_box_select">
                    <div class="menu_pane_onsame_link">
                        <a href="#">Get me sub contents  </a>
                        <div class="pane same_link_pane"> 
                            <div class="item pane_title">Pane 1
                                <div class="right_close_btn">
                                    <div class="close_bar close_bar1"></div>
                                    <div class="close_bar close_bar2"></div>
                                </div>
                            </div>
                            <div class="item pane_contents">

                            </div>
                        </div>
                    </div>

                    <a href="#">With other menus</a> <a href="#">Full in the window</a>
                </div>
                <div class="tab_content tab_content2">
                    <div class="pane same_link_pane show_on_load" style="position: absolute; top: 50px; left: 200px;  z-index: 18;"> 
                        <div class="item pane_title">Arrange 
                            <div class="right_close_btn">
                                <div class="close_bar close_bar1"></div>
                                <div class="close_bar close_bar2"></div>
                            </div>
                        </div>
                        <div class="item pane_contents same_link_pane_unit_content" id="color3">   </div>
                    </div>
                    <div class="page_container relate">
                        <div class="part2">
                            <div class="menu_pane_onsame_link show_on_load unit_holder">

                            </div>
                            <div class="item page_container gap_30">
                                <div class="row col_c5">
                                    <div class="item layout_adder"> +</div>
                                    <div class="item"><a href="#">Clear</a> </div>
                                    <div class="item">
                                        <a href="#" class="center_windows" data-bind="layout_view"  data-sc="9" data-window="layout_view">Layout view </a>
                                    </div>
                                    <div class="item"><a href="#">Clear</a> </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab_content tab_content3">
                    <div class="query_box page_container">
                        <div class="part1">
                            <%@include  file="/WEB-INF/views/query_maker.jsp"%>

                        </div>
                    </div>
                </div>
                            <div class="tab_content tab_content4"  >                           
                    <a href="#"  id="get_query_res">Bring many items</a>

                </div>
                <div class="tab_content tab_content5">                   <a href="#">With other menus</a>                   <a href="#">Full in the window</a>

                </div>
                            <div class="tab_content tab_content6 layout_fk_order" style=" float: left;width: 100%;"> 
                    <div class="layout_data_box brd pad" style="width: 45%; float: left;">
                        
                        
                    </div>
                    <div style="width: 45%; float: right; margin-right: 2%;" class="units_by_layout_databox"></div>
                </div>
                    <div class="tab_content tab_content7">Content 7 <a href="#">With other menus</a> <a href="#">Full in the window</a></div>
                    <div class="tab_content tab_content8">Content 8 <a href="#">With other menus</a> <a href="#">Full in the window</a></div>
                </div>
            </div>
            >
        </div>
    </div>  
    <%@include  file="/WEB-INF/views/query_layouts/other_layouts.jsp"%>

