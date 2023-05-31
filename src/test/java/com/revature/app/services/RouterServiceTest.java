package com.revature.app.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.app.utils.Session;

public class RouterServiceTest {

    @Mock
    private Session session;

    @Mock
    private RouterService routerService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNavigate() {
        String path = "/cart";
        Scanner scanner = new Scanner(System.in);
        routerService.navigate(path, scanner);
        Mockito.verify(routerService, times(1)).navigate(path, scanner);
    }
}
