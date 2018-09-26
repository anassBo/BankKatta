/**
 * 
 */
package com.example.demo.monitor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.services.IBankService;

/**
 * @author y.nadir
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitorAopTests {
	
	@Rule
	public OutputCapture outputCapture = new OutputCapture();
	
	@Autowired
    IBankService refFormeJuridiqueService;
	

	
	//@Test
	/*public void findByLibelleTest(){
		refFormeJuridiqueService.findAll();
		String output = this.outputCapture.toString();
		assertThat(output).contains("Completed: execution(List com.example.demo.services.impl.RefFormeJuridiqueServiceImpl.findAll())");
	}*/

}
