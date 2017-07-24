$(document).ready(function() {

	if (typeof top_menu_active !== 'undefined') {
		$("#" + top_menu_active).css('background-color', 'blue');
	}

	if (typeof slave_menu_show !== 'undefined') {
		console.log(slave_menu_show);
		$(".sub-menu").hide();
		/* $(".sub-menu." + slave_menu_show).show(); */
		$(".sub-menu." + slave_menu_show).show();
		console.log("slave_menu_show=" + slave_menu_show);
	} else {
		$(".sub-menu").hide();
	}

	if (typeof slave_menu_active !== 'undefined') {
		$("[href=" + slave_menu_active + "]").css('background-color', 'blue');
	}

	$("#systemgeneral").click(function() {
		$(".sub-menu").hide();
	});

	$("#viewing").click(function() {
		$(".sub-menu").hide();
	});

	$("#menu_configure").click(function() {
		$(".sub-menu").hide();
		$(".sub-menu.configure").show();
	});

	$("#menu_aministration").click(function() {
		$(".sub-menu").hide();
		$(".sub-menu.aministration").show();
	});

	$("#menu_shedule").click(function() {
		$(".sub-menu").hide();
		$(".sub-menu.shedule").show();
	});

})
