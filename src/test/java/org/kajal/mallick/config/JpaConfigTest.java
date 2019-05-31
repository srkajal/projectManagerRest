package org.kajal.mallick.config;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JpaConfigTest {
    @InjectMocks
    private JpaConfig jpaConfig;
    @Mock
    private Environment env;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void entityManagerFactory() {
        when(env.getProperty(anyString())).thenReturn("value");
        assertNotNull(jpaConfig.entityManagerFactory());
    }

    @Test
    public void transactionManager() {
        EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);
        assertNotNull(jpaConfig.transactionManager(entityManagerFactory));
    }

}