 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="box_6_2">
    <div class="page_container">
        <div class="part2">
            <div class="parts">  
                <input type="text" class="textbox" required="true" name="name" value="${name}"   /><br/>
                <input type="text" class="textbox" required="true" name="name" value=""  /><br/>
            </div>
            <div class="parts">
                <c:forEach items="${mdl_unit}" var="it">
                    <div> <c:out value="${it.name}"/> </div>
         
                </c:forEach>
            </div>
        </div>
    </div>

</div>
<script>
   
</script>


