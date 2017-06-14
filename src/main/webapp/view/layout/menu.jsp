<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--sidebar start-->
<aside>
    <div id="sidebar"  class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
        	  <p class="centered"><a href="#"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
        	  <h5 class="centered"><a href="${pageContext.request.contextPath}/showProfile">${pageContext.request.userPrincipal.name}</a></h5>
            <li class="sub-menu">
                <a class="active" href="javascript:;" >
                    <i class="fa fa-desktop"></i>
                    <span>EXTRA PAGES</span>
                </a>
                <ul class="sub">
                    <li><a href="index">HOME</a></li>
                <c:if test="${!empty pageContext.request.userPrincipal.name}">
                    <li><a href="managementGroup">CREATE GROUP</a></li>
                    <li><a href="showbooking">SHOWBOOKING</a></li>
                    <li><a href="${pageContext.request.contextPath}/statictical">STATICTICAL</a></li>
                </c:if>
                </ul>
            </li>
        </ul>
        <!-- sidebar menu end-->
    </div>
</aside>
<!--sidebar end-->