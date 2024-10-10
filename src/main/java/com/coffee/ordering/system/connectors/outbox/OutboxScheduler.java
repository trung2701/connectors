package com.coffee.ordering.system.connectors.outbox;

public interface OutboxScheduler {
    void process();
}
