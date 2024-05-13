package org.decardo.art.model;

import org.decardo.art.ArtInputDTO;
import org.decardo.firebase.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/05/08
 */
@Service
public class ModelMapper {
	@Autowired
	private FirebaseStorageService firebaseStorageService;

	public ModelSettingsDto convert(Model model) {
		return ModelSettingsDto.builder()
				.backgroundColor(model.getBackgroundColor())
				.ambientLight(createLightSettings(model.getAmbientLightIntensity()))
				.directionalLight(createLightSettings(model.getDirectionalLightIntensity()))
				.hemisphereLight(createLightSettings(model.getHemisphereLightIntensity(), model.getHemisphereLightGroundColor()))
				.build();
	}

	public Model convert(ArtInputDTO artInputDTO) {
		return Model.builder()
				.ambientLightIntensity(Double.valueOf(artInputDTO.getAmbientLightIntensity()))
				.directionalLightIntensity(Double.valueOf(artInputDTO.getDirectionalLightIntensity()))
				.hemisphereLightIntensity(Double.valueOf(artInputDTO.getHemisphereLightIntensity()))
				.hemisphereLightGroundColor(artInputDTO.getHemisphereLightGroundColor())
				.backgroundColor(artInputDTO.getBackgroundColor())
				.file(firebaseStorageService.upload(artInputDTO.getModel()))
				.preview(firebaseStorageService.upload(artInputDTO.getPreview()))
				.format(ModelFormat.valueOf(artInputDTO.getFormat()))
				.build();
	}

	private ModelLightSettingsDto createLightSettings(Double intensity) {
		return createLightSettings(intensity, null);
	}

	private ModelLightSettingsDto createLightSettings(Double intensity, String groundColor) {
		return ModelLightSettingsDto.builder()
				.intensity(intensity.toString())
				.groundColor(groundColor)
				.build();
	}
}
