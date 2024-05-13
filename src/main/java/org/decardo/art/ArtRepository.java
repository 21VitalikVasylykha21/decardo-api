package org.decardo.art;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/03/24
 */
public interface ArtRepository extends JpaRepository<Art, Long> {

	@Query(value = """
			SELECT DISTINCT a.* 
			FROM "ART" a
			LEFT JOIN UNNEST(a.TAG) t ON 1=1
			WHERE LOWER(a.TITLE) LIKE LOWER(CONCAT('%', :title, '%')) 
					AND (COALESCE(:tags) IS NULL OR COALESCE(:tags) = '{}' OR t = ANY(:tags))
			ORDER BY ID 
			""", nativeQuery = true)
	Page<Art> findByTitleAndTags(@Param("title") String name,
								 @Param("tags") String[] tags,
								 Pageable pageable);
}
