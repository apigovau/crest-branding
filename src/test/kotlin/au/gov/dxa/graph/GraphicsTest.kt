package au.gov.dxa.graph

import org.junit.Assert
import org.junit.Test


import java.io.*
import java.net.*
import java.awt.*
import java.awt.event.*
import java.awt.image.*
import javax.imageio.*


class GraphicsTest{


    @Test
    fun can_create_stacked(){

        val brand = Brand()
        val image = brand.stacked(listOf("ATO","DTA"))
        
	    val outputfile = File("saved.png")
	    ImageIO.write(image, "png", outputfile)
    }
}
