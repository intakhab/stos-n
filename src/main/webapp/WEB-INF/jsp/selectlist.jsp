<div id="demo-wrapper">
	
	<div class="row">
	
		<div class="col-md-5">
			<label for="items">Registered Test Case</label> <select multiple
				class="form-control crossover-box" id="items">
			</select>
		</div>

		<div class="col-md-1">
			<br />
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<button type="button" class="btn_link btn-success"
				id="crossover-btn-add">&nbsp;+&nbsp;&nbsp;</button>
			<button type="button" class="btn_link btn-success"
				id="crossover-btn-add-all">+&nbsp;+</button>
			<button type="button" class="btn_link btn-danger"
				id="crossover-btn-remove">&nbsp;&nbsp;-&nbsp;&nbsp;</button>
			<button type="button" class="btn_link btn-danger "
				id="crossover-btn-remove-all">&nbsp;-&nbsp;-&nbsp;</button>
		</div>

		<div class="col-md-5">
			<label for="selected">Selected Test Case</label> <select multiple
				class="form-control crossover-box" id="selected"></select>
		</div>
	</div>
	<br />
	<br />

</div>
<script type="text/javascript" src="js/crossover.js"></script>
<script type="text/javascript">
	
	$(function() {
		crossover();
		$(window).scroll(
				function() {
					var scroll = $(window).scrollTop();
					if (scroll > 65) {
						$('nav, .navbar-brand, .nav-link, .active-link')
								.addClass('scrolled');
					} else {
						$('nav, .navbar-brand, .nav-link, .active-link')
								.removeClass('scrolled');
					}
				});

		///////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////NAVBAR SCROLL/////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////
		$('.nav-link').click(function() {
			$('.active-link').removeClass('active-link');
			$(this).addClass('active-link');

			$('html, body').animate({
				scrollTop : $(this.hash).offset().top
			}, 900, function() {
				window.location.hash = this.hash;
			});
		});

	});
</script>