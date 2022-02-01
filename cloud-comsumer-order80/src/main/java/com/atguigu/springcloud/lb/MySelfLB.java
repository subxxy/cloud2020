package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MySelfLB implements LoadBalancer{

    private AtomicInteger ai = new AtomicInteger(0);

    private final int getAndIncrement() {
        int current;
        int next;
        do {
            current = ai.get();
            next = current > Integer.MAX_VALUE ? 0 : current+1;
        } while (!ai.compareAndSet(current, next));
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instabces) {
        int index = getAndIncrement() % instabces.size();
        return instabces.get(index);
    }
}
