package de.karlthebee.basicevents;

import de.karlthebee.basicevents.basic.BasicEvent;
import de.karlthebee.basicevents.basic.EventListener;
import org.junit.Assert;
import org.junit.Test;


public class SimpleTest {

    public static int TEST_RESULT = 0;

    @Test
    public void test() {
        EventHandler handler = new EventHandler();
        handler.register(new Listener());

        handler.handle(new TestEvent(42));

        Assert.assertEquals(42, TEST_RESULT);
    }


    class Listener implements EventListener {
        @Event
        public void onTest(TestEvent event) {
            System.out.println("Event has been called!");
            TEST_RESULT = event.value;
        }
    }

    class TestEvent implements BasicEvent {
        private int value;

        public TestEvent(int value) {
            this.value = value;
        }
    }
}
