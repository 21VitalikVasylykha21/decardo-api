package org.decardo.art;

import java.util.List;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU 
 * @since 2024/05/12
 */
public record ArtSearchResponse(Long total, List<Art> arts) {
}
