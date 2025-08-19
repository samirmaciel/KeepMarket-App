package com.sm.keepmarket.util

import com.sm.keepmarket.R
import com.sm.keepmarket.domain.FeaturedCard
import com.sm.keepmarket.domain.HighlightItem
import com.sm.keepmarket.domain.MarketItem
import com.sm.keepmarket.domain.NotificationItem
import com.sm.keepmarket.domain.PantryItem
import com.sm.keepmarket.domain.Route
import com.sm.keepmarket.domain.SearchItem
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

object Mock {



    fun getFeatureCard(): List<FeaturedCard>{
        return listOf(
            FeaturedCard(
                id = "123",
                name = "My Pantry 1",
                featureType = FeatureType.PANTRY,
                lastUpdate = LocalDateTime.now()
            ),
            FeaturedCard(
                id = "321",
                name = "My Market 1",
                featureType = FeatureType.MARKET,
                lastUpdate = LocalDateTime.now()
            ),
            FeaturedCard(
                id = "321",
                name = "My Market 2",
                featureType = FeatureType.MARKET,
                lastUpdate = LocalDateTime.now()
            ),
            FeaturedCard(
                id = "321",
                name = "My Market 3",
                featureType = FeatureType.MARKET,
                lastUpdate = LocalDateTime.now()
            )
        )
    }

    fun getRoute(name: String): Route{

        val route = Route()
        route.addRoute("TEST/${name}")

        return route
    }

    fun getHighlight(): List<HighlightItem> {
        return listOf(
            HighlightItem(
                id = "1",
                title = "Sliced Bread",
                subTitle = "2 days ago",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$0,30"
            ),
            HighlightItem(
                id = "2",
                title = "Milk",
                subTitle = "1 day ago",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$4,50"
            ),
            HighlightItem(
                id = "3",
                title = "Rice",
                subTitle = "Today",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$23,00"
            ),
            HighlightItem(
                id = "4",
                title = "Beans",
                subTitle = "Yesterday",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$8,90"
            ),
            HighlightItem(
                id = "5",
                title = "Eggs (12u)",
                subTitle = "3 days ago",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.PRICE_INCREASE,
                description = "R$12,00"
            ),
            HighlightItem(
                id = "6",
                title = "Tomatoes",
                subTitle = "Today",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.PRICE_DECREASE,
                description = "R$6,70"
            ),
            HighlightItem(
                id = "7",
                title = "Pasta",
                subTitle = "5 days ago",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.PRICE_INCREASE,
                description = "R$5,20"
            ),
            HighlightItem(
                id = "8",
                title = "Cheese",
                subTitle = "2 days ago",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.PRICE_DECREASE,
                description = "R$18,50"
            ),
            HighlightItem(
                id = "9",
                title = "Chicken",
                subTitle = "Today",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.PRICE_INCREASE,
                description = "R$22,90"
            ),
            HighlightItem(
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

    fun getNotificationItemList(): List<NotificationItem>{
        return listOf(
            NotificationItem(
                "123",
                "Sliced Bread",
                "2 days ago to expiration"
            ),
            NotificationItem(
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
                getHighlight().take(2)
            ),
            SearchItem(
                "123",
                "Price decrease",
                getHighlight().take(4)
            )

        )
    }

}