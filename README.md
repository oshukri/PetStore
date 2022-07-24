# Automation testcases for PetStore

## Overview

API automation test cases for Swagger PetStore Sample project.

## Requirements

You will need to have Java and Maven installed.

## Build

Run `mvn clean compile`

### Tests

Run `mvn clean test`

### Report

Run `mvn clean test surefire-report:report`

- Report generated in target/surefire-reports/apitests.PetApiTest/Command line test.html

### Code overview

Project code is divided as follows:

- Test cases in folder apitests.
- datatransferobject folder contains a class covering each object sent or received from the website.
- helpers folder contains a class that aids in the creation of the PetDTOs.
- endpoints folder contains a class that lists the pet service url strings.