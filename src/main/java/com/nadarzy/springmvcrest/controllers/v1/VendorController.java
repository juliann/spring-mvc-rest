package com.nadarzy.springmvcrest.controllers.v1;

import com.nadarzy.springmvcrest.api.v1.model.VendorDTO;
import com.nadarzy.springmvcrest.api.v1.model.VendorListDTO;
import com.nadarzy.springmvcrest.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(
    value = "includes all available Vendor API Calls",
    tags = {"Vendor Controller"})
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
  public static final String BASE_URL = "/api/v1/vendors";

  private final VendorService vendorService;

  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @ApiOperation(value = "This will GET a list of all Vendors")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public VendorListDTO getVendorList() {
    return new VendorListDTO(vendorService.getAllVendors());
  }

  @ApiOperation(
      value = "This will get a specific Vendor ",
      notes = "use the id ID to select which Vendor to GET")
  @GetMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO getVendorById(@PathVariable Long id) {
    return vendorService.getVendorById(id);
  }

  @ApiOperation(
      value = "This will create a new Vendor",
      notes = "POST a JSON of the Vendor you want to create")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
    return vendorService.createNewVendor(vendorDTO);
  }

  @ApiOperation(
      value = "This will update a Vendor",
      notes = "PUT a JSON of the Vendor you want to update + his ID")
  @PutMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.saveVendorByDTO(id, vendorDTO);
  }

  @ApiOperation(
      value = "This will update a Vendor",
      notes = "PATCH a JSON of the Vendor attributes you want to change + his ID")
  @PatchMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.saveVendorByDTO(id, vendorDTO);
  }

  @ApiOperation(
      value = "This will delete a Vendor",
      notes = "DELETE the ID of the Vendor you want to delete")
  @DeleteMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public void deleteVendor(@PathVariable Long id) {
    vendorService.deleteVendorById(id);
  }
}
