package com.etiennelamoureux.reverseflhook.jump;

import com.etiennelamoureux.reverseflhook.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HyperspaceCoordinatesService {
  @Autowired
  private CoordinatesRepository coordinatesRepository;

  public HyperspaceCoordinates survey(String system, String base) {
    return new HyperspaceCoordinates(IdUtil.generate(system),
        coordinatesRepository.findOneBySystemAndBase(system, base).blur());
  }

  public HyperspaceCoordinates survey(String system, float x, float y, float z) {
    return new HyperspaceCoordinates(IdUtil.generate(system), new Coordinates(x, y, z).blur());
  }

  public HyperspaceCoordinates refresh(String string) {
    HyperspaceCoordinates hyperspaceCoordinates = new HyperspaceCoordinates(string);

    return new HyperspaceCoordinates(hyperspaceCoordinates.getSystem(),
        hyperspaceCoordinates.getCoordinates(), hyperspaceCoordinates.getAccuracy());
  }

}
