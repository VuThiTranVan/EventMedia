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
	<label id="homeTitleModallery" class="hidden_elem">
		<spring:message code='home_title_modallery' text='' /></label>

	<section class="bg_white clearfix messageError">
		<div class="body clearfix mt20 hidden_elem" id="mgsVote">
			<spring:message code='vote_error' text='' /> </div>
	</section>
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
									<c:if test="${paging.prePage > 0}">
										<a href="#" onclick="prevPage(${paging.prePage})" id="btn_prev">Prev</a>
									</c:if>
									
									&nbsp;&nbsp;&nbsp;<input name="noPage" id="noPage" type="text" value="${paging.currentPage}"/>

									<c:if test="${paging.currentPage < paging.noOfPage}">
										&nbsp;&nbsp;&nbsp;<a href="#" onclick="nextPage(${paging.nextPage})" id="btn_next">Next</a>
									</c:if>
									&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
									<c:choose>
										<c:when test="${paging.noOfPage <= 1 || paging.currentPage == paging.noOfPage}">
											<button id="totalPpage"  disabled="disabled">${paging.noOfPage} page</button>
										</c:when>
										<c:otherwise>
											<button id="totalPpage" onclick="goPageTotal(${paging.noOfPage})"><span class="totalPage">${paging.noOfPage}</span> page</button>
										</c:otherwise>
									</c:choose>
								</div>
							</form>
						</div>
						<div class="clearfix common-padding-bottom10"></div>

						<!-- List image -->
						<div class="form-group form-group-lg listResult">
							<c:choose>
								<c:when test="${not empty image}">
									<c:forEach items="${image}" var="image">
										<div class="col-sm-4">
											<div class="foo">
												<div class="main">
													<a href="#" title="${image.title}">
														<img src="${image.url}" 
															data-to= "${image.url}" 
															 data-caption="<img alt='Vote for image' src='./assets/imgs/vote' class='iconCaptionModallery'/>&nbsp;&nbsp; ${fn:length(image.votes)}
															<br><img alt='Vote for image' src='./assets/imgs/event.png' class='iconCaptionModallery'/>&nbsp;&nbsp;${image.group.name}<br/>Tilte: ${image.title}<br>
															 Description: ${image.description}<br>
															" class="modallery" />
													</a>
												</div>
												<div class="hover">
													<c:choose>
														<c:when test="${empty pageContext.request.userPrincipal.name}">
															<a href="/EventMedia/login" title="Vote for image">
																<center><img src="./assets/imgs/add_vote.png" class="iconVote"/> Add Vote </center>
															</a>
														</c:when>
														<c:otherwise>
															<c:set var = "flgVote" value = "true"/>
															<c:forEach var="item" items="${image.votes}">
																<c:if test="${item.user.username == pageContext.request.userPrincipal.name}">
																	<c:set var = "flgVote" value = "false"/>
																</c:if>
															</c:forEach>
															<c:choose>
																<c:when test="${flgVote}">
																	<a onclick="voteImage(this, ${image.id})" id="add" href="#" title="Vote for image">
																		<center><img src="./assets/imgs/add_vote.png" class="iconVote"/> Add Vote </center>
																	</a>
																	<a onclick="removeVoteImage(this, ${image.id})" id="remove" href="#" title="Vote for image" class="hidden_elem">
																		<center><img src="./assets/imgs/remove_vote.png" class="iconVote"/> Remove Vote </center>
																	</a>
																</c:when>
																<c:otherwise>
																	<a onclick="voteImage(this, ${image.id})" id="add" href="#" title="Vote for image" class="hidden_elem">
																		<center><img src="./assets/imgs/add_vote.png" class="iconVote"/> Add Vote </center>
																	</a>
																	<a onclick="removeVoteImage(this, ${image.id})" id="remove" href="#" title="Vote for image">
																		<center><img src="./assets/imgs/remove_vote.png" class="iconVote"/> Remove Vote </center>
																	</a>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="clearfix common-padding-bottom10"></div>
											<div class="imageInfo">
												<span class="title_image">
													Title: ${image.title}
												</span>
												<span class="vote_image">
													<img alt="Vote for image" src="./assets/imgs/vote" class="iconVote"/>&nbsp;&nbsp;<span class="vote">${fn:length(image.votes)}</span>
												</span>
											</div>
											<div class="groupInfo common-float-left">
												<span>
													<a href="/EventMedia/groupInfo/${image.group.id}">
														<img alt="Vote for image" src="./assets/imgs/event.png" class="iconVote"/>
														&nbsp;&nbsp;${image.group.name}</a>
												</span>
											</div>
											<div class="clearfix common-padding-bottom10"></div>
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