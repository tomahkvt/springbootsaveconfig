var url = "ws://" + location.host + "/saveconfig/weblog";
console.log(url);
var sock;
var status_console;
$(document).ready(function() {

	$("#divUpdate").hide();
	$("#divAdd").hide();
	var array_for_delete_command = [];
	var array_for_delete_schedule = [];
	console.log(url);
	$('#task_table tfoot th').each(function(i) {
		var title = $('#task_table thead th').eq($(this).index()).text();
		$(this).html('<input type="text" size ="9" placeholder="Search ' + title + '" data-index="' + i + '" />');
	});

	// load table template
	$('#task_table').DataTable({

		"order" : [ [ 0, "desc" ] ],
		"processing" : true,
		"serverSide" : true,
		"autoWidth" : false,
		"scrollX" : "true",
		"fixedColumns" : "true",

		"columns" : [ {
			"data" : "taskcompid",
			"width" : '20px'
		}, {
			"data" : "taskname",
			"width" : '100px'
		}, {
			"data" : "type",
			"width" : '100px'
		}, {
			"data" : "status",
			"width" : '100px'
		}, {
			"data" : "startTime"
		}, {
			"data" : "stopTime"
		} ],
		"ajax" : {
			"url" : "api/taskcomplited/jointask",
			"type" : "POST"
		},

		initComplete : function() {
			var api = this.api();

			// Apply the search
			api.columns().every(function() {
				var that = this;

				$('input', this.footer()).on('keyup change', function() {
					if (that.search() !== this.value) {
						that.search(this.value).draw();
					}
				});
			});
		}

	});

	// DataTable
	var table = $('#task_table').DataTable();

	// Apply the search
	table.columns().every(function() {
		var that = this;

		$('input', this.footer()).on('keyup change', function() {
			if (that.search() !== this.value) {
				that.search(this.value).draw();
			}
		});
	});
	$('#complitedtask_table').DataTable();

	$('#complitedtasklogging_table').DataTable();

	$.getJSON("api/task/fortable", function(data) {
		$("#task_list").empty();
		$.each(data, function(index, value) {
			$("#task_list").append($('<option>', {
				value : value.DT_RowId,
				text : value.name
			}));
		});
	});

	$('#control').children().prop('disabled', true);

	$("#label_status").text("Disconected");
	$("#console_log").change(function() {

		var psconsole = $('#console_log');
		if (psconsole.length)
			psconsole.scrollTop(psconsole[0].scrollHeight - psconsole.height());
	});

	startwebsocket();

	setInterval(function() {
		$.get("api/log/getstatus", function(data) {
			// console.log("getstatus=" + data);
			switch (data) {
			case "IDLE":
				$('#bt_start').prop('disabled', false);
				$('#bt_pause').prop('disabled', true);
				$('#bt_stop').prop('disabled', true);
				$('#bt_pause').html('pause');
				break;
			case "STARTED":
				$('#bt_start').prop('disabled', true);
				$('#bt_pause').prop('disabled', false);
				$('#bt_stop').prop('disabled', false);
				$('#bt_pause').html('pause');
				break;
			case "PAUSED":
				$('#bt_start').prop('disabled', true);
				$('#bt_pause').prop('disabled', false);
				$('#bt_stop').prop('disabled', false);
				$('#bt_pause').html('resume');
				break;
			}
		});

		// console.log("test connection");
		console.log(status_console);
		console.log("sock.readyState=" + sock.readyState);
		if (status_console == "Disconnected") {
			if (sock.readyState == WebSocket.CLOSED) {
				startwebsocket();
				// console.log(sock.readyState);
			}
			if (sock.readyState == WebSocket.OPEN) {
				connectConsole();
			}
		}

		if (status_console == "Connected") {
			if (sock.readyState == WebSocket.CLOSED) {
				startwebsocket();
				// console.log(sock.readyState);
			}
		}
	}, 1000);

	function sayMarco() {
		/*
		 * console.log('Sending Marco!'); $('#console_log').append('Sending
		 * "Marco!"<br/>'); sock.send("Marco!");
		 */
		var psconsole = $('#console_log');
		if (psconsole.length)
			psconsole.scrollTop(psconsole[0].scrollHeight - psconsole.height());
	}

	$('#bt_stop').click(function() {
		$.get('api/log/stop');

	});

	$('#bt_start').click(function() {

		$selectedtask = $("#task_list").val();
		if ($selectedtask == null) {
			alert("Выберите устройство");
			return;
		}
		$.get('api/log/start/' + $selectedtask);
		$('#bt_stop').prop('disabled', false);
		$('#bt_pause').prop('disabled', false);
		$('#bt_start').prop('disabled', true);

	});

	$('#bt_pause').click(function() {
		if ($('#bt_pause').html() == "pause") {
			$.get("api/log/pause", function(data) {
			});
		}
		if ($('#bt_pause').html() == "resume") {
			$.get("api/log/resume", function(data) {
			});
		}

	});

	// task_table select row
	$('#task_table tbody').on('click', 'tr', function() {
		var table = $('#task_table').DataTable();
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var device = table.row('.selected').data();
		console.log(device);
		$('#complitedtasklogging_table').DataTable().destroy();
		$('#complitedtasklogging_table').DataTable({
			"ajax" : {
				"url" : "api/taskcomplitedlogging/taskcomplited/" + device.taskcompid,
				"dataSrc" : ""
			},
			"autoWidth" : false,
			scrollX : true,
			fixedColumns : true,
			"columns" : [ {
				"data" : "timeinsert",
				width : "140px"
			}, {
				"data" : "status",
				width : "20px"
			}, {
				"data" : "comment"
			} ]
		});
		$('#complitedtasklogging_table').DataTable().column('0:visible').order('desc').draw();
		$('#divcomplitedtask').slideDown("slow");

	});

});

function startwebsocket() {
	sock = new WebSocket(url);

	sock.onopen = function() {
		connectConsole();
	}

	sock.onmessage = function(e) {
		console.log('Received message: ', e.data);
		$('#console_log').append(e.data + '&#10;');
		var local_log = $('#console_log');
		local_log.scrollTop(local_log[0].scrollHeight);

	}

	sock.onclose = function() {
		console.log('Closing');
		$('#control').children().prop('disabled', true);
		$("#label_status").text("Disconected");
		status_console = "Disconnected";

	}

}

function connectConsole() {
	$('#control').children().prop('disabled', false);
	$('#bt_pause').prop('disabled', true);
	$('#bt_stop').prop('disabled', true);
	$("#label_status").text("Connected");
	status_console = "Connected";
	console.log('Opening');
}