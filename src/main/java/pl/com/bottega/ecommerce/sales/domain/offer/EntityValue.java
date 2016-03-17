package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class EntityValue {
	private BigDecimal value;
	private enum currency{PLN,USD,EUR};
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	};
}
