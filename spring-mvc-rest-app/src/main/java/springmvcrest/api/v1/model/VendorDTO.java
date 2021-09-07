package springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

  @ApiModelProperty(value = "This is the Vendor Name", required = true)
  private String name;

  @JsonProperty("vendor_url")
  private String vendorUrl;
}
