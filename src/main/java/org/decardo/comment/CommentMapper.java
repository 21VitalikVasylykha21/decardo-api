package org.decardo.comment;

import org.decardo.user.User;
import org.decardo.util.DateUtils;
import org.decardo.worker.Work;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/04/01
 */

@Service
public class CommentMapper {
	public CommentDTO convert(Comment comment) {
		return CommentDTO.builder()
				.id(comment.getId().toString())
				.comment(comment.getComment())
				.userId(comment.getUserId().toString())
				.workId(comment.getWorkId().toString())
				.date(DateUtils.formatDate(comment.getCreateDate()))
				.build();
	}

	public Comment convert(CommentDTO commentDTO, User user, Work work) {
		return Comment.builder()
				.id(commentDTO.getId() == null ? null : Long.valueOf(commentDTO.getId()))
				.workId(work.getId())
				.userId(user.getId())
				.comment(commentDTO.getComment())
				.build();
	}
}
