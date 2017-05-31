$("#search-image").click(function() {
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
});
