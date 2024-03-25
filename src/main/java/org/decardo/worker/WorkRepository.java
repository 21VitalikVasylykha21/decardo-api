package org.decardo.worker;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vitalii Vasylykha
 * @company Chainfulness
 * @since 2024/03/24
 */
public interface WorkRepository extends JpaRepository<Work, Long> {
}
