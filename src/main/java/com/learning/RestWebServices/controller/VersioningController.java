package com.learning.RestWebServices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.RestWebServices.versioning.Name;
import com.learning.RestWebServices.versioning.PersonV1;
import com.learning.RestWebServices.versioning.PersonV2;

@RestController
@RequestMapping("version")
public class VersioningController {

	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Yazhini Sumitra");
	}

	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Yazhini", "Sumitra"));
	}

	@GetMapping(value = "person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Yazhini Sumitra");
	}

	@GetMapping(value = "person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Yazhini", "Sumitra"));
	}

	@GetMapping(value = "person/header", headers = "API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Yazhini Sumitra");
	}

	@GetMapping(value = "person/header", headers = "API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Yazhini", "Sumitra"));
	}
	
	@GetMapping(value = "person/produces", produces = "application/my.company.app.v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Yazhini Sumitra");
	}

	@GetMapping(value = "person/produces", produces = "application/my.company.app.v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Yazhini", "Sumitra"));
	}
}
