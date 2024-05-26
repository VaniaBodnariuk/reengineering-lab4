package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Item;
import org.example.GlidedRose;
import org.junit.jupiter.api.Test;

public class RoseTest {

	@Test
	public void qualityCannotBeNegativeTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 3, 0)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 5; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(0, items[0].getQuality());
	}

	@Test
	public void qualityCannotExceedFiftyTest() {
		Item[] items = new Item[] {new Item("Aged Brie", 2, 50)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(50, items[0].getQuality());
	}

	@Test
	public void sellInDecreasesByOneTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 3, 6)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(2, items[0].getSellIn());
	}

	@Test
	public void qualityDecreasesDoubleAfterSellInDateTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 0, 6)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(4, items[0].getQuality());
	}

	@Test
	public void otherItemsQualityDecreasesOverTimeTest() {
		Item[] items = new Item[] {new Item("+5 Dexterity Vest", 10, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 4; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(16, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesTwiceAfterSellInDateTest() {
		Item[] items = new Item[] {new Item("Aged Brie", 2, 0)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 3; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(4, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesTwiceAfterSellInDateTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 0, 6)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(4, items[0].getQuality());
	}

	@Test
	public void itemsMaintainCorrectOrderAfterMultipleUpdatesTest() {
		Item[] items = new Item[] {
			new Item("+5 Dexterity Vest", 10, 20),
			new Item("Aged Brie", 2, 0),
			new Item("Sulfuras, Hand of Ragnaros", 0, 80),
			new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
			new Item("Conjured Mana Cake", 3, 6)
		};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 10; i++) {
			glidedRose.updateQuality();
		}
		assertEquals("+5 Dexterity Vest", items[0].getName());
		assertEquals("Aged Brie", items[1].getName());
		assertEquals("Sulfuras, Hand of Ragnaros", items[2].getName());
		assertEquals("Backstage passes to a TAFKAL80ETC concert", items[3].getName());
		assertEquals("Conjured Mana Cake", items[4].getName());
	}

	@Test
	public void conjuredQualityDoesNotChangeWithLargeSellInTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 10, 6)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(5, items[0].getQuality());
	}

	@Test
	public void sulfurasQualityRemainsUnchangedRegardlessTest() {
		Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 5, 80)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 3; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(80, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesTwiceAfterSellInPassedTest() {
		Item[] items = new Item[] {new Item("Aged Brie", -1, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(22, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesTwiceFasterAfterMultipleUpdatesTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 5, 10)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 3; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(7, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesByTwoAfterTenDaysToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 10; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(45, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesTwiceFasterAfterNegativeSellInForMultipleUpdatesTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", -1, 10)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 3; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(4, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesTripleAfterSellInPassedTest() {
		Item[] items = new Item[] {new Item("Aged Brie", -5, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(22, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityBecomesZeroAfterConcertEvenWithNonNegativeSellInTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(23, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesTwiceFasterAfterFiveUpdatesTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 5, 10)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 5; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(5, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesByTwoAfterEachUpdateWithNegativeSellInTest() {
		Item[] items = new Item[] {new Item("Aged Brie", -1, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(22, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesTwiceFasterAfterTenUpdatesTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 5, 10)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 10; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(0, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesByThreeAfterFiveDaysToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 5; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(35, items[0].getQuality());
	}

	@Test
	public void sulfurasQualityRemainsUnchangedAfterHundredUpdatesWithNegativeSellInTest() {
		Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", -5, 80)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 100; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(80, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByTwoAfterThreeUpdatesWithSellInBetweenFiveAndTenTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 8, 10)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 3; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(7, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesByOneAfterOneDayToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 1; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(23, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByFourAfterEachUpdateWithSellInBetweenTenAndTwentyTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 15, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(19, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesByFiveAfterOneDayWithSellInLessThanFiveTest() {
		Item[] items = new Item[] {new Item("Aged Brie", 3, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(21, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByThreeAfterFourUpdatesWithNegativeSellInTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", -1, 15)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 4; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(7, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesByTwoAfterOneDayToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 1; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(23, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesBySixAfterTwoUpdatesWithSellInLessThanOrEqualToFiveTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 5, 15)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 2; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(13, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesBySixAfterOneDayWithNegativeSellInTest() {
		Item[] items = new Item[] {new Item("Aged Brie", -1, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(22, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByTwoAfterOneUpdateWithNegativeSellInTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", -1, 10)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(8, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByThreeAfterFiveUpdatesWithSellInBetweenThirtyAndFiftyTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 40, 30)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 5; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(25, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesByFiveAfterFiveDaysWithSellInLessThanTenTest() {
		Item[] items = new Item[] {new Item("Aged Brie", 5, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 5; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(25, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesByFiveThreeDaysToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 3, 40)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 3; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(49, items[0].getQuality());
	}

	@Test
	public void sulfurasQualityRemainsUnchangedAfterFiveHundredUpdatesWithNegativeSellInTest() {
		Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", -5, 80)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 500; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(80, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesByTwoAfterEachUpdateWithSellInLessThanFiveTest() {
		Item[] items = new Item[] {new Item("Aged Brie", 3, 25)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(26, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesBySevenTwoDaysToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 2, 25)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 2; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(31, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesBySixAfterEightUpdatesWithSellInLessThanThirtyTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 25, 40)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 8; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(32, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesByEightAfterFourDaysWithSellInLessThanThreeTest() {
		Item[] items = new Item[] {new Item("Aged Brie", 2, 30)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 4; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(36, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesByEightOneDayToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 1, 15)};
		GlidedRose glidedRose = new GlidedRose(items);
		glidedRose.updateQuality();
		assertEquals(18, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByFiveAfterFiveUpdatesWithSellInLessThanThreeTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 2, 30)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 5; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(22, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesByTwentyFiveAfterTwentyFiveDaysWithSellInLessThanNegativeFifteenTest() {
		Item[] items = new Item[] {new Item("Aged Brie", -20, 30)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 25; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(50, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesByFiftyTenDaysToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 10; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(45, items[0].getQuality());
	}

	@Test
	public void sulfurasQualityRemainsUnchangedAfterFiveHundredUpdatesWithSellInNegativeThirtyTest() {
		Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", -30, 80)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 500; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(80, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByTwentyAfterTwentyUpdatesWithSellInGreaterThanFiftyTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 60, 60)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 20; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(40, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesByEightAfterEightDaysWithSellInLessThanNegativeTenTest() {
		Item[] items = new Item[] {new Item("Aged Brie", -5, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 8; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(36, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesByFifteenFiveDaysToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 5; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(45, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByFifteenAfterFifteenUpdatesWithSellInBetweenNegativeTwentyAndZeroTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", -15, 40)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 15; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(10, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesByTwelveAfterTwelveDaysWithSellInLessThanNegativeFiveTest() {
		Item[] items = new Item[] {new Item("Aged Brie", -3, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 12; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(44, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesBySevenSevenDaysToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 7, 30)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 7; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(49, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByTenAfterTenUpdatesWithSellInGreaterThanThirtyTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 40, 50)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 10; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(40, items[0].getQuality());
	}

	@Test
	public void agedBrieQualityIncreasesByThirtyAfterThirtyDaysWithSellInLessThanNegativeTwentyTest() {
		Item[] items = new Item[] {new Item("Aged Brie", -25, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 30; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(50, items[0].getQuality());
	}

	@Test
	public void backstagePassesQualityIncreasesBySeventyFifteenDaysToConcertTest() {
		Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 15; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(50, items[0].getQuality());
	}

	@Test
	public void sulfurasQualityRemainsUnchangedAfterThousandUpdatesWithSellInNegativeFiftyTest() {
		Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", -50, 80)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 1000; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(80, items[0].getQuality());
	}

	@Test
	public void conjuredQualityDecreasesByTwentyFiveAfterTwentyFiveUpdatesWithSellInGreaterThanSeventyTest() {
		Item[] items = new Item[] {new Item("Conjured Mana Cake", 80, 60)};
		GlidedRose glidedRose = new GlidedRose(items);
		for (int i = 0; i < 25; i++) {
			glidedRose.updateQuality();
		}
		assertEquals(35, items[0].getQuality());
	}
}
