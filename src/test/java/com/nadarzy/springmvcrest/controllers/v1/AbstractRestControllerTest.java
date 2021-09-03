package com.nadarzy.springmvcrest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestControllerTest {

  public static String asJsonString(Object obj) {
    try {
      String s = new ObjectMapper().writeValueAsString(obj);
      System.out.println(s);
      return s;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
