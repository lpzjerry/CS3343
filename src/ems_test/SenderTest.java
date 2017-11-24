package emsTest;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ems.Customer;
import ems.Position;
import ems.Sender;


public class SenderTest {
    Sender sender = null;

    class StubPosition extends Position {
        private int x;
        private int y;

        public StubPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAskToCreateOrder() {
        sender = EasyMock.createMock(Sender.class);
        StubPosition pos = new StubPosition(1, 3);
        Customer cus1 = new Customer(12, "Alex", "alex123", 1, pos);
        sender.askToCreateOrder("item1", cus1);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.replay(sender);
    }

    @Test
    public void testGetOrderLocation() {
        sender = EasyMock.createMock(Sender.class);
        StubPosition expectedPos = new StubPosition(2, 3);
        EasyMock.expect(sender.getOrderLocation(1)).andReturn(expectedPos);
        EasyMock.replay(sender);
    }

}
