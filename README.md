# BasicEvents
[![](https://jitpack.io/v/KarlTheBee/basicevents.svg)](https://jitpack.io/#KarlTheBee/basicevents)

Simple event handler written in Java 8

## Usage
### Creating the Handler
    EventHandler handler = new EventHandler();
    [...]
    handler.register(new Listener());
    [...]
    handler.handle(new TestEvent(42));
### Listener
    class Listener implements EventListener {
        @Event
        public void onTest(TestEvent event) {
            System.out.println("Event has been called!");
            TEST_RESULT = event.value;
        }
    }
### Event class
    class TestEvent implements BasicEvent {
        private int value;

        public TestEvent(int value) {
            this.value = value;
        }
    }


### Example
See [SimpleTest](https://github.com/KarlTheBee/basicevents/blob/master/src/test/java/de/karlthebee/basicevents/SimpleTest.java)

## Import
For maven/gradle releases see [Jitpack](https://jitpack.io/#KarlTheBee/basicevents/master)