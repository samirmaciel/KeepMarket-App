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
import java.util.UUID

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
                id = UUID.randomUUID().toString(),
                title = "Sliced Bread",
                subTitle = "2 days ago",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$0,30"
            ),
            Highlight(
                id = UUID.randomUUID().toString(),
                title = "Milk",
                subTitle = "1 day ago",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$4,50"
            ),
            Highlight(
                id = UUID.randomUUID().toString(),
                title = "Rice",
                subTitle = "Today",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$23,00"
            ),
            Highlight(
                id = UUID.randomUUID().toString(),
                title = "Beans",
                subTitle = "Yesterday",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.NEARING_EXPIRATION,
                description = "R$8,90"
            ),
            Highlight(
                id = UUID.randomUUID().toString(),
                title = "Eggs (12u)",
                subTitle = "3 days ago",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.PRICE_INCREASE,
                description = "R$12,00"
            ),
            Highlight(
                id = UUID.randomUUID().toString(),
                title = "Tomatoes",
                subTitle = "Today",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.PRICE_DECREASE,
                description = "R$6,70"
            ),
            Highlight(
                id = UUID.randomUUID().toString(),
                title = "Pasta",
                subTitle = "5 days ago",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.PRICE_INCREASE,
                description = "R$5,20"
            ),
            Highlight(
                id = UUID.randomUUID().toString(),
                title = "Cheese",
                subTitle = "2 days ago",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.PRICE_DECREASE,
                description = "R$18,50"
            ),
            Highlight(
                id = UUID.randomUUID().toString(),
                title = "Chicken",
                subTitle = "Today",
                icon = R.drawable.arrowiconupicon,
                type = HighlightType.PRICE_INCREASE,
                description = "R$22,90"
            ),
            Highlight(
                id = UUID.randomUUID().toString(),
                title = "Coffee",
                subTitle = "Yesterday",
                icon = R.drawable.arrowdowngreenicon,
                type = HighlightType.PRICE_DECREASE,
                description = "R$15,00"
            )
        )
    }

    fun getMarketItemList(ownerId: String): List<MarketItem>{
        val list = mutableListOf<MarketItem>()

        val marketItem1 = MarketItem(
            id = UUID.randomUUID().toString(),
            marketId = ownerId,
            name = "Milk",
            createdDate = LocalDateTime.now()
        )

        val marketItem2 = MarketItem(
            id = UUID.randomUUID().toString(),
            marketId = ownerId,
            name = "Chicken",
            createdDate = LocalDateTime.now()
        )

        val marketItem3 = MarketItem(
            id = UUID.randomUUID().toString(),
            marketId = ownerId,
            name = "Apple",
            createdDate = LocalDateTime.now()
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

        val item1 = PantryItem(UUID.randomUUID().toString(),  "123","Milk", amount = 1,LocalDate.of(2025, 10, 10))
        val item2 = PantryItem(UUID.randomUUID().toString(), "123","Chicken", 4, LocalDate.now().plusDays(2))

        list.add(item1)
        list.add(item2)

        return list
    }

    fun getNotificationList(): List<Notification>{
        return listOf(
            Notification(
                UUID.randomUUID().toString(),
                "Sliced Bread",
                "2 days ago to expiration"
            ),
            Notification(
                UUID.randomUUID().toString(),
                "Bread",
                "4 days ago to expiration"
            )
        )
    }

    fun getSearchItemList(): List<SearchItem>{
        return listOf(
            SearchItem(
                UUID.randomUUID().toString(),
                "Price increase",
                getHighlightList().take(2)
            ),
            SearchItem(
                UUID.randomUUID().toString(),
                "Price decrease",
                getHighlightList().take(4)
            )

        )
    }

    fun getMarketList() : List<Market>{
        return listOf(
            Market(
                UUID.randomUUID().toString(),
                "Mercado da semana",
                LocalDateTime.now(),
                LocalDateTime.now(),
                getMarketItemList(UUID.randomUUID().toString())
            ),
            Market(
                UUID.randomUUID().toString(),
                "Padaria",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2),
                getMarketItemList(UUID.randomUUID().toString())
            ),
            Market(
                UUID.randomUUID().toString(),
                "Mercado do mês",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(4),
                getMarketItemList(UUID.randomUUID().toString())
            ),
        )
    }

    fun getPantryList(): List<Pantry> {
        return listOf(
            Pantry(
                UUID.randomUUID().toString(),
                "Minha dispensa",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1),
                getPantryItemList(),

            ),
        )
    }

}