$(document).ready(function() {
	$(window).resize(function() {
		var h = Math.max($(window).height() - 0, 420);
		$('#container, #data, #tree, #data .content').height(h).filter('.default').css('lineHeight', h + 'px');
	}).resize();

	$('#tree').jstree({
		'core' : {
			'data' : {
				'url' : 'api/filetree?operation=get_node',
				'data' : function(node) {
					console.log(node);
					return {
						'id' : node.id
					};
				}
			},
			'force_text' : true,
			'themes' : {
				'responsive' : false,
				'variant' : 'small',
				'stripes' : true
			},
		},
		'types' : {
			'default' : {
				'icon' : 'folder'
			},
			'file' : {
				'valid_children' : [],
				'icon' : 'file'
			}
		},

		'plugins' : [ 'types', 'contextmenu' ],
		'contextmenu' : {
			"items" : function(node) { 
				return context_menu(node);
			}
		}
	}).on('changed.jstree', function(e, data) {
		console.log(data);
		if (data && data.selected && data.selected.length) {
			$.get('api/filetree?operation=get_content&id=' + data.selected.join(':'), function(d) {
				if (d && typeof d.type !== 'undefined') {
					$('#data .content').hide();
					switch (d.type) {
					case 'text':
					case 'txt':
					case 'md':
					case 'htaccess':
					case 'log':
					case 'sql':
					case 'php':
					case 'js':
					case 'json':
					case 'css':
					case 'html':
						$('#data .code').show();
						$('#code').val(d.content);
						break;
					case 'png':
					case 'jpg':
					case 'jpeg':
					case 'bmp':
					case 'gif':
						$('#data .image img').one('load', function() {
							$(this).css({
								'marginTop' : '-' + $(this).height() / 2 + 'px',
								'marginLeft' : '-' + $(this).width() / 2 + 'px'
							});
						}).attr('src', d.content);
						$('#data .image').show();
						break;
					default:
						$('#data .default').html(d.content).show();
						break;
					}
				}
			});
		} else {
			$('#data .content').hide();
			$('#data .default').html('Select a file from the tree.').show();
		}
	})
	.on('save_node.jstree', function(e, data) {console.log("save")})
	;

});

function context_menu(node) {
	if (node.icon === "file"){
	return {
		"save" : {
			label : "save",
			action : function() {
				save_node(node);
			}
		}
	}
	}else{
		return null;
	}
}

function save_node(node){
	console.log(node);
	window.location = 'api/filetree/save?id=' + node.id
}
