package org.kajal.mallick.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JpaConditionTest {
    @InjectMocks
    private JpaCondition jpaCondition;

    @Mock
    private ConditionContext context;

    @Mock
    private Environment environment;

    @Mock
    private AnnotatedTypeMetadata annotatedTypeMetadata;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void matches() {
        when(context.getEnvironment()).thenReturn(environment);
        when(environment.getProperty(anyString())).thenReturn("");
        assertTrue(jpaCondition.matches(context, annotatedTypeMetadata));
    }
}