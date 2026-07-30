package org.example.common.statemachine.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author duoyian
 * @date 2026/7/10
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.UNPAID) // 初始状态
                .states(EnumSet.allOf(OrderState.class)) // 所有状态
                .end(OrderState.DONE); // 结束状态
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal() // 外部状态流转
                .source(OrderState.UNPAID).target(OrderState.WAIT_SHIP).event(OrderEvent.PAY)
                .action(payAction()) // 绑定 Action
                .and()
                .withExternal()
                .source(OrderState.WAIT_SHIP).target(OrderState.DONE).event(OrderEvent.SHIP);
    }

    // 定义一个 Action Bean
    @Bean
    public Action<OrderState, OrderEvent> payAction() {
        return ctx -> {
            System.out.println("订单已支付，准备发货...发送短信通知等");
            // 可以从 ctx.getMessageHeader() 获取业务参数
        };
    }
}
