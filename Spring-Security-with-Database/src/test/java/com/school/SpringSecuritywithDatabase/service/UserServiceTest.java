package com.school.SpringSecuritywithDatabase.service;

import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = "external-api-active = false")
class UserServiceTest extends AbstractUserServiceTest{
}
