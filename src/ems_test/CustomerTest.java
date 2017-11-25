
package ems_test;

import ems.Customer;
import ems.Order;
import ems.Position;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    private Customer cus1, cus2, cus3;

    class StubPosition extends Position {
        private int x;
        private int y;

        public StubPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    class OrderStub extends Order {
        private int id;
        private String itemName;
        private ArrayList<Position> path = new ArrayList<Position>();
        private Customer sender;
        private Customer receiver;
        private int locationPtr = 0;
        private boolean received;

        public OrderStub(int id, String itemName, Customer sender, Customer receiver, ArrayList<Position> path) {
            super(id, itemName, sender, receiver, path);

            this.received = false;
        }

    }

    @Before
    public void setUp() throws Exception {
        StubPosition pos = new StubPosition(4, 2);
        cus1 = new Customer(12, "Alex", "alex123", 1, pos);
        cus2 = new Customer(44, "Ben", "ben123", pos);
        cus3 = new Customer(27, "Cara", "cara123");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetOrderLocation() {
        Customer mock = EasyMock.createMock(Customer.class);
        StubPosition
                expectedPos = new StubPosition(6, 7);
        EasyMock.expect(mock.getOrderLocation(1)).andReturn(expectedPos);
        EasyMock.replay(mock);
        Position result = mock.getOrderLocation(1);
        assertEquals(expectedPos, result);
        EasyMock.verify(mock);
    }

    @Test
    public void testConfirmReception() {
        Customer mockSender = EasyMock.createMock(Customer.class);
        Customer mockReceiver = EasyMock.createMock(Customer.class);
        OrderStub os = new OrderStub(1, "item1", mockSender, mockReceiver, null);
        mockSender.confirmReception(os);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.replay(mockSender);
        EasyMock.verify(mockSender);
    }

    @Test
    public void testAskToCreateOrder() {
        cus1.askToCreateOrder("item3", cus3);
        assertEquals(1, cus1.getSentOrderID(0));
    }

    @Test
    public void testGetPosition() {
        assertEquals(4, cus1.getPosition().getX());
        assertEquals(2, cus1.getPosition().getY());
    }

    @Test
    public void testGetId() {
        assertEquals(cus1.getId(), 12);
    }

}
