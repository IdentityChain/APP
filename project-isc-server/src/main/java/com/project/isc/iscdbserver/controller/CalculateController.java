package com.project.isc.iscdbserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * 
 * @author ISC
 *
 */
@Api(value = "算力管理", tags = "算力管理")
@RestController
@RequestMapping("/calculate")
@CrossOrigin
public class CalculateController {

}
