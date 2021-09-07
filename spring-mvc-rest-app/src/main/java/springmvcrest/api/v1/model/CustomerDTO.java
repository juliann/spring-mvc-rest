package springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {

  private Long id;

  @ApiModelProperty(value = "This is the first name", required = true)
  private String firstName;

  private String lastName;

  @JsonProperty("customer_url")
  private String customerUrl;

  @Override
  public String toString() {
    return "CustomerDTO{"
        + "id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", customerUrl='"
        + customerUrl
        + '\''
        + '}';
  }
}
