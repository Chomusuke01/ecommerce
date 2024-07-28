package com.ob.ecommerce.service;

import com.ob.ecommerce.repository.Repository;
import com.ob.ecommerce.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CartCleanUpService {

    private final Repository<Cart> repository;
    private static final int MAX_INACTIVE_TIME = 10;

    private final Logger logger = LoggerFactory.getLogger(CartCleanUpService.class);

    public CartCleanUpService(Repository<Cart> repository) {
        this.repository = repository;
    }


    @Scheduled(cron = "0 */10 * * * ?")
    public void deleteInactiveCarts(){

        logger.info("Cleaning inactive carts ... ");

        LocalDateTime lastUpdateTime = LocalDateTime.now().minusMinutes(MAX_INACTIVE_TIME);

        this.repository.getAll()
                .stream()
                .filter(cart -> cart.getLastUpdated().isBefore(lastUpdateTime))
                .forEach(this.repository::delete);
    }
}
