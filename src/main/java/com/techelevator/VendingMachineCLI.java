package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.Inventory;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private static final String FEED_MENU_INSERT_ONE_DOLLAR = "$1.00";
	private static final String FEED_MENU_INSERT_TWO_DOLLARS = "$2.00";
	private static final String FEED_MENU_INSERT_FIVE_DOLLARS = "$5.00";
	private static final String FEED_MENU_INSERT_TEN_DOLLARS = "$10.00";
	private static final String FEED_MENU_RETURN = "Return to Purchase Menu";
	private static final String[] FEED_MENU_OPTIONS = {FEED_MENU_INSERT_ONE_DOLLAR, FEED_MENU_INSERT_TWO_DOLLARS, FEED_MENU_INSERT_FIVE_DOLLARS, FEED_MENU_INSERT_TEN_DOLLARS, FEED_MENU_RETURN};

	private static final BigDecimal QUARTER = BigDecimal.valueOf(.25);
	private static final BigDecimal DIME = BigDecimal.valueOf(.10);
	private static final BigDecimal NICKEL = BigDecimal.valueOf(.05);


	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {

		//declaring objects to be used
		Inventory inv = new Inventory();
		File file = new File("vendingmachine.csv");
		Map<String, VendingItems> itemsForSale = inv.inventoryLoader();
		BigDecimal totalSales;
		BigDecimal customerBalance = BigDecimal.valueOf(0);
		Scanner vendingChoice = new Scanner(System.in);
		File auditLog = new File("SalesLog.txt");
		PrintWriter purchaseAuditor = new PrintWriter(new FileOutputStream(auditLog, true));
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
//		Menu purchaseMenu = new Menu(customerBalance, auditLog); //input is user inputting money, output is writing the audit log


		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				inv.displayItems(itemsForSale);

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				while (true) {
					System.out.println("Current Money Provided: $" + String.format("%.2f", customerBalance));
					String choicePurchaseMenu = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (choicePurchaseMenu.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						while (true) {
							BigDecimal startingBalance = customerBalance;
							System.out.println("Current Money Provided: $" + String.format("%.2f", customerBalance));
							String choiceFeedMoneyMenu = (String) menu.getChoiceFromOptions(FEED_MENU_OPTIONS);

							if (choiceFeedMoneyMenu.equals(FEED_MENU_INSERT_ONE_DOLLAR)) {
								customerBalance = customerBalance.add(BigDecimal.valueOf(1));
								purchaseAuditor.println(dateTimeFormat.format(now) + "FEED MONEY: " + startingBalance + " " + customerBalance);

							} else if (choiceFeedMoneyMenu.equals(FEED_MENU_INSERT_TWO_DOLLARS)) {
								customerBalance = customerBalance.add(BigDecimal.valueOf(2));
								purchaseAuditor.println(dateTimeFormat.format(now) + "FEED MONEY: " + startingBalance + " " + customerBalance);

							} else if (choiceFeedMoneyMenu.equals(FEED_MENU_INSERT_FIVE_DOLLARS)) {
								customerBalance = customerBalance.add(BigDecimal.valueOf(5));
								purchaseAuditor.println(dateTimeFormat.format(now) + "FEED MONEY: " + startingBalance + " " + customerBalance);

							} else if (choiceFeedMoneyMenu.equals(FEED_MENU_INSERT_TEN_DOLLARS)) {
								customerBalance = customerBalance.add(BigDecimal.valueOf(10));
								purchaseAuditor.println(dateTimeFormat.format(now) + "FEED MONEY: " + startingBalance + " " + customerBalance);

							} else if (choiceFeedMoneyMenu.equals(FEED_MENU_RETURN)) {
								purchaseAuditor.println(dateTimeFormat.format(now) + "FEED MONEY: " + startingBalance + " " + customerBalance);
								break;
							}
						}
					} else if (choicePurchaseMenu.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						BigDecimal startingBalance = customerBalance;
						inv.displayItems(itemsForSale);
						System.out.println("\n" + "Please select a product by selecting its slot ID (A1, B2, etc)");
						String itemSelected = vendingChoice.nextLine();

						if (itemsForSale.get(itemSelected) == null) {
							System.out.println("That product does not exist.");

						} else if (itemsForSale.get(itemSelected).getItemQuantity() <= 0) {
							System.out.println("Product sold out, please select another item.");

						} else if (itemsForSale.get(itemSelected).getPrice().doubleValue() > customerBalance.doubleValue()) {
							System.out.println("Insufficient Funds. Please insert more money.");

						} else if (itemsForSale.containsKey(itemSelected)) {
							customerBalance = customerBalance.subtract(itemsForSale.get(itemSelected).getPrice());
							itemsForSale.get(itemSelected).setItemQuantity(itemsForSale.get(itemSelected).getItemQuantity() - 1);
							System.out.println("Thank you for purchasing " + itemsForSale.get(itemSelected) + " for $" + String.format("%.2f", itemsForSale.get(itemSelected).getPrice()) + ", your new balance is $" + String.format("%.2f", customerBalance));
							itemsForSale.get(itemSelected).vendingMessage();
							purchaseAuditor.println(dateTimeFormat.format(now) + itemsForSale.get(itemSelected).getName() + itemsForSale.get(itemSelected).getItemSlot() + startingBalance + " " + customerBalance);

							//reduce item quantity by 1
							//print the item name, cost, and the money remaining, and print the item message
							//return to purchase menu


						} else if (choicePurchaseMenu.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
//							implement finish transaction functionality
							int quarterCounter = 0;
							int dimeCounter = 0;
							int nickelCounter = 0;
							BigDecimal customerChange = customerBalance;

							if (customerBalance.doubleValue() > 0) {
								for (int i = 0; i < customerBalance.doubleValue(); i++) {
									if (customerBalance.doubleValue() >= QUARTER.doubleValue()) {
										customerBalance = customerBalance.subtract(QUARTER);
										quarterCounter++;
									} else if (customerBalance.doubleValue() >= DIME.doubleValue()) {
										customerBalance = customerBalance.subtract(DIME);
										dimeCounter++;
									} else if (customerBalance.doubleValue() >= NICKEL.doubleValue()) {
										customerBalance = customerBalance.subtract(NICKEL);
										nickelCounter++;
									}
								}
								System.out.println("Thank you for shopping with us! \nYour change is $" + customerChange + ". Please collect your " + quarterCounter + " quarters, " + dimeCounter + " dimes, and " + nickelCounter + " nickels. \n Have a great day!" );
							} else if(customerBalance.doubleValue() == 0){
								System.out.println("Your balance is $0. Thank you for shopping with us!");
							}
						}
					}
				}
				} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
					// do exit
				}
			}
		}

		public static void main (String[]args) throws FileNotFoundException {
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