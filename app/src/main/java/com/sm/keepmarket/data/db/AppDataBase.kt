package com.sm.keepmarket.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sm.keepmarket.data.db.dao.HighlightDao
import com.sm.keepmarket.data.db.dao.MarketDao
import com.sm.keepmarket.data.db.dao.MarketItemDao
import com.sm.keepmarket.data.db.dao.MarketItemStateDao
import com.sm.keepmarket.data.db.dao.NotificationDao
import com.sm.keepmarket.data.db.dao.PantryDao
import com.sm.keepmarket.data.db.dao.PantryItemDao
import com.sm.keepmarket.data.model.HighlightEntity
import com.sm.keepmarket.data.model.MarketEntity
import com.sm.keepmarket.data.model.MarketItemEntity
import com.sm.keepmarket.data.model.MarketItemStateEntity
import com.sm.keepmarket.data.model.NotificationEntity
import com.sm.keepmarket.data.model.PantryEntity
import com.sm.keepmarket.data.model.PantryItemEntity

@Database(
    entities = [
        MarketEntity::class, PantryEntity::class, HighlightEntity::class, NotificationEntity::class, MarketItemEntity::class,
        PantryItemEntity::class, MarketItemStateEntity::class
    ], version = 1
)
@TypeConverters(RoomConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun MarketDao(): MarketDao
    abstract fun PantryDao(): PantryDao
    abstract fun MarketItemDao(): MarketItemDao
    abstract fun PantryItemDao(): PantryItemDao
    abstract fun NotificationDao(): NotificationDao
    abstract fun HighlightDao(): HighlightDao
    abstract fun MarketItemStateDao(): MarketItemStateDao
}