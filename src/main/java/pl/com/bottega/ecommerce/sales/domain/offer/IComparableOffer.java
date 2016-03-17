package pl.com.bottega.ecommerce.sales.domain.offer;

public interface IComparableOffer<T> {
	public boolean sameAs(T comparableObject,Double delta);
}
