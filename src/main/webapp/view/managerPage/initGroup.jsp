<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- regiter group
 * vu.thi.tran.van@framgia.com
 * 26/05/2017
 -->
<body onload='getGroup();'>
	<label id="mgsRejectUserError" class="hidden_elem">
		<spring:message code='reject_user_fail' text='' /></label>

	<label id="mgsAcceptUserError" class="hidden_elem">
		<spring:message code='accept_user_fail' text='' /></label>

	<label id="mgsRemoveUserSuccess" class="hidden_elem">
		<spring:message code='remove_user_success' text='' /></label>
	<label id="mgsRemoveUserError" class="hidden_elem">
		<spring:message code='remove_user_fail' text='' /></label>

	<label id="mgsRemoveImageSuccess" class="hidden_elem">
		<spring:message code='remove_image_success' text='' /></label>
	<label id="mgsRemoveImageError" class="hidden_elem">
		<spring:message code='remove_image_fail' text='' /></label>
		
	<label id="mgsUpdateGroupSuccess" class="hidden_elem">
		<spring:message code='update_group_success' text='' /></label>
	<label id="mgsUpdateGroupError" class="hidden_elem">
		<spring:message code='update_group_fail' text='' /></label>
		
	<section class="bg_white clearfix messageError">
		<div class="body clearfix mt20" id="message"></div>
	</section>

	<section class="bg_white clearfix messageError">
		<div class="body clearfix mt20 hidden_elem" id="messageContainer">
			<center><spring:message code='init_group' text='' /></center></div>
	</section>

	<section class="bg_white clearfix manageUser hidden_elem infoGroup">
		<div class="body clearfix mt20">
			<div class="panel panel-default">
				<div class="panel-heading" style="height: 45px;">
					<div class="head-left" style="float: left; with: 75%">
						<h3 class="panel-title">Information group</h3>
					</div>
					<div id="divBtnEdit" class="head-right" style="float: right; with: 20%; margin-top: -4px;">
					</div>
				</div>
				<div class="panel-body">
					<!-- Group Info -->
					<spring:url value="/manager/" var="groupActionUrl" />
					<form:form id="EditGroupForm" class="form-horizontal" method="POST"
						action="${groupActionUrl}" modelAttribute="group">
						<form:input path="id" name="id" id="id" class="hidden_elem" />
						<form:input path="deleteFlag" name="deleteFlag" id="deleteFlag" class="hidden_elem" />
						<div class="form-group form-group-lg">
							<div class="col-sm-12">
								<div style="width: 12%; float: left;">
									<label>Name</label>
								</div>
								<div style="width: 88%; float: left;">
									<form:textarea maxlength="50" path="name" name="name" id="name" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>
							<div class="col-sm-12" style="padding: 5px"></div>
							<div class="col-sm-12">
								<div style="width: 12%; float: left;">
									<label>Description</label>
								</div>
								<div style="width: 88%; float: left;">
									<form:textarea maxlength="500" path="description" name="description" id="description" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>
							<div class="col-sm-12" style="padding: 5px"></div>
							<div class="col-sm-6">
								<div class="detail-borrowed-left">
									<label>Date start</label>
								</div>
								<div class="detail-borrowed-right">
									<form:input path="dateStart" name="dateStart" id="dateStart" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>

							<div class="col-sm-6">
								<div class="detail-borrowed-left">
									<label>Date end</label>
								</div>
								<div class="detail-borrowed-right">
									<form:input path="dateEnd" name="dateEnd" id="dateEnd" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>
							
							<div class="col-sm-12" style="padding: 5px"></div>
							<div class="col-sm-6">
								<div class="detail-borrowed-left">
									<label>Note</label>
								</div>
								<div class="detail-borrowed-right">
									<form:textarea maxlength="500" path="note" name="note" id="note" class="form-control" style="display: inline; width: 100%;" disabled="true" />
								</div>
							</div>
							<div class="col-sm-3">
									<form:radiobutton path="status" name="status" id="active" value="0" disabled="true" />Active
									<span style="padding-left: 5px;"></span>
									<form:radiobutton path="status" name="status" id="inactive" value="1" disabled="true" />Inactive
							</div>
							<div class="col-sm-3">
									<form:radiobutton path="type" name="type" id="private" value="0" disabled="true" />Private
									<span style="padding-left: 5px;"></span>
									<form:radiobutton path="type" name="type" id="public" value="1" disabled="true" />Public

							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>

	<!-- List member -->
	<section class="bg_white clearfix manageUser hidden_elem listMember">
		<div class="body clearfix mt20">
				<div class="panel panel-default">
					<div class="panel-heading" style="height: 45px;">
						<div class="head-left" style="float: left; with: 75%">
							<h3 class="panel-title">List Member</h3>
						</div>
						<div id="divBtnEdit" class="head-right" style="float: right; with: 20%; margin-top: -4px;">
						</div>
					</div>

					<!-- /.panel-heading -->
					<div class="panel-body listMemberBody">
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-result" width="100%">
								<thead>
									<tr>
										<th>Username</th>
										<th>Name</th>
										<th>Email</th>
										<th>Phone</th>
										<th>Status</th>
										<th>Remove</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				
			</div>
		<div class="clearfix"></div>
	</section>
	
	
	<!-- List image -->
	<section class="bg_white clearfix manageUser hidden_elem listImage">
		<div class="body clearfix mt20">
				<div class="panel panel-default">
					<div class="panel-heading">List image</div>
					<!-- /.panel-heading -->
					<div class="panel-body listImageBody">
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-image" width="100%">
								<thead>
									<tr>
										<th>Tilte</th>
										<th>Author</th>
										<th>Number vote</th>
										<th>Remove</th>
										<th>View</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				
			</div>
		<div class="clearfix"></div>
	</section>

</body>