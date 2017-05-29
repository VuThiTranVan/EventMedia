<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- regiter group
 * vu.thi.tran.van@framgia.com
 * 26/05/2017
 -->
<section class="bg_white clearfix messageError">
	<div class="body clearfix mt20 hidden_elem" id="messageContainer">
		<spring:message code='regist_group_success' text='' /> </div>
</section>
<section class="bg_white clearfix messageError">
	<div class="body clearfix mt20 hidden_elem" id="messageContainerFail">
		<spring:message code='regist_group_fail' text='' /> </div>
</section>
<spring:url value="/user/registerGroup" var="groupActionUrl" />
<form:form id="registerForm" class="form-horizontal" method="POST"
			action="${groupActionUrl}" modelAttribute="group">
	<section class="pb50">
		<div class="body clearfix mt20">
			<div class="panel panel-default">
				<div class="panel-heading detail-user">
					<div class="detail-user-head-left">
						<h3 class="panel-title">Register group</h3>
					</div>
				</div>
				<!-- /.panel-heading -->

				<div class="panel-body">

					<table class="table-bordered profile_regist">
						<tr>
							<th>Name</th>
							<td>
								<form:input path="name" name="name" id="name"
										class="form-control css-required" placeholder="This is item required"
										style="display: inline; width: 65%;" />
							</td>
						</tr>
						<tr>
							<th>Description</th>

							<td><form:input path="description" name="description" id="description"
										class="form-control css-required" placeholder="This is item required"
										style="display: inline; width: 65%;" />
							</td>
						</tr>
						<tr>
							<th>Note</th>
							<td><form:input
										path="note" name="note" id="note"
										class="form-control"
										placeholder="This is not item required"
										style="display: inline; width: 65%;" />
							</td>
						</tr>
						<tr>
							<th>Type</th>
							<td>
								<form:radiobutton path="type" name="type" value="0" checked="true" />Private
								<span style="padding-left: 5px;"></span>
								<form:radiobutton path="type" name="type" value="1" />Public
							</td>
						</tr>
						<tr>
							<th>Date start</th>
							<td><form:input
										path="dateStart" name="dateStart" id="dateStart"
										class="form-control css-required"
										placeholder="This is item required"
										style="display: inline; width: 65%;" />
							</td>
						</tr>
						<tr>
							<th>Date end</th>
							<td><form:input
										path="dateEnd" name="dateEnd" id="dateEnd"
										class="form-control css-required"
										placeholder="This is item required"
										style="display: inline; width: 65%;" />
							</td>
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