<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Management users 
 * vu.thi.tran.van@framgia.com
 * 18/04/2017
 -->
<c:choose>
	<c:when test="${not empty imageInfo.id }">
		<spring:url value="/uploadImage/update" var="userActionUrl" />
		<form id="updateForm" class="form-horizontal" method="post"
			enctype="multipart/form-data" action="${userActionUrl}"
			modelAttribute="imageInfo">
			<input name="id" id="id" class="hidden_elem" value="${imageInfo.id}" />
			<section class="pb50">
				<div class="body clearfix mt20 manageUser">
					<div class="panel panel-default">
						<div class="panel-heading detail-user">
							<div class="detail-user-head-left">
								<h3 class="panel-title">Imfomation detail image</h3>
							</div>
							<div class="detail-user-head-right">
								<button onclick="clickBtnEdit()" id="editbtn" type="button"
									class="btnScrUser lableForm btn btn-detail">Edit</button>
								<button id="savebtn" type="button"
									class="btnScrUser editForm hidden_elem btn btn-detail">Save</button>
								<button onclick="clickBtnCancel()" id="cancelbtn" type="button"
									class="btnScrUser editForm hidden_elem btn btn-detail">Cancel</button>
							</div>
						</div>
						<!-- /.panel-heading -->

						<div class="panel-body">

							<table class="table-bordered profile_regist">
								<tr>
									<th>Group</th>
									<td><label>${imageInfo.group.name}</label></td>
								</tr>
								<tr>
									<th>Title</th>

									<td><span id="lbltitle" class="lableForm"><label>${imageInfo.title}</label></span>
										<span class="editForm hidden_elem"> <input name="title"
											id="title" value="${imageInfo.title}"
											class="form-control css-required"
											placeholder="This is item required"
											style="display: inline; width: 65%;" />
									</span></td>
								</tr>
								<tr>
									<th>Image</th>
									<td><span id="lblurl" class="lableForm"><img
											alt="TransformedImage" src='${imageInfo.url}' /></span><span
										class="editForm hidden_elem">
											<div class="form-inline">
												<input type="file" id="fileImport" name="fileImport"
													class="file" />
												<div class="input-group col-xs-12">
													<input type="text" id="fileName" name="fileName"
														value="${imageInfo.url}" class="form-control" disabled
														placeholder="" /> <span class="input-group-btn">
														<button class="browse btn btn-primary" type="button">
															<i class="glyphicon glyphicon-search"></i> Choose File
														</button>
													</span>
												</div>
											</div>
									</span></td>
								</tr>
								<tr>
									<th>Description</th>
									<td><span id="lbldescription" class="lableForm"><label>${imageInfo.description}</label></span>
										<span class="editForm hidden_elem"> <input
											name="description" id="description"
											value="${imageInfo.description}"
											class="form-control css-required"
											placeholder="This is item required"
											style="display: inline; width: 65%;" />
									</span></td>
								</tr>
							</table>

						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div id="sub_btn">
					<a href="/SpringSecurity/home"><input type="button"
						value="Back home" class="btn-forwardscreen"></a>
				</div>
			</section>
		</form>
	</c:when>
</c:choose>