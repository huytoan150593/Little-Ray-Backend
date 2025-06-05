package com.example.ray_little.utils;

import org.springframework.data.domain.Sort;

public class SortUtil {
  public static Sort parseSort(String sortParam) {
    if (sortParam == null || sortParam.isEmpty()) return Sort.unsorted();
    String[] parts = sortParam.split(",");
    if (parts.length == 2) {
      return Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);
    }
    return Sort.by(parts[0]);
  }
}

