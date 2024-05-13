package org.decardo.art.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU 
 * @since 2024/05/08
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`MODEL`")
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String file;
	private String preview;
	@Enumerated(EnumType.STRING)
	private ModelFormat format;
	@Column(name = "BACKGROUND_COLOR")
	private String backgroundColor;
	@Column(name = "AMBIENT_LIGHT_INTENSITY")
	private Double ambientLightIntensity;
	@Column(name = "DIRECTIONAL_LIGHT_INTENSITY")
	private Double directionalLightIntensity;
	@Column(name = "HEMISPHERE_LIGHT_INTENSITY")
	private Double hemisphereLightIntensity;
	@Column(name = "HEMISPHERE_LIGHT_GROUND_COLOR")
	private String hemisphereLightGroundColor;
}
