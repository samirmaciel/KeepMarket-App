package com.sm.keepmarket.domain.model

class Route(){
    private var route: String = ""


    fun addRoute(route: String){
        //Verify route
        this.route = route
    }

    fun getRoute() = route
}
