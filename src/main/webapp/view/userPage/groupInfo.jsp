<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- regiter group
 * vu.thi.tran.van@framgia.com
 * 26/05/2017
 -->
<body onload='getGroup(${idGroup});'>
	

	<section class="bg_white clearfix messageError">
		<div class="body clearfix mt20 hidden_elem" id="messageContainer">
			<center><spring:message code='init_group' text='' /></center></div>
	</section>

	<section class="bg_white clearfix manageUser infoGroup">
		<div class="body clearfix mt20">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="group-head-left">
						<h3 class="panel-title">Information group</h3>
					</div>
					<div id="divBtnEdit" class="btnRequestJoin hidden_elem">
						<input type="button" id="btnRequestJoin" onclick="clickBtnRequestJoin();" value="Request Join Group" class="btn btn-default">
					</div>
				</div>
				<div class="panel-body">
					<!-- Group Info -->
					<div class="form-group form-group-lg">
						<div class="col-sm-12">
							<div class="lblGroupLeft">
								<label>Name</label>
							</div>
							<div class="lblGroupRight">
								<label id="lblName"></label>
							</div>
						</div>
						<div class="col-sm-12 common-padding5"></div>
						<div class="col-sm-12">
							<div class="lblGroupLeft">
								<label>Description</label>
							</div>
							<div class="lblGroupRight">
								<label id="lblDescription"></label>
							</div>
						</div>
						<div class="col-sm-12 common-padding5"></div>
						<div class="col-sm-6">
							<div class="detail-borrowed-left">
								<label>Date start</label>
							</div>
							<div class="detail-borrowed-right">
								<label id="lblDateStart"></label>
							</div>
						</div>

						<div class="col-sm-6">
							<div class="detail-borrowed-left">
								<label>Date end</label>
							</div>
							<div class="detail-borrowed-right">
								<label id="lblDateEnd"></label>
							</div>
						</div>
						
						<div class="col-sm-12 common-padding5"></div>
						<div class="col-sm-6">
							<div class="detail-borrowed-left">
								<label>Note</label>
							</div>
							<div class="detail-borrowed-right">
								<label id="lblNote"></label>
							</div>
						</div>
						<div class="col-sm-3">
							<input type="radio" name="status" value="0" disabled="disabled"> Active
							<span class="common-padding-left5"></span>
							<input type="radio" name="status" value="1" disabled="disabled"> Active
						</div>
						<div class="col-sm-3">
							<input type="radio" name="type" value="0" disabled="disabled"> Private
							<span class="common-padding-left5"></span>
							<input type="radio" name="type" value="1" disabled="disabled"> Public
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- List member -->
	<section class="bg_white clearfix manageUser listMember">
		<div class="body clearfix mt20">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="head-left">
							<h3 class="panel-title">List Member</h3>
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
	<section class="bg_white clearfix manageUser listImage">
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
				<div id="sub_btn">
					<a href="/EventMedia/user"><input type="button"
						value="Back home" class="btn-forwardscreen"></a>
				</div>
			</div>
		<div class="clearfix"></div>
	</section>

</body>