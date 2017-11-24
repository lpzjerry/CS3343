package emsTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ems.Customer;
import ems.Order;
import ems.Position;
import ems.Receiver;
import ems.Sender;
 
public class ReceiverTest {
	Receiver receiver=null;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetOrderLocation(){
		receiver=EasyMock.createMock(Receiver.class);  
		
		class StubPosition extends Position{
			 private int x;
			 private int y;

			 public StubPosition(int x, int y) {
			        this.x = x;
			        this.y = y;
			 }
		 }
		StubPosition expectedPos=new StubPosition(4,2); 
		EasyMock.expect(receiver.getOrderLocation(1)).andReturn(expectedPos);  
	    EasyMock.replay(receiver);  
	}
	
	@Test
	public void testConfirmReception(){
		receiver=EasyMock.createMock(Receiver.class);  
		
		class OrderStub extends Order{
			private int id;
		    private String itemName;
		    //private Position location;
		    private ArrayList<Position> path= new ArrayList<Position>();
		    private Customer sender;
		    private Customer receiver;
		    private int locationPtr=0;
		    private boolean received;
		    
			public OrderStub(int id, String itemName, Customer sender, Customer receiver, ArrayList<Position> path) {
		        this.id = id;
		        this.itemName = itemName;
		        this.sender = sender;
		        this.receiver = receiver;
		        this.path = path; 
		        locationPtr = 0;
		        this.received = false;
		    }
			
		}  
		
		OrderStub os = new OrderStub(1, "item1", null, null, null);
	    receiver.confirmReception(os);;
	    EasyMock.expectLastCall().anyTimes();
	    EasyMock.replay(receiver);  
	    EasyMock.verify(receiver);
	}

}

