package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.ArrayList;
import java.util.List;

public class Offer implements IComparableOffer<Offer> {
private List<OfferItem> offerItems = new ArrayList<OfferItem>();
private List<OfferItem> availableItems = new ArrayList<OfferItem>();
	public Offer(List<OfferItem> items) {
		this.setOfferItems(items);
	}

	public List<OfferItem> getOfferItems() {
		return offerItems;
	}

	public void setOfferItems(List<OfferItem> offerItems) {
		this.offerItems = offerItems;
		this.availableItems = getAvailableItems();
	}
	
	private List<OfferItem> getAvailableItems() {
		List<OfferItem> availableItems = new ArrayList<OfferItem>();
		
		for(OfferItem offerItem : offerItems){
			if(offerItem.isAvailable())	{
				availableItems.add(offerItem);
			}			
		}
		return availableItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availableItems == null) ? 0 : availableItems.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		boolean isEqual = false;
		boolean isSameInstance = (this == obj) && (getClass() == obj.getClass());
		
		Offer other = (Offer)obj;
		
		boolean availableItemsExists = !((availableItems == null) && (availableItems != null));
		boolean isSameAvailableItems = availableItems.equals(other.availableItems);

		isEqual = isSameInstance && availableItemsExists && isSameAvailableItems;
		return isEqual;
	}

	private OfferItem findItem(String productId) {
		for (OfferItem item : availableItems){
			if (item.getProduct().getProductId().equals(productId))
				return item;
		}
		return null;
	}

	/**
	 * 
	 * @param seenOffer
	 * @param delta acceptable difference in percent
	 * @return
	 */
	public boolean sameAs(Offer seenOffer, Double delta) {
		if (! (availableItems.size() == seenOffer.availableItems.size()))
			return false;
		
		for (OfferItem item : availableItems) {
			OfferItem sameItem = seenOffer.findItem(item.getProduct().getProductId());
			if (sameItem == null)
				return false;
			if (!sameItem.sameAs(item, delta))
				return false;
		}
		
		return true;
	}	

}
