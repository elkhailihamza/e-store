package org.project.stockservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "cart", url = "http://localhost:8222/p/")
public interface CartClient {
}
