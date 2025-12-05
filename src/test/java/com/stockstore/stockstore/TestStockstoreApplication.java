package com.stockstore.stockstore;

import org.springframework.boot.SpringApplication;

public class TestStockstoreApplication {

	public static void main(String[] args) {
		SpringApplication.from(StockstoreApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
