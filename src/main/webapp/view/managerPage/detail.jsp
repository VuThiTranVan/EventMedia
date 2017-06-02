<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Management users 
 * vu.thi.tran.van@framgia.com
 * 18/04/2017
 -->

<c:choose>
	<c:when test="${not empty user }">
		<form:form id="viewUserForm" class="form-horizontal" modelAttribute="user">
			<section class="pb50">
				<div class="body clearfix mt20 manageUser">
					<div class="panel panel-default">
						<div class="panel-heading detail-user">
							<div class="detail-user-head-left">
								<h3 class="panel-title">Imfomation detail user</h3>
							</div>
						</div>
						<!-- /.panel-heading -->

						<div class="panel-body">

							<table class="table-bordered profile_regist">
								<tr>
									<th>User name</th>
									<td><label>${user.userName}</label></td>
								</tr>
								<tr>
									<th>Full name</th>
									<td><label>${user.name}</label></td>
								</tr>
								<tr>
									<th>Birthday</th>
									<td><label>${user.birthDate}</label></td>
								</tr>
								<tr>
									<th>Phone number</th>
									<td><label>${user.phone}</label></td>
								</tr>
								<tr>
									<th>Join status</th>
									<td><c:if test="${user.joinStatus == '2' }">
											<label>Request</label>
										</c:if>
										<c:if test="${user.joinStatus == '3' }">
											<label>Approve</label>
										</c:if>
									</td>
								</tr>
								<tr>
									<th>Email</th>
									<td><label>${user.email}</label></td>
								</tr>
							</table>

						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div id="sub_btn">
					<button onclick="clickRemoveUser(${user.userId},${user.idGroup},this)">
						<img src='./assets/imgs/delete-record.png' alt='Remove user'
							style='height: 20px;' />
					</button>

				</div>
			</section>
		</form:form>
	</c:when>
	<c:otherwise>
		<section class="bg_white clearfix messageError">
			<div class="body clearfix mt20 manageUser" id="messageContainer">
				 <spring:message code="no_find_info_detail" text="default text" />
			</div>
		</section>
	</c:otherwise>
</c:choose>