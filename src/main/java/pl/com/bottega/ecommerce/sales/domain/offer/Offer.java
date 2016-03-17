package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.ArrayList;
import java.util.List;

public class Offer {
private List<OfferItem> offerItems = new ArrayList<OfferItem>();
	
	public Offer(List<OfferItem> items) {
		this.setOfferItems(items);
	}

	public List<OfferItem> getOfferItems() {
		return offerItems;
	}

	public void setOfferItems(List<OfferItem> offerItems) {
		this.offerItems = offerItems;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availabeItems == null) ? 0 : availabeItems.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (availabeItems == null) {
			if (other.availabeItems != null)
				return false;
		} else if (!availabeItems.equals(other.availabeItems))
			return false;
		return true;
	}

	/**
	 * 
	 * @param seenOffer
	 * @param delta acceptable difference in percent
	 * @return
	 */
	public boolean sameAs(Offer seenOffer, double delta) {
		if (! (availableItems.size() == seenOffer.availableItems.size()))
			return false;
		
		for (OfferItem item : availableItems) {
			OfferItem sameItem = seenOffer.findItem(item.getProductId());
			if (sameItem == null)
				return false;
			if (!sameItem.sameAs(item, delta))
				return false;
		}
		
		return true;
	}

	private OfferItem findItem(String productId) {
		for (OfferItem item : availableItems){
			if (item.getProductId().equals(productId))
				return item;
		}
		return null;
	}	

}
