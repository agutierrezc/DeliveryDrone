package co.com.s4n.deliveryDrone;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.s4n.deliveryDrone.exceptions.BusinessException;
import co.com.s4n.deliveryDrone.services.IDeliveryDrone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:DeliveryDrone-beans.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class DeliveryDroneTest {
	
	@Value("${NOT_IMPLEMENTED_COMMAND_MESSAGE}")
	private String notImplementedCommand;
	
	@Value("${OUT_OF_LIMITS_MESSAGE}")
	private String outOfLimitsMessage;
	
	@Value("${NUMBER_OF_PACKAGES}")
	private int numberOfPackage;
	
	@Autowired
	private IDeliveryDrone iDeliveryDrone;
	
	private List<String> routs;
	private List<String> expectedPositions;
	
	@Before
	public void executedBeforeEach(){
		routs = new ArrayList<>();
		expectedPositions = new ArrayList<>();
	}
	
	@Test
	public void sendDelivery() throws BusinessException{
		routs.add("AAAAIAAD");
		expectedPositions.add("(-2, 4) dirección Norte");
		
		List<String> dronePosition = iDeliveryDrone.startDeliveries(routs);
		
		assertEquals(expectedPositions, dronePosition);
	}
	
	@Test
	public void sendThreeDeliveries() throws BusinessException{
		routs.add("AAAAIAAD");
		routs.add("DDAAIIADDAAA");
		routs.add("AAIADAD");
		expectedPositions.add("(-2, 4) dirección Norte");
		expectedPositions.add("(-2, 0) dirección Sur");
		expectedPositions.add("(-1, -3) dirección Occidente");
		
		List<String> dronePosition = iDeliveryDrone.startDeliveries(routs);
		
		assertEquals(expectedPositions, dronePosition);
	}
	
	@Test
	public void sendFourDeliveries() throws BusinessException{
		routs.add("AAAAIAAD");
		routs.add("DDAIAD");
		routs.add("AAIADADAAI");
		routs.add("AADAA");
		expectedPositions.add("(-2, 4) dirección Norte");
		expectedPositions.add("(-1, 3) dirección Sur");
		expectedPositions.add("(-2, 0) dirección Sur");
		expectedPositions.add("(-4, -2) dirección Occidente");
		
		List<String> dronePosition = iDeliveryDrone.startDeliveries(routs);
		
		assertEquals(expectedPositions, dronePosition);
	}
	
	@Test
	public void sendManyDeliveries() throws BusinessException{
		routs.add("AAIAAADADAAI");
		routs.add("AADAA");
		routs.add("AAAAIAAD");
		routs.add("DDAIAD");
		routs.add("IIAAAADA");
		routs.add("DAIADDDAA");
		routs.add("DAAAAAAADDAAAAIAAA");
		expectedPositions.add("(-1, 3) dirección Norte");
		expectedPositions.add("(1, 5) dirección Oriente");
		expectedPositions.add("(5, 7) dirección Oriente");
		expectedPositions.add("(4, 6) dirección Occidente");
		expectedPositions.add("(8, 5) dirección Sur");
		expectedPositions.add("(9, 4) dirección Oriente");
		expectedPositions.add("(6, 1) dirección Occidente");
		
		List<String> dronePosition = iDeliveryDrone.startDeliveries(routs);
		
		assertEquals(expectedPositions, dronePosition);
	}
	
	@Test
	public void sendElevenDeliveries() throws BusinessException{
		routs.add("AAIAAADADAAI");
		routs.add("AADAA");
		routs.add("AAAAIAAD");
		routs.add("DDAIAD");
		routs.add("IIAAAADA");
		routs.add("DAIADDDAA");
		routs.add("DAAAAAAADDAAAAIAAA");
		routs.add("IIAAAD");
		routs.add("AAADAAIAA");
		routs.add("AADAAAADAA");
		routs.add("DDDIIIIAAAA");
		expectedPositions.add("(-1, 3) dirección Norte");
		expectedPositions.add("(1, 5) dirección Oriente");
		expectedPositions.add("(5, 7) dirección Oriente");
		expectedPositions.add("(4, 6) dirección Occidente");
		expectedPositions.add("(8, 5) dirección Sur");
		expectedPositions.add("(9, 4) dirección Oriente");
		expectedPositions.add("(6, 1) dirección Occidente");
		expectedPositions.add("(9, 1) dirección Sur");
		expectedPositions.add("(7, -4) dirección Sur");
		expectedPositions.add("(3, -4) dirección Norte");
		expectedPositions.add("(-4, 0) dirección Occidente");
		
		List<String> dronePosition = iDeliveryDrone.startDeliveries(routs);
		
		assertEquals(expectedPositions, dronePosition);
	}
	
	@Test
	public void sendOutOfLimitsDelivery() throws BusinessException{
		routs.add("AAAAAAAAAAAD");
		expectedPositions.add(outOfLimitsMessage);
		String lastposition = "(0, 9) dirección Norte";
		
		List<String> dronePosition = iDeliveryDrone.startDeliveries(routs);
		
		assertEquals(expectedPositions, dronePosition);
		assertEquals(lastposition, iDeliveryDrone.getDronePosition());
	}
	
	@Test
	public void sendWrongDelivery() throws BusinessException{
		routs.add("AAAAAN");
		expectedPositions.add(notImplementedCommand);
		String lastposition = "(0, 0) dirección Norte";
		
		List<String> dronePosition = iDeliveryDrone.startDeliveries(routs);
		
		assertEquals(expectedPositions, dronePosition);
		assertEquals(lastposition, iDeliveryDrone.getDronePosition());
	}
	
	@Test
	public void sendWrongDeliveries() throws BusinessException{
		routs.add("AAAAAD");
		routs.add("AAAAAI");
		routs.add("AAAN");
		expectedPositions.add("(0, 5) dirección Oriente");
		expectedPositions.add("(5, 5) dirección Norte");
		expectedPositions.add(notImplementedCommand);
		String lastposition = "(5, 5) dirección Norte";
		
		List<String> dronePosition = iDeliveryDrone.startDeliveries(routs);
		
		assertEquals(expectedPositions, dronePosition);
		assertEquals(lastposition, iDeliveryDrone.getDronePosition());
	}
	
	/**
	 * Ejemplo marcado en el enunciado el cual es erroneo ya que el drone no gira correctamente en la segunda entrega
	 * Las coordenas correctas en cada entrega son (1)-2,4 Norte (2)-1,3 Sur (3) 0,0 Occidente
	 * @throws BusinessException
	 */
	@Test
	public void sendExampleDeliveriesEtapa1() throws BusinessException{
		routs.add("AAAAIAAD");
		routs.add("DDAIAD");
		routs.add("AAIADAD");
		expectedPositions.add("(-2, 4) dirección Norte");
		expectedPositions.add("(-1, 3) dirección Sur");
		expectedPositions.add("(0, 0) dirección Occidente");
		
		List<String> dronePosition = iDeliveryDrone.startDeliveries(routs);
		
		assertEquals(expectedPositions, dronePosition);
	}

}
