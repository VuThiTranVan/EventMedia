var scrollTrigger = 100, // px
backToTop = function() {
	var scrollTop = $(window).scrollTop();
	if (scrollTop > scrollTrigger) {
		$('#back-top').addClass('show');
	} else {
		$('#back-top').removeClass('show');
	}
};

backToTop();

$(window).on('scroll', function() {
	backToTop();
});

$('#back-top').on('click', function(e) {
	e.preventDefault();
	$('html,body').animate({
		scrollTop : 0
	}, 300);
});