<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Statistical
 * vu.thi.tran.van@framgia.com
 * 12/06/2017
 -->

<c:choose>
	<c:when test="${not empty highchart}">
		<section class="pb50">
			<div id="container" class="body clearfix mt20">
<pre id="csv" hidden="true">,${highchart.note} images
<c:forEach var="data" items="${highchart.content}">
${data}
</c:forEach>
</pre>
			</div>
			<div class="clearfix"></div>
		</section>
	</c:when>
	<c:otherwise>
		<section class="bg_white clearfix">
			<div class="body clearfix mt20 manageUser" id="messageContainer">
				 <spring:message code="statistical_image_of_group" text="default text" />
			</div>
		</section>
	</c:otherwise>
</c:choose>
