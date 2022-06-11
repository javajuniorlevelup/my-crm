package ru.levelup.mycrm.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestContainerTest {

    @Container
    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12-alpine");

    @Test
    public void test() {
        
    }


}
