package com.nadarzy.springmvcrest.api.v1.mapper;

import com.nadarzy.springmvcrest.api.v1.model.VendorDTO;
import com.nadarzy.springmvcrest.model.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

  VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

  VendorDTO vendorToVendorDTO(Vendor vendor);

  Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
