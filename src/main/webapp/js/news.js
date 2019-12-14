$(function() {

	var $selectedComment = null;

	var loadNews = function(news) {
		$.ajax({
			method : "GET",
			url : "news.html",
			data : {
				title : title,
				comment : comment
			}
		}).done(function(response) {
			$('#commentsList').text(response.title + responce.comment);
		});
	}

	var loadInitialPage = function() {
		var news = $('#commentsList').val();
		loadNews(news);
	}

	$("#publish").on("click", function() {
		var title = $('#title').val();
		var $title = $('#title');
		var title = $title.val();
		var comment = $('#comment').val();
		var $comment = $('#comment');
		var comment = $comment.val();

		postComment(title, comment);
		$title.val('');
		$comment.val('');
	})
	var postComment = function(title, comment) {
		$.ajax({
			method : "POST",
			url : "createPost",
			data : {
				title : title,
				comment : comment
			}
		}).done(function(response) {
			renderComment(response.id, response.title, response.comment);
		}).fail(function(response) {
			console.log(response);
		})
	}

	getUserPosts = function() {
		$.ajax({
			method : "GET",
			url : "getPosts"
		}).done(
				function(response) {

					for (var i = 0; i < response.length; i++) {
						var currentPost = response[i];
						renderComment(currentPost.id, currentPost.place,
								currentPost.comment, currentPost.temp);
					}

				}).fail(function(response) {
			console.log(response);
		})
	}

	var renderComment = function(id, cityLabel, comment, temperature) {

		var $template = $('#comment-template').html();
		$template = $($template);

		$template.find('.remove-item').attr('id', id);
		$template.find('h4').text(title);
		$template.find('p').text(comment);

		var $commentsList = $("#comments-list");
		$commentsList.append($template);
	}

	$(document).on('click', '.remove-item', function() {
		$selectedComment = $(this).closest('.list-group-item');
	})

	$("#confirm-delete").on("click", function() {

		var postId = $selectedComment.find('.remove-item').attr('id');
		removePostById(postId);
	})

	removePostById = function(id) {
		$.ajax({
			method : "POST",
			url : "removeMyPost",
			data : {
				id : id
			}
		}).done(function(response) {
			$selectedComment.remove();
			$('#confirmDeleteModal').modal('hide');

		}).fail(function(response) {
			console.log(response);
		})

	}

	loadInitialPage();
	getUserPosts();

})