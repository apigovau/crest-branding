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
        val image = brand.stacked(listOf("Australian Taxation\nOffice","Digital Transformation Agency"), 800, false)
	    val outputfile = File("stacked.png")
	    ImageIO.write(image, "png", outputfile)
    }
    @Test
    fun can_create_inline(){
        val brand = Brand()
        val image = brand.inline(listOf("Australian Taxation\nOffice","Digital Transformation Agency"), 800, false)
	    val outputfile = File("inline.png")
	    ImageIO.write(image, "png", outputfile)
    }
}
