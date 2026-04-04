package com.sm.keepmarket.domain

class Route(){
    private var route: String = ""


    fun addRoute(route: String){
        this.route = route
    }

    fun getRoute() = route
}