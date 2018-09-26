/**
 * 
 */
package com.example.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author y.nadir
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RefFormeJuridiqueRepositoryTests {
    
    //@Autowired
    //RefFormeJuridiqueRepository refFormeJuridiqueRepository;
    
    Runnable r;
    
    @Before
    public void beforeEachTest() throws Exception {
        System.out.println("before");
        Function<Integer, Boolean> isPositif = (valeur) -> {
            return valeur >= 0;
        };
        System.out.println(isPositif.apply(5));
        
        Callable<String> monCallable = () -> "Mon traitement";
        
        Consumer<String> consumer = (toBeprint) -> System.out.println(toBeprint);
        Consumer<String> consumer2 = consumer.andThen((toBeprint) -> System.out.println(toBeprint));
        
        consumer2.accept("younes");
        
        System.out.println(monCallable.call());
    }
    
    /*@Test
    public void displayAllTest() {
        refFormeJuridiqueRepository.findAll().forEach(refFormeJuridique -> {
            System.out.println(refFormeJuridique.getId());
            System.out.println(refFormeJuridique.getLibelle());
        });
    }
    
    @Test
    public void filterByIdEqualsOneTest() {
        assertThat(refFormeJuridiqueRepository.findAll().stream()
                .filter(refFormeJuridique -> refFormeJuridique.getId().equals(Long.valueOf(1))).collect(Collectors.toList()).size())
                        .isEqualTo(1);
        ;
    }
    
    @Test
    public void findByIdTest() {
        assertThat(refFormeJuridiqueRepository.findById(1l).getLibelle().equals("La Societe en nom collectif SNC"));
    }
    
    @Test
    public void findByLibelleTest() {
        assertThat(refFormeJuridiqueRepository.findByLibelle("La Societe en nom collectif SNC").getId().equals(Long.valueOf(1)));
    }
    */
}
