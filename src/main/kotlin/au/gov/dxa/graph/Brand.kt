package au.gov.dxa.graph

import java.io.*
import java.net.*
import java.awt.*
import java.awt.event.*
import java.awt.image.*
import javax.imageio.*
import com.twelvemonkeys.image.*


class Brand{


    fun inline(lines:List<String>, setHeight:Int, square:Boolean):BufferedImage{
            val linesStart = (BRANDING_X * 1.95).toInt()
			val longestLine = getLongestWidth(lines)
			val height = Math.max (crestH, 80 * (lines.size + howManyNewLines(lines) + 1) + linesStart)
			val width = longestLine + 2 * BRANDING_X + crestW

			val canvas = createImage(width, height)
			val g = createGraphics(canvas)

			g.drawImage(crest, QUARTER_BRANDING_X, QUARTER_BRANDING_X, null)
			drawLines(g, lines, width, linesStart, longestLine, false)			

            return sizeImage(canvas, setHeight, square) 
    }

    fun stacked(lines:List<String>, setHeight:Int, square:Boolean):BufferedImage{
			val longestLine = getLongestWidth(lines)
			val height = crestH + 80 * (lines.size + howManyNewLines(lines) + 1)
			val width = longestLine + 2 * BRANDING_X

			val canvas = createImage(width, height)
			val g = createGraphics(canvas)

			g.drawImage(crest, (width - crest.getWidth()) / 2, 0, null)
			drawLines(g, lines, width, crest.getHeight(), longestLine, true)			

            return sizeImage(canvas, setHeight, square) 
    }

    fun sizeImage(canvas:BufferedImage, setHeight:Int, square:Boolean):BufferedImage {

            val width = canvas.getWidth()
            val height = canvas.getHeight()

	        if(!square){	
                    val heightScale = height / setHeight.toFloat()
                    val widthScale = width / heightScale
                    val resampler = ResampleOp(widthScale.toInt(), setHeight, ResampleOp.FILTER_MITCHELL);
                    val resized = resampler.filter(canvas, null);
                    return resized 
            }

            // forced square
            val box = createImage(width, width)
			val gBox = createGraphics(box)
            gBox.setColor(Color.WHITE)
			gBox.fillRect(0, 0, width, width)
			gBox.drawImage(canvas, 0, (width - height) / 2, null)

            val resampler = ResampleOp(setHeight, setHeight, ResampleOp.FILTER_MITCHELL);
            val resized = resampler.filter(box, null);

            return resized

    }


    fun howManyNewLines(lines:List<String>):Int{
			var newLineCount = 0
			for(line in lines) if(line.contains("\n")) newLineCount ++
            return newLineCount
    }


	fun createImage(width:Int, height:Int):BufferedImage =
		 BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

	fun createGraphics(canvas:BufferedImage):Graphics2D{
		val g = canvas.createGraphics()
		g.setColor(Color.WHITE)
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight())
		g.setFont(fontBold)
		g.setColor(Color.BLACK)
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON)
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
		return g		
	}
	


	fun drawLines(g:Graphics2D, initialLines:List<String>, width:Int, yOffset:Int, longestLine:Int, center:Boolean){
			val lines = listOf("Australian Government") + initialLines
			var offset = 40 + yOffset
			for(line in lines){
				val actualLineWidth = if(line == lines.last()) 0 else longestLine
				val lineOffset = drawAndUnderline(g, line, offset, width, actualLineWidth, center, longestLine)
				offset += lineOffset
			}
	}

	fun getLongestWidth(initialLines:List<String>):Int{

		val g = createGraphics(createImage(1,1))
		
        val lines = listOf("Australian Government") + initialLines
		var longest = 0
		for(line in lines){
			for(part in line.split("\n")){
				val width = g.getFontMetrics().stringWidth(part)
				longest = Math.max(longest, width)
			}
		}
		return longest
	}

	fun drawAndUnderline(g:Graphics2D, text:String, y:Int, canvasWidth:Int, lineWidth:Int, center:Boolean, longestLine:Int):Int{

			if(text.contains("\n")){
				val parts = text.split("\n")
				var offset = 0

				for(part in parts){
				    val actualLineWidth = if(part!= parts.last()) 0 else lineWidth
                    drawAndUnderline(g, part, y + offset, canvasWidth, actualLineWidth, center, longestLine)
                    val addedOffset = if(part != parts.last()) 60 else 80
                    offset += addedOffset
				}
                return offset
			}

			val stringWidth = g.getFontMetrics().stringWidth(text)
            val leftTextOffset = if(center) ((canvasWidth - stringWidth) / 2) else crestW + QUARTER_BRANDING_X
            val leftLineOffset = if(center) ((canvasWidth - lineWidth) / 2) else crestW + QUARTER_BRANDING_X

            if(text.startsWith("|")){
                g.setFont(font)
            
                g.drawString(text.replace("|",""), leftTextOffset, y - 20)
                g.fillRect( leftLineOffset, y, lineWidth, 2)
                val leftLineOffsetErase = if(center) ((canvasWidth - longestLine) / 2) else crestW + QUARTER_BRANDING_X
                g.setColor(Color.WHITE)
                g.fillRect( leftLineOffsetErase, y - 60, longestLine, 2)   
                g.setColor(Color.BLACK)
                g.setFont(fontBold)
                
                return 60
            }

			g.drawString(text.replace("|",""), leftTextOffset, y)
            g.fillRect( leftLineOffset, y + 20, lineWidth, 2)
			return 80
	}


    companion object{
        @JvmStatic val fontBold = loadTimesFontBold()
        @JvmStatic val font = loadTimesFont()
        @JvmStatic val crest= loadCrest()
        @JvmStatic val crestW = crest.getWidth()
		@JvmStatic val crestH = crest.getHeight()
        @JvmStatic val BRANDING_X = 60
        @JvmStatic val QUARTER_BRANDING_X = (BRANDING_X / 4).toInt()

        @JvmStatic
        fun loadTimesFontBold():Font{
            val cls = Brand::class.java
            val fontStream = cls.getResourceAsStream("/LiberationSerif-Bold.ttf")!!
            val liberationSerif = Font.createFont(Font.TRUETYPE_FONT, fontStream)
            val font = liberationSerif.deriveFont(54f)
            return font
        }

        @JvmStatic
        fun loadTimesFont():Font{
            val cls = Brand::class.java
            val fontStream = cls.getResourceAsStream("/LiberationSerif-Regular.ttf")!!
            val liberationSerif = Font.createFont(Font.TRUETYPE_FONT, fontStream)
            val font = liberationSerif.deriveFont(54f)
            return font
        }


        @JvmStatic
        fun loadCrest():BufferedImage{
            val cls = Brand::class.java
            return ImageIO.read(cls.getResourceAsStream("/crest.png"))!! 
        }
    }

}
