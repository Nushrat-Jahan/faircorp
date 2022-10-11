package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.service.AdressSearchService;
import com.emse.spring.faircorp.service.dto.ApiGouvAdressDto;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@CrossOrigin
@RestController // (1)
@RequestMapping("/api/address") // (2)
@Transactional // (3)
public class AddressController {
    private final AdressSearchService address;

    public AddressController(AdressSearchService address) { // (4)
        this.address = address;
    }
    @GetMapping // (5)
    public List<ApiGouvAdressDto> findAll(@RequestParam List<String> keys) {
        return address.findAdress(keys);  // (6)
    }
}
