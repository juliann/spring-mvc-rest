package springmvcrest.services;

import org.springframework.stereotype.Service;
import springmvcrest.api.v1.mapper.VendorMapper;
import springmvcrest.api.v1.model.VendorDTO;
import springmvcrest.controllers.v1.VendorController;
import springmvcrest.exceptions.ResourceNotFoundException;
import springmvcrest.model.Vendor;
import springmvcrest.repositiories.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

  private final VendorRepository vendorRepository;
  private final VendorMapper vendorMapper = VendorMapper.INSTANCE;

  public VendorServiceImpl(VendorRepository vendorRepository) {
    this.vendorRepository = vendorRepository;
  }

  @Override
  public List<VendorDTO> getAllVendors() {
    return vendorRepository.findAll().stream()
        .map(
            vendor -> {
              VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
              vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
              return vendorDTO;
            })
        .collect(Collectors.toList());
  }

  @Override
  public VendorDTO getVendorById(Long id) {
    return vendorRepository
        .findById(id)
        .map(vendorMapper::vendorToVendorDTO)
        .map(
            vendorDTO -> {
              vendorDTO.setVendorUrl(VendorController.BASE_URL + "/" + id);
              return vendorDTO;
            })
        .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public VendorDTO createNewVendor(VendorDTO vendorDTO) {
    return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
  }

  @Override
  public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

    Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendorDTO);
    vendorToSave.setId(id);

    return saveAndReturnDTO(vendorToSave);
  }

  @Override
  public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
    return vendorRepository
        .findById(id)
        .map(
            vendor -> {
              // todo if more properties, add more if statements

              if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
              }

              return saveAndReturnDTO(vendor);
            })
        .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public void deleteVendorById(Long id) {
    vendorRepository.deleteById(id);
  }

  private String getVendorUrl(Long id) {
    return VendorController.BASE_URL + "/" + id;
  }

  private VendorDTO saveAndReturnDTO(Vendor vendor) {
    Vendor savedVendor = vendorRepository.save(vendor);

    VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);

    returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

    return returnDto;
  }
}
