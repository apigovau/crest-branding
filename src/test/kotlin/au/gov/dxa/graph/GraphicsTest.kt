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

	val BRANDING_X = 60


    @Test
    fun can_get_puml_from_swagger(){
			
			/*val width = 1000
			val height = 1000
			val canvas = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
			val crest= ImageIO.read(File("crest.png"))
			val g = canvas.createGraphics()
			g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON)
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
            val cls = GraphicsTest::class.java
            val fontStream = cls.getResourceAsStream("/LiberationSerif-Bold.ttf")!!
            val liberationSerif = Font.createFont(Font.TRUETYPE_FONT, fontStream)
           	//GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(liberationSerif) 
			*/



			val crest= ImageIO.read(File("crest.png"))
			val crestW = crest.getWidth()
			val crestH = crest.getHeight()

			//val lines = listOf("Australian Financial\nSecurity Authority", "Australian Taxation Office", "Department of Health", "Digital Transformation Agency")
			val lines = listOf("Australian Financial\nSecurity\nAuthority","Australian Taxation Office")

			val font = getFont()
			val dummyG = createGraphics(createImage(1,1))
			dummyG.setFont(font)

			val longestLine = getLongestWidth(dummyG, lines)

			var newLineCount = 0
			for(line in lines) if(line.contains("\n")) newLineCount ++

			val height = crestH + 80 * (lines.size + newLineCount + 1)
			val width = longestLine + 2 * BRANDING_X


			val canvas = createImage(width, height)
			val g = createGraphics(canvas)
			g.setFont(font)
				
			g.setColor(Color.WHITE)
			g.fillRect(0, 0, width, height)

			g.drawImage(crest, (width - crest.getWidth()) / 2, 0, null)
			
			drawLines(g, lines, width, crest.getHeight())			

			
			val box = createImage(width, width)
			val gBox = createGraphics(box)
			gBox.fillRect(0, 0, width, width)
			gBox.drawImage(canvas, 0, (width - height) / 2, null)


			val outputfile = File("saved.png")
			//ImageIO.write(canvas, "png", outputfile)
			ImageIO.write(box, "png", outputfile)
						   
    }

	fun createImage(width:Int, height:Int):BufferedImage =
		 BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

	fun createGraphics(canvas:BufferedImage):Graphics2D{
		val g = canvas.createGraphics()
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON)
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
		return g		
	}
	
	fun getFont():Font{
        val cls = GraphicsTest::class.java
        val fontStream = cls.getResourceAsStream("/LiberationSerif-Bold.ttf")!!
        val liberationSerif = Font.createFont(Font.TRUETYPE_FONT, fontStream)
		val font = liberationSerif.deriveFont(54f)
		return font
	}


	fun drawLines(g:Graphics2D, initialLines:List<String>, width:Int, yOffset:Int){
			val lines = listOf("Australian Government") + initialLines
			val lineWidth = getLongestWidth(g, lines)
			var offset = 40 + yOffset
			for(line in lines){
				val actualLineWidth = if(line == lines.last()) 0 else lineWidth
				val lineOffset = drawAndUnderline(g, line, offset, width, actualLineWidth)
				offset += lineOffset
			}
	}

	fun getLongestWidth(g:Graphics2D, lines:List<String>):Int{
		var longest = 0
		for(line in lines){
			for(part in line.split("\n")){
				val width = g.getFontMetrics().stringWidth(part)
				longest = Math.max(longest, width)
			}
		}
		return longest
	}

	fun drawAndUnderline(g:Graphics2D, text:String, y:Int, canvasWidth:Int, lineWidth:Int):Int{

			if(text.contains("\n")){
				val parts = text.split("\n")
				/*
				val before = parts[0]
				val after = parts[1]
				drawAndUnderline(g, before, y, canvasWidth, 0)
				drawAndUnderline(g, after, y + 60, canvasWidth, lineWidth)
				return 140
				*/

				var offset = 0
				for(part in parts){
				    val actualLineWidth = if(part!= parts.last()) 0 else lineWidth
                    drawAndUnderline(g, part, y + offset, canvasWidth, actualLineWidth)
                    val addedOffset = if(part != parts.last()) 60 else 80
                    offset += addedOffset
				}
                return offset

			}


			g.setColor(Color.BLACK)
			val stringWidth = g.getFontMetrics().stringWidth(text);
			g.drawString(text, (canvasWidth - stringWidth) / 2, y)
			g.fillRect( (canvasWidth - lineWidth) / 2, y + 20, lineWidth, 2)

			return 80
	}




}
