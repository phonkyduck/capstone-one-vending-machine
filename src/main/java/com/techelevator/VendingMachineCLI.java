package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.Inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};



	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

		//declaring objects to be used
		Inventory inv = new Inventory();
		File file = new File("vendingmachine.csv");
		Map<String, VendingItems> itemsForSale = inv.inventoryLoader();
		BillBox bill;
		BigDecimal totalSales;
		BigDecimal currentBalance = BigDecimal.valueOf(0);



		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inv.displayItems(itemsForSale);

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				while (true) {
					String choicePurchaseMenu = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
						//implement select product functionality

					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){
						//implement finish transaction functionality

					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// do exit
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

//	@Override
//	public double selectItemAndGetPrice(Item item) {
//		if(itemInventory.hasItem(item)){
//			currentItem = item;
//			return currentItem.getPrice();
//		}
//		throw new SoldOutException("Sold Out, Please buy another item");
//	}
//
//	@Override
//	public void insertBills(BillBox bill) {
//		currentBalance = currentBalance + bill.getBillDenomination();
//		cashInventory.add(bill);
//	}
//
//	@Override
//	public List<Coin> refund() {
//		List<Coin> refund = getChange(currentBalance);
//		updateCashInventory(refund);
//		currentBalance = 0;
//		currentItem = null;
//		return refund;
//	}
//
//	private Item collectItem() throws NotSufficientChangeException,
//			NotFullPaidException{
//		if(isFullPaid()){
//			if(hasSufficientChange()){
//				itemInventory.deduct(currentItem);
//				return currentItem;
//			}
//			throw new NotSufficientChangeException("Not Sufficient change in Inventory");
//		}
//		double remainingBalance = currentItem.getPrice() - currentBalance;
//		throw new NotFullPaidException("Price not full paid, remaining : ",
//				remainingBalance);
//	}
//
//	private List<Coin> collectChange() {
//		double changeAmount = currentBalance - currentItem.getPrice();
//		List<Coin> change = getChange(changeAmount);
//		updateCashInventory(change);
//		currentBalance = 0;
//		currentItem = null;
//		return change;
//	}
//
//	private boolean isFullPaid() {
//		if(currentBalance >= currentItem.getPrice()){
//			return true;
//		}
//		return false;
//	}
//
//	private List<Coin> getChange(double amount) throws NotSufficientChangeException{
//		List<Coin> changes = Collections.EMPTY_LIST;
//
//		if(amount > 0){
//			changes = new ArrayList<Coin>();
//			double balance = amount;
//			while(balance > 0){
//				if(balance >= Coin.QUARTER.getDenomination()
//						&& cashInventory.hasItem(Coin.QUARTER)){
//					changes.add(Coin.QUARTER);
//					balance = balance - Coin.QUARTER.getDenomination();
//					continue;
//
//				}else if(balance >= Coin.DIME.getDenomination()
//						&& cashInventory.hasItem(Coin.DIME)) {
//					changes.add(Coin.DIME);
//					balance = balance - Coin.DIME.getDenomination();
//					continue;
//
//				}else if(balance >= Coin.NICKLE.getDenomination()
//						&& cashInventory.hasItem(Coin.NICKLE)) {
//					changes.add(Coin.NICKLE);
//					balance = balance - Coin.NICKLE.getDenomination();
//					continue;
//
//				}else if(balance >= Coin.PENNY.getDenomination()
//						&& cashInventory.hasItem(Coin.PENNY)) {
//					changes.add(Coin.PENNY);
//					balance = balance - Coin.PENNY.getDenomination();
//					continue;
//
//				}else{
//					throw new NotSufficientChangeException("NotSufficientChange, Please try another product");
//				}
//			}
//		}
//
//		return changes;
//	}
//
//	public void printStats(){
//		System.out.println("Total Sales : " + totalSales);
//		System.out.println("Current Item Inventory : " + itemInventory);
//		System.out.println("Current Cash Inventory : " + cashInventory);
//	}
//
//
//	private boolean hasSufficientChange(){
//		return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
//	}
//
//	private boolean hasSufficientChangeForAmount(double amount){
//		boolean hasChange = true;
//		try{
//			getChange(amount);
//		}catch(NotSufficientChangeException nsce){
//			return hasChange = false;
//		}
//
//		return hasChange;
//	}
//
//	private void updateCashInventory(List change) {
//		for(Object c : change){
//			cashInventory.deduct(c);
//		}
//	}
//
//	public double getTotalSales(){
//		return totalSales;
//	}
//
//	@Override
//	public void reset() {
//		cashInventory.clear();
//		itemInventory.clear();
//		totalSales = 0;
//		currentItem = null;
//		currentBalance = 0;
//	}
//}