package com.sm.keepmarket.util

import com.sm.keepmarket.R
import com.sm.keepmarket.domain.FeaturedCard
import com.sm.keepmarket.domain.Highlight
import com.sm.keepmarket.domain.MarketItem
import com.sm.keepmarket.domain.NotificationItem
import com.sm.keepmarket.domain.PantryItem
import com.sm.keepmarket.domain.Route
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

object Mock {



    fun getFeatureCard(): List<FeaturedCard>{
        return listOf(
            FeaturedCard(
                id = "123",
                type = "Pantry",
                title = "My Pantry 1",
                route = getRoute("Pantry"),
                date = LocalDateTime.now()
            ),
            FeaturedCard(
                id = "321",
                type = "Marktet",
                title = "My Market 1",
                route = getRoute("Marktet"),
                date = LocalDateTime.now()
            ),
            FeaturedCard(
                id = "321",
                type = "Marktet",
                title = "My Market 2",
                route = getRoute("Marktet"),
                date = LocalDateTime.now()
            ),
            FeaturedCard(
                id = "321",
                type = "Marktet",
                title = "My Market 3",
                route = getRoute("Marktet"),
                date = LocalDateTime.now()
            )
        )
    }

    fun getRoute(name: String): Route{

        val route = Route()
        route.addRoute("TEST/${name}")

        return route
    }

    fun getHighlight(): List<Highlight> {
        return listOf(
            Highlight(
                id = "1",
                title = "Sliced Bread",
                subTitle = "2 days ago",
                icon = R.drawable.arrowiconupicon,
                infoText = "R$0,30"
            ),
            Highlight(
                id = "2",
                title = "Milk",
                subTitle = "1 day ago",
                icon = R.drawable.arrowdowngreenicon,
                infoText = "R$4,50"
            ),
            Highlight(
                id = "3",
                title = "Rice",
                subTitle = "Today",
                icon = R.drawable.arrowiconupicon,
                infoText = "R$23,00"
            ),
            Highlight(
                id = "4",
                title = "Beans",
                subTitle = "Yesterday",
                icon = R.drawable.arrowdowngreenicon,
                infoText = "R$8,90"
            ),
            Highlight(
                id = "5",
                title = "Eggs (12u)",
                subTitle = "3 days ago",
                icon = R.drawable.arrowiconupicon,
                infoText = "R$12,00"
            ),
            Highlight(
                id = "6",
                title = "Tomatoes",
                subTitle = "Today",
                icon = R.drawable.arrowdowngreenicon,
                infoText = "R$6,70"
            ),
            Highlight(
                id = "7",
                title = "Pasta",
                subTitle = "5 days ago",
                icon = R.drawable.arrowiconupicon,
                infoText = "R$5,20"
            ),
            Highlight(
                id = "8",
                title = "Cheese",
                subTitle = "2 days ago",
                icon = R.drawable.arrowdowngreenicon,
                infoText = "R$18,50"
            ),
            Highlight(
                id = "9",
                title = "Chicken",
                subTitle = "Today",
                icon = R.drawable.arrowiconupicon,
                infoText = "R$22,90"
            ),
            Highlight(
                id = "10",
                title = "Coffee",
                subTitle = "Yesterday",
                icon = R.drawable.arrowdowngreenicon,
                infoText = "R$15,00"
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

}