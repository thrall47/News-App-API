package com.example.newsproject
import com.google.gson.annotations.SerializedName

class News {

    val articles: ArrayList<Article> = arrayListOf()

}

class Article{


    var title: String = ""
    val url: String = ""
    val urlToImage:String = ""


}