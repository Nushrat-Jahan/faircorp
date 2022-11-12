package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.service.AddressSearchService;
import com.emse.spring.faircorp.service.dto.ApiGouvAddressDto;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@CrossOrigin
@RestController // (1)
@RequestMapping("/api/address") // (2)
@Transactional // (3)
public class AddressController {
    private final AddressSearchService address;

    public AddressController(AddressSearchService address) { // (4)
        this.address = address;
    }
    @GetMapping // (5)
    public List<ApiGouvAddressDto> findAll(@RequestParam List<String> keys) {
        return address.findAddress(keys);  // (6)
    }
}
