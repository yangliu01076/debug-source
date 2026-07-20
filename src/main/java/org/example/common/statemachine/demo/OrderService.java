package org.example.common.statemachine.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 * @author duoyian
 * @date 2026/7/10
 */
@Service
public class OrderService {
    @Autowired
    private StateMachine<OrderState, OrderEvent> stateMachine;

    public void processOrder() {
        stateMachine.start();
        // 发送支付事件
        stateMachine.sendEvent(OrderEvent.PAY);
        // 发送发货事件
        stateMachine.sendEvent(OrderEvent.SHIP);
    }
}
