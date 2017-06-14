<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- regiter group
 * vu.thi.tran.van@framgia.com
 * 02/06/2017
 -->

<c:choose>
	<c:when test="${not empty user}">
		<section class="pb50">
			<div class="body clearfix mt20">
				<div class="panel panel-default">
					<div class="panel-heading detail-user">
						<div class="detail-user-head-left">
							<h3 class="panel-title">Information detail user</h3>
						</div>
					</div>
					<div class="panel-body">

						<table class="table-bordered profile_regist">
							<tr>
								<th>User name</th>
								<td><label>${user.username}</label></td>
							</tr>
							<tr>
								<th>Full name</th>

								<td><label>${user.name}</label></td>
							</tr>
							<tr>
								<th>Birthday</th>
								<td><label>${user.birthday}</label></td>
							</tr>

							<tr>
								<th>Phone number</th>
								<td><label>${user.phone}</label></td>
							</tr>
							<tr>
								<th>Gender</th>
								<td><label>${user.gender}</label></td>
							</tr>
							<tr>
								<th>Email</th>
								<td><label>${user.email}</label></td>
							</tr>
							<tr>
								<th>Status Join</th>
								<td><label>${user.statusJoin}</label></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
			<div id="sub_btn">
				<a href="/EventMedia/manager">
					<input type="button" value="Back manger home" class="btn-forwardscreen"></a>
			</div>
		</section>
		<c:choose>
			<c:when test="${not empty image}">
				<section class="pb50">
					<div class="body clearfix mt20">
						<div class="panel panel-default">
							<div class="panel-heading detail-user">
								<div class="detail-user-head-left">
									<h3 class="panel-title">Information image</h3>
								</div>
							</div>
							<div class="panel-body">
		
								<table class="table-bordered profile_regist">
									<tr>
										<th>Title</th>
										<td><label>${image.title}</label></td>
									</tr>
									<tr>
										<th>Description</th>
		
										<td><label>${image.description}</label></td>
									</tr>
									
									
										<tr>
											<th>Number vote</th>
											<td><label>${fn:length(image.votes)}</label></td>
										</tr>
										<c:if test="${fn:length(image.votes) > 0}">
											<tr>
												<th>User voted</th>
												<td>
													<label>
														<c:forEach var="vote" items="${image.votes}" varStatus="counter">
															${vote.user.username};
														</c:forEach>
													</label>
												</td>
											</tr>
										</c:if>
								</table>
								<div class="panel-body-image">
									<center><img alt="image" src="${image.url}"/></center>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>
				</section>
			</c:when>
		</c:choose>
	</c:when>
	<c:otherwise>
		<section class="bg_white clearfix">
			<div class="body clearfix mt20 manageUser" id="messageContainer">
				 <spring:message code="no_find_info_detail" text="default text" />
			</div>
		</section>
	</c:otherwise>
</c:choose>
