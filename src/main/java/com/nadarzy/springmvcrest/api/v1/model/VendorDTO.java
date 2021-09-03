package com.nadarzy.springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {


  private String name;

  @JsonProperty("vendor_url")
  private String vendorUrl;
}
