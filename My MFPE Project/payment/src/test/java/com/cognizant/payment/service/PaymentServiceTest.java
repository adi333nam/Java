package com.cognizant.payment.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

	PaymentService paymentService = new PaymentService();

	@Test
	void testIsValidProcessingCharge_PositiveScenario() {

		assertTrue(paymentService.isValidProcessingCharge(10000));
	}

	@Test
	void testIsValidProcessingCharge_ZeroValueScenario() {

		assertTrue(paymentService.isValidProcessingCharge(0));
	}

	@Test
	void testIsValidProcessingCharge_NegativeScenario() {
		assertFalse(paymentService.isValidProcessingCharge(-10000));
	}

	@Test
	void testIsValidCardNumber_BasicPositiveScenario() {
		assertTrue(paymentService.isValidCardNumber(379354508162306L));
	}

	@Test
	void testIsValidCardNumber_BasicNegativeScenario() {
		assertFalse(paymentService.isValidCardNumber(37935450));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_10digit() {
		assertFalse(paymentService.isValidCardNumber(3793545081L));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_11digit() {
		assertFalse(paymentService.isValidCardNumber(37935450816L));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_12digit() {
		assertFalse(paymentService.isValidCardNumber(379354508162L));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_13digit() {
		assertFalse(paymentService.isValidCardNumber(3793545081623L));
	}

	@Test
	void testIsValidCardNumber_PositiveScenario_13digit() {
		assertTrue(paymentService.isValidCardNumber(3793545081629L));
	}

	@Test
	void testIsValidCardNumber_PositiveScenario_14digit() {
		assertTrue(paymentService.isValidCardNumber(37935450816230L));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_14digit() {
		assertFalse(paymentService.isValidCardNumber(37935450816231L));
	}

	@Test
	void testIsValidCardNumber_PositiveScenario_15digit() {
		assertTrue(paymentService.isValidCardNumber(379354508162306L));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_15digit() {
		assertFalse(paymentService.isValidCardNumber(379354508162307L));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_16digit() {
		assertFalse(paymentService.isValidCardNumber(3793545081623061L));
	}

	@Test
	void testIsValidCardNumber_PositiveScenario_16digit() {
		assertTrue(paymentService.isValidCardNumber(37935450816230618L));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_17digit() {
		assertFalse(paymentService.isValidCardNumber(37935450816230612L));
	}

	@Test
	void testIsValidCardNumber_PositiveScenario_17digit() {
		assertTrue(paymentService.isValidCardNumber(37935450816230618L));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_18digit() {
		assertFalse(paymentService.isValidCardNumber(379354508162306123L));
	}

	@Test
	void testIsValidCardNumber_PositiveScenario_18digit() {
		assertTrue(paymentService.isValidCardNumber(379354508162306122L));
	}

	@Test
	void testIsValidCardNumber_NegativeScenario_19digit() {
		assertFalse(paymentService.isValidCardNumber(3793545081623061234L));
	}

	@Test
	void testIsValidCardNumber_PositiveScenario_19digit() {
		assertTrue(paymentService.isValidCardNumber(3793545081623061230L));
	}

}
