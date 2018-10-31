package au.gov.dxa.graph

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.net.URL
import java.security.MessageDigest

import javax.imageio.*
import java.io.*

@RestController
class Controller {

    @CrossOrigin
    @RequestMapping("/stacked.png")
    fun stacked_png(@RequestParam agency:List<String>, @RequestParam height:Int = 800, @RequestParam(required = false, defaultValue="false") square:Boolean = false): ByteArray{
        val brand = Brand()
        val image = brand.stacked(agency, height, square)

        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "png", baos)
        return baos.toByteArray()
    }

    @CrossOrigin
    @RequestMapping("/inline.png")
    fun inline_png(@RequestParam agency:List<String>, @RequestParam height:Int = 800, @RequestParam(required = false, defaultValue="false") square:Boolean = false): ByteArray{
        val brand = Brand()
        val image = brand.inline(agency, height, square)

        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "png", baos)
        return baos.toByteArray()
    }

    private fun hashString(type: String, input: String) =
            MessageDigest
                    .getInstance(type)
                    .digest(input.toByteArray())
                    .map { String.format("%02X", it) }
                    .joinToString(separator = "")

}
