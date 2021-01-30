package com.udacity.pricing;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import com.udacity.pricing.entity.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PricingServiceApplicationTests {

	@Autowired
	private PriceRepository priceRepository;

	private void insertPrices() {
		priceRepository.deleteAll();
		priceRepository.save(new Price("USD", new BigDecimal(49999.99),1L));
		priceRepository.save(new Price("USD", new BigDecimal(25499.99),2L));
		priceRepository.save(new Price("USD", new BigDecimal(18499.99),3L));
		priceRepository.save(new Price("CAD", new BigDecimal(34999.99),4L));
		priceRepository.save(new Price("CAD", new BigDecimal(31999.99),5L));
		priceRepository.save(new Price("GBP", new BigDecimal(17399.99),6L));
	}

	@Test
	public void testInsertPrice() {
		Price price = priceRepository.save(new Price("USD", new BigDecimal(49.999),1L));
		Optional<Price> testPrice = priceRepository.findById(1L);
		assertTrue(testPrice.isPresent());
		assertEquals(price.getVehicleId(), testPrice.get().getVehicleId());
		assertEquals(price.getPrice(),new BigDecimal(49.999));
		assertEquals(price.getCurrency(), "USD");
	}

	@Test
	public void testGetPriceById() {
		insertPrices();
		Optional<Price> price = priceRepository.findById(2L);
		assertTrue(price.isPresent());
		assertEquals(price.get().getVehicleId(), Long.valueOf(2L));
		assertEquals(price.get().getPrice(), BigDecimal.valueOf(25499.99));
		assertEquals(price.get().getCurrency(), "USD");

		price = priceRepository.findById(5L);
		assertTrue(price.isPresent());
		assertEquals(price.get().getVehicleId(), Long.valueOf(5L));
		assertEquals(price.get().getPrice(), BigDecimal.valueOf(31999.99));
		assertEquals(price.get().getCurrency(), "CAD");
	}

	@Test
	public void retrieveAllPrices(){
		insertPrices();
		assertEquals(priceRepository.count(), 6);

	}

}
