<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- regiter user
 * vu.thi.tran.van@framgia.com
 * 25/05/2017
 -->
<section class="bg_white clearfix messageError">
	<div class="body clearfix mt20 hidden_elem" id="messageContainer">
		<spring:message code='username_exited' text='' /> </div>
</section>

<spring:url value="/register" var="userActionUrl" />
<form:form id="registerForm" class="form-horizontal" method="POST"
			action="${userActionUrl}" modelAttribute="user">
	<section class="pb50">
		<div class="body clearfix mt20">
			<div class="panel panel-default">
				<div class="panel-heading detail-user">
					<div class="detail-user-head-left">
						<h3 class="panel-title">Register user</h3>
					</div>
				</div>
				<!-- /.panel-heading -->

				<div class="panel-body">

					<table class="table-bordered profile_regist">
						<tr>
							<th>Username</th>
							<td>
								<form:input path="username" name="username" id="username"
										class="form-control css-required" placeholder="This is item required"
										style="display: inline; width: 65%;" />
							</td>
						</tr>
						<tr>
							<th>Password</th>

							<td><form:password path="password" name="password" id="password"
										class="form-control css-required" placeholder="This is item required"
										style="display: inline; width: 65%;" />
							</td>
						</tr>
						<tr>
							<th>Full name</th>

							<td><form:input path="name" name="name" id="name"
										class="form-control css-required" placeholder="This is item required"
										style="display: inline; width: 65%;" />
							</td>
						</tr>
						<tr>
							<th>Birthday</th>
							<td> <form:input
										path="birthday" name="birthday" id="birthday"
										class="form-control css-required birthday_picker"
										placeholder="This is item required"
										style="display: inline; width: 65%;" />
							</td>
						</tr>
						<tr>
							<th>Phone number</th>
							<td><form:input
										path="phone" name="phone" id="phone"
										class="form-control css-required"
										placeholder="This is item required"
										style="display: inline; width: 65%;" />
							</td>
						</tr>
						<tr>
							<th>Gender</th>
							<td>
								<form:radiobutton path="gender" name="gender" value="0" checked="true" /> Male
								<span style="padding-left: 5px;"></span>
								<form:radiobutton path="gender" name="gender" value="1" />Fmale
							</td>
						</tr>
						<tr>
							<th>Email</th>
							<td><form:input path="email" name="email" id="email"
									class="form-control css-required" placeholder="This is item required"
										style="display: inline; width: 65%;" /></td>
						</tr>
					</table>

				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<div id="sub_btn">
			<input type="button" id="btnRegiser" value="Register" class="btn-forwardscreen">
			<input type="button" id="btnClear" value="Clear" class="btn-forwardscreen">
		</div>
	</section>
</form:form>