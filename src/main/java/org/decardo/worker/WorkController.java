package org.decardo.worker;

import org.decardo.response.ListObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/03/24
 */

@CrossOrigin
@RestController
@RequestMapping("api/v1/work")
public class WorkController {
	@Autowired
	private WorkService workService;
	@Autowired
	private WorkMapper workMapper;

	@GetMapping
	public ListObjectResponse<WorkDTO> getAll() {
		return new ListObjectResponse<>(workService.findAll().stream()
				.map(workMapper::convert)
				.toList());
	}
}
