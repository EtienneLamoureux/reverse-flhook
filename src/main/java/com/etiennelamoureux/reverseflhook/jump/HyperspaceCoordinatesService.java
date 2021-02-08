package com.etiennelamoureux.reverseflhook.jump;

import com.etiennelamoureux.reverseflhook.utils.Constants;
import com.etiennelamoureux.reverseflhook.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HyperspaceCoordinatesService {
  private boolean adminMode = false;

  @Autowired
  private CoordinatesRepository coordinatesRepository;

  public HyperspaceCoordinates survey(String system, String base) {
    return survey(system, coordinatesRepository.findOneBySystemAndBase(system, base));
  }

  public HyperspaceCoordinates survey(String system, float x, float y, float z) {
    return survey(system, new Coordinates(x, y, z));
  }

  HyperspaceCoordinates survey(String system, Coordinates coordinates) {
    float accuracy = Constants.SURVEY_MK3_ACCURACY;

    if (adminMode) {
      accuracy = Constants.SURVEY_ADMIN_ACCURACY;
    } else {
      coordinates.blur();
    }

    return new HyperspaceCoordinates(IdUtil.generate(system), coordinates, accuracy);
  }

  public HyperspaceCoordinates refresh(String string) {
    HyperspaceCoordinates hyperspaceCoordinates = new HyperspaceCoordinates(string);

    return new HyperspaceCoordinates(hyperspaceCoordinates.getSystem(),
        hyperspaceCoordinates.getCoordinates(), hyperspaceCoordinates.getAccuracy());
  }

  public void isAdminMode() {
    adminMode = true;
  }

}
