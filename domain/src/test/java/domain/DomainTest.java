package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import domain.utils.GetterAndSetterTester;

import java.io.Serializable;
import java.util.Set;

class DomainTest {

    Set<Class<? extends Serializable>> allClasses;
    GetterAndSetterTester tester;

    @BeforeEach
    public void setUp() {
        tester = new GetterAndSetterTester();
        Reflections reflections = new Reflections("io.github.wesleyosantos91.core.domain");
        allClasses = reflections.getSubTypesOf(Serializable.class);
    }

    @Test
    @DisplayName("[domain] - Coverage all class the domain package")
    void coverageClassTheDomainPackage() {
        for (Class<? extends Object> clazz : allClasses)
            tester.testClass(clazz);
    }
    
}