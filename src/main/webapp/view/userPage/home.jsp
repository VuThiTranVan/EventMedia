<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- regiter group
 * vu.thi.tran.van@framgia.com
 * 05/06/2017
 -->

<body>

	<section class="bg_white clearfix messageError">
		<div class="body clearfix mt20" id="messageContainer">
			<c:if test="${not empty valueSearch && empty image}">
				<spring:message code='no_find_result_search' text='' />
			</c:if>
			<c:if test="${empty valueSearch && empty image}">
				<spring:message code='no_find_list_image' text='' />
				
			</c:if>
		</div>
	</section>
	<section class="bg_white clearfix manageUser listImage">
		<div class="body clearfix mt20">
				<div class="panel panel-default">
					<div class="panel-heading">List image</div>
					<!-- /.panel-heading -->
					<div class="panel-body listImageBody">
								
						<div class="form-group form-group-lg">
							<!-- Search -->
							<form role="form" id="searchForm" action="" method="post">
								<div class="col-sm-5 search-area">
									<input name="valueSearch" value="${valueSearch}" id="valueSearch" type="text" placeholder="Search by title or name event" />
									<i id="search-image" class="glyphicon glyphicon-search"></i>
								</div>
								<!-- Paging -->
								<div class="col-sm-7 pagging-image">
									${paging.noOfRecord} record
									&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
									<a href="#" class="hidden_elem" onclick="prevPage(${paging.prePage})" id="btn_prev">Prev</a>
									&nbsp;&nbsp;&nbsp;<input name="noPage" type="text" value="${paging.currentPage}"/>
									&nbsp;&nbsp;&nbsp;<a href="#" onclick="nextPage(${paging.nextPage})" id="btn_next">Next</a>
									&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
									<button id="totalPpage">${paging.noOfPage} page</button>
								</div>
							</form>
						</div>
						<div class="clearfix" style="padding-bottom: 10px;"></div>
						

						<!-- List image -->
						<div class="form-group form-group-lg listResult">
							<c:choose>
								<c:when test="${not empty image}">
									<c:forEach items="${image}" var="image">
										<div class="col-sm-4">
											<div class="foo">
												<div class="main">
													<a href="Media/imageInfo/${image.id}" title="${image.title}">
														<img src="${image.url}" style="width: 243.5px; height: 200px;"/>
													</a>
												</div>
												<div class="hover">
													<a onclick="voteImage(this, ${image.id})" href="#" title="Vote for image">
														<center><img src="./assets/imgs/add_vote.png" style="width: 30px; height: 30px;"/> Vote </center>
													</a>
												</div>
											</div>
											<div class="clearfix" style="padding-bottom: 10px;"></div>
											<div class="imageInfo">
												<span class="title_image">
													Title: ${image.title}
												</span>
												<span class="vote_image">
													<img alt="Vote for image" src="./assets/imgs/vote" style="width: 30px; height: 30px;"/>&nbsp;&nbsp;${fn:length(image.votes)}
												</span>
											</div>
											<div class="groupInfo" style="float: left;">
												<span>
													<a href="Media/groupInfo/${image.group.id}">
														<img alt="Vote for image" src="./assets/imgs/event.png" style="width: 30px; height: 25px;"/>
														&nbsp;${image.group.name}</a>
												</span>
											</div>
											<div class="clearfix" style="padding-bottom: 10px;"></div>
										</div>

									</c:forEach>
								</c:when>
							</c:choose>
						</div>

						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				
			</div>
		<div class="clearfix"></div>
	</section>

</body>