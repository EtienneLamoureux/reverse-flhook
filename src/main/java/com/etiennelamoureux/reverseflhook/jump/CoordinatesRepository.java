package com.etiennelamoureux.reverseflhook.jump;

import com.etiennelamoureux.reverseflhook.exceptions.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class CoordinatesRepository {
  private static final String POS = "pos = ";
  private static final String BASE = "base = ";

  @Value(value = "${data.ini.rootPath}")
  private String rootPath;
  @Value(value = "${data.ini.relativePaths.systems}")
  private String systemsRelativePath;

  public Coordinates findOneBySystemAndBase(String system, String base) {
    try (Stream<String> lines = Files.lines(Paths.get(buildPath(system)))) {
      String lastPosLine = lines.takeWhile(line -> !line.equalsIgnoreCase(BASE + base))
          .filter(line -> line.startsWith(POS)).reduce((first, second) -> second).get();

      return new Coordinates(Arrays.asList(lastPosLine.substring(POS.length()).split(", ")).stream()
          .map(n -> Float.parseFloat(n)).collect(Collectors.toList()));
    } catch (IOException e) {
      throw new EntityNotFoundException(String.format(Locale.ROOT,
          "Could not find coordinates for system '%s', base '%s'", system, base));
    }
  }

  private String buildPath(String system) {
    StringJoiner stringJoiner = new StringJoiner(File.separator);
    stringJoiner.add(rootPath);
    stringJoiner.add(systemsRelativePath);
    stringJoiner.add(system.toUpperCase());
    stringJoiner.add(system.toUpperCase() + ".ini");

    return stringJoiner.toString();
  }

}
