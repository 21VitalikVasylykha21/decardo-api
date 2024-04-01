package org.decardo.response;

import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/04/23
 */
@Getter
public class ListObjectResponse<T> {
	private final Long total;
	private final Integer status;
	private final List<T> result;

	public ListObjectResponse(Integer status, List<T> result) {
		this.status = status;
		this.result = result;
		this.total = (long) result.size();

	}

	public ListObjectResponse(List<T> result) {
		this(HttpStatus.OK.value(), result);
	}
}