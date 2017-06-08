$(document).ready(function () {
            var win = $(window);
            var load = true;
            var page = 1;
            $('#loading').hide();
            win.scroll(function () {
            	if(load){
                    var scroll = win.scrollTop();
                    var position =$('#tournamentsContainer').offset().top + $('#tournamentsContainer').outerHeight() - window.innerHeight;
                    position = position + 50;
                    if (scroll >= position) {
                            load = false;
                            $('#loading').show();
                            $.ajax({
                            				url: ('/moreTournaments?page=' + page + '&size=10'),
                            	 				dataType: 'html',
                            	 				success: function(html) {
                            	 					if($.trim(html) === ""){
                            	 						$('#loading').hide();
                            	 						$('#tournamentsContainer').append('<div class="text-center"><p>No more tournaments</p></div>')
                            	 				    } else {
	                            	 					$('#tournamentsContainer').append(html);
	                            	 					$('#loading').hide();
	                            	 					page++;
	                            	 					load = true;
                            	 				    }
                            	 				}
                            	 			});
                        }
            	}
              });
            });