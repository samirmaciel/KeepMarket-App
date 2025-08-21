package com.sm.keepmarket.util

import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.FeaturedCard
import com.sm.keepmarket.domain.model.Highlight
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.domain.model.Notification
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.domain.model.PantryItem
import com.sm.keepmarket.domain.model.SearchItem
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

object Mock {



    fun getFeatureCard(): List<FeaturedCard>{
        return listOf(
            FeaturedCard(
                name = "My Pantry 1",
                featuredType = FeaturedType.PANTRY,
                lastUpdate = LocalDateTime.now()
            ),
            FeaturedCard(
                name = "My Market 1",
                featuredType = FeaturedType.MARKET,
                lastUpdate = LocalDateTime.now()
            ),
            FeaturedCard(
                name = "My Market 2",
                featuredType = FeaturedType.MARKET,
                lastUpdate = LocalDateTime.now()
            ),
            FeaturedCard(
                name = "My Market 3",
                featuredType = FeaturedType.MARKET,
                lastUpdate = LocalDateTime.now()
            )
        )
    }

    fun getHighlightList(): List<Highlight> {
        return listOf(
            Highlight(
                id = "1",
                title = "Sliced Bread",
                subTitle = "2 days ago",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$0,30"
            ),
            Highlight(
                id = "2",
                title = "Milk",
                subTitle = "1 day ago",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$4,50"
            ),
            Highlight(
                id = "3",
                title = "Rice",
                subTitle = "Today",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$23,00"
            ),
            Highlight(
                id = "4",
                title = "Beans",
                subTitle = "Yesterday",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$8,90"
            ),
            Highlight(
                id = "5",
                title = "Eggs (12u)",
                subTitle = "3 days ago",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.PRICE_INCREASE,
                description = "R$12,00"
            ),
            Highlight(
                id = "6",
                title = "Tomatoes",
                subTitle = "Today",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.PRICE_DECREASE,
                description = "R$6,70"
            ),
            Highlight(
                id = "7",
                title = "Pasta",
                subTitle = "5 days ago",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.PRICE_INCREASE,
                description = "R$5,20"
            ),
            Highlight(
                id = "8",
                title = "Cheese",
                subTitle = "2 days ago",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.PRICE_DECREASE,
                description = "R$18,50"
            ),
            Highlight(
                id = "9",
                title = "Chicken",
                subTitle = "Today",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.PRICE_INCREASE,
                description = "R$22,90"
            ),
            Highlight(
                id = "10",
                title = "Coffee",
                subTitle = "Yesterday",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.PRICE_DECREASE,
                description = "R$15,00"
            )
        )
    }

    fun getMarketItemList(): List<MarketItem>{
        val list = mutableListOf<MarketItem>()

        val marketItem1 = MarketItem(
            id = "123",
            name = "Milk"
        )

        val marketItem2 = MarketItem(
            id = "123",
            name = "Chicken"
        )

        val marketItem3 = MarketItem(
            id = "123",
            name = "Apple"
        )

        marketItem1.price = BigDecimal(1.25)
        marketItem1.amount = 5

        list.add(marketItem1)
        list.add(marketItem2)
        list.add(marketItem3)

        return list
    }

    fun getPantryItemList(): List<PantryItem>{
        val list = mutableListOf<PantryItem>()

        val item1 = PantryItem("123", "Milk", LocalDate.of(2025, 10, 10))
        val item2 = PantryItem("123", "Chicken", LocalDate.of(2025, 7, 18))

        item1.amount = 1
        item2.amount = 4

        list.add(item1)
        list.add(item2)

        return list
    }

    fun getNotificationItemList(): List<Notification>{
        return listOf(
            Notification(
                "123",
                "Sliced Bread",
                "2 days ago to expiration"
            ),
            Notification(
                "123",
                "Bread",
                "4 days ago to expiration"
            )
        )
    }

    fun getSearchItemList(): List<SearchItem>{
        return listOf(
            SearchItem(
                "123",
                "Price increase",
                getHighlightList().take(2)
            ),
            SearchItem(
                "123",
                "Price decrease",
                getHighlightList().take(4)
            )

        )
    }

    fun getMarketList() : List<Market>{
        return listOf(
            Market(
                "123",
                "Mercado da semana",
                LocalDateTime.now(),
                getMarketItemList()
            ),
            Market(
                "124",
                "Padaria",
                LocalDateTime.now().plusDays(2),
                getMarketItemList()
            ),
            Market(
                "125",
                "Mercado do mês",
                LocalDateTime.now().plusDays(4),
                getMarketItemList()
            ),
        )
    }

    fun getPantryList(): List<Pantry> {
        return listOf(
            Pantry(
                "123",
                "Minha dispensa",
                FeaturedType.PANTRY,
                LocalDateTime.now().plusMinutes(1),
                getPantryItemList(),

            ),
        )
    }

}