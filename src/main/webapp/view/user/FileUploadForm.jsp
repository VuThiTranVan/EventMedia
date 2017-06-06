<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
	<section class="bg_white clearfix">
		<div class="body clearfix mt20">

			<div class="alert alert-success">
				<p id="err_data" style="color: red">${err_data}</p>
				<div class="alert alert-danger alert-dismissable" id="formAlert">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<strong><spring:message code='error' /></strong>
					<spring:message code='required_fileUpload' />
				</div>
				<div class="alert alert-danger alert-dismissable"
					id="formAlertImage">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<strong><spring:message code='error' /></strong>
					<spring:message code='error_format' />
				</div>
				<div class="alert alert-danger alert-dismissable"
					id="formAlertError">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<strong><spring:message code='error' /></strong>
					<spring:message code='title_max_length' />
				</div>
				<div class="alert alert-danger alert-dismissable"
					id="formAlertErrorDes">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<strong><spring:message code='error' /></strong>
					<spring:message code='des_max_length' />
				</div>
				<div class="alert alert-danger alert-dismissable"
					id="formAlertEmpty">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<strong><spring:message code='error' /></strong>
					<spring:message code='fill_empty' />
				</div>

			</div>
			<form method="POST"
				action="${pageContext.request.contextPath}/uploadImage"
				enctype="multipart/form-data" modelAttribute="dataImportBean"
				id="formImport">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">UPLOAD IMAGE</h3>
					</div>
					<div class="panel-body">
						<table class="table-bordered profile_regist">
							<tr>
								<th>Title <font class="red">*</font>
								</th>
								<td><input class="form-control" id="title" name="title"
									type="text" placeholder="please input text" required></td>
							</tr>
							<tr>
								<th>Import Image <font class="red">*</font>
								</th>
								<td>
									<div class="form-inline">
										<input type="file" id="fileImport" name="fileImport"
											class="file" />
										<div class="input-group col-xs-12">
											<input type="text" id="fileName" name="fileName"
												class="form-control" disabled placeholder="" /> <span
												class="input-group-btn">
												<button class="browse btn btn-primary" type="button">
													<i class="glyphicon glyphicon-search"></i> Choose File
												</button>
											</span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<th>Description <font class="red">*</font>
								</th>
								<td><textarea rows="10" cols="100" required
										class="form-control" id="description" name="description"
										placeholder="please input text"></textarea></td>
							</tr>
						</table>
					</div>
				</div>
				<div id="sub_btn">
					<a href="/EventMedia/user" class="btn btn-detail"><input
						type="button" value="BACK HOME" class="btn-forwardscreen"></a>
					<input type="submit" value="UPLOAD IMAGE" class="btn-forwardscreen"
						id="btnImport">
				</div>
			</form>
		</div>
	</section>

</body>

</html>
