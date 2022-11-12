package com.emse.spring.faircorp.hello;

import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService {
    @Override
    public void greetAll() {
        System.out.println("Hello, Elodie! , Hello, Charles!");
    }
}
