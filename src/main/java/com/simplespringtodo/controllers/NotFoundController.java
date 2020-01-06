package com.simplespringtodo.controllers;

import com.simplespringtodo.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Not found controller.
 */
@RestController
public class NotFoundController {
    /**
     * Handles invalid request mappings.
     */
    @RequestMapping(path = "*")
    public void notFound () {
        throw new CustomException("Sorry, not a valid request path", HttpStatus.NOT_FOUND);
    }
}
